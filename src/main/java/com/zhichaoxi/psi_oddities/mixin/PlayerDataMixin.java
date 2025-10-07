package com.zhichaoxi.psi_oddities.mixin;

import com.zhichaoxi.psi_oddities.item.base.ModItems;
import com.zhichaoxi.psi_oddities.util.BatteryFluxUtil;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.energy.IEnergyStorage;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.psi.api.cad.EnumCADComponent;
import vazkii.psi.api.cad.ICAD;
import vazkii.psi.common.core.handler.PlayerDataHandler;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

@Mixin(PlayerDataHandler.PlayerData.class)
public abstract class PlayerDataMixin {
    @Shadow @Final public WeakReference<Player> playerWR;

    @Shadow public abstract void deductPsi(int psi, int cd, boolean sync, boolean shatter);

    @Shadow public abstract ItemStack getCAD();

    @Inject(method = "deductPsi(IIZZ)V", at = @At("HEAD"), cancellable = true)
    private void deductPsi(int psi, int cd, boolean sync, boolean shatter, CallbackInfo ci) {
        ItemStack cad = getCAD();
        if (!cad.isEmpty()) {
            ICAD cadItem = (ICAD) cad.getItem();
            ItemStack battery = cadItem.getComponentInSlot(cad, EnumCADComponent.BATTERY);
            if (!battery.isEmpty() && battery.getItem() == ModItems.cadBatteryFlux) {
                Player player = playerWR.get();
                int cost = psi;
                ArrayList<IEnergyStorage> energyStorages;
                if (player != null) {
                    energyStorages = BatteryFluxUtil.findEnergyStorage(player);
                    cost -= BatteryFluxUtil.extractEnergy(energyStorages, cost, false);
                }
                if (cost < psi) {
                    deductPsi(cost, cd, sync, shatter);
                    ci.cancel();
                }
            }
        }
    }
}
