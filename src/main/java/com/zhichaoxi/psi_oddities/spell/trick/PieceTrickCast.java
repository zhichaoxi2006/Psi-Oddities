package com.zhichaoxi.psi_oddities.spell.trick;

import com.zhichaoxi.psi_oddities.spell.param.ParamItemStack;
import com.zhichaoxi.psi_oddities.spell.param.SpellParams;
import com.zhichaoxi.psi_oddities.util.ErrorUtil;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import vazkii.psi.api.PsiAPI;
import vazkii.psi.api.spell.*;
import vazkii.psi.api.spell.piece.PieceTrick;
import vazkii.psi.common.core.handler.PlayerDataHandler;
import vazkii.psi.common.item.ItemCAD;

public class PieceTrickCast extends PieceTrick {

    SpellParam<ItemStack> stack;

    public PieceTrickCast(Spell spell) {
        super(spell);
        setStatLabel(EnumSpellStat.COMPLEXITY, new StatLabel(2));
        setStatLabel(EnumSpellStat.POTENCY, new StatLabel(SpellParams.GENERIC_NAME_ITEM_STACK, true).mul(8));
    }

    @Override
    public void initParams() {
        addParam(stack = new ParamItemStack(SpellParams.GENERIC_NAME_ITEM_STACK, SpellParam.RED, false, false));
    }

    @Override
    public void addToMetadata(SpellMetadata meta) throws SpellCompilationException {
        super.addToMetadata(meta);
        meta.addStat(EnumSpellStat.COMPLEXITY, 1);
        meta.addStat(EnumSpellStat.POTENCY, 8);
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

        Player caster = context.caster;
        PlayerDataHandler.PlayerData data = PlayerDataHandler.get(caster);
        ItemStack cadStack = PsiAPI.getPlayerCAD(caster);
        ItemStack bullet = this.getParamValue(context, stack);

        ISpellAcceptor spellAcceptor = bullet.getCapability(PsiAPI.SPELL_ACCEPTOR_CAPABILITY);

        if (spellAcceptor == null) {
            throw new SpellRuntimeException(ErrorUtil.INVAILD_SPELL_ACCEPTOR);
        }

        boolean did;
        try {
            did = ItemCAD.cast(caster.level(), caster, data, bullet, cadStack, 0, 0, 0.0F,
                    ctx -> ctx.customData.put("cast_depth", cast_depth + 1) ).isPresent();
        } catch (StackOverflowError error) {
            throw new SpellRuntimeException(ErrorUtil.STACK_OVERFLOW);
        }

        if (!did) {
            throw new SpellRuntimeException(ErrorUtil.CAST_FAILED);
        }

        return null;
    }
}
