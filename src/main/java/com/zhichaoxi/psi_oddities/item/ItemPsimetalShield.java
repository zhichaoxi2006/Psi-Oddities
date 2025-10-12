package com.zhichaoxi.psi_oddities.item;

import com.zhichaoxi.psi_oddities.PsiOddities;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingShieldBlockEvent;
import vazkii.psi.api.PsiAPI;
import vazkii.psi.api.cad.ISocketable;
import vazkii.psi.api.spell.SpellContext;
import vazkii.psi.common.core.handler.PlayerDataHandler;
import vazkii.psi.common.item.ItemCAD;
import vazkii.psi.common.item.tool.IPsimetalTool;

@EventBusSubscriber(modid = PsiOddities.MODID)
public class ItemPsimetalShield extends ShieldItem implements IPsimetalTool {
    public ItemPsimetalShield(Properties properties) {
        super(properties);
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    private static void onShieldBlocked(LivingShieldBlockEvent event) {
        boolean blocked = event.getBlocked();
        LivingEntity entity = event.getEntity();
        LivingEntity source = (LivingEntity) event.getDamageSource().getEntity();
        if (entity instanceof Player player) {
            if (blocked) {
                PlayerDataHandler.PlayerData data = PlayerDataHandler.get(player);
                ItemStack playerCad = PsiAPI.getPlayerCAD(player);
                ItemStack shieldStack = player.getUseItem();

                if (!(shieldStack.getItem() instanceof ItemPsimetalShield)) {
                    return;
                }

                if(!playerCad.isEmpty()) {
                    ItemStack bullet = ISocketable.socketable(shieldStack).getSelectedBullet();
                    ItemCAD.cast(player.getCommandSenderWorld(), player, data, bullet, playerCad, 5, 10, 0.05F,
                            (SpellContext context) -> {
                                context.attackingEntity = source;
                                context.tool = shieldStack;
                            });
                }
            }
        }
    }
}
