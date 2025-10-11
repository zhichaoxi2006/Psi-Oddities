package com.zhichaoxi.psi_oddities.component;

import com.mojang.serialization.Codec;
import com.zhichaoxi.psi_oddities.PsiOddities;
import net.minecraft.core.UUIDUtil;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

public class ModComponents {
    public static final DeferredRegister<DataComponentType<?>> DR = DeferredRegister
            .create(Registries.DATA_COMPONENT_TYPE, PsiOddities.MODID);


    public static final DataComponentType<Integer> STORED_ENERGY = register("stored_energy",
            builder -> builder.persistent(Codec.INT).networkSynchronized(ByteBufCodecs.INT));

    public static final DataComponentType<List<UUID>> STORED_ENTITY = register("stored_entity",
            builder -> builder.persistent(UUIDUtil.CODEC.listOf()).networkSynchronized(UUIDUtil.STREAM_CODEC.apply(ByteBufCodecs.list())));

    private static <T> DataComponentType<T> register(String name, Consumer<DataComponentType.Builder<T>> customizer) {
        var builder = DataComponentType.<T>builder();
        customizer.accept(builder);
        var componentType = builder.build();
        DR.register(name, () -> componentType);
        return componentType;
    }
}
