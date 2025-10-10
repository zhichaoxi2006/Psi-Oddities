package com.zhichaoxi.psi_oddities.mixin;

import com.zhichaoxi.psi_oddities.attribute.base.ModAttributes;
import com.zhichaoxi.psi_oddities.util.FluxDriveUtil;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.energy.IEnergyStorage;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.psi.common.core.handler.PlayerDataHandler;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

@Mixin(PlayerDataHandler.PlayerData.class)
public abstract class PlayerDataMixin {
    @Shadow @Final public WeakReference<Player> playerWR;

    @Shadow public abstract void deductPsi(int psi, int cd, boolean sync, boolean shatter);

    @Shadow public abstract ItemStack getCAD();

    @Shadow public int totalPsi;

    @Shadow public int regen;

    @Shadow public int availablePsi;

    @Inject(method = "deductPsi(IIZZ)V", at = @At("HEAD"), cancellable = true)
    private void deductPsi(int psi, int cd, boolean sync, boolean shatter, CallbackInfo ci) {
        Player player = playerWR.get();
        int cost = psi;
        ArrayList<IEnergyStorage> energyStorages;
        if (player != null) {
            ItemStack fluxDrive = FluxDriveUtil.findFluxDrive(player);
            cost -= FluxDriveUtil.extractEnergy(fluxDrive, cost, false);
        }
        if (cost < psi) {
            deductPsi(cost, cd, sync, shatter);
            ci.cancel();
        }
    }

    @Unique private boolean psiOddities$marked;
    @Unique private int psiOddities$lastStored;

    @Inject(method = "tick", at = @At("HEAD"))
    private void tick(CallbackInfo ci) {
        Player player = playerWR.get();
        if (player != null) {
            int attrValue = (int) player.getAttributeValue(ModAttributes.MAX_PSI);
            ItemStack fluxDrive = FluxDriveUtil.findFluxDrive(player);
            int maxValue = FluxDriveUtil.getMaxEnergyStored(fluxDrive);
            int stored = FluxDriveUtil.getEnergyStored(fluxDrive);
            totalPsi = attrValue + maxValue + 5000;
            if (availablePsi >= totalPsi - maxValue) {
                regen = 0;
            } else {
                regen = 25;
            }
            if (!psiOddities$marked) {
                availablePsi += stored;
                psiOddities$marked = true;
            } else {
                availablePsi -= psiOddities$lastStored - stored;
            }
            if (maxValue <= 0) {
                psiOddities$marked = false;
            }
            psiOddities$lastStored = stored;
        }
    }
}
