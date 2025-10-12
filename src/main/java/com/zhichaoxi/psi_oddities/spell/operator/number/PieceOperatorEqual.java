package com.zhichaoxi.psi_oddities.spell.operator.number;

import vazkii.psi.api.spell.Spell;
import vazkii.psi.api.spell.piece.PieceOperator;

public class PieceOperatorEqual extends PieceOperator {

    public PieceOperatorEqual(Spell spell) {
        super(spell);
    }

    @Override
    public Class<?> getEvaluationType() {
        return Integer.class;
    }
}
