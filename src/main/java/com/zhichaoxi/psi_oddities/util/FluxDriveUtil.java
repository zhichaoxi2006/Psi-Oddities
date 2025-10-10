package com.zhichaoxi.psi_oddities.util;

import com.zhichaoxi.psi_oddities.item.ItemFluxDrive;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.energy.IEnergyStorage;

public class FluxDriveUtil {
    public static ItemStack findFluxDrive(Player player) {
        for (ItemStack stack : player.getInventory().items) {
            if (stack.getItem() instanceof ItemFluxDrive) {
                return stack;
            }
        }
        return ItemStack.EMPTY;
    }

    public static int extractEnergy(ItemStack stack, int amount, boolean simulate) {
        IEnergyStorage energyStorage = stack.getCapability(Capabilities.EnergyStorage.ITEM);
        if (energyStorage != null) {
            return energyStorage.extractEnergy(amount, simulate);
        }
        return 0;
    }

    public static int getMaxEnergyStored(ItemStack stack) {
        IEnergyStorage energyStorage = stack.getCapability(Capabilities.EnergyStorage.ITEM);
        if (energyStorage != null) {
            return energyStorage.getMaxEnergyStored();
        }
        return 0;
    }

    public static int getEnergyStored(ItemStack stack) {
        IEnergyStorage energyStorage = stack.getCapability(Capabilities.EnergyStorage.ITEM);
        if (energyStorage != null) {
            return energyStorage.getEnergyStored();
        }
        return 0;
    }
}
