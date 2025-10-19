package com.zhichaoxi.psi_oddities.spell.base;

import com.zhichaoxi.psi_oddities.PsiOddities;
import com.zhichaoxi.psi_oddities.lib.LibPieceNames;
import com.zhichaoxi.psi_oddities.spell.operator.entity.PieceOperatorTraverse;
import com.zhichaoxi.psi_oddities.spell.operator.math.*;
import com.zhichaoxi.psi_oddities.spell.operator.math.bitwise.*;
import com.zhichaoxi.psi_oddities.spell.operator.string.*;
import com.zhichaoxi.psi_oddities.spell.selector.PieceSelectorRecursionDepth;
import com.zhichaoxi.psi_oddities.spell.selector.entity.PieceSelectorSavedEntity;
import com.zhichaoxi.psi_oddities.spell.selector.itemstack.PieceSelectorBulletInCAD;
import com.zhichaoxi.psi_oddities.spell.trick.PieceTrickCast;
import com.zhichaoxi.psi_oddities.spell.trick.blink.PieceTrickCasterBlink;
import com.zhichaoxi.psi_oddities.spell.trick.entity.*;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import vazkii.psi.api.PsiAPI;
import vazkii.psi.api.spell.SpellPiece;
import vazkii.psi.common.lib.LibPieceGroups;

import java.util.Arrays;
import java.util.Collection;

@SuppressWarnings("unused")
public final class ModSpellPieces {

    public static final DeferredRegister<Class<? extends SpellPiece>> SPELL_PIECES =
            DeferredRegister.create(PsiAPI.SPELL_PIECE_REGISTRY_TYPE_KEY, PsiOddities.MODID);
    public static final DeferredRegister<Collection<Class<? extends SpellPiece>>> ADVANCEMENT_GROUPS =
            DeferredRegister.create(PsiAPI.ADVANCEMENT_GROUP_REGISTRY_KEY, PsiOddities.MODID);

    // ========== STRING PROCESSING ==========
    public static final DeferredHolder<Class<? extends SpellPiece>, Class<PieceOperatorCommentFormat>> OPERATOR_COMMENT_FORMAT =
            SPELL_PIECES.register(LibPieceNames.OPERATOR_COMMENT_FORMAT, () -> PieceOperatorCommentFormat.class);

    public static final DeferredHolder<Class<? extends SpellPiece>, Class<PieceOperatorGetComment>> OPERATOR_GET_COMMENT =
            SPELL_PIECES.register(LibPieceNames.OPERATOR_GET_COMMENT, () -> PieceOperatorGetComment.class);

    public static final DeferredHolder<Class<? extends SpellPiece>, Class<PieceOperatorGetCommentNumber>> OPERATOR_GET_COMMENT_NUMBER =
            SPELL_PIECES.register(LibPieceNames.OPERATOR_GET_COMMENT_NUMBER, () -> PieceOperatorGetCommentNumber.class);

    public static final DeferredHolder<Class<? extends SpellPiece>, Class<PieceOperatorStringConcatenate>> OPERATOR_STRING_CONCATENATE =
            SPELL_PIECES.register(LibPieceNames.OPERATOR_STRING_CONCATENATE, () -> PieceOperatorStringConcatenate.class);

    public static final DeferredHolder<Class<? extends SpellPiece>, Class<PieceOperatorStringJoin>> OPERATOR_STRING_JOIN =
            SPELL_PIECES.register(LibPieceNames.OPERATOR_STRING_JOIN, () -> PieceOperatorStringJoin.class);

    public static final DeferredHolder<Class<? extends SpellPiece>, Class<PieceOperatorGetCommentVector>> OPERATOR_GET_COMMENT_VECTOR =
            SPELL_PIECES.register(LibPieceNames.OPERATOR_GET_COMMENT_VECTOR, () -> PieceOperatorGetCommentVector.class);

    public static final DeferredHolder<Collection<Class<? extends SpellPiece>>, Collection<Class<? extends SpellPiece>>> STRING_PROCESSING =
            ADVANCEMENT_GROUPS.register(com.zhichaoxi.psi_oddities.lib.LibPieceGroups.STRING_PROCESSING,
                    () -> Arrays.asList(
                            PieceOperatorCommentFormat.class,
                            PieceOperatorGetComment.class,
                            PieceOperatorGetCommentNumber.class,
                            PieceOperatorStringConcatenate.class,
                            PieceOperatorStringJoin.class,
                            PieceOperatorGetCommentVector.class
                    ));

    // ========== NUMBER INTRO ==========
    public static final DeferredHolder<Class<? extends SpellPiece>, Class<PieceOperatorEqual>> OPERATOR_EQUAL =
            SPELL_PIECES.register(LibPieceNames.OPERATOR_EQUAL, () -> PieceOperatorEqual.class);

    public static final DeferredHolder<Class<? extends SpellPiece>, Class<PieceOperatorUnEqual>> OPERATOR_UNEQUAL =
            SPELL_PIECES.register(LibPieceNames.OPERATOR_UNEQUAL, () -> PieceOperatorUnEqual.class);

    public static final DeferredHolder<Class<? extends SpellPiece>, Class<PieceOperatorGreaterThan>> OPERATOR_GT =
            SPELL_PIECES.register(LibPieceNames.OPERATOR_GT, () -> PieceOperatorGreaterThan.class);

    public static final DeferredHolder<Class<? extends SpellPiece>, Class<PieceOperatorGreaterThanOrEqual>> OPERATOR_GE =
            SPELL_PIECES.register(LibPieceNames.OPERATOR_GE, () -> PieceOperatorGreaterThanOrEqual.class);

