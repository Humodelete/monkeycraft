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
        basicItem(ModBlocks.PALM_TREE_DOOR.get().asItem());
        withExistingParent(ModBlocks.PALM_TREE_TRAPDOOR.getId().getPath(), modLoc("block/palm_tree_trapdoor_bottom"));
        basicItem(ModItems.PALM_TREE_SIGN.get());
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
}