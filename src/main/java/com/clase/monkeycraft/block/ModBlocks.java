package com.clase.monkeycraft.block;

import com.clase.monkeycraft.MonkeyCraft;
import com.clase.monkeycraft.block.custom.SkullBlock; // <- Cambiado a tu clase personalizada
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, MonkeyCraft.MOD_ID);

    public static final RegistryObject<Block> SKULL = BLOCKS.register("monkey_skull_t0",
            () -> new SkullBlock(BlockBehaviour.Properties.of()
                    .strength(1.0f)
                    .noOcclusion()));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}