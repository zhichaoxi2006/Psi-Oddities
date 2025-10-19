package com.zhichaoxi.psi_oddities.spell.operator.math;

import com.zhichaoxi.psi_oddities.spell.base.SpellParams;
import vazkii.psi.api.spell.Spell;
import vazkii.psi.api.spell.SpellContext;
import vazkii.psi.api.spell.SpellParam;
import vazkii.psi.api.spell.SpellRuntimeException;
import vazkii.psi.api.spell.param.ParamNumber;
import vazkii.psi.api.spell.piece.PieceOperator;

public class PieceOperatorLessThan extends PieceOperator {
    SpellParam<Number> param1;
    SpellParam<Number> param2;

    public PieceOperatorLessThan(Spell spell) {
        super(spell);
    }

    @Override
    public void initParams() {
        addParam(param1 = new ParamNumber(SpellParams.GENERIC_NAME_PARAM1, SpellParam.RED, false, false));
        addParam(param2 = new ParamNumber(SpellParams.GENERIC_NAME_PARAM2, SpellParam.GREEN, false, false));
    }

    @Override
    public Object execute(SpellContext context) throws SpellRuntimeException {
        double doubleVal1 = this.getParamValue(context, param1).doubleValue();
        double doubleVal2 = this.getParamValue(context, param2).doubleValue();

        return doubleVal1 < doubleVal2  ? 1.0 : 0.0;
    }

    @Override
    public Class<?> getEvaluationType() {
        return Double.class;
    }

}
