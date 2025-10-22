package com.zhichaoxi.psi_oddities.spell.operator.spell;


import com.zhichaoxi.psi_oddities.spell.base.SpellParams;
import com.zhichaoxi.psi_oddities.spell.param.ParamSpell;
import net.minecraft.world.item.ItemStack;
import vazkii.psi.api.spell.*;
import vazkii.psi.api.spell.param.ParamNumber;
import vazkii.psi.api.spell.piece.PieceOperator;
import vazkii.psi.common.item.ItemSpellDrive;
import vazkii.psi.common.item.base.ModItems;

public class PieceOperatorSpellBulletVirtualization extends PieceOperator {

    SpellParam<Spell> spell;

    public PieceOperatorSpellBulletVirtualization(Spell spell) {
        super(spell);
    }

    @Override
    public void initParams() {
        addParam(spell = new ParamSpell(SpellParams.GENERIC_NAME_SPELL, SpellParam.BLUE, false, false));
    }

    @Override
    public void addToMetadata(SpellMetadata meta) throws SpellCompilationException {
        meta.addStat(EnumSpellStat.COMPLEXITY, 2);
    }

    @Override
    public Object execute(SpellContext context) throws SpellRuntimeException {
        Spell spellVal = this.getParamValue(context, spell);

        ItemStack bullet = new ItemStack(ModItems.spellBullet);
        ItemSpellDrive.setSpell(bullet, spellVal);
        return bullet;
    }

    @Override
    public Class<?> getEvaluationType() {
        return ItemStack.class;
    }
}
