package com.zhichaoxi.psi_oddities.spell.operator.entity;

import net.minecraft.world.entity.Entity;
import vazkii.psi.api.spell.*;
import vazkii.psi.api.spell.param.ParamEntityListWrapper;
import vazkii.psi.api.spell.piece.PieceOperator;
import vazkii.psi.api.spell.wrapper.EntityListWrapper;

import javax.swing.*;
import java.util.Collection;
import java.util.Stack;
import java.util.stream.Collectors;

public class PieceOperatorTraverse extends PieceOperator {

    SpellParam<EntityListWrapper> targets;

    public PieceOperatorTraverse(Spell spell) {
        super(spell);
    }

    @Override
    public void initParams() {
        addParam(targets = new ParamEntityListWrapper(SpellParam.GENERIC_NAME_TARGET, SpellParam.RED, false, false));
    }

    @Override
    public void addToMetadata(SpellMetadata meta) throws SpellCompilationException {
        super.addToMetadata(meta);
    }

    public static boolean hasPath(SpellGrid grid, SpellPiece from, SpellPiece to) throws SpellCompilationException {
        if (from == to) {
            return true;
        }
        for (SpellParam.Side side : SpellParam.Side.values()) {
            if (side == SpellParam.Side.OFF) {
                continue;
            }
            SpellPiece piece = grid.getPieceAtSideWithRedirections(from.x, from.y, side);
            if (from.isInputSide(side) && piece != null) {
                return hasPath(grid, piece, to);
            }
        }
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object execute(SpellContext context) throws SpellRuntimeException {
        EntityListWrapper listVal = this.getParamValue(context, targets);

        if(listVal.size() == 0) {
            throw new SpellRuntimeException(SpellRuntimeException.NULL_TARGET);
        }

        String id = String.format("traverse_index_%d_%d", x, y);

        Object object = context.customData.get(id);
        int traverse_index;
        if (object != null) {
            traverse_index = (int) object;
        } else {
            traverse_index = 0;
        }

        if (traverse_index < listVal.size() - 1) {
            Stack<CompiledSpell.Action> stack = (Stack<CompiledSpell.Action>) context.cspell.actions.clone();
            CompiledSpell.Action currentAction = context.cspell.currentAction;

            boolean flag = false;
            while (!stack.isEmpty()) {
                CompiledSpell.Action action = stack.pop();
                if (action == currentAction) {
                    flag = true;
                }
                if (flag) {
                    context.actions.push(currentAction);
                    context.actions.addAll(stack);
                    break;
                }
            }

            context.customData.put(id, traverse_index + 1);
        }

        if (traverse_index < listVal.size()) {
            return listVal.get(traverse_index);
        }
        throw new SpellRuntimeException(SpellRuntimeException.OUT_OF_BOUNDS);
    }

    @Override
    public Class<?> getEvaluationType() {
        return Entity.class;
    }
}
