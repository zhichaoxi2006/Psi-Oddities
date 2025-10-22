package com.zhichaoxi.psi_oddities.spell.selector.itemstack;

import com.zhichaoxi.psi_oddities.util.ErrorUtil;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import vazkii.psi.api.PsiAPI;
import vazkii.psi.api.cad.ISocketable;
import vazkii.psi.api.spell.*;
import vazkii.psi.api.spell.param.ParamNumber;
import vazkii.psi.api.spell.piece.PieceSelector;

public class PieceSelectorGetBullet extends PieceSelector {

    SpellParam<Number> index;

    public PieceSelectorGetBullet(Spell spell) {
        super(spell);
        setStatLabel(EnumSpellStat.POTENCY, new StatLabel(SpellParam.GENERIC_NAME_NUMBER, true).mul(8));
    }

    @Override
    public void initParams() {
        addParam(index = new ParamNumber(SpellParam.GENERIC_NAME_NUMBER, SpellParam.BLUE, false, true));
    }

    @Override
    public void addToMetadata(SpellMetadata meta) throws SpellCompilationException {
        super.addToMetadata(meta);

        Double numberVal = this.<Double>getParamEvaluation(index);
        if(numberVal == null || numberVal <= 0 || numberVal != numberVal.intValue()) {
            throw new SpellCompilationException(SpellCompilationException.NON_POSITIVE_INTEGER, x, y);
        }

        meta.addStat(EnumSpellStat.POTENCY, numberVal.intValue() * 8);
    }

    @Override
    public Object execute(SpellContext context) throws SpellRuntimeException {
        int n = this.getParamValue(context, index).intValue() - 1;

        ItemStack toolStack = context.tool;
        if (toolStack.isEmpty()) {
            Inventory inventory = context.caster.getInventory();
            toolStack = inventory.getItem(context.getTargetSlot());
        }

        ISocketable socketable = toolStack.getCapability(PsiAPI.SOCKETABLE_CAPABILITY);
        ItemStack bullet = ItemStack.EMPTY;
        if (socketable != null) {
            bullet = socketable.getBulletInSocket(n);
        }

        if (bullet == ItemStack.EMPTY) {
            throw new SpellRuntimeException(ErrorUtil.NULL_ITEM_STACK);
        }

        return bullet;
    }

    @Override
    public Class<?> getEvaluationType() {
        return ItemStack.class;
    }
}
