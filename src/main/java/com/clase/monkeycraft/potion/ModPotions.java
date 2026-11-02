package com.clase.monkeycraft.potion;

import com.clase.monkeycraft.MonkeyCraft;
import com.clase.monkeycraft.effect.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(ForgeRegistries.POTIONS, MonkeyCraft.MOD_ID);

    public static final RegistryObject<Potion> DOOM_POTION = POTIONS.register("doom_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.DOOM_EFFECT.getHolder().get(), 600, 0)));
    public static final RegistryObject<Potion> LONG_DOOM_POTION = POTIONS.register("long_doom_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.DOOM_EFFECT.getHolder().get(), 1800, 0)));
    public static final RegistryObject<Potion> STRONG_DOOM_POTION = POTIONS.register("strong_doom_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.DOOM_EFFECT.getHolder().get(), 440, 1)));

    public static final RegistryObject<Potion> MONKEY_POTION = POTIONS.register("monkey_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.MONKEY_EFFECT.getHolder().get(), 1200, 0)));
    public static final RegistryObject<Potion> LONG_MONKEY_POTION = POTIONS.register("long_monkey_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.MONKEY_EFFECT.getHolder().get(), 3600, 0)));
    public static final RegistryObject<Potion> STRONG_MONKEY_POTION = POTIONS.register("strong_monkey_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.MONKEY_EFFECT.getHolder().get(), 600, 1)));

    public static void register (IEventBus eventBus){
        POTIONS.register(eventBus);
    }
}