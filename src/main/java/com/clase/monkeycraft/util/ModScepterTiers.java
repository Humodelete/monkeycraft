package com.clase.monkeycraft.util;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModScepterTiers {

    public static int TIER_0_ENERGY = 4; //Requires balance changes
    public static int TIER_1_ENERGY = 6; //Requires balance changes
    public static int TIER_2_ENERGY = 8; //Requires balance changes
    public static int TIER_3_ENERGY = 10; //Requires balance changes

    public static final Tier TIER_0 = new ForgeTier(TIER_0_ENERGY + 1, 0, 0, 0,
            BlockTags.DIRT,
            () -> Ingredient.of(Ingredient.EMPTY.getItems()),
            BlockTags.INCORRECT_FOR_WOODEN_TOOL);

    public static final Tier TIER_1 = new ForgeTier(TIER_1_ENERGY + 1, 0, 0, 0,
            BlockTags.DIRT,
            () -> Ingredient.of(Ingredient.EMPTY.getItems()),
            BlockTags.INCORRECT_FOR_WOODEN_TOOL);

    public static final Tier TIER_2 = new ForgeTier(TIER_2_ENERGY + 1, 0, 0, 0,
            BlockTags.DIRT,
            () -> Ingredient.of(Ingredient.EMPTY.getItems()),
            BlockTags.INCORRECT_FOR_WOODEN_TOOL);

    public static final Tier TIER_3 = new ForgeTier(TIER_3_ENERGY + 1, 0, 0, 0,
            BlockTags.DIRT,
            () -> Ingredient.of(Ingredient.EMPTY.getItems()),
            BlockTags.INCORRECT_FOR_WOODEN_TOOL);
}
