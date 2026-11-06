package com.clase.monkeycraft.datagen;

import com.clase.monkeycraft.MonkeyCraft;
import com.clase.monkeycraft.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
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
        simpleBlock(ModBlocks.MONKEY_HEAD.get(), models().getExistingFile(modLoc("block/monkey_head")));
        blockWithItem(ModBlocks.PALM_TREE_PLANKS);
        logBlock(ModBlocks.PALM_TREE_LOG.get());
        axisBlock(ModBlocks.PALM_TREE_WOOD.get(), blockTexture(ModBlocks.PALM_TREE_LOG.get()), blockTexture(ModBlocks.PALM_TREE_LOG.get()));
        logBlock(ModBlocks.STRIPPED_PALM_TREE_LOG.get());
        axisBlock(ModBlocks.STRIPPED_PALM_TREE_WOOD.get(), blockTexture(ModBlocks.STRIPPED_PALM_TREE_LOG.get()), blockTexture(ModBlocks.STRIPPED_PALM_TREE_LOG.get()));
        signBlock(((StandingSignBlock) ModBlocks.PALM_TREE_SIGN.get()), ((WallSignBlock) ModBlocks.PALM_TREE_WALL_SIGN.get()), modLoc("block/palm_tree_planks"));
        ModelFile hangingSign = models().sign(ModBlocks.PALM_TREE_HANGING_SIGN.getId().getPath(), blockTexture(ModBlocks.PALM_TREE_PLANKS.get()));
        ModelFile wallHangingSign = models().sign(ModBlocks.PALM_TREE_WALL_HANGING_SIGN.getId().getPath(), blockTexture(ModBlocks.PALM_TREE_PLANKS.get()));

        getVariantBuilder(ModBlocks.PALM_TREE_HANGING_SIGN.get())
                .partialState().setModels(new net.minecraftforge.client.model.generators.ConfiguredModel(hangingSign));

        getVariantBuilder(ModBlocks.PALM_TREE_WALL_HANGING_SIGN.get())
                .partialState().setModels(new net.minecraftforge.client.model.generators.ConfiguredModel(wallHangingSign));
        fenceBlock(((FenceBlock) ModBlocks.PALM_TREE_FENCE.get()), blockTexture(ModBlocks.PALM_TREE_PLANKS.get()));
        fenceGateBlock(((FenceGateBlock) ModBlocks.PALM_TREE_FENCE_GATE.get()), blockTexture(ModBlocks.PALM_TREE_PLANKS.get()));

        stairsBlock(((StairBlock) ModBlocks.PALM_TREE_STAIRS.get()), blockTexture(ModBlocks.PALM_TREE_PLANKS.get()));
        slabBlock(((SlabBlock) ModBlocks.PALM_TREE_SLAB.get()), blockTexture(ModBlocks.PALM_TREE_PLANKS.get()), blockTexture(ModBlocks.PALM_TREE_PLANKS.get()));
        buttonBlock(((ButtonBlock) ModBlocks.PALM_TREE_BUTTON.get()), blockTexture(ModBlocks.PALM_TREE_PLANKS.get()));
        pressurePlateBlock(((PressurePlateBlock) ModBlocks.PALM_TREE_PRESSURE_PLATE.get()), blockTexture(ModBlocks.PALM_TREE_PLANKS.get()));

        blockItem(ModBlocks.PALM_TREE_LOG);
        blockItem(ModBlocks.PALM_TREE_WOOD);
        blockItem(ModBlocks.STRIPPED_PALM_TREE_LOG);
        blockItem(ModBlocks.STRIPPED_PALM_TREE_WOOD);

        leavesBlock(ModBlocks.PALM_TREE_LEAVES);
        saplingBlock(ModBlocks.PALM_TREE_SAPLING);

        doorBlockWithRenderType((DoorBlock) ModBlocks.PALM_TREE_DOOR.get(),
                modLoc("block/palm_tree_door_bottom"),
                modLoc("block/palm_tree_door_top"),
                "cutout");

        trapdoorBlockWithRenderType((TrapDoorBlock) ModBlocks.PALM_TREE_TRAPDOOR.get(),
                modLoc("block/palm_tree_trapdoor"),
                true,
                "cutout");
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