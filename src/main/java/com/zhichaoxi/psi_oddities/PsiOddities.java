package com.zhichaoxi.psi_oddities;

import com.zhichaoxi.psi_oddities.attachment.ModAttachments;
import com.zhichaoxi.psi_oddities.client.render.PsiWingLayer;
import com.zhichaoxi.psi_oddities.client.render.spell.SpellPieceMaterial;
import com.zhichaoxi.psi_oddities.component.ModComponents;
import com.zhichaoxi.psi_oddities.core.handler.ConfigHandler;
import com.zhichaoxi.psi_oddities.item.component.DefaultStats;
import com.zhichaoxi.psi_oddities.network.MessageRegister;
import com.zhichaoxi.psi_oddities.spell.base.ModSpellPieces;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@Mod(PsiOddities.MODID)
public class PsiOddities {
    public static final String MODID = "psi_oddities";

    public PsiOddities(IEventBus modEventBus, Dist dist, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        ModComponents.DR.register(modEventBus);
        ModAttachments.DR.register(modEventBus);
        ModSpellPieces.SPELL_PIECES.register(modEventBus);
        ModSpellPieces.ADVANCEMENT_GROUPS.register(modEventBus);
        modContainer.registerConfig(ModConfig.Type.COMMON, ConfigHandler.COMMON_SPEC);
        modEventBus.addListener(MessageRegister::onRegisterPayloadHandler);
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

    @EventBusSubscriber(modid = MODID)
    public static class ClientSetup {
        @SubscribeEvent
        public static void registerRenderers(final EntityRenderersEvent.AddLayers event) {
            addLayerToPlayerSkin(event, PlayerSkin.Model.SLIM);
            addLayerToPlayerSkin(event, PlayerSkin.Model.WIDE);
        }

        @SuppressWarnings({"rawtypes", "unchecked"})
        private static void addLayerToPlayerSkin(EntityRenderersEvent.AddLayers event, PlayerSkin.Model skinName) {
            EntityRenderer<? extends Player> render = event.getSkin(skinName);
            if (render instanceof LivingEntityRenderer livingRenderer) {
                livingRenderer.addLayer(new PsiWingLayer(livingRenderer));
            }
        }
    }
}
