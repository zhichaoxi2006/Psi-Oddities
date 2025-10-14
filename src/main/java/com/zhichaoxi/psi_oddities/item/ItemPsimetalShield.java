package com.zhichaoxi.psi_oddities.item;

import com.zhichaoxi.psi_oddities.PsiOddities;
import net.fabric_extras.shield_api.item.CustomShieldItem;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Tuple;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingShieldBlockEvent;
import org.jetbrains.annotations.Nullable;
import vazkii.psi.api.PsiAPI;
import vazkii.psi.api.cad.ISocketable;
import vazkii.psi.api.spell.SpellContext;
import vazkii.psi.common.core.handler.PlayerDataHandler;
import vazkii.psi.common.item.ItemCAD;
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
}
