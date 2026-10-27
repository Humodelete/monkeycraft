package com.clase.monkeycraft.item;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

/**
 * Class to handle the monkey summoner
 */
public class MonkeyScepter2 extends Item {
    public MonkeyScepter2(Properties properties) {
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

            Zombie zombie = EntityType.ZOMBIE.create(dimension); //Replace Wolf with Monkey once it's done

            if (zombie != null) {
                zombie.moveTo(
                        pos.getX() + 0.5,
                        pos.getY() + 1,
                        pos.getZ() + 0.5
                );

                dimension.addFreshEntity(zombie);
            }
        }

        return InteractionResult.SUCCESS;
    }
}
