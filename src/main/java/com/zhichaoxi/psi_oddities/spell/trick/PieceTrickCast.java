package com.zhichaoxi.psi_oddities.spell.trick;

import com.zhichaoxi.psi_oddities.util.ErrorUtil;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import vazkii.psi.api.PsiAPI;
import vazkii.psi.api.cad.ISocketable;
import vazkii.psi.api.spell.*;
import vazkii.psi.api.spell.param.ParamNumber;
import vazkii.psi.api.spell.piece.PieceTrick;
import vazkii.psi.common.core.handler.PlayerDataHandler;
import vazkii.psi.common.item.ItemCAD;

public class PieceTrickCast extends PieceTrick {
    private static final int STACK_SIZE = 512;

    SpellParam<Number> number;

    public PieceTrickCast(Spell spell) {
        super(spell);
        setStatLabel(EnumSpellStat.COMPLEXITY, new StatLabel(2));
        setStatLabel(EnumSpellStat.POTENCY, new StatLabel(SpellParam.GENERIC_NAME_NUMBER, true).mul(8));
    }

    @Override
    public void initParams() {
        addParam(number = new ParamNumber(SpellParam.GENERIC_NAME_NUMBER, SpellParam.RED, false, true));
    }

    @Override
    public void addToMetadata(SpellMetadata meta) throws SpellCompilationException {
        super.addToMetadata(meta);
        meta.addStat(EnumSpellStat.COMPLEXITY, 1);

        Double numberVal = this.<Double>getParamEvaluation(number);
        if(numberVal == null || numberVal <= 0 || numberVal != numberVal.intValue()) {
            throw new SpellCompilationException(SpellCompilationException.NON_POSITIVE_INTEGER, x, y);
        }
        meta.addStat(EnumSpellStat.POTENCY, numberVal.intValue() * 8);
    }

    @Override
    public Object execute(SpellContext context) throws SpellRuntimeException {
        Object object = context.customData.get("cast_depth");
        int cast_depth;
        if (object != null) {
            cast_depth = (int) object;
        } else {
            cast_depth = 0;
        }
        if (cast_depth > STACK_SIZE) {
            throw new SpellRuntimeException(ErrorUtil.STACK_OVERFLOW);
        }

        int slot = this.getParamValue(context, number).intValue() - 1;
        Player caster = context.caster;
        PlayerDataHandler.PlayerData data = PlayerDataHandler.get(caster);
        ItemStack cadStack = PsiAPI.getPlayerCAD(caster);
        ISocketable socket = cadStack.getCapability(PsiAPI.SOCKETABLE_CAPABILITY);
        if (socket != null) {
            ItemStack bullet = socket.getBulletInSocket(slot);
            boolean did = ItemCAD.cast(caster.level(), caster, data, bullet, cadStack, 0, 25, 0.5F,
                    ctx -> ctx.customData.put("cast_depth", cast_depth + 1) ).isPresent();
            if (!did) {
                throw new SpellRuntimeException(ErrorUtil.CAST_FAILED);
            }
        }

        return null;
    }
}
