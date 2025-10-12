package com.zhichaoxi.psi_oddities.client.render.spell;

import com.zhichaoxi.psi_oddities.PsiOddities;
import com.zhichaoxi.psi_oddities.lib.LibPieceNames;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import vazkii.psi.api.ClientPsiAPI;

public class SpellPieceMaterial {
    // ========== REGISTRIES ==========
    public static final DeferredRegister<Material> SPELL_PIECE_MATERIAL =
            DeferredRegister.create(ClientPsiAPI.SPELL_PIECE_MATERIAL, PsiOddities.MODID);

    // ========== MEMORY MANAGEMENT ==========
    public static final DeferredHolder<Material, Material> SELECTOR_SAVED_ENTITY =
            SPELL_PIECE_MATERIAL.register(LibPieceNames.SELECTOR_SAVED_ENTITY,
                    () -> new Material(InventoryMenu.BLOCK_ATLAS, location(LibPieceNames.SELECTOR_SAVED_ENTITY)));

    public static final DeferredHolder<Material, Material> TRICK_SAVE_ENTITY =
            SPELL_PIECE_MATERIAL.register(LibPieceNames.TRICK_SAVE_ENTITY,
                    () -> new Material(InventoryMenu.BLOCK_ATLAS, location(LibPieceNames.TRICK_SAVE_ENTITY)));

    // ========== COMBAT MAGIC  ==========
    public static final DeferredHolder<Material, Material> TRICK_ATTACK =
                SPELL_PIECE_MATERIAL.register(LibPieceNames.TRICK_ATTACK,
                        () -> new Material(InventoryMenu.BLOCK_ATLAS, location(LibPieceNames.TRICK_ATTACK)));

    public static final DeferredHolder<Material, Material> TRICK_NULLIFY_DEFENSE =
            SPELL_PIECE_MATERIAL.register(LibPieceNames.TRICK_NULLIFY_DEFENSE,
                    () -> new Material(InventoryMenu.BLOCK_ATLAS, location(LibPieceNames.TRICK_NULLIFY_DEFENSE)));

    // ========= MISC ==========
    public static final DeferredHolder<Material, Material> TRICK_DISPEL =
            SPELL_PIECE_MATERIAL.register(LibPieceNames.TRICK_DISPEL,
                    () -> new Material(InventoryMenu.BLOCK_ATLAS, location(LibPieceNames.TRICK_DISPEL)));

    public static final DeferredHolder<Material, Material> TRICK_CASTER_BLINK =
            SPELL_PIECE_MATERIAL.register(LibPieceNames.TRICK_CASTER_BLINK,
                    () -> new Material(InventoryMenu.BLOCK_ATLAS, location(LibPieceNames.TRICK_CASTER_BLINK)));

    public static final DeferredHolder<Material, Material> TRICK_CAST =
            SPELL_PIECE_MATERIAL.register(LibPieceNames.TRICK_CAST,
                    () -> new Material(InventoryMenu.BLOCK_ATLAS, location(LibPieceNames.TRICK_CAST)));

    public static final DeferredHolder<Material, Material> SELECTOR_RECURSION_DEPTH =
            SPELL_PIECE_MATERIAL.register(LibPieceNames.SELECTOR_RECURSION_DEPTH,
                    () -> new Material(InventoryMenu.BLOCK_ATLAS, location(LibPieceNames.SELECTOR_RECURSION_DEPTH)));

    private static ResourceLocation location(String string) {
        return PsiOddities.location("spell/" + string);
    }
}
