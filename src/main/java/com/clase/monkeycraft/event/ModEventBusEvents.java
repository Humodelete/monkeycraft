package com.clase.monkeycraft.event;

import com.clase.monkeycraft.MonkeyCraft;
import com.clase.monkeycraft.entity.ModEntities;
import com.clase.monkeycraft.entity.client.*;
import com.clase.monkeycraft.entity.custom.*;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MonkeyCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(MonkeyModel.LAYER_LOCATION, MonkeyModel::createBodyLayer);
        event.registerLayerDefinition(MonkeyTankModel.LAYER_LOCATION, MonkeyTankModel::createBodyLayer);
        event.registerLayerDefinition(MonkeyDartModel.LAYER_LOCATION, MonkeyDartModel::createBodyLayer);
        event.registerLayerDefinition(MonkeyWizardModel.LAYER_LOCATION, MonkeyWizardModel::createBodyLayer);
        event.registerLayerDefinition(MonkeyJesterModel.LAYER_LOCATION, MonkeyJesterModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.MONKEY.get(), MonkeyEntity.createAttributes().build());
        event.put(ModEntities.MONKEY_TANK.get(), MonkeyTankEntity.createAttributes().build());
        event.put(ModEntities.MONKEY_DART.get(), MonkeyDartEntity.createAttributes().build());
        event.put(ModEntities.MONKEY_WIZARD.get(), MonkeyWizardEntity.createAttributes().build());
        event.put(ModEntities.MONKEY_JESTER.get(), MonkeyJesterEntity.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawnPlacement(SpawnPlacementRegisterEvent event){
        event.register(ModEntities.MONKEY.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
    }
}
