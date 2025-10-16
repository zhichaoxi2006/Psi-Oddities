package com.zhichaoxi.psi_oddities.capablity;

import com.zhichaoxi.psi_oddities.PsiOddities;
import com.zhichaoxi.psi_oddities.item.base.ModItems;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.items.ComponentItemHandler;
import vazkii.psi.api.PsiAPI;
import vazkii.psi.common.item.base.ModDataComponents;
import vazkii.psi.common.item.tool.ToolSocketable;

@EventBusSubscriber(modid = PsiOddities.MODID)
public class CapablityHandler {
    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerItem(Capabilities.EnergyStorage.ITEM,
                (stack, ctx) -> new EnergyStorageItemstack(1000000, stack),
                ModItems.fluxDrive);

        event.registerItem(
                Capabilities.ItemHandler.ITEM,
                (itemStack, context) -> new ComponentItemHandler(itemStack, ModDataComponents.BULLETS.get(), 3),
                ModItems.psimetalShield);

        event.registerItem(
                PsiAPI.PSI_BAR_DISPLAY_CAPABILITY,
                (tool, ctx) -> new ToolSocketable(tool, 3),
                ModItems.psimetalShield);

        event.registerItem(
                PsiAPI.SPELL_ACCEPTOR_CAPABILITY,
                (tool, ctx) -> new ToolSocketable(tool, 3),
                ModItems.psimetalShield);

        event.registerItem(
                PsiAPI.SOCKETABLE_CAPABILITY,
                (tool, ctx) -> new ToolSocketable(tool, 3),
                ModItems.psimetalShield);
    }
}
