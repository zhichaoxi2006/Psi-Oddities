package com.zhichaoxi.psi_oddities.spell.trick.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import vazkii.psi.api.spell.*;
import vazkii.psi.api.spell.param.ParamEntity;
import vazkii.psi.api.spell.param.ParamNumber;
import vazkii.psi.api.spell.piece.PieceTrick;
import vazkii.psi.common.lib.LibResources;

public class PieceTrickAttack extends PieceTrick {

    SpellParam<Entity> target;
    SpellParam<Number> number;

    public PieceTrickAttack(Spell spell) {
        super(spell);
        setStatLabel(EnumSpellStat.POTENCY, new StatLabel(SpellParam.GENERIC_NAME_NUMBER, true).abs().mul(15));
        setStatLabel(EnumSpellStat.COST, new StatLabel(SpellParam.GENERIC_NAME_NUMBER, true).abs().mul(20));
    }

    @Override
    public void initParams() {
        addParam(target = new ParamEntity(SpellParam.GENERIC_NAME_TARGET, SpellParam.YELLOW, false, false));
        addParam(number = new ParamNumber(SpellParam.GENERIC_NAME_NUMBER, SpellParam.RED, false, true));
    }

    @Override
    public void addToMetadata(SpellMetadata meta) throws SpellCompilationException {
        super.addToMetadata(meta);
        Double damageVal = this.<Double>getParamEvaluation(number);

        if(damageVal == null || damageVal <= 0) {
            throw new SpellCompilationException(SpellCompilationException.NON_POSITIVE_VALUE, x, y);
        }

        meta.addStat(EnumSpellStat.POTENCY, (int) (damageVal * 15));
        meta.addStat(EnumSpellStat.COST, (int) (damageVal * 20));
    }

    public static void attack(SpellContext context, Entity targetVal, float damageVal) throws SpellRuntimeException {
        Player caster = context.caster;
        context.verifyEntity(targetVal);
        if (!context.isInRadius(targetVal)) {
            throw new SpellRuntimeException(SpellRuntimeException.OUTSIDE_RADIUS);
        }
        targetVal.hurt(targetVal.damageSources().source(LibResources.PSI_OVERLOAD, caster), damageVal);
    }

    @Override
    public Object execute(SpellContext context) throws SpellRuntimeException {
        Entity targetVal = this.getParamValue(context, target);
        float damageVal = this.getParamValue(context, number).floatValue();

        attack(context, targetVal, damageVal);

        return null;
    }

}
