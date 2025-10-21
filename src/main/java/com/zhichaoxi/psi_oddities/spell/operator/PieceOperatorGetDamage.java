package com.zhichaoxi.psi_oddities.spell.operator;

import net.minecraft.world.entity.player.Inventory;
import vazkii.psi.api.spell.*;
import vazkii.psi.api.spell.piece.PieceOperator;

public class PieceOperatorGetDamage extends PieceOperator {

    public PieceOperatorGetDamage(Spell spell) {
        super(spell);
    }

    @Override
    public void addToMetadata(SpellMetadata meta) throws SpellCompilationException {
        meta.addStat(EnumSpellStat.COMPLEXITY, 2);
    }

    @Override
    public Object execute(SpellContext context) throws SpellRuntimeException {
        int slot = context.getTargetSlot();
        Inventory inventory = context.caster.getInventory();
        if (!inventory.getItem(slot).isEmpty())
            return inventory.getItem(slot).getDamageValue() * 1.0;
        return 0.0;
    }

    @Override
    public Class<?> getEvaluationType() {
        return Double.class;
    }
}
