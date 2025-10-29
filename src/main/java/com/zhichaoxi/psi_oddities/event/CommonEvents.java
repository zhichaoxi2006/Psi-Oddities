package com.zhichaoxi.psi_oddities.event;

import com.zhichaoxi.psi_oddities.PsiOddities;
import com.zhichaoxi.psi_oddities.attachment.ModAttachments;
import com.zhichaoxi.psi_oddities.attachment.PsiWingData;
import com.zhichaoxi.psi_oddities.item.ItemPsimetalShield;
import com.zhichaoxi.psi_oddities.item.base.ModItems;
import com.zhichaoxi.psi_oddities.util.PsiArmorUtil;
import com.zhichaoxi.psi_oddities.util.PsiWingUtil;
import com.zhichaoxi.psi_tweaks.core.ModAttributes;
import com.zhichaoxi.psi_tweaks.util.PsiUtil;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.ItemAttributeModifierEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingShieldBlockEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import vazkii.psi.api.PsiAPI;
import vazkii.psi.api.cad.EnumCADComponent;
import vazkii.psi.api.cad.ICAD;
import vazkii.psi.api.cad.ISocketable;
import vazkii.psi.api.exosuit.PsiArmorEvent;
import vazkii.psi.api.spell.PreSpellCastEvent;
import vazkii.psi.api.spell.SpellContext;
import vazkii.psi.common.core.handler.PlayerDataHandler;
import vazkii.psi.common.item.ItemCAD;
import vazkii.psi.common.item.armor.ItemPsimetalExosuitBoots;
import vazkii.psi.common.item.armor.ItemPsimetalExosuitChestplate;
import vazkii.psi.common.item.armor.ItemPsimetalExosuitHelmet;
import vazkii.psi.common.item.armor.ItemPsimetalExosuitLeggings;
import vazkii.psi.common.item.tool.IPsimetalTool;

import java.util.Stack;

@EventBusSubscriber(modid = PsiOddities.MODID)
public class CommonEvents {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    private static void onPreSpellCast(PreSpellCastEvent event) {
        Player player = event.getPlayer();
        ItemStack cad = event.getCad();
        ICAD cadItem = (ICAD) cad.getItem();
        int cost = event.getCost();

        int availablePsi = PsiUtil.getRealAvailablePsi(player);

        if (cost < availablePsi) {
            return;
        }
        ItemStack assembly = cadItem.getComponentInSlot(cad, EnumCADComponent.BATTERY);
        if(!assembly.isEmpty() && assembly.getItem() == ModItems.cadBatteryTheDiracSanction) {
            event.setCooldown(40);
            player.getCooldowns().addCooldown((Item) cadItem, 40);
            if (player instanceof ServerPlayer) {
                event.setCancellationMessage("psi_oddities.not_enough_psi");
            } else {
                event.setCancellationMessage("");
            }
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    private static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        if (player instanceof LocalPlayer) {
            return;
        }

        float health = player.getHealth();
        Stack<Float> healthChangelog = player.getData(ModAttachments.EIDOS_CHANGELOG);
        if(healthChangelog.size() > 600) {
            healthChangelog.removeFirst();
        }
        healthChangelog.push(health);

        PsiWingData psiWingData = PsiWingUtil.getPsiWingData(player);
        if (psiWingData.isEnabled() && psiWingData.getGracePeriod() == 0 && (player.onGround() || player.horizontalCollision)) {
            PsiWingUtil.disableWing(player);
        } else if(psiWingData.isEnabled()) {
            psiWingData.setGracePeriod(Math.max(psiWingData.getGracePeriod() - 1, 0));
            if (player.level().random.nextFloat() < 0.02) {
                player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                        SoundEvents.ELYTRA_FLYING, SoundSource.PLAYERS, 0.2f, 1f);
            }
        }
    }

    @SubscribeEvent
    private static void onPlayerDeath(LivingDeathEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity instanceof Player player) {
            PsiArmorEvent.post(new PsiArmorEvent(player, PsiArmorUtil.ON_DYING));
            if (player.getHealth() > 0) {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    private static void onShieldBlock(LivingShieldBlockEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity instanceof Player player) {
            ItemStack stack = player.getUseItem();
            if (stack.getItem() instanceof ItemPsimetalShield) {
                PlayerDataHandler.PlayerData data = PlayerDataHandler.get(player);
                ItemStack playerCad = PsiAPI.getPlayerCAD(player);
                if(IPsimetalTool.isEnabled(stack) && !playerCad.isEmpty()) {

                    ItemStack bullet = ISocketable.socketable(stack).getSelectedBullet();

                    LivingEntity attacker;
                    if (event.getDamageSource().getEntity() instanceof LivingEntity) {
                        attacker = (LivingEntity) event.getDamageSource().getEntity();
                    } else {
                        attacker = null;
                    }
                    ItemCAD.cast(player.getCommandSenderWorld(), player, data, bullet, playerCad, 5, 0, 0.025f, (SpellContext context) -> {
                        context.tool = stack;
                        context.attackingEntity = attacker;
                        context.damageTaken = event.getBlockedDamage();
                    }, 0);

                }
            }
        }
    }

    @SubscribeEvent
    private static void modifyItemAttribute(ItemAttributeModifierEvent event) {
        ItemStack stack = event.getItemStack();
        if (stack.getItem() instanceof ICAD) {
            event.addModifier(ModAttributes.REGEN_PSI,
                    new AttributeModifier(PsiOddities.location("cad_rp"), 0.25, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
                    EquipmentSlotGroup.MAINHAND);
        }
        switch (stack.getItem()) {
            case ItemPsimetalExosuitHelmet ignored:
                event.addModifier(ModAttributes.MAX_PSI,
                        new AttributeModifier(PsiOddities.location("psimetal_exosuit_helmet_mp"), 200, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.HEAD);
                event.addModifier(ModAttributes.REGEN_PSI,
                        new AttributeModifier(PsiOddities.location("psimetal_exosuit_helmet_rp"), 2, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.HEAD);
                break;
            case ItemPsimetalExosuitChestplate ignored:
                event.addModifier(ModAttributes.MAX_PSI,
                        new AttributeModifier(PsiOddities.location("psimetal_exosuit_chestplate_mp"), 600, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.CHEST);
                event.addModifier(ModAttributes.REGEN_PSI,
                        new AttributeModifier(PsiOddities.location("psimetal_exosuit_chestplate_rp"), 6, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.CHEST);
                break;
            case ItemPsimetalExosuitLeggings ignored:
                event.addModifier(ModAttributes.MAX_PSI,
                        new AttributeModifier(PsiOddities.location("psimetal_exosuit_leggings_mp"), 500, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.LEGS);
                event.addModifier(ModAttributes.REGEN_PSI,
                        new AttributeModifier(PsiOddities.location("psimetal_exosuit_leggings_rp"), 5, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.LEGS);
                break;
            case ItemPsimetalExosuitBoots ignored:
                event.addModifier(ModAttributes.MAX_PSI,
                        new AttributeModifier(PsiOddities.location("psimetal_exosuit_boots_mp"), 200, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.FEET);
                event.addModifier(ModAttributes.REGEN_PSI,
                        new AttributeModifier(PsiOddities.location("psimetal_exosuit_boots_rp"), 2, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.FEET);
                break;
            default:
        }
    }
}
