package com.clase.monkeycraft.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * Class to handle the monkey summoner
 */
public class MonkeySummon extends Item {
    public MonkeySummon(Properties properties) {
        super(properties);
    }

    /**
     * Method to summon an entity on right click with item
     * @param dimension Current dimension the player is in (Overworld, Nether, End)
     * @param player Player entity
     * @param hand The hand of the player (main hand or off-hand)
     * @return The action of spawning a monkey
     */
    @Override
    public InteractionResultHolder<ItemStack> use(
            Level dimension,
            Player player,
            InteractionHand hand
    ) {
        ItemStack stack = player.getItemInHand(hand);

        if (!dimension.isClientSide) {
            Wolf monkey = new Wolf(EntityType.WOLF, dimension); //Replace this with a monkey when the mob is ready

            monkey.moveTo(      //Figure out how to spawn it where you are looking at instead of on you
                    player.getX(),
                    player.getY(),
                    player.getZ(),
                    player.getYRot(),
                    0
            );

            dimension.addFreshEntity(monkey);
        }

        return InteractionResultHolder.sidedSuccess(
                stack,
                dimension.isClientSide()
        );
    }
}
