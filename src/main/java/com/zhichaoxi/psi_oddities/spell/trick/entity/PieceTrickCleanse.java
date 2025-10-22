package com.zhichaoxi.psi_oddities.spell.trick.entity;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import vazkii.psi.api.spell.*;
import vazkii.psi.api.spell.param.ParamEntity;
import vazkii.psi.api.spell.piece.PieceTrick;

import java.util.Collection;

public class PieceTrickCleanse extends PieceTrick {
    SpellParam<Entity> target;

    public PieceTrickCleanse(Spell spell) {
        super(spell);
    }

    @Override
    public void initParams() {
        addParam(target = new ParamEntity(SpellParam.GENERIC_NAME_TARGET, SpellParam.YELLOW, false, false));
    }

    @Override
    public void addToMetadata(SpellMetadata meta) throws SpellCompilationException {
        super.addToMetadata(meta);

        meta.addStat(EnumSpellStat.POTENCY, 30);
        meta.addStat(EnumSpellStat.COST,  50);
    }

    @Override
    public Object execute(SpellContext context) throws SpellRuntimeException {
        Entity targetVal = this.getParamValue(context, target);

        context.verifyEntity(targetVal);
        if (!context.isInRadius(targetVal)) {
            throw new SpellRuntimeException(SpellRuntimeException.OUTSIDE_RADIUS);
        }
        if (targetVal instanceof LivingEntity entity) {
            Collection<MobEffectInstance> effects = entity.getActiveEffects();
            MobEffectInstance[] array = effects.toArray(new MobEffectInstance[0]);
            for (MobEffectInstance e : array) {
                MobEffectCategory category = e.getEffect().value().getCategory();
                if (category == MobEffectCategory.HARMFUL) {
                    entity.removeEffect(e.getEffect());
                }
            }
        }

        return null;
    }
}
