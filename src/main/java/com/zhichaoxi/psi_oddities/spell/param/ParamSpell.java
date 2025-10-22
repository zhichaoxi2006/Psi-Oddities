package com.zhichaoxi.psi_oddities.spell.param;

import vazkii.psi.api.spell.Spell;
import vazkii.psi.api.spell.param.ParamSpecific;

public class ParamSpell extends ParamSpecific<Spell> {

    public ParamSpell(String name, int color, boolean canDisable, boolean constant) {
        super(name, color, canDisable, constant);
    }

    @Override
    public Class<Spell> getRequiredType() {
        return Spell.class;
    }

}

