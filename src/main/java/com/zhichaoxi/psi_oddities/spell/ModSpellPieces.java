package com.zhichaoxi.psi_oddities.spell;

import com.zhichaoxi.psi_oddities.PsiOddities;
import com.zhichaoxi.psi_oddities.lib.LibPieceNames;
import com.zhichaoxi.psi_oddities.spell.selector.PieceSelectorRecursionDepth;
import com.zhichaoxi.psi_oddities.spell.selector.entity.PieceSelectorSavedEntity;
import com.zhichaoxi.psi_oddities.spell.trick.PieceTrickCast;
import com.zhichaoxi.psi_oddities.spell.trick.blink.PieceTrickCasterBlink;
import com.zhichaoxi.psi_oddities.spell.trick.entity.PieceTrickAttack;
import com.zhichaoxi.psi_oddities.spell.trick.entity.PieceTrickDispel;
import com.zhichaoxi.psi_oddities.spell.trick.entity.PieceTrickNullifyDefense;
import com.zhichaoxi.psi_oddities.spell.trick.entity.PieceTrickSaveEntity;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import vazkii.psi.api.PsiAPI;
import vazkii.psi.api.spell.SpellPiece;
import vazkii.psi.common.lib.LibPieceGroups;

import java.util.Arrays;
import java.util.Collection;

public final class ModSpellPieces {

    public static final DeferredRegister<Class<? extends SpellPiece>> SPELL_PIECES =
            DeferredRegister.create(PsiAPI.SPELL_PIECE_REGISTRY_TYPE_KEY, PsiOddities.MODID);
    public static final DeferredRegister<Collection<Class<? extends SpellPiece>>> ADVANCEMENT_GROUPS =
            DeferredRegister.create(PsiAPI.ADVANCEMENT_GROUP_REGISTRY_KEY, PsiOddities.MODID);

    // ========== MEMORY MANAGEMENT ==========
    public static final DeferredHolder<Class<? extends SpellPiece>, Class<PieceSelectorSavedEntity>> SELECTOR_SAVED_ENTITY =
            SPELL_PIECES.register(LibPieceNames.SELECTOR_SAVED_ENTITY, () -> PieceSelectorSavedEntity.class);
    public static final DeferredHolder<Class<? extends SpellPiece>, Class<PieceTrickSaveEntity>> TRICK_SAVE_ENTITY =
            SPELL_PIECES.register(LibPieceNames.TRICK_SAVE_ENTITY, () -> PieceTrickSaveEntity.class);

    public static final DeferredHolder<Collection<Class<? extends SpellPiece>>, Collection<Class<? extends SpellPiece>>> MEMORY_MANAGEMENT =
            ADVANCEMENT_GROUPS.register(LibPieceGroups.MEMORY_MANAGEMENT,
                    () -> Arrays.asList(
                            PieceSelectorSavedEntity.class,
                            PieceTrickSaveEntity.class
                    ));

    // ========== COMBAT MAGIC  ==========
    public static final DeferredHolder<Class<? extends SpellPiece>, Class<PieceTrickAttack>> TRICK_ATTACK =
            SPELL_PIECES.register(LibPieceNames.TRICK_ATTACK, () -> PieceTrickAttack.class);

    public static final DeferredHolder<Class<? extends SpellPiece>, Class<PieceTrickNullifyDefense>> TRICK_NULLIFY_DEFENSE =
            SPELL_PIECES.register(LibPieceNames.TRICK_NULLIFY_DEFENSE, () -> PieceTrickNullifyDefense.class);

    public static final DeferredHolder<Collection<Class<? extends SpellPiece>>, Collection<Class<? extends SpellPiece>>> COMBAT_MAGIC =
            ADVANCEMENT_GROUPS.register(com.zhichaoxi.psi_oddities.lib.LibPieceGroups.COMBAT_MAGIC,
                    () -> Arrays.asList(
                            PieceTrickAttack.class,
                            PieceTrickNullifyDefense.class
                    ));

    // ========== MISC ==========

    public static final DeferredHolder<Class<? extends SpellPiece>, Class<PieceTrickCasterBlink>> TRICK_CASTER_BLINK =
            SPELL_PIECES.register(LibPieceNames.TRICK_CASTER_BLINK, () -> PieceTrickCasterBlink.class);

    public static final DeferredHolder<Class<? extends SpellPiece>, Class<PieceTrickDispel>> TRICK_DISPEL =
            SPELL_PIECES.register(LibPieceNames.TRICK_DISPEL, () -> PieceTrickDispel.class);

    public static final DeferredHolder<Class<? extends SpellPiece>, Class<PieceTrickCast>> TRICK_CAST =
            SPELL_PIECES.register(LibPieceNames.TRICK_CAST, () -> PieceTrickCast.class);

    public static final DeferredHolder<Class<? extends SpellPiece>, Class<PieceSelectorRecursionDepth>> SELECTOR_RECURSION_DEPTH =
            SPELL_PIECES.register(LibPieceNames.SELECTOR_RECURSION_DEPTH, () -> PieceSelectorRecursionDepth.class);

    public static final DeferredHolder<Collection<Class<? extends SpellPiece>>, Collection<Class<? extends SpellPiece>>> MISC =
            ADVANCEMENT_GROUPS.register(LibPieceGroups.MISC_TRICKS,
                    () -> Arrays.asList(
                            PieceTrickCasterBlink.class,
                            PieceTrickDispel.class,
                            PieceTrickCast.class,
                            PieceSelectorRecursionDepth.class
                    ));
}