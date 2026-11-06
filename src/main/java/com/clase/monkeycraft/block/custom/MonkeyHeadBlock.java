package com.clase.monkeycraft.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MonkeyHeadBlock extends HorizontalDirectionalBlock implements Equipable {
    public static final MapCodec<MonkeyHeadBlock> CODEC = simpleCodec(MonkeyHeadBlock::new);

    protected static final VoxelShape SHAPE_NS = Block.box(2.5D, 0.0D, 3.0D, 13.5D, 10.0D, 13.0D);
    protected static final VoxelShape SHAPE_EW = Block.box(3.0D, 0.0D, 2.5D, 13.0D, 10.0D, 13.5D);

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        switch (state.getValue(FACING)) {
            case EAST:
            case WEST:
                return SHAPE_EW;
            case NORTH:
            case SOUTH:
            default:
                return SHAPE_NS;
        }
    }

    public MonkeyHeadBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, net.minecraft.core.Direction.NORTH));
    }

    @Override
    public EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.HEAD;
    }

    @Override
    protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return CODEC;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}