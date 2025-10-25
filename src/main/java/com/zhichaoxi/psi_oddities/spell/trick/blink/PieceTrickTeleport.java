package com.zhichaoxi.psi_oddities.spell.trick.blink;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.portal.DimensionTransition;
import vazkii.psi.api.internal.Vector3;
import vazkii.psi.api.spell.*;
import vazkii.psi.api.spell.param.ParamEntity;
import vazkii.psi.api.spell.param.ParamVector;
import vazkii.psi.api.spell.piece.PieceTrick;
import vazkii.psi.common.network.MessageRegister;
import vazkii.psi.common.network.message.MessageBlink;

public class PieceTrickTeleport extends PieceTrick {

    SpellParam<Vector3> vector;
    SpellParam<Entity> target;

    public PieceTrickTeleport(Spell spell) {
        super(spell);
    }

    @Override
    public void initParams() {
        addParam(vector = new ParamVector(SpellParam.GENERIC_NAME_VECTOR, SpellParam.GREEN, false, false));
        addParam(target = new ParamEntity(SpellParam.GENERIC_NAME_TARGET, SpellParam.RED, false, false));
    }

    @Override
    public void addToMetadata(SpellMetadata meta) throws SpellCompilationException {
        super.addToMetadata(meta);
        meta.addStat(EnumSpellStat.POTENCY, 300);
        meta.addStat(EnumSpellStat.COST, 3000);
    }

    @Override
    public Object execute(SpellContext context) throws SpellRuntimeException {
        Vector3 offsetVal = this.getParamValue(context, vector);
        Entity targetVal = this.getParamValue(context, target);
        targetVal.setPos(targetVal.getX() + offsetVal.x, targetVal.getY() + offsetVal.y,
                targetVal.getZ() + offsetVal.z);
        if(targetVal instanceof ServerPlayer) {
            MessageRegister.sendToPlayer((ServerPlayer) targetVal,
                    new MessageBlink(offsetVal.x, offsetVal.y, offsetVal.z));
        }
        return null;
    }

}
