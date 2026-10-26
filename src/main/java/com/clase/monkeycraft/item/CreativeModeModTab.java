package com.clase.monkeycraft.item;

import com.clase.monkeycraft.MonkeyCraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

/**
 * Class to add a tab to the creative menu
 */
public class CreativeModeModTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MonkeyCraft.MOD_ID);

    /**
     * Method to add the creative mod tab
     */
    public static final RegistryObject<CreativeModeTab> MONKEYCRAFT_TAB = CREATIVE_MODE_TAB.register("monkeycraft_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.MONKEY_SKULL.get()))
                    .title(Component.translatable("creativetab.monkeycraft.monkeycraft_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.MONKEY_SKULL.get());
                        output.accept(ModItems.MONKEY_SUMMON.get());
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
