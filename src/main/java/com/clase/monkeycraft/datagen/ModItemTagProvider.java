package com.clase.monkeycraft.datagen;

import com.clase.monkeycraft.MonkeyCraft;
import com.clase.monkeycraft.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture,
                              CompletableFuture<TagLookup<Block>> lookupCompletableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, completableFuture, lookupCompletableFuture, MonkeyCraft.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        // Hace que estos ítems cuenten como troncos en la mesa de crafteo y hornos
        tag(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.PALM_TREE_LOG.get().asItem())
                .add(ModBlocks.PALM_TREE_WOOD.get().asItem())
                .add(ModBlocks.STRIPPED_PALM_TREE_LOG.get().asItem())
                .add(ModBlocks.STRIPPED_PALM_TREE_WOOD.get().asItem());

        // Hace que tus tablones cuenten como tablones de madera normales
        tag(ItemTags.PLANKS)
                .add(ModBlocks.PALM_TREE_PLANKS.get().asItem());
    }
}