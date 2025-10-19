package com.zhichaoxi.psi_oddities;

import com.zhichaoxi.psi_oddities.client.render.spell.SpellPieceMaterial;
import com.zhichaoxi.psi_oddities.component.ModComponents;
import com.zhichaoxi.psi_oddities.core.handler.ConfigHandler;
import com.zhichaoxi.psi_oddities.item.component.DefaultStats;
import com.zhichaoxi.psi_oddities.spell.base.ModSpellPieces;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(PsiOddities.MODID)
public class PsiOddities {
    public static final String MODID = "psi_oddities";

    public PsiOddities(IEventBus modEventBus, Dist dist, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        ModComponents.DR.register(modEventBus);
        ModSpellPieces.SPELL_PIECES.register(modEventBus);
        ModSpellPieces.ADVANCEMENT_GROUPS.register(modEventBus);
        modContainer.registerConfig(ModConfig.Type.COMMON, ConfigHandler.COMMON_SPEC);
        if (dist.isClient()) {
            SpellPieceMaterial.SPELL_PIECE_MATERIAL.register(modEventBus);
        }
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        DefaultStats.registerStats();
    }

    public static ResourceLocation location(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }
}
