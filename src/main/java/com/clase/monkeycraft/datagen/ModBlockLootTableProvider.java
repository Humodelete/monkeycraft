package com.clase.monkeycraft.datagen;

import com.clase.monkeycraft.block.ModBlocks;
import com.clase.monkeycraft.item.ModItems;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {

    public ModBlockLootTableProvider(HolderLookup.Provider pRegistries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), pRegistries);
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.PALM_TREE_LOG.get());
        this.dropSelf(ModBlocks.PALM_TREE_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_PALM_TREE_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_PALM_TREE_WOOD.get());
        this.dropSelf(ModBlocks.PALM_TREE_PLANKS.get());
        this.dropSelf(ModBlocks.PALM_TREE_SAPLING.get());

        this.add(ModBlocks.PALM_TREE_LEAVES.get(), block ->
                createLeavesDrops(block, ModBlocks.PALM_TREE_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES)
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .when(this.hasSilkTouch().invert()) // Evita que caiga con Toque de Seda
                                .when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS)).invert()) // Evita que caiga con Tijeras
                                .add(LootItem.lootTableItem(ModItems.BANANA.get()))
                                .when(LootItemRandomChanceCondition.randomChance(0.05f)) // 5% de probabilidad
                        )
        );
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream()
                .map(RegistryObject::get)
                .filter(block -> block != ModBlocks.SKULL.get())
                ::iterator;    }

}