package com.zhichaoxi.psi_oddities.mixin;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.ComponentItemHandler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import vazkii.psi.common.item.tool.ToolSocketable;

import java.util.ArrayList;
import java.util.List;

@Mixin(ToolSocketable.class)
public abstract class ToolSocketableMixin {

    @Shadow public abstract boolean isSocketSlotAvailable(int slot);

    @Shadow @Final private ComponentItemHandler toolHandler;

    @Shadow @Final protected int slots;

    /**
     * @author zhichaoxi2006
     * @reason fix #834
     */
    @Overwrite
    public ItemStack getBulletInSocket(int slot) {
        if(!isSocketSlotAvailable(slot)) {
            return ItemStack.EMPTY;
        }
        return toolHandler.getStackInSlot(slot);
    }

    /**
     * @author zhichaoxi2006
     * @reason fix #834
     */
    @Overwrite
    public void setBulletInSocket(int slot, ItemStack bullet) {
        if(isSocketSlotAvailable(slot)) {
            toolHandler.setStackInSlot(slot, bullet);
        }
    }

    /**
     * @author zhichaoxi2006
     * @reason fix $833
     */
    @Overwrite
    public List<Integer> getRadialMenuSlots() {
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < slots; i++) {
            list.add(i);
        }
        return list;
    }
}
