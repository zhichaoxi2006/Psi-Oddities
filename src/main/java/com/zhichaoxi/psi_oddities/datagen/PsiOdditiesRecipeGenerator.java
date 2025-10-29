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
        Criterion<InventoryChangeTrigger.TriggerInstance> hasPsigem = has(ModTags.GEM_PSIGEM);
        Criterion<InventoryChangeTrigger.TriggerInstance> hasPsimetal = has(ModTags.INGOT_PSIMETAL);
        Criterion<InventoryChangeTrigger.TriggerInstance> hasEbonyPsimetal = has(ModTags.INGOT_EBONY_PSIMETAL);
        Criterion<InventoryChangeTrigger.TriggerInstance> hasTotemOfUndying = has(Items.TOTEM_OF_UNDYING);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.inlineCaster)
                .define('S', vazkii.psi.common.item.base.ModItems.cadSocketBasic.get())
                .define('P', vazkii.psi.common.item.base.ModItems.psimetal.get())
                .pattern("   ")
                .pattern("PPP")
                .pattern("PS ")
                .unlockedBy("has_psimetal_ingot", hasPsimetal)
                .save(consumer, PsiOddities.location("inline_caster"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.dyingExosuitSensor)
                .define('T', Items.TOTEM_OF_UNDYING)
                .define('I', Items.IRON_INGOT)
                .define('P', vazkii.psi.common.item.base.ModItems.psimetal.get())
                .pattern(" P ")
                .pattern("PTI")
                .pattern(" I ")
                .unlockedBy("has_totem_of_undying", hasTotemOfUndying)
                .save(consumer, PsiOddities.location("exosuit_sensor_dying"));


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.cadBatteryTheDiracSanction)
                .define('N', Items.NETHER_STAR)
                .define('I', vazkii.psi.common.item.base.ModItems.psimetal.get())
                .define('W', vazkii.psi.common.item.base.ModItems.ebonyPsimetal.get())
                .define('B', vazkii.psi.common.item.base.ModItems.ivoryPsimetal.get())
                .pattern(" W ")
                .pattern("INI")
                .pattern(" B ")
                .unlockedBy("has_nether_star", hasNetherStar)
                .save(consumer, PsiOddities.location("cad_battery_the_dirac_sanction"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.fluxDrive)
                .define('R', Items.REDSTONE)
                .define('W', vazkii.psi.common.item.base.ModItems.ebonyPsimetal.get())
                .define('B', vazkii.psi.common.item.base.ModItems.ivoryPsimetal.get())
                .pattern("W ")
                .pattern("R ")
                .pattern("B ")
                .unlockedBy("has_ebony_psimetal", hasEbonyPsimetal)
                .save(consumer, PsiOddities.location("flux_drive"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.psimetalShield)
                .define('M', vazkii.psi.common.item.base.ModItems.psimetal.get())
                .define('G', vazkii.psi.common.item.base.ModItems.psigem.get())
                .pattern("MMM")
                .pattern("GMG")
                .pattern(" M ")
                .unlockedBy("has_psigem", hasPsigem)
                .save(consumer, PsiOddities.location("psimetal_shield"));
    }

}
