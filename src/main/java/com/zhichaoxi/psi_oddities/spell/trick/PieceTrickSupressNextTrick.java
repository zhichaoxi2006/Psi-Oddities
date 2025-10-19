package com.zhichaoxi.psi_oddities.spell.trick;

import vazkii.psi.api.spell.*;
import vazkii.psi.api.spell.param.ParamNumber;
import vazkii.psi.api.spell.piece.PieceTrick;

import java.util.Stack;

public class PieceTrickSupressNextTrick extends PieceTrick {

    private SpellParam<Number> target;

    public PieceTrickSupressNextTrick(Spell spell) {
        super(spell);
    }


    @Override
    public void initParams() {
        addParam(target = new ParamNumber(SpellParam.GENERIC_NAME_TARGET, SpellParam.BLUE, false, false));
    }

    @Override
    public void addToMetadata(SpellMetadata meta) throws SpellCompilationException {
        meta.addStat(EnumSpellStat.COMPLEXITY, 3);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object execute(SpellContext context) throws SpellRuntimeException {
        double boolVal = this.getParamValue(context, target).doubleValue();
        if (Math.abs(boolVal) < 1) {
            Stack<CompiledSpell.Action> stack = (Stack<CompiledSpell.Action>) context.actions.clone();

            while (!stack.isEmpty()) {
                CompiledSpell.Action a = stack.pop();
                if (a.piece instanceof PieceTrick && a != context.cspell.currentAction) {
                    context.actions.remove(a);
                    break;
                }
            }
        }


        return null;
    }
}
