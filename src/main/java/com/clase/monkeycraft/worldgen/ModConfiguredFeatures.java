package com.clase.monkeycraft.worldgen;

import com.clase.monkeycraft.MonkeyCraft;
import com.clase.monkeycraft.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> PALM_KEY = registerKey("palm_tree");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        context.register(PALM_KEY, new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.PALM_TREE_LOG.get()), // El bloque para el tronco
                new StraightTrunkPlacer(5, 2, 0), // Altura base 5, + hasta 2 extra aleatorios
                BlockStateProvider.simple(ModBlocks.PALM_TREE_LEAVES.get()), // El bloque para las hojas
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3), // Radio de hojas 2, altura 3
                new TwoLayersFeatureSize(1, 0, 1) // Espacio libre necesario alrededor para que pueda crecer
        ).build()));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID, name));
    }
}