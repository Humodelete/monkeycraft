package com.clase.monkeycraft.effect;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class DoomEffect extends MobEffect {
    public DoomEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);

        // Atributos internos (Ocultos en el inventario)
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED,
                ResourceLocation.parse("monkeycraft:doom_slowness"),
                -0.15D, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);

        this.addAttributeModifier(Attributes.ATTACK_DAMAGE,
                ResourceLocation.parse("monkeycraft:doom_weakness"),
                -4.0D, AttributeModifier.Operation.ADD_VALUE);
    }

    @Override
    public boolean applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        pLivingEntity.hurt(pLivingEntity.damageSources().magic(), 2.0F);
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int pDuration, int pAmplifier) {
        int rate = 20;
        return pDuration % rate == 0;
    }
}