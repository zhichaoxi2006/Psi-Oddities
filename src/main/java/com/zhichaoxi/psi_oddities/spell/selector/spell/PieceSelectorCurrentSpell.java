package com.zhichaoxi.psi_oddities.spell.selector.spell;

import vazkii.psi.api.spell.*;
import vazkii.psi.api.spell.piece.PieceSelector;

public class PieceSelectorCurrentSpell extends PieceSelector {

    public PieceSelectorCurrentSpell(Spell spell) {
        super(spell);
        setStatLabel(EnumSpellStat.POTENCY, new StatLabel(SpellParam.GENERIC_NAME_NUMBER, true).add(6));
    }

    @Override
    public void addToMetadata(SpellMetadata meta) throws SpellCompilationException {
        super.addToMetadata(meta);

        meta.addStat(EnumSpellStat.POTENCY, 6);
    }

    @Override
    public Object execute(SpellContext context) throws SpellRuntimeException {
        return spell;
    }

    @Override
    public Class<?> getEvaluationType() {
        return Spell.class;
    }
}
