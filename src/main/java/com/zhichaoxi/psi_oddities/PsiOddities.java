package com.zhichaoxi.psi_oddities;

import com.zhichaoxi.psi_oddities.item.component.DefaultStats;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(PsiOddities.MODID)
public class PsiOddities {
    public static final String MODID = "psi_oddities";

    public PsiOddities(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        DefaultStats.registerStats();
    }

    public static ResourceLocation location(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }
}
