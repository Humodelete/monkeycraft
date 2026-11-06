package com.clase.monkeycraft.datagen;

import com.clase.monkeycraft.MonkeyCraft;
import com.clase.monkeycraft.block.ModBlocks;
import com.clase.monkeycraft.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MonkeyCraft.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        saplingItem(ModBlocks.PALM_TREE_SAPLING);
        withExistingParent(ModBlocks.PALM_TREE_DOOR.getId().getPath(), mcLoc("item/generated"))
                .texture("layer0", modLoc("item/palm_tree_door"));

        withExistingParent(ModBlocks.PALM_TREE_SAPLING.getId().getPath(), mcLoc("item/generated"))
                .texture("layer0", modLoc("block/palm_tree_sapling"));

        withExistingParent(ModBlocks.PALM_TREE_FENCE.getId().getPath(), mcLoc("block/fence_inventory"))
                .texture("texture", modLoc("block/palm_tree_planks"));

        withExistingParent(ModBlocks.PALM_TREE_FENCE_GATE.getId().getPath(), modLoc("block/palm_tree_fence_gate"));
        withExistingParent(ModBlocks.PALM_TREE_TRAPDOOR.getId().getPath(), modLoc("block/palm_tree_trapdoor_bottom"));
        withExistingParent(ModBlocks.PALM_TREE_STAIRS.getId().getPath(), modLoc("block/palm_tree_stairs"));
        withExistingParent(ModBlocks.PALM_TREE_SLAB.getId().getPath(), modLoc("block/palm_tree_slab"));
        withExistingParent(ModBlocks.PALM_TREE_PRESSURE_PLATE.getId().getPath(), modLoc("block/palm_tree_pressure_plate"));
        buttonItem(ModBlocks.PALM_TREE_BUTTON, ModBlocks.PALM_TREE_PLANKS);
        basicItem(ModItems.PALM_TREE_SIGN.get());
        basicItem(ModItems.PALM_TREE_HANGING_SIGN.get());
    }

    private ItemModelBuilder saplingItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID, "block/" + item.getId().getPath()));
    }

    public ItemModelBuilder basicItem(net.minecraft.world.item.Item item) {
        return withExistingParent(ForgeRegistries.ITEMS.getKey(item).getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID, "item/" + ForgeRegistries.ITEMS.getKey(item).getPath()));
    }
    public void buttonItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }
}