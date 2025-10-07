package com.zhichaoxi.psi_oddities.mixin;

import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.psi.api.spell.ISpellImmune;

@Mixin(ISpellImmune.class)
public interface ISpellImmuneMixin {
    @Inject(method = "isImmune(Lnet/minecraft/world/entity/Entity;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;canUsePortal(Z)Z"), cancellable = true)
    private static void isImmune$canUsePortal(Entity e, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }
}
