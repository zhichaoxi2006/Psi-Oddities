package com.zhichaoxi.psi_oddities.item.component;

import com.zhichaoxi.psi_oddities.PsiOddities;
import com.zhichaoxi.psi_oddities.item.base.ModItems;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import vazkii.psi.api.cad.CADStatEvent;
import vazkii.psi.api.cad.EnumCADComponent;
import vazkii.psi.api.cad.EnumCADStat;
import vazkii.psi.api.cad.ICAD;
import vazkii.psi.common.item.component.ItemCADComponent;

@EventBusSubscriber(modid = PsiOddities.MODID)
public class DefaultStats {
    public static void registerStats() {
        registerBatteryStats();
    }

    public static void registerBatteryStats() {
        // Flux
        ItemCADComponent.addStatToStack(ModItems.cadBatteryTheDiracSanction, EnumCADStat.OVERFLOW, 50);
    }

    @SubscribeEvent
    public static void modifyBatteryFluxStats(CADStatEvent event) {
        ItemStack cad = event.getCad();
        ICAD cadItem = (ICAD) cad.getItem();
        ItemStack assembly = cadItem.getComponentInSlot(cad, EnumCADComponent.BATTERY);
        if(!assembly.isEmpty() && assembly.getItem() == ModItems.cadBatteryTheDiracSanction) {
            if (event.getStat() == EnumCADStat.POTENCY) {
                event.setStatValue(-1);
            }
        }
    }
}
