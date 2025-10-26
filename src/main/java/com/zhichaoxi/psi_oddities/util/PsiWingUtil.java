package com.zhichaoxi.psi_oddities.util;

import com.zhichaoxi.psi_oddities.attachment.ModAttachments;
import com.zhichaoxi.psi_oddities.attachment.PsiWingData;
import com.zhichaoxi.psi_oddities.network.MessageRegister;
import com.zhichaoxi.psi_oddities.network.message.MessagePsiWing;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public class PsiWingUtil {
    public static void enableWing(LivingEntity entity, int gracePeriod) {
        entity.setData(ModAttachments.PSI_WING,
                getPsiWingData(entity)
                        .setGracePeriod(gracePeriod)
                        .setEnabled(true));
        if (entity instanceof ServerPlayer player) {
            MessagePsiWing message = new MessagePsiWing(true);
            MessageRegister.sendToPlayer(player, message);
        }
    }

    public static void disableWing(LivingEntity entity) {
        entity.setData(ModAttachments.PSI_WING,
                getPsiWingData(entity)
                        .setEnabled(false));
        if (entity instanceof ServerPlayer player) {
            MessagePsiWing message = new MessagePsiWing(false);
            MessageRegister.sendToPlayer(player, message);
        }
    }

    public static boolean hasWing(LivingEntity entity) {
        return getPsiWingData(entity).isEnabled();
    }

    public static @NotNull PsiWingData getPsiWingData(LivingEntity entity) {
        return entity.getData(ModAttachments.PSI_WING);
    }
}
