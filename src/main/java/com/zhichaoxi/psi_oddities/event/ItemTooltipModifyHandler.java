//package com.zhichaoxi.psi_oddities.event;
//
//import com.zhichaoxi.psi_oddities.PsiOddities;
//import com.zhichaoxi.psi_oddities.item.base.ModItems;
//import net.minecraft.ChatFormatting;
//import net.minecraft.network.chat.Component;
//import net.minecraft.world.item.ItemStack;
//import net.neoforged.api.distmarker.Dist;
//import net.neoforged.api.distmarker.OnlyIn;
//import net.neoforged.bus.api.SubscribeEvent;
//import net.neoforged.fml.common.EventBusSubscriber;
//import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
//
//@EventBusSubscriber(modid = PsiOddities.MODID)
//public class ItemTooltipModifyHandler {
//    @SubscribeEvent
//    @OnlyIn(Dist.CLIENT)
//    private static void modifyItemTooltip(ItemTooltipEvent event) {
//        ItemStack stack = event.getItemStack();
//        if (stack.getItem() == ModItems.cadBatteryTheDiracSanction) {
//            event.getToolTip().add(
//                    Component.translatable("item.psi_oddities.cad_battery_the_dirac_sanction.tooltip")
//                            .withStyle(ChatFormatting.GRAY)
//            );
//        }
//    }
//}
