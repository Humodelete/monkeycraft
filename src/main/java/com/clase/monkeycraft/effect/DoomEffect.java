package com.clase.monkeycraft.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class DoomEffect extends MobEffect {
    public DoomEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public boolean applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        pLivingEntity.hurt(pLivingEntity.damageSources().magic(), 2.0F);

        pLivingEntity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 60, pAmplifier));
        pLivingEntity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 60, pAmplifier));
        pLivingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, pAmplifier));
        pLivingEntity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 60, pAmplifier));
        pLivingEntity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 60, pAmplifier));

        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int pDuration, int pAmplifier) {
        int rate = 20;
        return pDuration % rate == 0;
    }
}
