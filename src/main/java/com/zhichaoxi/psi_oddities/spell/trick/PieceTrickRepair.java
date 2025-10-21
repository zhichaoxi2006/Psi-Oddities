package com.zhichaoxi.psi_oddities.spell.trick;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import vazkii.psi.api.cad.ISocketable;
import vazkii.psi.api.spell.*;
import vazkii.psi.api.spell.piece.PieceTrick;

public class PieceTrickRepair extends PieceTrick {

    public PieceTrickRepair(Spell spell) {
        super(spell);
    }

    @Override
    public void addToMetadata(SpellMetadata meta) throws SpellCompilationException {
        super.addToMetadata(meta);

        meta.addStat(EnumSpellStat.POTENCY, 3);
        meta.addStat(EnumSpellStat.COST, 150);
    }

    @Override
    public Object execute(SpellContext context) throws SpellRuntimeException {
        if (doRepair(context.tool)) {
            Inventory inventory = context.caster.getInventory();
            doRepair(inventory.getItem(context.getTargetSlot()));
        }
        return null;
    }

    private boolean doRepair(ItemStack stack) {
        if (stack.isEmpty())
            return true;
        Item item = stack.getItem();
        if (item.isRepairable(stack) && item.getDamage(stack) > 0 && ISocketable.isSocketable(stack)) {
            item.setDamage(stack, item.getDamage(stack) - 1);
            return false;
        }
        return true;
    }
}
