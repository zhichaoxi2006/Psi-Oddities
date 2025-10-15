package com.zhichaoxi.psi_oddities.spell.param;

import net.minecraft.world.item.ItemStack;
import vazkii.psi.api.spell.Spell;
import vazkii.psi.api.spell.param.ParamSpecific;

public class ParamItemStack extends ParamSpecific<ItemStack> {

    public ParamItemStack(String name, int color, boolean canDisable, boolean constant) {
        super(name, color, canDisable, constant);
    }

    @Override
    public Class<ItemStack> getRequiredType() {
        return ItemStack.class;
    }

}
