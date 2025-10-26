package com.zhichaoxi.psi_oddities.attachment;

import com.mojang.serialization.Codec;
import com.zhichaoxi.psi_oddities.PsiOddities;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModAttachments {
    public static final DeferredRegister<AttachmentType<?>> DR =
            DeferredRegister.create(
                    NeoForgeRegistries.Keys.ATTACHMENT_TYPES, PsiOddities.MODID);

    public static final Supplier<AttachmentType<PsiWingData>> PSI_WING =
            DR.register("psi_wing",
                        () -> AttachmentType.builder(PsiWingData::new)
                                .build());
}
