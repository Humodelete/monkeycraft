package com.clase.monkeycraft.datagen;

import com.clase.monkeycraft.MonkeyCraft;
import com.clase.monkeycraft.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, MonkeyCraft.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.PALM_TREE_LOG.get())
                .add(ModBlocks.PALM_TREE_WOOD.get())
                .add(ModBlocks.STRIPPED_PALM_TREE_LOG.get())
                .add(ModBlocks.STRIPPED_PALM_TREE_WOOD.get());

        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.PALM_TREE_LOG.get())
                .add(ModBlocks.PALM_TREE_WOOD.get())
                .add(ModBlocks.STRIPPED_PALM_TREE_LOG.get())
                .add(ModBlocks.STRIPPED_PALM_TREE_WOOD.get())
                .add(ModBlocks.PALM_TREE_PLANKS.get());

        this.tag(BlockTags.MINEABLE_WITH_HOE)
                .add(ModBlocks.PALM_TREE_LEAVES.get());
    }
}