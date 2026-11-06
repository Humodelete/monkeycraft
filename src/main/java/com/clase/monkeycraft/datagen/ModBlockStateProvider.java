package com.clase.monkeycraft.datagen;

import com.clase.monkeycraft.MonkeyCraft;
import com.clase.monkeycraft.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, MonkeyCraft.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.SKULL);

        logBlock(ModBlocks.PALM_TREE_LOG.get());
        axisBlock(ModBlocks.PALM_TREE_WOOD.get(), blockTexture(ModBlocks.PALM_TREE_LOG.get()), blockTexture(ModBlocks.PALM_TREE_LOG.get()));
        logBlock(ModBlocks.STRIPPED_PALM_TREE_LOG.get());
        axisBlock(ModBlocks.STRIPPED_PALM_TREE_WOOD.get(), blockTexture(ModBlocks.STRIPPED_PALM_TREE_LOG.get()), blockTexture(ModBlocks.STRIPPED_PALM_TREE_LOG.get()));

        blockItem(ModBlocks.PALM_TREE_LOG);
        blockItem(ModBlocks.PALM_TREE_WOOD);
        blockItem(ModBlocks.STRIPPED_PALM_TREE_LOG);
        blockItem(ModBlocks.STRIPPED_PALM_TREE_WOOD);

        blockWithItem(ModBlocks.PALM_TREE_PLANKS);

        leavesBlock(ModBlocks.PALM_TREE_LEAVES);
        saplingBlock(ModBlocks.PALM_TREE_SAPLING);
    }

    private void saplingBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void leavesBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), ResourceLocation.parse("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void blockItem(RegistryObject<? extends Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(MonkeyCraft.MOD_ID + ":block/" +
                ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }
}