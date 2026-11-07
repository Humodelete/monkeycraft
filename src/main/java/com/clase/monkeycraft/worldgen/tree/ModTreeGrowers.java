package com.clase.monkeycraft.worldgen.tree;

import com.clase.monkeycraft.MonkeyCraft;
import com.clase.monkeycraft.worldgen.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrowers {
    public static final TreeGrower PALM = new TreeGrower(
            MonkeyCraft.MOD_ID + ":palm",
            Optional.empty(),
            Optional.of(ModConfiguredFeatures.PALM_KEY),
            Optional.empty()
    );
}