package com.clase.monkeycraft.entity.client;

import com.clase.monkeycraft.MonkeyCraft;
import com.clase.monkeycraft.entity.custom.MonkeyTankEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MonkeyTankRenderer extends MobRenderer<MonkeyTankEntity, MonkeyTankModel<MonkeyTankEntity>> {
    public MonkeyTankRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new MonkeyTankModel<>(pContext.bakeLayer(MonkeyTankModel.LAYER_LOCATION)), 0.85f);
    }

    @Override
    public ResourceLocation getTextureLocation(MonkeyTankEntity pEntity) {
        return ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID, "textures/entity/monkey_tank/monkey_tank.png");
    }

    @Override
    public void render(MonkeyTankEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pPoseStack.scale(0.25f, 0.25f, 0.25f);
        } else {
            pPoseStack.scale(1.25f,1.25f, 1.25f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
