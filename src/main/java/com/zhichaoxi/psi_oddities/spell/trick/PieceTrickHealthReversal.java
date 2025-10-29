package com.zhichaoxi.psi_oddities.spell.trick;

import com.zhichaoxi.psi_oddities.attachment.ModAttachments;
import net.minecraft.world.entity.player.Player;
import vazkii.psi.api.spell.*;
import vazkii.psi.api.spell.param.ParamNumber;
import vazkii.psi.api.spell.piece.PieceTrick;

import java.util.Stack;

public class PieceTrickHealthReversal extends PieceTrick {

    SpellParam<Number> time;

    public PieceTrickHealthReversal(Spell spell) {
        super(spell);
        setStatLabel(EnumSpellStat.POTENCY, new StatLabel(SpellParam.GENERIC_NAME_TIME, true).add(20));
        setStatLabel(EnumSpellStat.COST, new StatLabel(SpellParam.GENERIC_NAME_TIME, true).mul(10).add(1000));
    }

    @Override
    public void initParams() {
        addParam(time = new ParamNumber(SpellParam.GENERIC_NAME_TIME, SpellParam.RED, false, true));
    }

    @Override
    public void addToMetadata(SpellMetadata meta) throws SpellCompilationException {
        super.addToMetadata(meta);
        Double timeVal = this.<Double>getParamEvaluation(time);

        if(timeVal == null || timeVal <= 0 || timeVal != timeVal.intValue()) {
            throw new SpellCompilationException(SpellCompilationException.NON_POSITIVE_INTEGER, x, y);
        }

        meta.addStat(EnumSpellStat.POTENCY, (int) (timeVal + 20));
        meta.addStat(EnumSpellStat.COST, timeVal.intValue() * 10 + 1000);
    }

    @Override
    public Object execute(SpellContext context) throws SpellRuntimeException {
        int timeVal = this.getParamValue(context, time).intValue();
        Player player = context.caster;
        Stack<Float> healthChangelog = player.getData(ModAttachments.EIDOS_CHANGELOG);
        for(int counter = 0;counter < timeVal;counter++) {
            int size = healthChangelog.size() - 1;
            float health = 0;
            try {
                health = healthChangelog.get(size - counter);
            } catch (ArrayIndexOutOfBoundsException e) {
                return null;
            }
            player.setHealth(health);
        }

        return null;
    }

}
