package com.zhichaoxi.psi_oddities.mixin;

import com.zhichaoxi.psi_oddities.core.handler.ConfigHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import vazkii.psi.api.spell.SpellContext;

@Mixin(SpellContext.class)
public class SpellContextMixin {
    @ModifyConstant(method = "isInRadius(DDD)Z", constant = @Constant(doubleValue = 32.0))
    private double isInRadius(double constant) {
        double number = ConfigHandler.COMMON.maxCastRadius.getAsDouble();
        if (number == -1) {
            return Double.MAX_VALUE;
        }
        return number;
    }
}
