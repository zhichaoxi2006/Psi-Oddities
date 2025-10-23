package com.zhichaoxi.psi_oddities.spell.param;

import vazkii.psi.api.spell.Spell;
import vazkii.psi.api.spell.param.ParamSpecific;

public class ParamString extends ParamSpecific<String> {

    public ParamString(String name, int color, boolean canDisable, boolean constant) {
        super(name, color, canDisable, constant);
    }

    @Override
    public Class<String> getRequiredType() {
        return String.class;
    }

}

