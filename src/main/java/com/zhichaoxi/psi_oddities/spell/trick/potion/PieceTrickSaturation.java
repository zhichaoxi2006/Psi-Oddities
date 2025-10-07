package com.zhichaoxi.psi_oddities.spell.trick.potion;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import vazkii.psi.api.spell.*;
import vazkii.psi.common.spell.trick.potion.PieceTrickPotionBase;

public class PieceTrickSaturation extends PieceTrickPotionBase {

    public PieceTrickSaturation(Spell spell) {
        super(spell);
        setStatLabel(EnumSpellStat.POTENCY, new StatLabel(SpellParam.GENERIC_NAME_POWER, true)
                .cube().mul(SpellParam.GENERIC_NAME_TIME, true)
                .mul(5));
    }

    @Override
    public Holder<MobEffect> getPotion() {
        return MobEffects.SATURATION;
    }

    @Override
    public int getPotency(int power, int time) throws SpellCompilationException {
        return (int) multiplySafe(power, power, power, time, 5);
    }

}
