package com.clase.monkeycraft.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import com.clase.monkeycraft.effect.ModEffects; // Añade la importación de tu clase

public class ModFoodProperties {
    public static final FoodProperties DOOM_AND_DESPAIR = new FoodProperties.Builder()
            .nutrition(5)
            .saturationModifier(1)

            .effect(new MobEffectInstance(ModEffects.DOOM.getHolder().get(), 600, 10), 1.0F)
            .build();
}