package com.zhichaoxi.psi_oddities.mixin;

import com.zhichaoxi.psi_oddities.attribute.base.ModAttributes;
import com.zhichaoxi.psi_oddities.util.FluxDriveUtil;
import com.zhichaoxi.psi_oddities.util.PsiUtil;
import net.minecraft.core.Registry;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.psi.api.cad.ICAD;
import vazkii.psi.common.core.handler.PlayerDataHandler;
import vazkii.psi.common.lib.LibResources;
import vazkii.psi.common.network.MessageRegister;
import vazkii.psi.common.network.message.MessageDeductPsi;

import java.lang.ref.WeakReference;

@Mixin(PlayerDataHandler.PlayerData.class)
public abstract class PlayerDataMixin {
    @Shadow @Final public WeakReference<Player> playerWR;

    @Final
    @Shadow public int totalPsi;

    @Final
    @Shadow public int regen;

    @Shadow public int availablePsi;

    @Shadow public abstract int getTotalPsi();

    @Shadow public abstract ItemStack getCAD();

    @Shadow public int regenCooldown;

    @Shadow public abstract void save();

    @Shadow public boolean loopcasting;

    @Shadow @Final private boolean client;

    @Shadow public boolean overflowed;

    /**
     * @author zhichaoxi2006
     * @reason add extra Psi logic
     */
    @Overwrite
    public void deductPsi(int psi, int cd, boolean sync, boolean shatter) {
        int currentPsi = availablePsi;

        Player player = playerWR.get();
        if(player == null) {
            return;
        }

        ItemStack cadStack = getCAD();

        if(!cadStack.isEmpty()) {
            ICAD cad = (ICAD) cadStack.getItem();
            int storedPsi = cad.getStoredPsi(cadStack);
            if(storedPsi == -1) {
                return;
            }
        }

        int amount = 0;
        amount += PsiUtil.costExtraAvailablePsi(player, psi);

        psi -= amount;

        availablePsi -= psi;

        if(regenCooldown < cd) {
            regenCooldown = cd;
        }

        if(availablePsi < 0) {
            int overflow = -availablePsi;
            availablePsi = 0;

            if(!cadStack.isEmpty()) {
                ICAD cad = (ICAD) cadStack.getItem();
                overflow = cad.consumePsi(cadStack, overflow);
            }

            if(!shatter && overflow > 0) {
                float dmg = (float) overflow / (loopcasting ? 50 : 125);
                if(!client) {
                    Registry<DamageType> types = player.damageSources().damageTypes;
                    DamageSource overloadSource = new DamageSource(types.getHolderOrThrow(LibResources.PSI_OVERLOAD));
                    player.hurt(overloadSource, dmg);
                }
                overflowed = true;
            }
        }

        if(sync && player instanceof ServerPlayer) {
            MessageDeductPsi message = new MessageDeductPsi(currentPsi, availablePsi, regenCooldown, shatter);
            MessageRegister.sendToPlayer((ServerPlayer) player, message);
        }

        save();
    }

    @Inject(method = "getTotalPsi", at = @At("RETURN"), cancellable = true)
    private void getTotalPsi(CallbackInfoReturnable<Integer> cir) {
        int totalPsi = cir.getReturnValueI();
        Player player = playerWR.get();
        if (player != null) {
            int attrValue = (int) player.getAttributeValue(ModAttributes.MAX_PSI);
            cir.setReturnValue(totalPsi + attrValue);
        }
    }

    @Inject(method = "getRegenPerTick", at = @At("RETURN"), cancellable = true)
    private void getRegenPerTick(CallbackInfoReturnable<Integer> cir) {
        int regen = cir.getReturnValueI();
        Player player = playerWR.get();
        if (player != null) {
            int attrValue = (int) player.getAttributeValue(ModAttributes.REGEN_PSI);
            cir.setReturnValue(regen + attrValue);
        }
    }
}
