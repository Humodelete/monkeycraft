package com.clase.monkeycraft.effect;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class MonkeyEffect extends MobEffect {
    public MonkeyEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);

        this.addAttributeModifier(Attributes.MOVEMENT_SPEED,
                ResourceLocation.parse("monkeycraft:monkey_speed"),
                0.10D, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);

        this.addAttributeModifier(Attributes.JUMP_STRENGTH,
                ResourceLocation.parse("monkeycraft:monkey_jump"),
                0.005D, AttributeModifier.Operation.ADD_VALUE);

        this.addAttributeModifier(Attributes.GRAVITY,
                ResourceLocation.parse("monkeycraft:monkey_gravity"),
                -0.02D, AttributeModifier.Operation.ADD_VALUE);

        this.addAttributeModifier(Attributes.SAFE_FALL_DISTANCE,
                ResourceLocation.parse("monkeycraft:monkey_safe_fall"),
                3.0D, AttributeModifier.Operation.ADD_VALUE);
    }
}