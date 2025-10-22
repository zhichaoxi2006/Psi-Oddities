package com.zhichaoxi.psi_oddities.datagen;

import com.zhichaoxi.psi_oddities.PsiOddities;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import vazkii.psi.data.PsiBlockTagProvider;
import vazkii.psi.data.PsiItemTagProvider;

import java.util.concurrent.CompletableFuture;

@SuppressWarnings("removal")
@EventBusSubscriber(modid = PsiOddities.MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerator {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        net.minecraft.data.DataGenerator generator = event.getGenerator();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        PackOutput packOutput = generator.getPackOutput();

        if(event.includeServer()) {
            PsiOdditiesBlockTagsProvider blockTagProvider = new PsiOdditiesBlockTagsProvider(packOutput, lookupProvider, existingFileHelper);
            generator.addProvider(true, blockTagProvider);
            generator.addProvider(true,
                    new PsiOdditiesDamageTypeTagsProvider(packOutput, lookupProvider, existingFileHelper));
            generator.addProvider(true,
                    new PsiOdditiesRecipeGenerator(event.getGenerator().getPackOutput(), event.getLookupProvider()));
            generator.addProvider(true,
                    new PsiOdditiesItemTagProvider(packOutput, lookupProvider, blockTagProvider.contentsGetter(), existingFileHelper));
        }
    }
}
