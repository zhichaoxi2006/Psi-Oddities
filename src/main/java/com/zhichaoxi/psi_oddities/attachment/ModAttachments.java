package com.zhichaoxi.psi_oddities.attachment;

import com.zhichaoxi.psi_oddities.PsiOddities;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Stack;
import java.util.function.Supplier;

public class ModAttachments {
    public static final DeferredRegister<AttachmentType<?>> DR =
            DeferredRegister.create(
                    NeoForgeRegistries.Keys.ATTACHMENT_TYPES, PsiOddities.MODID);

    public static final Supplier<AttachmentType<PsiWingData>> PSI_WING =
            DR.register("psi_wing",
                        () -> AttachmentType.builder(PsiWingData::new)
                                .build());

    public static final Supplier<AttachmentType<Stack<Float>>>  EIDOS_CHANGELOG =
            DR.register("eidos_changelog",
                    () -> AttachmentType.builder(Stack<Float>::new)
                            .build());
}
