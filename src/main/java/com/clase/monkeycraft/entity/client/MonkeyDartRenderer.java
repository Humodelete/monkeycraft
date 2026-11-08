package com.clase.monkeycraft.entity.client;

import com.clase.monkeycraft.MonkeyCraft;
import com.clase.monkeycraft.entity.custom.MonkeyDartEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MonkeyDartRenderer extends MobRenderer<MonkeyDartEntity, MonkeyDartModel<MonkeyDartEntity>> {
    public MonkeyDartRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new MonkeyDartModel<>(pContext.bakeLayer(MonkeyDartModel.LAYER_LOCATION)), 0.85f);
    }

    @Override
    public ResourceLocation getTextureLocation(MonkeyDartEntity pEntity) {
        return ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID, "textures/entity/monkey_dart/monkey_dart.png");
    }

    @Override
    public void render(MonkeyDartEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pPoseStack.scale(0.25f, 0.25f, 0.25f);
        } else {
            pPoseStack.scale(0.6f,0.6f, 0.6f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
