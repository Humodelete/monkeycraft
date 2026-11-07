package com.clase.monkeycraft.item;

import com.clase.monkeycraft.MonkeyCraft;
import com.clase.monkeycraft.block.ModBlocks;
import com.clase.monkeycraft.entity.ModEntities;
import com.clase.monkeycraft.util.ModScepterTiers;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * Generic class to add all items to the list
 */
public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MonkeyCraft.MOD_ID);

    public static final RegistryObject<Item> MONKEY_SKULL_T0 = ITEMS.register("monkey_skull_t0",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MONKEY_SKULL_T1 = ITEMS.register("monkey_skull_t1",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MONKEY_SKULL_T2 = ITEMS.register("monkey_skull_t2",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MONKEY_SKULL_T3 = ITEMS.register("monkey_skull_t3",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MONKEY_SPAWN_EGG = ITEMS.register("monkey_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.MONKEY, 0x4a3018,0xe3c73b, new Item.Properties()));

    public static final RegistryObject<Item> PALM_TREE_SIGN = ITEMS.register("palm_tree_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16), ModBlocks.PALM_TREE_SIGN.get(),
                    ModBlocks.PALM_TREE_WALL_SIGN.get()));
    public static final RegistryObject<Item> PALM_TREE_HANGING_SIGN = ITEMS.register("palm_tree_hanging_sign",
            () -> new HangingSignItem(ModBlocks.PALM_TREE_HANGING_SIGN.get(), ModBlocks.PALM_TREE_WALL_HANGING_SIGN.get(),
                    new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> MONKEY_SCEPTER_T0 = ITEMS.register("monkey_scepter_t0",
            () -> new ScepterItem(ModScepterTiers.TIER_0, new Item.Properties().attributes(ScepterItem.createAttributes(
                    ModScepterTiers.TIER_0, 0, -3))));
    public static final RegistryObject<Item> MONKEY_SCEPTER_T1 = ITEMS.register("monkey_scepter_t1",
            () -> new ScepterItem(ModScepterTiers.TIER_1, new Item.Properties().attributes(ScepterItem.createAttributes(
                    ModScepterTiers.TIER_1, 0, -3))));
    public static final RegistryObject<Item> MONKEY_SCEPTER_T2 = ITEMS.register("monkey_scepter_t2",
            () -> new ScepterItem(ModScepterTiers.TIER_2, new Item.Properties().attributes(ScepterItem.createAttributes(
                    ModScepterTiers.TIER_2, 0, -3))));
    public static final RegistryObject<Item> MONKEY_SCEPTER_T3 = ITEMS.register("monkey_scepter_t3",
            () -> new ScepterItem(ModScepterTiers.TIER_3, new Item.Properties().attributes(ScepterItem.createAttributes(
                    ModScepterTiers.TIER_3, 0, -3))));

    public static final RegistryObject<Item> BANANA_PEEL = ITEMS.register("banana_peel",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BANANA_OF_DOOM_AND_DESPAIR = ITEMS.register("banana_of_doom_and_despair",
            () -> new BananaItem(new Item.Properties().food(ModFoodProperties.DOOM_AND_DESPAIR)));
    public static final RegistryObject<Item> BANANA = ITEMS.register("banana",
            () -> new BananaItem(new Item.Properties().food(ModFoodProperties.BANANA)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);

    }
}
