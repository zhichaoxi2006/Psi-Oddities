package com.zhichaoxi.psi_oddities.item;

import com.zhichaoxi.psi_oddities.util.PsiArmorUtil;
import net.minecraft.world.item.ItemStack;
import vazkii.psi.common.item.ItemExosuitSensor;

public class ItemDyingExosuitSensor extends ItemExosuitSensor {
    public ItemDyingExosuitSensor(Properties properties) {
        super(properties);
    }

    @Override
    public String getEventType(ItemStack stack) {
        return PsiArmorUtil.ON_DYING;
    }
}
