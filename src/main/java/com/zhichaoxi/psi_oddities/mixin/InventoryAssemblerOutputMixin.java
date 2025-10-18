package com.zhichaoxi.psi_oddities.mixin;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.psi.api.cad.EnumCADComponent;
import vazkii.psi.api.cad.ICAD;
import vazkii.psi.common.block.tile.TileCADAssembler;
import vazkii.psi.common.block.tile.container.slot.InventoryAssemblerOutput;

@Mixin(InventoryAssemblerOutput.class)
public class InventoryAssemblerOutputMixin {
    @Shadow
    @Final
    private TileCADAssembler assembler;

    @Inject(method = "setItem", at = @At("HEAD"))
    private void setItem(int index, ItemStack stack, CallbackInfo ci) {
        Item item = stack.getItem();
        if(item instanceof ICAD cadItem) {
            EnumCADComponent[] enumCADComponents = EnumCADComponent.values();
            for (EnumCADComponent comp : enumCADComponents) {
                assembler.setStackForComponent(comp,
                        cadItem.getComponentInSlot(stack, comp));
            }
        }
    }
}
