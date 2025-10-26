package com.zhichaoxi.psi_oddities.mixin.elytra;


import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.zhichaoxi.psi_oddities.util.PsiWingUtil;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LocalPlayer.class)
public class ClientElytraMixin {

    @ModifyExpressionValue(
            method = "aiStep",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;canElytraFly(Lnet/minecraft/world/entity/LivingEntity;)Z",
                    remap = false
            )
    )
    public boolean elytraOverride(boolean original) {
        return original || PsiWingUtil.hasWing(((LivingEntity) ((Object) this)));
    }
}