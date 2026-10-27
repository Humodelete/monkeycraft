package com.clase.monkeycraft.util;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModScepterTiers {

    public static final Tier TIER_0 = new ForgeTier(10, 1, 1, 5,
            BlockTags.DIRT,
            () -> Ingredient.of(Ingredient.EMPTY.getItems()),
            BlockTags.INCORRECT_FOR_WOODEN_TOOL);

    public static final Tier TIER_1 = new ForgeTier(15, 1, 1, 10,
            BlockTags.DIRT,
            () -> Ingredient.of(Ingredient.EMPTY.getItems()),
            BlockTags.INCORRECT_FOR_WOODEN_TOOL);

    public static final Tier TIER_2 = new ForgeTier(20, 1, 1, 15,
            BlockTags.DIRT,
            () -> Ingredient.of(Ingredient.EMPTY.getItems()),
            BlockTags.INCORRECT_FOR_WOODEN_TOOL);

    public static final Tier TIER_3 = new ForgeTier(30, 1, 1, 25,
            BlockTags.DIRT,
            () -> Ingredient.of(Ingredient.EMPTY.getItems()),
            BlockTags.INCORRECT_FOR_WOODEN_TOOL);
}
