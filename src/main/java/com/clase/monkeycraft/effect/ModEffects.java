package com.clase.monkeycraft.effect;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(Registries.MOB_EFFECT, "monkeycraft");

    public static final RegistryObject<MobEffect> DOOM_EFFECT = MOB_EFFECTS.register("doom_effect",
            () -> new DoomEffect(MobEffectCategory.HARMFUL, 0x111111));

    public static final RegistryObject<MobEffect> MONKEY_EFFECT = MOB_EFFECTS.register("monkey_effect",
            () -> new MonkeyEffect(MobEffectCategory.BENEFICIAL, 0xFFFF00));
}