package com.zhichaoxi.psi_oddities.event;

import com.zhichaoxi.psi_oddities.PsiOddities;
import com.zhichaoxi.psi_oddities.attribute.base.ModAttributes;
import com.zhichaoxi.psi_oddities.item.base.ModItems;
import com.zhichaoxi.psi_oddities.util.PsiUtil;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.ItemAttributeModifierEvent;
import vazkii.psi.api.cad.EnumCADComponent;
import vazkii.psi.api.cad.ICAD;
import vazkii.psi.api.spell.PreSpellCastEvent;
import vazkii.psi.common.item.armor.*;

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
            if (player instanceof ServerPlayer) {
                event.setCancellationMessage("psi_oddities.not_enough_psi");
            } else {
                event.setCancellationMessage("");
            }
            event.setCanceled(true);
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
