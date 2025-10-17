package com.zhichaoxi.psi_oddities.datagen;

import com.zhichaoxi.psi_oddities.PsiOddities;
import com.zhichaoxi.psi_oddities.item.base.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class PsiOdditiesItemTagProvider extends ItemTagsProvider {
    public PsiOdditiesItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagsProvider.TagLookup<Block>> pBlockTags,  ExistingFileHelper existingFileHelper) {
        super(output, pLookupProvider, pBlockTags, PsiOddities.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(ItemTags.DURABILITY_ENCHANTABLE).add(ModItems.psimetalShield);
        tag(Tags.Items.ENCHANTABLES).add(ModItems.psimetalShield);
        tag(Tags.Items.TOOLS_SHIELD).add(ModItems.psimetalShield);
    }
}
