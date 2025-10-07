package com.zhichaoxi.psi_oddities.util;

import vazkii.psi.api.internal.Vector3;
import vazkii.psi.api.spell.*;

public class ParamHelper {
    public static double positive(SpellPiece piece, SpellParam<Number> param) throws SpellCompilationException {
        double res = piece.getNotNullParamEvaluation(param).doubleValue();
        if (res <= 0) ErrorUtil.compile(SpellCompilationException.NON_POSITIVE_VALUE, piece.x, piece.y);
        return res;
    }

    public static Vector3 nonNull(SpellPiece piece, SpellContext context, SpellParam<Vector3> param)
            throws SpellRuntimeException {
        Vector3 res = piece.getNotNullParamValue(context, param);
        if (res.isZero()) ErrorUtil.runtime(SpellRuntimeException.NULL_VECTOR);
        return res;
    }
}
