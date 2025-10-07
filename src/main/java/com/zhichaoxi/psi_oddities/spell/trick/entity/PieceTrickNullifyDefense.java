package com.zhichaoxi.psi_oddities.spell.trick.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import vazkii.psi.api.spell.*;
import vazkii.psi.api.spell.param.ParamEntity;
import vazkii.psi.api.spell.piece.PieceTrick;

public class PieceTrickNullifyDefense extends PieceTrick {

    SpellParam<Entity> target;

    public PieceTrickNullifyDefense(Spell spell) {
        super(spell);
    }

    @Override
    public void initParams() {
        addParam(target = new ParamEntity(SpellParam.GENERIC_NAME_TARGET, SpellParam.YELLOW, false, false));
    }

    @Override
    public void addToMetadata(SpellMetadata meta) throws SpellCompilationException {
        super.addToMetadata(meta);

        meta.addStat(EnumSpellStat.POTENCY, 60);
        meta.addStat(EnumSpellStat.COST,  170);
    }

    @Override
    public Object execute(SpellContext context) throws SpellRuntimeException {
        Entity targetVal = this.getParamValue(context, target);

        context.verifyEntity(targetVal);
        if (targetVal instanceof LivingEntity) {
            targetVal.invulnerableTime = 0;
        }

        return null;
    }
}
