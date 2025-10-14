package com.zhichaoxi.psi_oddities.spell.operator.math;

import vazkii.psi.api.spell.Spell;
import vazkii.psi.api.spell.SpellContext;
import vazkii.psi.api.spell.SpellParam;
import vazkii.psi.api.spell.SpellRuntimeException;
import vazkii.psi.api.spell.param.ParamNumber;
import vazkii.psi.api.spell.piece.PieceOperator;

public class PieceOperatorEqual extends PieceOperator {
    SpellParam<Number> num1;
    SpellParam<Number> num2;

    public PieceOperatorEqual(Spell spell) {
        super(spell);
    }

    @Override
    public void initParams() {
        addParam(num1 = new ParamNumber(SpellParam.GENERIC_NAME_NUMBER1, SpellParam.RED, false, false));
        addParam(num2 = new ParamNumber(SpellParam.GENERIC_NAME_NUMBER2, SpellParam.GREEN, false, false));
    }

    @Override
    public Object execute(SpellContext context) throws SpellRuntimeException {
        int num1Val = this.getParamValue(context, num1).intValue();
        int num2Val = this.getParamValue(context, num2).intValue();

        return num1Val == num2Val ? 1.0 : 0.0;
    }

    @Override
    public Class<?> getEvaluationType() {
        return Double.class;
    }

}
