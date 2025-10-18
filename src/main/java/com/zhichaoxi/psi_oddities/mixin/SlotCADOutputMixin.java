package com.zhichaoxi.psi_oddities.mixin;

import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.psi.api.PsiAPI;
import vazkii.psi.api.cad.ICAD;
import vazkii.psi.api.cad.ISocketable;
import vazkii.psi.common.block.tile.container.slot.SlotCADOutput;

@Mixin(SlotCADOutput.class)
public class SlotCADOutputMixin {
    @Inject(method = "mayPlace", at = @At("HEAD"), cancellable = true)
    private void mayPlace(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        boolean canPlace = false;
        if (stack.getItem() instanceof ICAD) {
            canPlace = true;
            ISocketable socketable = stack.getCapability(PsiAPI.SOCKETABLE_CAPABILITY);
            if (socketable != null) {
                for (int i = 0;i < socketable.getLastSlot();i++) {
                    ItemStack bullet = socketable.getBulletInSocket(i);
                    if (!bullet.isEmpty()) {
                        canPlace = false;
                        break;
                    }
                }
            }
        }
        cir.setReturnValue(canPlace);
    }
}
