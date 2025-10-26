package com.zhichaoxi.psi_oddities.spell.trick.entity;

import com.zhichaoxi.psi_oddities.util.PsiWingUtil;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import vazkii.psi.api.internal.Vector3;
import vazkii.psi.api.spell.*;
import vazkii.psi.api.spell.param.ParamEntity;
import vazkii.psi.api.spell.piece.PieceTrick;
import vazkii.psi.common.spell.trick.entity.PieceTrickAddMotion;

public class PieceTrickPsiWing extends PieceTrick {
    SpellParam<Entity> target;

    public PieceTrickPsiWing(Spell spell) {
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
        if (!context.isInRadius(targetVal)) {
            throw new SpellRuntimeException(SpellRuntimeException.OUTSIDE_RADIUS);
        }

        if (targetVal instanceof LivingEntity livingEntity) {
            PieceTrickAddMotion.addMotion(context, livingEntity, new Vector3(0, 1, 0), 5.0);
            PsiWingUtil.enableWing(livingEntity, 40);
        }

        return null;
    }
}
