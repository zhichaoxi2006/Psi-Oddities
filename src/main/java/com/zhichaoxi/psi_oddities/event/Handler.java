package com.zhichaoxi.psi_oddities.event;

import com.zhichaoxi.psi_oddities.PsiOddities;
import com.zhichaoxi.psi_oddities.attribute.base.ModAttributes;
import com.zhichaoxi.psi_oddities.item.base.ModItems;
import com.zhichaoxi.psi_oddities.util.BatteryFluxUtil;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.energy.IEnergyStorage;
import net.neoforged.neoforge.event.ItemAttributeModifierEvent;
import vazkii.psi.api.cad.EnumCADComponent;
import vazkii.psi.api.cad.ICAD;
import vazkii.psi.api.spell.PreSpellCastEvent;
import vazkii.psi.common.item.component.ItemCADCore;

import java.util.ArrayList;

@EventBusSubscriber(modid = PsiOddities.MODID)
public class Handler {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    private static void onPreSpellCast(PreSpellCastEvent event) {
        Player player = event.getPlayer();
        ItemStack cad = event.getCad();
        ICAD cadItem = (ICAD) cad.getItem();
        ItemStack assembly = cadItem.getComponentInSlot(cad, EnumCADComponent.BATTERY);
        if(!assembly.isEmpty() && assembly.getItem() == ModItems.cadBatteryFlux) {
            int cost =  event.getCost();
            cost -= event.getPlayerData().getAvailablePsi();
            ArrayList<IEnergyStorage> energyStorages;
            energyStorages = BatteryFluxUtil.findEnergyStorage(player);
            cost -= BatteryFluxUtil.extractEnergy(energyStorages, cost, true);
            if (cost > 0) {
                if (player instanceof ServerPlayer) {
                    event.setCancellationMessage("psi_oddities.not_enough_energy");
                } else {
                    event.setCancellationMessage("");
                }
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    private static void modifyItemAttribute(ItemAttributeModifierEvent event) {
        ItemStack stack = event.getItemStack();
        if (stack.getItem() instanceof ItemCADCore) {
            event.addModifier(ModAttributes.REGEN_PSI,
                    new AttributeModifier(PsiOddities.location("test"),
                            100.0, AttributeModifier.Operation.ADD_VALUE),
                    EquipmentSlotGroup.MAINHAND);
        }
    }
}
