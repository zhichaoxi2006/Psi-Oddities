package com.zhichaoxi.psi_oddities.capablity;

import com.zhichaoxi.psi_oddities.PsiOddities;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

@EventBusSubscriber(modid = PsiOddities.MODID)
public class ModCapablities {
    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {}
}
