package com.zhichaoxi.psi_oddities.util;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import vazkii.psi.api.PsiAPI;
import vazkii.psi.api.cad.ICAD;
import vazkii.psi.common.core.handler.PlayerDataHandler;

public class PsiUtil {
    public static int getRealAvailablePsi(Player player) {
        int availablePsi = 0;
        ItemStack cad = PsiAPI.getPlayerCAD(player);
        ICAD cadItem = (ICAD) cad.getItem();
        PlayerDataHandler.PlayerData data = PlayerDataHandler.get(player);

        //Forge Energy
        ItemStack fluxDrive = FluxDriveUtil.findFluxDrive(player);
        availablePsi += FluxDriveUtil.extractEnergy(fluxDrive, Integer.MAX_VALUE, true);

        //Psi
        availablePsi += data.getAvailablePsi();
        availablePsi += cadItem.getStoredPsi(cad);
        return availablePsi;
    }

    public static int getExtraAvailablePsi(Player player) {
        int availablePsi = 0;

        //Forge Energy
        ItemStack fluxDrive = FluxDriveUtil.findFluxDrive(player);
        availablePsi += FluxDriveUtil.extractEnergy(fluxDrive, Integer.MAX_VALUE, true);

        return availablePsi;
    }

    public static int costExtraAvailablePsi(Player player, int cost) {
        int availablePsi = 0;

        //Forge Energy
        ItemStack fluxDrive = FluxDriveUtil.findFluxDrive(player);
        availablePsi += FluxDriveUtil.extractEnergy(fluxDrive, cost, false);

        return availablePsi;
    }
}
