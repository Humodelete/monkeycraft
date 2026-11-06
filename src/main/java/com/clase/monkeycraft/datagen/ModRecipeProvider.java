package com.clase.monkeycraft.datagen;

import com.clase.monkeycraft.MonkeyCraft;
import com.clase.monkeycraft.block.ModBlocks;
import com.clase.monkeycraft.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;
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

        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ModBlocks.PALM_TREE_DOOR.get(), 3)
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .define('#', ModBlocks.PALM_TREE_PLANKS.get())
                .unlockedBy(getHasName(ModBlocks.PALM_TREE_PLANKS.get()), has(ModBlocks.PALM_TREE_PLANKS.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ModBlocks.PALM_TREE_TRAPDOOR.get(), 2)
                .pattern("###")
                .pattern("###")
                .define('#', ModBlocks.PALM_TREE_PLANKS.get())
                .unlockedBy(getHasName(ModBlocks.PALM_TREE_PLANKS.get()), has(ModBlocks.PALM_TREE_PLANKS.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModItems.PALM_TREE_SIGN.get(), 3)
                .pattern("###")
                .pattern("###")
                .pattern(" / ")
                .define('#', ModBlocks.PALM_TREE_PLANKS.get())
                .define('/', Items.STICK)
                .unlockedBy(getHasName(ModBlocks.PALM_TREE_PLANKS.get()), has(ModBlocks.PALM_TREE_PLANKS.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.PALM_TREE_FENCE.get(), 3)
                .pattern("W#W")
                .pattern("W#W")
                .define('W', ModBlocks.PALM_TREE_PLANKS.get())
                .define('#', Items.STICK)
                .unlockedBy(getHasName(ModBlocks.PALM_TREE_PLANKS.get()), has(ModBlocks.PALM_TREE_PLANKS.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ModBlocks.PALM_TREE_FENCE_GATE.get())
                .pattern("#W#")
                .pattern("#W#")
                .define('W', ModBlocks.PALM_TREE_PLANKS.get())
                .define('#', Items.STICK)
                .unlockedBy(getHasName(ModBlocks.PALM_TREE_PLANKS.get()), has(ModBlocks.PALM_TREE_PLANKS.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModItems.PALM_TREE_HANGING_SIGN.get(), 6)
                .pattern("C C")
                .pattern("WWW")
                .pattern("WWW")
                .define('C', Items.CHAIN)
                .define('W', ModBlocks.STRIPPED_PALM_TREE_LOG.get())
                .unlockedBy(getHasName(ModBlocks.STRIPPED_PALM_TREE_LOG.get()), has(ModBlocks.STRIPPED_PALM_TREE_LOG.get()))
                .save(pRecipeOutput);
    }
}