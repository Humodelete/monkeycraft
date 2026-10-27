package com.clase.monkeycraft.item;

import com.clase.monkeycraft.MonkeyCraft;
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
            () -> new MonkeyScepter0(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> MONKEY_SCEPTER_T1 = ITEMS.register("monkey_scepter_t1",
            () -> new MonkeyScepter1(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> MONKEY_SCEPTER_T2 = ITEMS.register("monkey_scepter_t2",
            () -> new MonkeyScepter2(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> MONKEY_SCEPTER_T3 = ITEMS.register("monkey_scepter_t3",
            () -> new MonkeyScepter3(new Item.Properties().stacksTo(1)));
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);

    }
}
