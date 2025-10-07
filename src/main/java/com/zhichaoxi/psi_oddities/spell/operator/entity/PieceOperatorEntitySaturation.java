package com.zhichaoxi.psi_oddities.spell.operator.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import vazkii.psi.api.spell.Spell;
import vazkii.psi.api.spell.SpellContext;
import vazkii.psi.api.spell.SpellParam;
import vazkii.psi.api.spell.SpellRuntimeException;
import vazkii.psi.api.spell.param.ParamEntity;
import vazkii.psi.api.spell.piece.PieceOperator;

public class PieceOperatorEntitySaturation extends PieceOperator {

        SpellParam<Entity> target;

        public PieceOperatorEntitySaturation(Spell spell) {
            super(spell);
        }

        @Override
        public void initParams() {
            addParam(target = new ParamEntity(SpellParam.GENERIC_NAME_TARGET, SpellParam.YELLOW, false, false));
        }

        @Override
        public Class<?> getEvaluationType() {
            return Float.class;
        }

        @Override
        public Object execute(SpellContext context) throws SpellRuntimeException {
            Entity entity = this.getNotNullParamValue(context, target);
            if(!(entity instanceof Player)) {
                throw new SpellRuntimeException(SpellRuntimeException.NULL_TARGET);
            }

            return ((Player) entity).getFoodData().getSaturationLevel();
        }
}


