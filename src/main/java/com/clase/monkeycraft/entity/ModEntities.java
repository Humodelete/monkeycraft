package com.clase.monkeycraft.entity;

import com.clase.monkeycraft.MonkeyCraft;
import com.clase.monkeycraft.entity.custom.MonkeyEntity;
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
                    .sized(1.5f, 1.5f).build("monkey"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
