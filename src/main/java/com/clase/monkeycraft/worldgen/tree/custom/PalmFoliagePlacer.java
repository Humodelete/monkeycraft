package com.clase.monkeycraft.worldgen.tree.custom;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class PalmFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<PalmFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
            foliagePlacerParts(instance).apply(instance, PalmFoliagePlacer::new));

    public PalmFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return ModTreePlacers.PALM_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader level, FoliageSetter blockSetter, RandomSource random, TreeConfiguration config, int maxFreeTreeHeight, FoliageAttachment attachment, int foliageHeight, int foliageRadius, int offset) {
        BlockPos center = attachment.pos().above(offset);

        tryPlaceLeaf(level, blockSetter, random, config, center);
        tryPlaceLeaf(level, blockSetter, random, config, center.above());

        for (Direction dir : Direction.Plane.HORIZONTAL) {
            BlockPos b1 = center.relative(dir);
            BlockPos b2 = b1.relative(dir).below();
            BlockPos b3 = b2.relative(dir).below();

            BlockPos conector1 = b1.below();
            BlockPos conector2 = b2.below();

            tryPlaceLeaf(level, blockSetter, random, config, b1);
            tryPlaceLeaf(level, blockSetter, random, config, b2);
            tryPlaceLeaf(level, blockSetter, random, config, b3);

            tryPlaceLeaf(level, blockSetter, random, config, conector1);
            tryPlaceLeaf(level, blockSetter, random, config, conector2);
        }
    }

    @Override
    public int foliageHeight(RandomSource random, int height, TreeConfiguration config) {
        return 3;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int localX, int localY, int localZ, int range, boolean large) {
        return false;
    }
}