package com.zhichaoxi.psi_oddities.util;

import vazkii.psi.api.spell.SpellCompilationException;
import vazkii.psi.api.spell.SpellRuntimeException;

public class ErrorUtil {
    public static void compile(String reason, int x, int y) throws SpellCompilationException {
        throw new SpellCompilationException(reason, x, y);
    }

    public static void runtime(String reason) throws SpellRuntimeException {
        throw new SpellRuntimeException(reason);
    }
}
