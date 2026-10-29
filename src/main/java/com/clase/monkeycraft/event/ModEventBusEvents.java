package com.clase.monkeycraft.event;

import com.clase.monkeycraft.MonkeyCraft;
import com.clase.monkeycraft.entity.ModEntities;
import com.clase.monkeycraft.entity.client.MonkeyModel;
import com.clase.monkeycraft.entity.custom.MonkeyEntity;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MonkeyCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(MonkeyModel.LAYER_LOCATION, MonkeyModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.MONKEY.get(), MonkeyEntity.createAttributes().build());
    }
}
