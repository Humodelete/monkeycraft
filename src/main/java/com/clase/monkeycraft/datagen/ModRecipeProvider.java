package com.clase.monkeycraft.datagen;

import com.clase.monkeycraft.MonkeyCraft;
import com.clase.monkeycraft.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PALM_TREE_PLANKS.get(), 4)
                .requires(ModBlocks.PALM_TREE_LOG.get())
                .unlockedBy(getHasName(ModBlocks.PALM_TREE_LOG.get()), has(ModBlocks.PALM_TREE_LOG.get()))
                .save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PALM_TREE_PLANKS.get(), 4)
                .requires(ModBlocks.PALM_TREE_WOOD.get())
                .unlockedBy(getHasName(ModBlocks.PALM_TREE_WOOD.get()), has(ModBlocks.PALM_TREE_WOOD.get()))
                .save(pRecipeOutput, MonkeyCraft.MOD_ID + ":palm_tree_planks_from_wood");

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PALM_TREE_WOOD.get(), 3)
                .pattern("##")
                .pattern("##")
                .define('#', ModBlocks.PALM_TREE_LOG.get())
                .unlockedBy(getHasName(ModBlocks.PALM_TREE_LOG.get()), has(ModBlocks.PALM_TREE_LOG.get()))
                .save(pRecipeOutput);
    }
}