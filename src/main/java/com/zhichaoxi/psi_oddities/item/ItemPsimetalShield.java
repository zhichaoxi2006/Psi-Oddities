package com.zhichaoxi.psi_oddities.item;

import net.fabric_extras.shield_api.item.CustomShieldItem;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Tuple;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.capabilities.ICapabilityProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vazkii.psi.common.item.tool.IPsimetalTool;

import java.util.List;
import java.util.function.Supplier;

public class ItemPsimetalShield extends CustomShieldItem implements IPsimetalTool {
    public ItemPsimetalShield(@Nullable Holder<SoundEvent> equipSound,
                              Supplier<Ingredient> repairIngredientSupplier,
                              List<Tuple<Holder<Attribute>, AttributeModifier>> attributeModifierList,
                              Properties settings) {
        super(equipSound, repairIngredientSupplier, attributeModifierList, settings);
    }

    @Override
    public void setDamage(ItemStack stack, int damage) {
        if(damage > stack.getMaxDamage()) {
            damage = stack.getDamageValue();
        }
        super.setDamage(stack, damage);
    }

    @Override
    public void inventoryTick(@NotNull ItemStack stack, @NotNull Level worldIn, @NotNull Entity entityIn, int itemSlot, boolean isSelected) {
        IPsimetalTool.regen(stack, entityIn);
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return slotChanged;
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return IPsimetalTool.super.initCapabilities(stack, nbt);
    }
}
