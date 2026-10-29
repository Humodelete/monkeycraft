package com.clase.monkeycraft.util;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModScepterTiers {

    public static int TIER_0_ENERGY = 10;
    public static int TIER_1_ENERGY = 15;
    public static int TIER_2_ENERGY = 20;
    public static int TIER_3_ENERGY = 30;

    public static final Tier TIER_0 = new ForgeTier(TIER_0_ENERGY + 1, 1, 1, 5,
            BlockTags.DIRT,
            () -> Ingredient.of(Ingredient.EMPTY.getItems()),
            BlockTags.INCORRECT_FOR_WOODEN_TOOL);

    public static final Tier TIER_1 = new ForgeTier(TIER_1_ENERGY + 1, 1, 1, 10,
            BlockTags.DIRT,
            () -> Ingredient.of(Ingredient.EMPTY.getItems()),
            BlockTags.INCORRECT_FOR_WOODEN_TOOL);

    public static final Tier TIER_2 = new ForgeTier(TIER_2_ENERGY + 1, 1, 1, 15,
            BlockTags.DIRT,
            () -> Ingredient.of(Ingredient.EMPTY.getItems()),
            BlockTags.INCORRECT_FOR_WOODEN_TOOL);

    public static final Tier TIER_3 = new ForgeTier(TIER_3_ENERGY + 1, 1, 1, 25,
            BlockTags.DIRT,
            () -> Ingredient.of(Ingredient.EMPTY.getItems()),
            BlockTags.INCORRECT_FOR_WOODEN_TOOL);
}
