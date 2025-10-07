package com.zhichaoxi.psi_oddities.spell.trick.blink;

import com.zhichaoxi.psi_oddities.util.ParamHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import vazkii.psi.api.internal.Vector3;
import vazkii.psi.api.spell.EnumSpellStat;
import vazkii.psi.api.spell.Spell;
import vazkii.psi.api.spell.SpellCompilationException;
import vazkii.psi.api.spell.SpellContext;
import vazkii.psi.api.spell.SpellMetadata;
import vazkii.psi.api.spell.SpellParam;
import vazkii.psi.api.spell.SpellRuntimeException;
import vazkii.psi.api.spell.param.ParamNumber;
import vazkii.psi.api.spell.param.ParamVector;
import vazkii.psi.api.spell.piece.PieceTrick;
import vazkii.psi.common.network.MessageRegister;
import vazkii.psi.common.network.message.MessageBlink;

public class PieceTrickCasterBlink extends PieceTrick {

    SpellParam<Vector3> direction;
    SpellParam<Number> distance;

    public PieceTrickCasterBlink(Spell spell) {
        super(spell);
    }

    @Override
    public void initParams() {
        addParam(direction = new ParamVector(SpellParam.GENERIC_NAME_DIRECTION, SpellParam.GREEN, false, false));
        addParam(distance = new ParamNumber(SpellParam.GENERIC_NAME_DISTANCE, SpellParam.RED, false, true));
    }

    @Override
    public void addToMetadata(SpellMetadata meta) throws SpellCompilationException {
        super.addToMetadata(meta);
        double maxDistance = ParamHelper.positive(this, distance);
        meta.addStat(EnumSpellStat.POTENCY, (int) (maxDistance * 30));
        meta.addStat(EnumSpellStat.COST, (int) (maxDistance * 40));
    }

    @Override
    public Object execute(SpellContext context) throws SpellRuntimeException {
        double distanceVal = getNotNullParamValue(context, distance).doubleValue();
        Vector3 directionVal = ParamHelper.nonNull(this, context, direction).copy().normalize().multiply(distanceVal);
        Player caster = context.caster;
        caster.setPos(caster.getX() + directionVal.x, caster.getY() + directionVal.y,
                caster.getZ() + directionVal.z);
        if(caster instanceof ServerPlayer) {
            MessageRegister.sendToPlayer((ServerPlayer) caster,
                    new MessageBlink(directionVal.x, directionVal.y, directionVal.z));
        }
        return null;
    }

}