    public static final DeferredHolder<Class<? extends SpellPiece>, Class<PieceOperatorLessThan>> OPERATOR_LT =
            SPELL_PIECES.register(LibPieceNames.OPERATOR_LT, () -> PieceOperatorLessThan.class);

    public static final DeferredHolder<Class<? extends SpellPiece>, Class<PieceOperatorLessThanOrEqual>> OPERATOR_LE =
            SPELL_PIECES.register(LibPieceNames.OPERATOR_LE, () -> PieceOperatorLessThanOrEqual.class);

    public static final DeferredHolder<Class<? extends SpellPiece>, Class<PieceOperatorAnd>> OPERATOR_BIT_AND =
            SPELL_PIECES.register(LibPieceNames.OPERATOR_BIT_AND, () -> PieceOperatorAnd.class);

    public static final DeferredHolder<Class<? extends SpellPiece>, Class<PieceOperatorLogicalRShift>> OPERATOR_BIT_LOGIC_SHR =
            SPELL_PIECES.register(LibPieceNames.OPERATOR_BIT_LOGIC_SHR, () -> PieceOperatorLogicalRShift.class);

    public static final DeferredHolder<Class<? extends SpellPiece>, Class<PieceOperatorLShift>> OPERATOR_BIT_SHL =
            SPELL_PIECES.register(LibPieceNames.OPERATOR_BIT_SHL, () -> PieceOperatorLShift.class);

    public static final DeferredHolder<Class<? extends SpellPiece>, Class<PieceOperatorNot>> OPERATOR_BIT_NOT =
            SPELL_PIECES.register(LibPieceNames.OPERATOR_BIT_NOT, () -> PieceOperatorNot.class);

    public static final DeferredHolder<Class<? extends SpellPiece>, Class<PieceOperatorOr>> OPERATOR_OR =
            SPELL_PIECES.register(LibPieceNames.OPERATOR_OR, () -> PieceOperatorOr.class);

    public static final DeferredHolder<Class<? extends SpellPiece>, Class<PieceOperatorRShift>> OPERATOR_BIT_SHR =
            SPELL_PIECES.register(LibPieceNames.OPERATOR_BIT_SHR, () -> PieceOperatorRShift.class);

    public static final DeferredHolder<Class<? extends SpellPiece>, Class<PieceOperatorXor>> OPERATOR_BIT_XOR =
            SPELL_PIECES.register(LibPieceNames.OPERATOR_BIT_XOR, () -> PieceOperatorXor.class);

    public static final DeferredHolder<Collection<Class<? extends SpellPiece>>, Collection<Class<? extends SpellPiece>>> OPERATORS =
            ADVANCEMENT_GROUPS.register(LibPieceGroups.NUMBERS_INTRO,
                    () -> Arrays.asList(
                            PieceOperatorEqual.class,
                            PieceOperatorUnEqual.class,
                            PieceOperatorGreaterThan.class,
                            PieceOperatorGreaterThanOrEqual.class,
                            PieceOperatorLessThan.class,
                            PieceOperatorLessThanOrEqual.class,
                            PieceOperatorAnd.class,
                            PieceOperatorLogicalRShift.class,
                            PieceOperatorLShift.class,
                            PieceOperatorNot.class,
                            PieceOperatorOr.class,
                            PieceOperatorLShift.class,
                            PieceOperatorXor.class
                    ));

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

    public static final DeferredHolder<Class<? extends SpellPiece>, Class<PieceTrickMassAttack>> TRICK_MASS_ATTACK =
            SPELL_PIECES.register(LibPieceNames.TRICK_MASS_ATTACK, () -> PieceTrickMassAttack.class);

    public static final DeferredHolder<Class<? extends SpellPiece>, Class<PieceTrickNullifyDefense>> TRICK_NULLIFY_DEFENSE =
            SPELL_PIECES.register(LibPieceNames.TRICK_NULLIFY_DEFENSE, () -> PieceTrickNullifyDefense.class);

    public static final DeferredHolder<Collection<Class<? extends SpellPiece>>, Collection<Class<? extends SpellPiece>>> COMBAT_MAGIC =
            ADVANCEMENT_GROUPS.register(com.zhichaoxi.psi_oddities.lib.LibPieceGroups.COMBAT_MAGIC,
                    () -> Arrays.asList(
                            PieceTrickAttack.class,
                            PieceTrickMassAttack.class,
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

    public static final DeferredHolder<Class<? extends SpellPiece>, Class<PieceSelectorBulletInCAD>> SELECTOR_BULLET_IN_CAD =
            SPELL_PIECES.register(LibPieceNames.SELECTOR_BULLET_IN_CAD, () -> PieceSelectorBulletInCAD.class);

    public static final DeferredHolder<Class<? extends SpellPiece>, Class<PieceOperatorTraverse>> OPERATOR_TRAVERSE =
            SPELL_PIECES.register(LibPieceNames.OPERATOR_TRAVERSE, () -> PieceOperatorTraverse.class);

    public static final DeferredHolder<Collection<Class<? extends SpellPiece>>, Collection<Class<? extends SpellPiece>>> MISC =
            ADVANCEMENT_GROUPS.register(LibPieceGroups.MISC_TRICKS,
                    () -> Arrays.asList(
                            PieceTrickCasterBlink.class,
                            PieceTrickDispel.class,
                            PieceTrickCast.class,
                            PieceSelectorRecursionDepth.class,
                            PieceSelectorBulletInCAD.class,
                            PieceOperatorTraverse.class
                    ));
}