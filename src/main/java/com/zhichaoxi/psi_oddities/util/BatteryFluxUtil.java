package com.zhichaoxi.psi_oddities.util;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.energy.IEnergyStorage;

import java.util.ArrayList;

public class BatteryFluxUtil {
    public static ArrayList<IEnergyStorage> findEnergyStorage(Player player) {
        ArrayList<IEnergyStorage> result =  new ArrayList<>();
        for (ItemStack stack : player.getInventory().items) {
            IEnergyStorage energyStorage = stack.getCapability(Capabilities.EnergyStorage.ITEM);
            if (energyStorage != null) {
                result.add(energyStorage);
            }
        }
        return result;
    }

    public static int extractEnergy(ArrayList<IEnergyStorage> energyStorages, int number, boolean sim) {
        int total_extracted = 0;
        for (IEnergyStorage energyStorage : energyStorages) {
            int extracted = energyStorage.extractEnergy(number, sim);
            total_extracted += extracted;
        }
        return total_extracted;
    }
}
