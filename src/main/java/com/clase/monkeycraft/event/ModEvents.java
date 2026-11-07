package com.clase.monkeycraft.event;

import com.clase.monkeycraft.MonkeyCraft;
import com.clase.monkeycraft.block.ModBlocks;
import com.clase.monkeycraft.entity.custom.MonkeyEntity;
import com.clase.monkeycraft.item.ModItems;
import com.clase.monkeycraft.item.ScepterItem;
import com.clase.monkeycraft.potion.ModPotions;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.brewing.BrewingRecipeRegisterEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MonkeyCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {

    @SubscribeEvent
    public static void onBrewingRecipeRegister(BrewingRecipeRegisterEvent event) {
        PotionBrewing.Builder builder = event.getBuilder();

        builder.addMix(Potions.AWKWARD, ModItems.BANANA_OF_DOOM_AND_DESPAIR.get(), ModPotions.DOOM_POTION.getHolder().get());
        builder.addMix(ModPotions.DOOM_POTION.getHolder().get(), Items.REDSTONE, ModPotions.LONG_DOOM_POTION.getHolder().get());
        builder.addMix(ModPotions.DOOM_POTION.getHolder().get(), Items.GLOWSTONE_DUST, ModPotions.STRONG_DOOM_POTION.getHolder().get());

        builder.addMix(Potions.AWKWARD, ModItems.BANANA_PEEL.get(), ModPotions.MONKEY_POTION.getHolder().get());
        builder.addMix(ModPotions.MONKEY_POTION.getHolder().get(), Items.REDSTONE, ModPotions.LONG_MONKEY_POTION.getHolder().get());
        builder.addMix(ModPotions.MONKEY_POTION.getHolder().get(), Items.GLOWSTONE_DUST, ModPotions.STRONG_MONKEY_POTION.getHolder().get());
    }

    @SubscribeEvent
    public static void onAnvilUpdate(AnvilUpdateEvent event) {
        ItemStack left = event.getLeft();
        ItemStack right = event.getRight();

        if (left.getItem() instanceof ScepterItem && right.is(ModItems.BANANA.get())) {
            if (left.getDamageValue() == 0) {
                return;
            }
            int currentDamage = left.getDamageValue();
            int bananasProvided = right.getCount();

            int bananasToConsume = Math.min(currentDamage, bananasProvided);

            ItemStack output = left.copy();
            output.setDamageValue(currentDamage - bananasToConsume);

            event.setOutput(output);
            event.setMaterialCost(bananasToConsume);
            event.setCost(bananasToConsume);
        }
    }
    @SubscribeEvent
    public static void onFurnaceFuelBurnTime(net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent event) {
        ItemStack itemStack = event.getItemStack();

        if (itemStack.is(ModBlocks.PALM_TREE_PLANKS.get().asItem()) ||
                itemStack.is(ModBlocks.PALM_TREE_STAIRS.get().asItem()) ||
                itemStack.is(ModBlocks.PALM_TREE_TRAPDOOR.get().asItem()) ||
                itemStack.is(ModBlocks.PALM_TREE_FENCE.get().asItem()) ||
                itemStack.is(ModBlocks.PALM_TREE_FENCE_GATE.get().asItem()) ||
                itemStack.is(ModBlocks.PALM_TREE_PRESSURE_PLATE.get().asItem())) {
            event.setBurnTime(300);
        }
        else if (itemStack.is(ModBlocks.PALM_TREE_DOOR.get().asItem()) ||
                itemStack.is(ModItems.PALM_TREE_SIGN.get())) {
            event.setBurnTime(200);
        }
        else if (itemStack.is(ModBlocks.PALM_TREE_SLAB.get().asItem())) {
            event.setBurnTime(150);
        }
        else if (itemStack.is(ModBlocks.PALM_TREE_BUTTON.get().asItem()) ||
                itemStack.is(ModBlocks.PALM_TREE_SAPLING.get().asItem())) {
            event.setBurnTime(100);
        }
        else if (itemStack.is(ModItems.PALM_TREE_HANGING_SIGN.get())) {
            event.setBurnTime(800);
        }
    }

    @SubscribeEvent
    public static void onMonkeyDeathByCreeper(LivingDropsEvent event) {
        if (event.getEntity() instanceof MonkeyEntity monkey) {
            if (event.getSource().getEntity() instanceof Creeper creeper) {
                if (creeper.isPowered() && creeper.canDropMobsSkull()) {
                    creeper.increaseDroppedSkulls();

                    ItemStack monkeyHead = new ItemStack(ModBlocks.MONKEY_HEAD.get());
                    ItemEntity headDrop = new ItemEntity(monkey.level(), monkey.getX(), monkey.getY(), monkey.getZ(), monkeyHead);

                    event.getDrops().add(headDrop);
                }
            }
        }
    }
}