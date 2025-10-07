package com.zhichaoxi.psi_oddities.datagen;

import com.zhichaoxi.psi_oddities.PsiOddities;
import com.zhichaoxi.psi_oddities.item.base.ModItems;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;
import vazkii.psi.common.lib.ModTags;

import java.util.concurrent.CompletableFuture;

public class PsiOdditiesRecipeGenerator extends RecipeProvider {
    public PsiOdditiesRecipeGenerator(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput consumer) {

        Criterion<InventoryChangeTrigger.TriggerInstance> hasNetherStar = has(Items.NETHER_STAR);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.cadBatteryFlux)
                .define('N', Items.NETHER_STAR)
                .define('I', vazkii.psi.common.item.base.ModItems.cadAssemblyPsimetal)
                .define('W', vazkii.psi.common.item.base.ModItems.ebonyPsimetal)
                .define('B', vazkii.psi.common.item.base.ModItems.ivoryPsimetal)
                .pattern(" W ")
                .pattern("INI")
                .pattern(" B ")
                .unlockedBy("has_nether_star", hasNetherStar)
                .save(consumer, PsiOddities.location("cad_battery_flux"));
    }

}
