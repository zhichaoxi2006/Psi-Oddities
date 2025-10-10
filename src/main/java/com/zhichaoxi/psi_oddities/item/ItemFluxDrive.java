package com.zhichaoxi.psi_oddities.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.energy.IEnergyStorage;
import org.jetbrains.annotations.NotNull;

public class ItemFluxDrive extends Item {
    public ItemFluxDrive(Properties properties) {
        super(properties.stacksTo(1));
    }

    @Override
    public boolean isBarVisible(@NotNull ItemStack stack) {
        return true;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        // 获取当前能量存储
        IEnergyStorage energy = stack.getCapability(Capabilities.EnergyStorage.ITEM);
        if (energy != null) {
            // 根据剩余能量比例计算显示宽度
            return (int) (13.0F * ((float) energy.getEnergyStored() / energy.getMaxEnergyStored()));
        }
        return 0;
    }

    @Override
    public int getBarColor(@NotNull ItemStack stack) {
        // 能量条颜色，这里使用绿色
        return 0x00FF00;
    }
}
