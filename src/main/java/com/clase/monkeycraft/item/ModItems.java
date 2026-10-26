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

    public static final RegistryObject<Item> MONKEY_SKULL = ITEMS.register("monkey_skull",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MONKEY_SUMMON = ITEMS.register("monkey_summon",
            () -> new MonkeySummon(new Item.Properties()));
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
