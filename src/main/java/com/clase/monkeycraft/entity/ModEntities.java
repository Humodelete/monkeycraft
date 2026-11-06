package com.clase.monkeycraft.entity;

import com.clase.monkeycraft.MonkeyCraft;
import com.clase.monkeycraft.entity.custom.MonkeyEntity;
import com.clase.monkeycraft.entity.custom.MonkeyTankEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MonkeyCraft.MOD_ID);

    public static final RegistryObject<EntityType<MonkeyEntity>> MONKEY =
            ENTITY_TYPES.register("monkey", () -> EntityType.Builder.of(MonkeyEntity::new, MobCategory.CREATURE)
                    .sized(0.75f, 0.75f).build("monkey"));

    public static final RegistryObject<EntityType<MonkeyTankEntity>> MONKEY_TANK =
            ENTITY_TYPES.register("monkey_tank", () -> EntityType.Builder.of(MonkeyTankEntity::new, MobCategory.CREATURE)
                    .sized(1.5f, 2.5f).build("monkey_tank"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
