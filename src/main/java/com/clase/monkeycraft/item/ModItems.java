package com.clase.monkeycraft.item;

import com.clase.monkeycraft.MonkeyCraft;
import com.clase.monkeycraft.util.ModScepterTiers;
import net.minecraft.world.item.Item;
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

    public static final RegistryObject<Item> MONKEY_SCEPTER_T0 = ITEMS.register("monkey_scepter_t0",
            () -> new ScepterItem(ModScepterTiers.TIER_0, new Item.Properties().attributes(ScepterItem.createAttributes(
                    ModScepterTiers.TIER_0, 1, -1))));
    public static final RegistryObject<Item> MONKEY_SCEPTER_T1 = ITEMS.register("monkey_scepter_t1",
            () -> new ScepterItem(ModScepterTiers.TIER_1, new Item.Properties().attributes(ScepterItem.createAttributes(
                    ModScepterTiers.TIER_1, 1, -1))));
    public static final RegistryObject<Item> MONKEY_SCEPTER_T2 = ITEMS.register("monkey_scepter_t2",
            () -> new ScepterItem(ModScepterTiers.TIER_2, new Item.Properties().attributes(ScepterItem.createAttributes(
                    ModScepterTiers.TIER_2, 1, -1))));
    public static final RegistryObject<Item> MONKEY_SCEPTER_T3 = ITEMS.register("monkey_scepter_t3",
            () -> new ScepterItem(ModScepterTiers.TIER_3, new Item.Properties().attributes(ScepterItem.createAttributes(
                    ModScepterTiers.TIER_3, 1, -1))));

    public static final RegistryObject<Item> BANANA_OF_DOOM_AND_DESPAIR = ITEMS.register("banana_of_doom_and_despair",
            () -> new Item(new Item.Properties().food(ModFoodProperties.DOOM_AND_DESPAIR)));
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);

    }
}
