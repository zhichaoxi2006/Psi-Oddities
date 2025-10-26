package com.zhichaoxi.psi_oddities.item;

import dev.kosmx.playerAnim.mixin.ElytraLayerMixin;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import vazkii.psi.api.cad.ISocketable;
import vazkii.psi.api.spell.ISpellAcceptor;
import vazkii.psi.common.block.BlockProgrammer;
import vazkii.psi.common.block.base.ModBlocks;
import vazkii.psi.common.core.handler.PlayerDataHandler;
import vazkii.psi.common.item.ItemCAD;
import vazkii.psi.common.item.base.ModDataComponents;

import javax.annotation.Nullable;
import java.util.List;

public class ItemInlineCaster extends Item {
    public ItemInlineCaster(Properties properties) {
        super(
                properties
                        .stacksTo(1)
                        .component(ModDataComponents.BULLETS.get(), ItemContainerContents.EMPTY)
        );
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand usedHand) {
        ItemStack held = player.getItemInHand(usedHand);
        PlayerDataHandler.PlayerData data = PlayerDataHandler.get(player);
        ItemStack cad = data.getCAD();

        if(cad.isEmpty()) {
            return new InteractionResultHolder<>(InteractionResult.PASS, held);
        }

        ISocketable sockets = ISocketable.socketable(held);
        ItemStack bullet = sockets.getSelectedBullet();

        if (!ISpellAcceptor.hasSpell(bullet)) {
            return new InteractionResultHolder<>(InteractionResult.PASS, held);
        }

        boolean casted = ItemCAD.cast(level, player, data, bullet, cad, 100, 25, 0.5f,
                ctx -> {
            ctx.castFrom = usedHand;
            ctx.tool = held;
        }).isPresent();

        return new InteractionResultHolder<>(casted ? InteractionResult.CONSUME : InteractionResult.PASS, held);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext ctx) {
        Level worldIn = ctx.getLevel();
        InteractionHand hand = ctx.getHand();
        BlockPos pos = ctx.getClickedPos();
        Player playerIn = ctx.getPlayer();
        if(playerIn == null) {
            return InteractionResult.FAIL;
        }
        ItemStack stack = playerIn.getItemInHand(hand);
        Block block = worldIn.getBlockState(pos).getBlock();
        return block == ModBlocks.programmer.get() ? ((BlockProgrammer) block).setSpell(worldIn, pos, playerIn, stack) : InteractionResult.PASS;
    }

    @Override
    public @NotNull InteractionResult interactLivingEntity(@NotNull ItemStack stack, @NotNull Player player, @NotNull LivingEntity interactionTarget, @NotNull InteractionHand usedHand) {
        if(player.isSecondaryUseActive()) {
            return this.use(player.level(), player, usedHand).getResult();
        }

        return super.interactLivingEntity(stack, player, interactionTarget, usedHand);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable TooltipContext context, List<Component> tooltip, @NotNull TooltipFlag advanced) {
        Component componentName = ISocketable.getSocketedItemName(stack, "psimisc.none");
        tooltip.add(Component.translatable("psimisc.spell_selected", componentName));
    }
}
