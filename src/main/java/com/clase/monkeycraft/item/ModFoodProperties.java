package com.clase.monkeycraft.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    public static final FoodProperties DOOM_AND_DESPAIR = new FoodProperties.Builder().nutrition(5).saturationModifier(1)
            .effect(new MobEffectInstance(MobEffects.POISON, 600, 10), 1)
            .effect(new MobEffectInstance(MobEffects.BLINDNESS, 600, 10), 1)
            .effect(new MobEffectInstance(MobEffects.BAD_OMEN, 600, 10), 1)
            .effect(new MobEffectInstance(MobEffects.HUNGER, 600, 10), 1)
            .effect(new MobEffectInstance(MobEffects.UNLUCK, 600, 10), 1)
            .effect(new MobEffectInstance(MobEffects.WEAKNESS, 600, 10), 1)
            .effect(new MobEffectInstance(MobEffects.CONFUSION, 600, 10), 1)
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 600, 10), 1)
            .effect(new MobEffectInstance(MobEffects.GLOWING, 600, 10), 1)
            .effect(new MobEffectInstance(MobEffects.WITHER, 600, 10), 1)
            .effect(new MobEffectInstance(MobEffects.DARKNESS, 600, 10), 1)
            .effect(new MobEffectInstance(MobEffects.HARM, 1, 1), 1)

            .build();
}
