package com.zhichaoxi.psi_oddities.spell.operator.string;

import com.zhichaoxi.psi_oddities.spell.base.SpellParams;
import com.zhichaoxi.psi_oddities.util.ErrorUtil;
import vazkii.psi.api.spell.*;
import vazkii.psi.api.spell.param.ParamAny;
import vazkii.psi.api.spell.piece.PieceOperator;

public class PieceOperatorCommentFormat extends PieceOperator {

     SpellParam<?> param1;
     SpellParam<?> param2;
     SpellParam<?> param3;

    public PieceOperatorCommentFormat(Spell spell) {
        super(spell);
    }

    @Override
    public void addToMetadata(SpellMetadata meta) throws SpellCompilationException {
        if (this.comment == null || this.comment.isEmpty())
            throw new SpellCompilationException(ErrorUtil.NAN_COMMENT, x, y);
    }

    @Override
    public void initParams() {
        addParam(param1 = new ParamAny(SpellParams.GENERIC_NAME_PARAM1, SpellParam.RED, false));
        addParam(param2 = new ParamAny(SpellParams.GENERIC_NAME_PARAM2, SpellParam.BLUE, true));
        addParam(param3 = new ParamAny(SpellParams.GENERIC_NAME_PARAM3, SpellParam.GREEN, true));
    }

    @Override
    public Object execute(SpellContext context) throws SpellRuntimeException {
        Object o1 = getParamValue(context, param1);
        Object o2 = getParamValue(context, param2);
        Object o3 = getParamValue(context, param3);

        String s = comment;
        if (o1 == null)
            throw new SpellRuntimeException(ErrorUtil.NULL_STRING);
        if (o2 != null) {
            if (o3 != null)
                return String.format(s, o1, o2, o3);
            return String.format(s, o1, o2);
        }


        return String.format(s, o1);
    }

    @Override
    public Class<?> getEvaluationType() {
        return String.class;
    }
}
