package com.zhichaoxi.psi_oddities.spell;

import com.zhichaoxi.psi_oddities.PsiOddities;
import com.zhichaoxi.psi_oddities.lib.LibPieceNames;
import com.zhichaoxi.psi_oddities.spell.operator.entity.PieceOperatorEntityFoodLevel;
import com.zhichaoxi.psi_oddities.spell.operator.entity.PieceOperatorEntitySaturation;
import com.zhichaoxi.psi_oddities.spell.trick.entity.PieceTrickAttack;
import com.zhichaoxi.psi_oddities.spell.trick.entity.PieceTrickNullifyDefense;
import com.zhichaoxi.psi_oddities.spell.trick.potion.PieceTrickSaturation;
import net.minecraft.resources.ResourceLocation;
import vazkii.psi.api.PsiAPI;
import vazkii.psi.api.spell.Spell;
import vazkii.psi.api.spell.SpellPiece;
import vazkii.psi.common.lib.LibPieceGroups;

public final class ModSpellPieces {

    public static PieceContainer operatorEntityFoodLevel;
    public static PieceContainer operatorEntitySaturation;

    public static PieceContainer trickAttack;
    public static PieceContainer trickNullifyDefense;
    public static PieceContainer trickSaturation;

    public static void init() {
        operatorEntityFoodLevel = register(PieceOperatorEntityFoodLevel.class, LibPieceNames.OPERATOR_ENTITY_FOODLEVEL, LibPieceGroups.SECONDARY_OPERATORS);
        operatorEntitySaturation = register(PieceOperatorEntitySaturation.class, LibPieceNames.OPERATOR_ENTITY_SATURATION, LibPieceGroups.SECONDARY_OPERATORS);

        trickAttack = register(PieceTrickAttack.class, LibPieceNames.TRICK_ATTACK, LibPieceGroups.MISC_TRICKS);
        trickNullifyDefense = register(PieceTrickNullifyDefense.class, LibPieceNames.TRICK_NULLIFY_DEFENSE, LibPieceGroups.MISC_TRICKS);
        trickSaturation = register(PieceTrickSaturation.class, LibPieceNames.TRICK_SATURATION, LibPieceGroups.POSITIVE_EFFECTS);
    }

    public static PieceContainer register(Class<? extends SpellPiece> clazz, String name, String group) {
        return register(clazz, name, group, false);
    }

    public static PieceContainer register(Class<? extends SpellPiece> clazz, String name, String group, boolean main) {
        PsiAPI.registerSpellPieceAndTexture(ResourceLocation.fromNamespaceAndPath(PsiOddities.MODID, name), clazz);
        PsiAPI.addPieceToGroup(clazz, ResourceLocation.fromNamespaceAndPath(PsiOddities.MODID, group), main);
        return (Spell s) -> SpellPiece.create(clazz, s);
    }

    public interface PieceContainer {
        SpellPiece get(Spell s);
    }
}
