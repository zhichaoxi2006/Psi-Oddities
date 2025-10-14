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
            registerMaterial(LibPieceNames.SELECTOR_SAVED_ENTITY);

    public static final DeferredHolder<Material, Material> TRICK_SAVE_ENTITY =
            registerMaterial(LibPieceNames.TRICK_SAVE_ENTITY);

    // ========== COMBAT MAGIC  ==========
    public static final DeferredHolder<Material, Material> TRICK_ATTACK =
            registerMaterial(LibPieceNames.TRICK_ATTACK);

    public static final DeferredHolder<Material, Material> TRICK_NULLIFY_DEFENSE =
            registerMaterial(LibPieceNames.TRICK_NULLIFY_DEFENSE);

    // ========== STRING PROCESSING ==========
    public static final DeferredHolder<Material, Material> OPERATOR_COMMENT_FORMAT =
            registerMaterial(LibPieceNames.OPERATOR_COMMENT_FORMAT);

    public static final DeferredHolder<Material, Material> OPERATOR_GET_COMMENT =
            registerMaterial(LibPieceNames.OPERATOR_GET_COMMENT);

    public static final DeferredHolder<Material, Material> OPERATOR_GET_COMMENT_NUMBER =
            registerMaterial(LibPieceNames.OPERATOR_GET_COMMENT_NUMBER);

    public static final DeferredHolder<Material, Material> OPERATOR_STRING_CONCATENATE =
            registerMaterial(LibPieceNames.OPERATOR_STRING_CONCATENATE);

    public static final DeferredHolder<Material, Material>OPERATOR_STRING_JOIN =
            registerMaterial(LibPieceNames.OPERATOR_STRING_JOIN);

    // ========== NUMBER INTRO ==========
    public static final DeferredHolder<Material, Material> PIECE_OPERATOR_EQUAL =
            registerMaterial(LibPieceNames.OPERATOR_EQUAL);

    public static final DeferredHolder<Material, Material> PIECE_OPERATOR_BIT_AND =
            registerMaterial(LibPieceNames.OPERATOR_BIT_AND);

    public static final DeferredHolder<Material, Material> PIECE_OPERATOR_BIT_LOGIC_SHR =
            registerMaterial(LibPieceNames.OPERATOR_BIT_LOGIC_SHR);

    public static final DeferredHolder<Material, Material> PIECE_OPERATOR_BIT_SHL =
            registerMaterial(LibPieceNames.OPERATOR_BIT_SHL);

    public static final DeferredHolder<Material, Material> PIECE_OPERATOR_BIT_NOT =
            registerMaterial(LibPieceNames.OPERATOR_BIT_NOT);

    public static final DeferredHolder<Material, Material> PIECE_OPERATOR_OR =
            registerMaterial(LibPieceNames.OPERATOR_OR);

    public static final DeferredHolder<Material, Material> PIECE_OPERATOR_BIT_SHR =
            registerMaterial(LibPieceNames.OPERATOR_BIT_SHR);

    public static final DeferredHolder<Material, Material> PIECE_OPERATOR_BIT_XOR =
            registerMaterial(LibPieceNames.OPERATOR_BIT_XOR);

    // ========= MISC ==========
    public static final DeferredHolder<Material, Material> TRICK_DISPEL =
            registerMaterial(LibPieceNames.TRICK_DISPEL);

    public static final DeferredHolder<Material, Material> TRICK_CASTER_BLINK =
            registerMaterial(LibPieceNames.TRICK_CASTER_BLINK);

    public static final DeferredHolder<Material, Material> TRICK_CAST =
            registerMaterial(LibPieceNames.TRICK_CAST);

    public static final DeferredHolder<Material, Material> SELECTOR_RECURSION_DEPTH =
            registerMaterial(LibPieceNames.SELECTOR_RECURSION_DEPTH);

    private static DeferredHolder<Material, Material> registerMaterial(String name) {
        return SPELL_PIECE_MATERIAL.register(name,
                () -> new Material(InventoryMenu.BLOCK_ATLAS, location(name)));
    }

    private static ResourceLocation location(String string) {
        return PsiOddities.location("spell/" + string);
    }
}
