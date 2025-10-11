package com.zhichaoxi.psi_oddities.mixin;

import com.zhichaoxi.psi_oddities.attribute.base.ModAttributes;
import com.zhichaoxi.psi_oddities.util.FluxDriveUtil;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.psi.common.core.handler.PlayerDataHandler;

import java.lang.ref.WeakReference;

@Mixin(PlayerDataHandler.PlayerData.class)
public abstract class PlayerDataMixin {
    @Shadow @Final public WeakReference<Player> playerWR;

    @Shadow public abstract void deductPsi(int psi, int cd, boolean sync, boolean shatter);

    @Shadow public int totalPsi;

    @Shadow public int regen;

    @Shadow public int availablePsi;

    @Shadow public abstract int getTotalPsi();

    @Inject(method = "deductPsi(IIZZ)V", at = @At("HEAD"), cancellable = true)
    private void deductPsi(int psi, int cd, boolean sync, boolean shatter, CallbackInfo ci) {
        Player player = playerWR.get();
        int cost = psi;
        if (player != null) {
            ItemStack fluxDrive = FluxDriveUtil.findFluxDrive(player);
            cost -= FluxDriveUtil.extractEnergy(fluxDrive, cost, false);
        }
        if (cost < psi) {
            deductPsi(cost, cd, sync, shatter);
            ci.cancel();
        }
    }

    @Inject(method = "getTotalPsi", at = @At("RETURN"), cancellable = true)
    private void getTotalPsi(CallbackInfoReturnable<Integer> cir) {
        Player player = playerWR.get();
        if (player != null) {
            int extraMaxPsi = 0;
            ItemStack fluxDrive = FluxDriveUtil.findFluxDrive(player);
            extraMaxPsi += FluxDriveUtil.getMaxEnergyStored(fluxDrive);
            int attrValue = (int) player.getAttributeValue(ModAttributes.MAX_PSI);
            cir.setReturnValue(totalPsi + attrValue + extraMaxPsi);
        }
    }

    @Inject(method = "getRegenPerTick", at = @At("RETURN"), cancellable = true)
    private void getRegenPerTick(CallbackInfoReturnable<Integer> cir) {
        Player player = playerWR.get();
        if (player != null) {
            int extraMaxPsi = 0;
            int attrValue = (int) player.getAttributeValue(ModAttributes.REGEN_PSI);
            ItemStack fluxDrive = FluxDriveUtil.findFluxDrive(player);
            extraMaxPsi += FluxDriveUtil.getMaxEnergyStored(fluxDrive);
            if (availablePsi >= getTotalPsi() - extraMaxPsi) {
                cir.setReturnValue(0);
            } else {
                cir.setReturnValue(regen + attrValue);
            }
        }
    }

    @Unique private boolean psiOddities$marked;
    @Unique private int psiOddities$lastExtraAvailablePsi;

    @Inject(method = "tick", at = @At("HEAD"))
    private void tick(CallbackInfo ci) {
        Player player = playerWR.get();
        if (player != null) {
            ItemStack fluxDrive = FluxDriveUtil.findFluxDrive(player);
            int extraMaxPsi = 0;
            extraMaxPsi += FluxDriveUtil.getMaxEnergyStored(fluxDrive);

            int extraAvailablePsi = 0;
            extraAvailablePsi += FluxDriveUtil.getEnergyStored(fluxDrive);;

            if (!psiOddities$marked) {
                availablePsi += extraAvailablePsi;
                psiOddities$marked = true;
            } else {
                availablePsi -= psiOddities$lastExtraAvailablePsi - extraAvailablePsi;
            }
            if (extraMaxPsi <= 0) {
                psiOddities$marked = false;
            }
            psiOddities$lastExtraAvailablePsi = extraAvailablePsi;
        }
    }
}
