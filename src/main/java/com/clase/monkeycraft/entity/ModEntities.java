package com.clase.monkeycraft.entity;

import com.clase.monkeycraft.MonkeyCraft;
import com.clase.monkeycraft.entity.custom.*;
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

    public static final RegistryObject<EntityType<MonkeyDartEntity>> MONKEY_DART =
            ENTITY_TYPES.register("monkey_dart", () -> EntityType.Builder.of(MonkeyDartEntity::new, MobCategory.CREATURE)
                    .sized(0.75f, 1.25f).build("monkey_dart"));

    public static final RegistryObject<EntityType<MonkeyWizardEntity>> MONKEY_WIZARD =
            ENTITY_TYPES.register("monkey_wizard", () -> EntityType.Builder.of(MonkeyWizardEntity::new, MobCategory.CREATURE)
                    .sized(0.9f, 1.4f).build("monkey_wizard"));

    public static final RegistryObject<EntityType<MonkeyJesterEntity>> MONKEY_JESTER =
            ENTITY_TYPES.register("monkey_jester", () -> EntityType.Builder.of(MonkeyJesterEntity::new, MobCategory.CREATURE)
                    .sized(1.0f, 1.5f).build("monkey_jester"));

    public static final RegistryObject<EntityType<ModBoatEntity>> PALM_TREE_BOAT_ENTITY =
            ENTITY_TYPES.register("palm_tree_boat", () -> EntityType.Builder.<ModBoatEntity>of(ModBoatEntity::new, MobCategory.MISC)
                    .sized(1.375F, 0.5625F).clientTrackingRange(10).build("palm_tree_boat"));

    public static final RegistryObject<EntityType<ModChestBoatEntity>> PALM_TREE_CHEST_BOAT_ENTITY =
            ENTITY_TYPES.register("palm_tree_chest_boat", () -> EntityType.Builder.<ModChestBoatEntity>of(ModChestBoatEntity::new, MobCategory.MISC)
                    .sized(1.375F, 0.5625F).clientTrackingRange(10).build("palm_tree_chest_boat"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
