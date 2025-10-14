package com.zhichaoxi.psi_oddities.spell.operator.string;

import com.zhichaoxi.psi_oddities.util.ErrorUtil;
import vazkii.psi.api.spell.*;
import vazkii.psi.api.spell.piece.PieceOperator;

public class PieceOperatorGetComment extends PieceOperator {

    public PieceOperatorGetComment(Spell spell) {
        super(spell);
    }

    @Override
    public void addToMetadata(SpellMetadata meta) throws SpellCompilationException {
        if (this.comment == null || this.comment.isEmpty())
            throw new SpellCompilationException(ErrorUtil.NAN_COMMENT, x, y);
    }

    @Override
    public Object execute(SpellContext context) throws SpellRuntimeException {
        return comment;
    }

    @Override
    public Class<?> getEvaluationType() {
        return String.class;
    }
}
