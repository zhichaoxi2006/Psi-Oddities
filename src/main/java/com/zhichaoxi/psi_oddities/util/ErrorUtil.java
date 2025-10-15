package com.zhichaoxi.psi_oddities.util;

import vazkii.psi.api.spell.SpellCompilationException;
import vazkii.psi.api.spell.SpellRuntimeException;

public class ErrorUtil {
    public static final String STACK_OVERFLOW = "psi_oddities.spellerror.stack_overflow";
    public static final String CAST_FAILED = "psi_oddities.spellerror.cast_failed";
    public static final String NAN_COMMENT = "psi_oddities.spellerror.nan_comment";
    public static final String NULL_STRING = "psi_oddities.spellerror.null_string";
    public static final String NULL_VECTOR = "psi_oddities.spellerror.null_vector";
    public static final String NULL_ITEM_STACK = "psi_oddities.spellerror.null_item_stack";
    public static final String INVAILD_SPELL_ACCEPTOR  = "psi_oddities.spellerror.invaild_spell_acceptor";

    public static void compile(String reason, int x, int y) throws SpellCompilationException {
        throw new SpellCompilationException(reason, x, y);
    }

    public static void runtime(String reason) throws SpellRuntimeException {
        throw new SpellRuntimeException(reason);
    }
}
