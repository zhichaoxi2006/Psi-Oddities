/*
 * This class is distributed as part of the Psi Mod.
 * Get the Source Code in GitHub:
 * https://github.com/Vazkii/Psi
 *
 * Psi is Open Source and distributed under the
 * Psi License: https://psi.vazkii.net/license.php
 */
package com.zhichaoxi.psi_oddities.network.message;

import com.zhichaoxi.psi_oddities.PsiOddities;
import com.zhichaoxi.psi_oddities.attachment.ModAttachments;
import com.zhichaoxi.psi_oddities.attachment.PsiWingData;
import com.zhichaoxi.psi_oddities.util.PsiWingUtil;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;
import vazkii.psi.common.Psi;

public record MessagePsiWing(boolean enable) implements CustomPacketPayload {

	public static final ResourceLocation ID = PsiOddities.location("message_psi_wing");
	public static final Type<MessagePsiWing> TYPE = new Type<>(ID);

	public static final StreamCodec<RegistryFriendlyByteBuf, MessagePsiWing> CODEC = StreamCodec.composite(
			ByteBufCodecs.BOOL, MessagePsiWing::enable,
			MessagePsiWing::new);

	@Override
	public @NotNull Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

	public void handle(IPayloadContext ctx) {
		ctx.enqueueWork(() -> {
			Player player = Psi.proxy.getClientPlayer();
			if(player != null) {
                PsiWingData data = PsiWingUtil.getPsiWingData(player);
                player.setData(ModAttachments.PSI_WING, data.setEnabled(enable));
			}
		});
	}

}
