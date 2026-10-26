package com.clase.monkeycraft.item;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

/**
 * Class to handle the monkey summoner
 */
public class MonkeySummon0 extends Item {
    public MonkeySummon0(Properties properties) {
        super(properties);
    }

    /**
     * Method to spawn monkeys with the monkey spawner
     * @param context Context of the click (Dimension, player, block...)
     * @return Returns the spawn
     */
    @Override
    public InteractionResult useOn(
            UseOnContext context
    ) {
        if (!context.getLevel().isClientSide()) {
            Level dimension = context.getLevel();
            BlockPos pos = context.getClickedPos();

            Wolf wolf = EntityType.WOLF.create(dimension); //Replace Wolf with Monkey once it's done

            if (wolf != null) {
                wolf.moveTo(
                        pos.getX() + 0.5,
                        pos.getY() + 1,
                        pos.getZ() + 0.5
                );

                dimension.addFreshEntity(wolf);
            }
        }

        return InteractionResult.SUCCESS;
    }
}
