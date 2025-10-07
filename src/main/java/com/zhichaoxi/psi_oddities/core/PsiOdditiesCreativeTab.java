package com.zhichaoxi.psi_oddities.core;


import com.zhichaoxi.psi_oddities.PsiOddities;
import com.zhichaoxi.psi_oddities.item.base.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.RegisterEvent;

@EventBusSubscriber(modid = PsiOddities.MODID)
public class PsiOdditiesCreativeTab {
    public static final ResourceKey<CreativeModeTab> PSI_ODDITIES_CREATIVE_TAB =
            ResourceKey.create(Registries.CREATIVE_MODE_TAB, PsiOddities.location("creative_tab"));

    @SubscribeEvent
    public static void register(RegisterEvent evt) {
        evt.register(Registries.CREATIVE_MODE_TAB, creativeModeTabRegisterHelper -> {
            CreativeModeTab psiCreativeTab = CreativeModeTab.builder().title(Component.translatable("itemGroup.psi_oddities"))
                    .icon(() -> new ItemStack(ModItems.cadBatteryFlux))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.cadBatteryFlux);
                    })
                    .build();
            creativeModeTabRegisterHelper.register(PSI_ODDITIES_CREATIVE_TAB, psiCreativeTab);
        });
    }
}
