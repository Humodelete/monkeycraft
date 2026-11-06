package com.clase.monkeycraft.block;

import com.clase.monkeycraft.MonkeyCraft;
import com.clase.monkeycraft.block.custom.ModFlammableRotatedPillarBlock;
import com.clase.monkeycraft.block.entity.ModHangingSignBlockEntity;
import com.clase.monkeycraft.block.entity.ModSignBlockEntity;
import com.clase.monkeycraft.item.ModItems;
import com.clase.monkeycraft.worldgen.tree.ModTreeGrowers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, MonkeyCraft.MOD_ID);
    public static final RegistryObject<Block> MONKEY_HEAD = registerBlock("monkey_head",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PLAYER_HEAD).noOcclusion()));

    public static final BlockSetType PALM_SET_TYPE = BlockSetType.register(new BlockSetType(MonkeyCraft.MOD_ID + ":palm_tree"));

    public static final WoodType PALM_WOOD_TYPE = WoodType.register(new WoodType(MonkeyCraft.MOD_ID + ":palm_tree", PALM_SET_TYPE));

    public static final RegistryObject<RotatedPillarBlock> PALM_TREE_LOG = registerBlock("palm_tree_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));

    public static final RegistryObject<RotatedPillarBlock> PALM_TREE_WOOD = registerBlock("palm_tree_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)));

    public static final RegistryObject<RotatedPillarBlock> STRIPPED_PALM_TREE_WOOD = registerBlock("stripped_palm_tree_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)));

    public static final RegistryObject<RotatedPillarBlock> STRIPPED_PALM_TREE_LOG = registerBlock("stripped_palm_tree_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)));

    public static final RegistryObject<Block> PALM_TREE_PLANKS = registerBlock("palm_tree_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });

    public static final RegistryObject<Block> PALM_TREE_LEAVES = registerBlock("palm_tree_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }
            });

    public static final RegistryObject<Block> PALM_TREE_SAPLING = registerBlock("palm_tree_sapling",
            () -> new SaplingBlock(ModTreeGrowers.PALM, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));

    public static final RegistryObject<Block> PALM_TREE_DOOR = registerBlock("palm_tree_door",
            () -> new DoorBlock(PALM_SET_TYPE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR)));

    public static final RegistryObject<Block> PALM_TREE_TRAPDOOR = registerBlock("palm_tree_trapdoor",
            () -> new TrapDoorBlock(PALM_SET_TYPE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)));

    public static final RegistryObject<Block> PALM_TREE_SIGN = BLOCKS.register("palm_tree_sign",
            () -> new StandingSignBlock(PALM_WOOD_TYPE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN)) {
                @Override
                public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
                    return new ModSignBlockEntity(pPos, pState);
                }
            });

    public static final RegistryObject<Block> PALM_TREE_WALL_SIGN = BLOCKS.register("palm_tree_wall_sign",
            () -> new WallSignBlock(PALM_WOOD_TYPE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_SIGN)) {
                @Override
                public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
                    return new ModSignBlockEntity(pPos, pState);
                }
            });

    public static final RegistryObject<Block> PALM_TREE_FENCE = registerBlock("palm_tree_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));

    public static final RegistryObject<Block> PALM_TREE_FENCE_GATE = registerBlock("palm_tree_fence_gate",
            () -> new FenceGateBlock(PALM_WOOD_TYPE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)));

    public static final RegistryObject<Block> PALM_TREE_HANGING_SIGN = BLOCKS.register("palm_tree_hanging_sign",
            () -> new CeilingHangingSignBlock(PALM_WOOD_TYPE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN)) {
                @Override
                public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
                    return new ModHangingSignBlockEntity(pPos, pState);
                }
            });

    public static final RegistryObject<Block> PALM_TREE_WALL_HANGING_SIGN = BLOCKS.register("palm_tree_wall_hanging_sign",
            () -> new WallHangingSignBlock(PALM_WOOD_TYPE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_HANGING_SIGN)) {
                @Override
                public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
                    return new ModHangingSignBlockEntity(pPos, pState);
                }
            });
    public static final RegistryObject<Block> PALM_TREE_STAIRS = registerBlock("palm_tree_stairs",
            () -> new StairBlock(ModBlocks.PALM_TREE_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)));

    public static final RegistryObject<Block> PALM_TREE_SLAB = registerBlock("palm_tree_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));

    public static final RegistryObject<Block> PALM_TREE_BUTTON = registerBlock("palm_tree_button",
            () -> new ButtonBlock(BlockSetType.OAK, 30, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON)));

    public static final RegistryObject<Block> PALM_TREE_PRESSURE_PLATE = registerBlock("palm_tree_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}