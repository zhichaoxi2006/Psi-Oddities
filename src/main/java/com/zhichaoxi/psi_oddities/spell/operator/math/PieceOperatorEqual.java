package com.zhichaoxi.psi_oddities.spell.operator.math;

import com.zhichaoxi.psi_oddities.spell.base.SpellParams;
import vazkii.psi.api.spell.Spell;
import vazkii.psi.api.spell.SpellContext;
import vazkii.psi.api.spell.SpellParam;
import vazkii.psi.api.spell.SpellRuntimeException;
import vazkii.psi.api.spell.param.ParamAny;
import vazkii.psi.api.spell.piece.PieceOperator;

public class PieceOperatorEqual extends PieceOperator {
    SpellParam<?> param1;
    SpellParam<?> param2;

    public PieceOperatorEqual(Spell spell) {
        super(spell);
    }

    @Override
    public void initParams() {
        addParam(param1 = new ParamAny(SpellParams.GENERIC_NAME_PARAM1, SpellParam.RED, false));
        addParam(param2 = new ParamAny(SpellParams.GENERIC_NAME_PARAM2, SpellParam.GREEN, false));
    }

    @Override
    public Object execute(SpellContext context) throws SpellRuntimeException {
        String string1 = this.getParamValue(context, param1).toString();
        String string2 = this.getParamValue(context, param2).toString();

        return string1.equals(string2) ? 1.0 : 0.0;
    }

    @Override
    public Class<?> getEvaluationType() {
        return Double.class;
    }

}
