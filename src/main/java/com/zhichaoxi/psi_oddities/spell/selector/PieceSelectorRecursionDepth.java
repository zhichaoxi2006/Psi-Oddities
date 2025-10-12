package com.zhichaoxi.psi_oddities.spell.selector;

import vazkii.psi.api.spell.*;
import vazkii.psi.api.spell.piece.PieceSelector;

import java.util.Objects;

public class PieceSelectorRecursionDepth extends PieceSelector {
    public PieceSelectorRecursionDepth(Spell spell) {
        super(spell);
        setStatLabel(EnumSpellStat.POTENCY, new StatLabel(4));
    }

    @Override
    public void initParams() {
        super.initParams();
    }

    @Override
    public void addToMetadata(SpellMetadata meta) throws SpellCompilationException {
        super.addToMetadata(meta);
    }

    @Override
    public Object execute(SpellContext context) {
        Object object = context.customData.get("cast_depth");
        return Objects.requireNonNullElse(object, 0);
    }

    @Override
    public Class<?> getEvaluationType() {
        return Integer.class;
    }
}
