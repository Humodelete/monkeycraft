package com.clase.monkeycraft.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BananaItem extends Item {
    public BananaItem(Properties pProperties){
        super (pProperties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving) {
        ItemStack itemstack = super.finishUsingItem(pStack, pLevel, pEntityLiving);

        if (pEntityLiving instanceof Player player && !player.getAbilities().instabuild) {
            if (itemstack.isEmpty()) {
                return new ItemStack(ModItems.BANANA_PEEL.get());
            } else {
                player.getInventory().add(new ItemStack(ModItems.BANANA_PEEL.get()));
            }
        }
        return itemstack;
    }
}
