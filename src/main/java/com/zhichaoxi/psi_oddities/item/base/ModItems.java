package com.zhichaoxi.psi_oddities.item.base;

import com.zhichaoxi.psi_oddities.PsiOddities;
import com.zhichaoxi.psi_oddities.item.ItemFluxDrive;
import com.zhichaoxi.psi_oddities.lib.LibItemNames;
import com.zhichaoxi.psi_oddities.spell.ModSpellPieces;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.RegisterEvent;
import vazkii.psi.common.item.component.ItemCADBattery;

@EventBusSubscriber(modid = PsiOddities.MODID)
public final class ModItems {
    public static Item cadBatteryFlux;
    public static Item fluxDrive;

    @SubscribeEvent
    public static void register(RegisterEvent evt) {
        evt.register(Registries.ITEM, helper -> {
            cadBatteryFlux = new ItemCADBattery(defaultBuilder());
            fluxDrive = new ItemFluxDrive(defaultBuilder());

            ModSpellPieces.init();

            helper.register(PsiOddities.location(LibItemNames.CAD_BATTERY_FLUX), cadBatteryFlux);
            helper.register(PsiOddities.location(LibItemNames.FLUX_DRIVE), fluxDrive);
        });
    }

    public static Item.Properties defaultBuilder() {
        return new Item.Properties();
    }
}
