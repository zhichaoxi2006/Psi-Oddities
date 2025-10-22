package com.zhichaoxi.psi_oddities.datagen;

import com.zhichaoxi.psi_oddities.PsiOddities;
import com.zhichaoxi.psi_oddities.lib.LibResources;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageType;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class PsiOdditiesDamageTypeTagsProvider extends TagsProvider<DamageType> {

	public PsiOdditiesDamageTypeTagsProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, ExistingFileHelper existingFileHelper) {
		super(pOutput, Registries.DAMAGE_TYPE, pLookupProvider, PsiOddities.MODID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.Provider pProvider) {
		this.tag(DamageTypeTags.BYPASSES_ARMOR).add(LibResources.SPELL);
		this.tag(DamageTypeTags.BYPASSES_SHIELD).add(LibResources.SPELL);
		this.tag(DamageTypeTags.BYPASSES_RESISTANCE).add(LibResources.SPELL);
		this.tag(DamageTypeTags.BYPASSES_EFFECTS).add(LibResources.SPELL);
		this.tag(DamageTypeTags.BYPASSES_ENCHANTMENTS).add(LibResources.SPELL);
        this.tag(DamageTypeTags.BYPASSES_COOLDOWN).add(LibResources.SPELL);
	}

	@Override
	public @NotNull String getName() {
		return "Psi damage type tags";
	}
}
