package com.zhichaoxi.psi_oddities;

import com.zhichaoxi.psi_oddities.attribute.base.ModAttributes;
import com.zhichaoxi.psi_oddities.component.ModComponents;
import com.zhichaoxi.psi_oddities.item.component.DefaultStats;
import com.zhichaoxi.psi_oddities.spell.ModSpellPieces;
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
        ModAttributes.DR.register(modEventBus);
        ModComponents.DR.register(modEventBus);
        ModSpellPieces.SPELL_PIECES.register(modEventBus);
        ModSpellPieces.ADVANCEMENT_GROUPS.register(modEventBus);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        DefaultStats.registerStats();
    }

    public static ResourceLocation location(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }
}
