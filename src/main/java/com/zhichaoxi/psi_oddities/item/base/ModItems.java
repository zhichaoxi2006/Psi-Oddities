package com.zhichaoxi.psi_oddities.item.base;

import com.zhichaoxi.psi_oddities.PsiOddities;
import com.zhichaoxi.psi_oddities.item.ItemDyingExosuitSensor;
import com.zhichaoxi.psi_oddities.item.ItemFluxDrive;
import com.zhichaoxi.psi_oddities.item.ItemInlineCaster;
import com.zhichaoxi.psi_oddities.item.ItemPsimetalShield;
import com.zhichaoxi.psi_oddities.lib.LibItemNames;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.RegisterEvent;
import vazkii.psi.common.item.component.ItemCADBattery;

import java.util.List;

@EventBusSubscriber(modid = PsiOddities.MODID)
public final class ModItems {
    public static Item cadBatteryTheDiracSanction;
    public static Item fluxDrive;
    public static Item psimetalShield;
    public static Item inlineCaster;
    public static Item dyingExosuitSensor;

    @SubscribeEvent
    public static void register(RegisterEvent evt) {
        evt.register(Registries.ITEM, helper -> {
            cadBatteryTheDiracSanction = new ItemCADBattery(defaultBuilder());
            fluxDrive = new ItemFluxDrive(defaultBuilder());
            psimetalShield = new ItemPsimetalShield(null,
                    () -> Ingredient.of(
                            vazkii.psi.common.item.base.ModItems.psigem.get(),
                            vazkii.psi.common.item.base.ModItems.psimetal.get()),
                    List.of(),
                    defaultBuilder().durability(972)
            );
            inlineCaster = new ItemInlineCaster(defaultBuilder());
            dyingExosuitSensor = new ItemDyingExosuitSensor(defaultBuilder());

            helper.register(PsiOddities.location(LibItemNames.CAD_BATTERY_THE_DIRAC_SANCTION), cadBatteryTheDiracSanction);
            helper.register(PsiOddities.location(LibItemNames.FLUX_DRIVE), fluxDrive);
            helper.register(PsiOddities.location(LibItemNames.PSIMETAL_SHIELD), psimetalShield);
            helper.register(PsiOddities.location(LibItemNames.INLINE_CASTER), inlineCaster);
            helper.register(PsiOddities.location(LibItemNames.EXOSUIT_SENSOR_DYING), dyingExosuitSensor);
        });
    }

    public static Item.Properties defaultBuilder() {
        return new Item.Properties();
    }
}
