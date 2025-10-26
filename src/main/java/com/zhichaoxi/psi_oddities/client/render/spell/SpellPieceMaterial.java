package com.zhichaoxi.psi_oddities.client.render.spell;

import com.zhichaoxi.psi_oddities.PsiOddities;
import com.zhichaoxi.psi_oddities.lib.LibPieceNames;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import vazkii.psi.api.ClientPsiAPI;

@SuppressWarnings("unused")
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

    public static final DeferredHolder<Material, Material> TRICK_MASS_ATTACK =
            registerMaterial(LibPieceNames.TRICK_MASS_ATTACK);

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

    public static final DeferredHolder<Material, Material> OPERATOR_STRING_JOIN =
            registerMaterial(LibPieceNames.OPERATOR_STRING_JOIN);

    public static final DeferredHolder<Material, Material> OPERATOR_GET_COMMENT_VECTOR =
            registerMaterial(LibPieceNames.OPERATOR_GET_COMMENT_VECTOR);

    // ========== NUMBER INTRO ==========
    public static final DeferredHolder<Material, Material> OPERATOR_EQUAL =
            registerMaterial(LibPieceNames.OPERATOR_EQUAL);

    public static final DeferredHolder<Material, Material> OPERATOR_UNEQUAL =
            registerMaterial(LibPieceNames.OPERATOR_UNEQUAL);

    public static final DeferredHolder<Material, Material> OPERATOR_GT =
            registerMaterial(LibPieceNames.OPERATOR_GT);

    public static final DeferredHolder<Material, Material> OPERATOR_GE =
            registerMaterial(LibPieceNames.OPERATOR_GE);

    public static final DeferredHolder<Material, Material> OPERATOR_LT =
            registerMaterial(LibPieceNames.OPERATOR_LT);

    public static final DeferredHolder<Material, Material> OPERATOR_LE =
            registerMaterial(LibPieceNames.OPERATOR_LE);

    public static final DeferredHolder<Material, Material> OPERATOR_BIT_AND =
            registerMaterial(LibPieceNames.OPERATOR_BIT_AND);

    public static final DeferredHolder<Material, Material> OPERATOR_BIT_LOGIC_SHR =
            registerMaterial(LibPieceNames.OPERATOR_BIT_LOGIC_SHR);

    public static final DeferredHolder<Material, Material> OPERATOR_BIT_SHL =
            registerMaterial(LibPieceNames.OPERATOR_BIT_SHL);

    public static final DeferredHolder<Material, Material> OPERATOR_BIT_NOT =
            registerMaterial(LibPieceNames.OPERATOR_BIT_NOT);

    public static final DeferredHolder<Material, Material> OPERATOR_OR =
            registerMaterial(LibPieceNames.OPERATOR_OR);

    public static final DeferredHolder<Material, Material> OPERATOR_BIT_SHR =
            registerMaterial(LibPieceNames.OPERATOR_BIT_SHR);

    public static final DeferredHolder<Material, Material> OPERATOR_BIT_XOR =
            registerMaterial(LibPieceNames.OPERATOR_BIT_XOR);

    // ========= MISC ==========
    public static final DeferredHolder<Material, Material> TRICK_DISPEL =
            registerMaterial(LibPieceNames.TRICK_DISPEL);

    public static final DeferredHolder<Material, Material> TRICK_CASTER_BLINK =
            registerMaterial(LibPieceNames.TRICK_CASTER_BLINK);

    public static final DeferredHolder<Material, Material> TRICK_CAST =
            registerMaterial(LibPieceNames.TRICK_CAST);

    public static final DeferredHolder<Material, Material> SELECTOR_BULLET_IN_CAD =
            registerMaterial(LibPieceNames.SELECTOR_BULLET_IN_CAD);

    public static final DeferredHolder<Material, Material> SELECTOR_RECURSION_DEPTH =
            registerMaterial(LibPieceNames.SELECTOR_RECURSION_DEPTH);

    public static final DeferredHolder<Material, Material> OPERATOR_TRAVERSE =
            registerMaterial(LibPieceNames.OPERATOR_TRAVERSE);

    public static final DeferredHolder<Material, Material> TRICK_REPAIR =
            registerMaterial(LibPieceNames.TRICK_REPAIR);

    public static final DeferredHolder<Material, Material> OPERATOR_GET_DAMAGE =
            registerMaterial(LibPieceNames.OPERATOR_GET_DAMAGE);

    public static final DeferredHolder<Material, Material> TRICK_HEAL =
            registerMaterial(LibPieceNames.TRICK_HEAL);

    public static final DeferredHolder<Material, Material> SELECTOR_CURRENT_SPELL =
            registerMaterial(LibPieceNames.SELECTOR_CURRENT_SPELL);

    public static final DeferredHolder<Material, Material> OPERATOR_SPELL_BULLET_VIRTUALIZATION =
            registerMaterial(LibPieceNames.OPERATOR_SPELL_BULLET_VIRTUALIZATION);

    public static final DeferredHolder<Material, Material> SELECTOR_GET_BULLET =
            registerMaterial(LibPieceNames.SELECTOR_GET_BULLET);

    public static final DeferredHolder<Material, Material> TRICK_CLEANSE =
            registerMaterial(LibPieceNames.TRICK_CLEANSE);

    public static final DeferredHolder<Material, Material> TRICK_PLAY_MUSIC =
            registerMaterial(LibPieceNames.TRICK_PLAY_MUSIC);

    public static final DeferredHolder<Material, Material> TRICK_TELEPORT =
            registerMaterial(LibPieceNames.TRICK_TELEPORT);

    public static final DeferredHolder<Material, Material> TRICK_PSI_WING =
            registerMaterial(LibPieceNames.TRICK_PSI_WING);

    public static final DeferredHolder<Material, Material> TRICK_HEALTH_REVERSAL =
            registerMaterial(LibPieceNames.TRICK_HEALTH_REVERSAL);

    private static DeferredHolder<Material, Material> registerMaterial(String name) {
        return SPELL_PIECE_MATERIAL.register(name,
                () -> new Material(InventoryMenu.BLOCK_ATLAS, location(name)));
    }

    private static ResourceLocation location(String string) {
        return PsiOddities.location("spell/" + string);
    }
}
