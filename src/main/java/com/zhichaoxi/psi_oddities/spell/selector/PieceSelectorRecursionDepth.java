package com.zhichaoxi.psi_oddities.spell.selector;

import vazkii.psi.api.spell.*;
import vazkii.psi.api.spell.piece.PieceSelector;

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
    public Object execute(SpellContext context) throws SpellRuntimeException {
        Object object = context.customData.get("cast_depth");
        if (object != null) {
            return object;
        } else {
            return 0;
        }
    }

    @Override
    public Class<?> getEvaluationType() {
        return Integer.class;
    }
}
