package com.zhichaoxi.psi_oddities.network;

import com.zhichaoxi.psi_oddities.PsiOddities;
import com.zhichaoxi.psi_oddities.network.message.MessagePsiWing;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import org.jetbrains.annotations.NotNull;

public class MessageRegister {
	public static final StreamCodec<RegistryFriendlyByteBuf, Vec3> VEC3 = new StreamCodec<>() {
		public @NotNull Vec3 decode(RegistryFriendlyByteBuf pBuffer) {
			return pBuffer.readVec3();
		}

		public void encode(RegistryFriendlyByteBuf pBuffer, @NotNull Vec3 pVec3) {
			pBuffer.writeVec3(pVec3);
		}
	};
	private static final String VERSION = "3";

	@SubscribeEvent
	public static void onRegisterPayloadHandler(RegisterPayloadHandlersEvent event) {
		final PayloadRegistrar registrar = event.registrar(PsiOddities.MODID)
				.versioned(VERSION)
				.optional();
		registrar.playBidirectional(MessagePsiWing.TYPE, MessagePsiWing.CODEC, MessagePsiWing::handle);

	}

	public static <MSG extends CustomPacketPayload> void sendToServer(MSG message) {
		PacketDistributor.sendToServer(message);
	}

	public static <MSG extends CustomPacketPayload> void sendToPlayer(ServerPlayer player, MSG message) {
		PacketDistributor.sendToPlayer(player, message);
	}

	public static <MSG extends CustomPacketPayload> void sendToPlayersTrackingEntity(Entity entity, MSG message) {
		PacketDistributor.sendToPlayersTrackingEntity(entity, message);
	}

	public static <MSG extends CustomPacketPayload> void sendToPlayersTrackingEntityAndSelf(Entity entity, MSG message) {
		PacketDistributor.sendToPlayersTrackingEntityAndSelf(entity, message);
	}

	public static <MSG extends CustomPacketPayload> void sendToPlayersInDimension(ServerLevel level, MSG message) {
		PacketDistributor.sendToPlayersInDimension(level, message);
	}
}
