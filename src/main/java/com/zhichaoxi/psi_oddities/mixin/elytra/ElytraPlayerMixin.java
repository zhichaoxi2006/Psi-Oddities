package com.zhichaoxi.psi_oddities.mixin.elytra;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.zhichaoxi.psi_oddities.util.PsiWingUtil;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Player.class)
public class ElytraPlayerMixin {
    @ModifyExpressionValue(
            method = "tryToStartFallFlying",
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
