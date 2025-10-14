package com.zhichaoxi.psi_oddities.spell.operator.math.bitwise;

import vazkii.psi.api.spell.Spell;
import vazkii.psi.api.spell.SpellContext;
import vazkii.psi.api.spell.SpellParam;
import vazkii.psi.api.spell.SpellRuntimeException;
import vazkii.psi.api.spell.param.ParamNumber;
import vazkii.psi.api.spell.piece.PieceOperator;

public class PieceOperatorNot extends PieceOperator {

    SpellParam<Number> num;

    public PieceOperatorNot(Spell spell) {
        super(spell);
    }

    @Override
    public void initParams() {
        addParam(num = new ParamNumber(SpellParam.GENERIC_NAME_NUMBER1, SpellParam.GREEN, false, false));
    }

    @Override
    public Object execute(SpellContext context) throws SpellRuntimeException {
        int numVal = this.getParamValue(context, num).intValue();

        return (double) (~ numVal);
    }

    @Override
    public Class<?> getEvaluationType() {
        return Double.class;
    }

}