package com.clase.monkeycraft.event;

import com.clase.monkeycraft.MonkeyCraft;
import com.clase.monkeycraft.item.ModItems;
import com.clase.monkeycraft.potion.ModPotions;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.event.brewing.BrewingRecipeRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MonkeyCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {
    @SubscribeEvent
    public static void onBrewingRecipeRegister(BrewingRecipeRegisterEvent event) {
        PotionBrewing.Builder builder = event.getBuilder();

        builder.addMix(Potions.AWKWARD, ModItems.BANANA_OF_DOOM_AND_DESPAIR.get(), ModPotions.DOOM_POTION.getHolder().get());
        builder.addMix(ModPotions.DOOM_POTION.getHolder().get(), Items.REDSTONE, ModPotions.DOOM_POTION.getHolder().get());
        builder.addMix(ModPotions.DOOM_POTION.getHolder().get(), Items.GLOWSTONE_DUST, ModPotions.DOOM_POTION.getHolder().get());

        builder.addMix(Potions.AWKWARD, ModItems.BANANA_PEEL.get(), ModPotions.MONKEY_POTION.getHolder().get());
    }
}