package com.zhichaoxi.psi_oddities.mixin;

import com.zhichaoxi.psi_oddities.util.FluxDriveUtil;
import com.zhichaoxi.psi_oddities.util.NumberFormatter;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.psi.api.PsiAPI;
import vazkii.psi.client.core.handler.HUDHandler;
import vazkii.psi.common.core.handler.ConfigHandler;

@Mixin(HUDHandler.class)
public class HUDHandlerMixin {
    @Inject(method = "drawPsiBar", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/client/gui/GuiGraphics;drawString(Lnet/minecraft/client/gui/Font;Ljava/lang/String;IIIZ)I", ordinal = 1))
    private static void drawPsiBar(GuiGraphics graphics, DeltaTracker deltatracker, CallbackInfo ci) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        ItemStack fluxDrive = null;
        int extraAvailablePsi = 0;
        if (player != null) {
            fluxDrive = FluxDriveUtil.findFluxDrive(player);
            extraAvailablePsi += FluxDriveUtil.getEnergyStored(fluxDrive);
        }


        int pad = 3;
        int width = 32;
        boolean right = ConfigHandler.CLIENT.psiBarOnRight.get();

        int x = -pad;
        if(right) {
            x = graphics.guiWidth() + pad - width;
        }

        x += 8;

        String s3 = NumberFormatter.formatNumber(extraAvailablePsi);
        int offStr3 = mc.font.width(s3) - 19;
        graphics.drawString(mc.font, s3,  x - offStr3, -130, 0xFFFFFF, true);
    }
}
