package com.zhichaoxi.psi_oddities.lib;

import com.zhichaoxi.psi_oddities.PsiOddities;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;

public class LibResources {

    public static final ResourceKey<DamageType> SPELL = ResourceKey.create(Registries.DAMAGE_TYPE,
            PsiOddities.location("spell"));

}
