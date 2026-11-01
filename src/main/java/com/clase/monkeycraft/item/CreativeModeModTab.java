package com.clase.monkeycraft.item;

import com.clase.monkeycraft.MonkeyCraft;
import com.clase.monkeycraft.potion.ModPotions;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
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
                    .icon(() -> new ItemStack(ModItems.MONKEY_SKULL_T0.get()))
                    .title(Component.translatable("creativetab.monkeycraft.monkeycraft_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.MONKEY_SKULL_T0.get());
                        output.accept(ModItems.MONKEY_SKULL_T1.get());
                        output.accept(ModItems.MONKEY_SKULL_T2.get());
                        output.accept(ModItems.MONKEY_SKULL_T3.get());
                        output.accept(ModItems.MONKEY_SCEPTER_T0.get());
                        output.accept(ModItems.MONKEY_SCEPTER_T1.get());
                        output.accept(ModItems.MONKEY_SCEPTER_T2.get());
                        output.accept(ModItems.MONKEY_SCEPTER_T3.get());
                        output.accept(ModItems.BANANA_OF_DOOM_AND_DESPAIR.get());

                        output.accept(PotionContents.createItemStack(Items.POTION, ModPotions.DOOM_POTION.getHolder().get()));
                        output.accept(PotionContents.createItemStack(Items.SPLASH_POTION, ModPotions.DOOM_POTION.getHolder().get()));
                        output.accept(PotionContents.createItemStack(Items.LINGERING_POTION, ModPotions.DOOM_POTION.getHolder().get()));
                        output.accept(PotionContents.createItemStack(Items.TIPPED_ARROW, ModPotions.DOOM_POTION.getHolder().get()));                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
