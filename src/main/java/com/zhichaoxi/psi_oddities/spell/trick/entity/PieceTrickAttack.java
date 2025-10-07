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
        setStatLabel(EnumSpellStat.POTENCY, new StatLabel(SpellParam.GENERIC_NAME_NUMBER, true).abs().mul(40));
        setStatLabel(EnumSpellStat.COST, new StatLabel(SpellParam.GENERIC_NAME_NUMBER, true).abs().mul(65));
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

        meta.addStat(EnumSpellStat.POTENCY, (int) (damageVal * 40));
        meta.addStat(EnumSpellStat.COST, (int) (damageVal * 65));
    }

    @Override
    public Object execute(SpellContext context) throws SpellRuntimeException {
        Player caster = context.caster;
        Entity targetVal = this.getParamValue(context, target);
        float damageVal = this.getParamValue(context, number).floatValue();

        context.verifyEntity(targetVal);
        targetVal.hurt(targetVal.damageSources().source(LibResources.PSI_OVERLOAD, caster), damageVal);

        return null;
    }

}
