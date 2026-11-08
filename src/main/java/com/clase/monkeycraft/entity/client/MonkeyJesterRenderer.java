package com.clase.monkeycraft.entity.client;

import com.clase.monkeycraft.MonkeyCraft;
import com.clase.monkeycraft.entity.custom.MonkeyJesterEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MonkeyJesterRenderer extends MobRenderer<MonkeyJesterEntity, MonkeyJesterModel<MonkeyJesterEntity>> {
    public MonkeyJesterRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new MonkeyJesterModel<>(pContext.bakeLayer(MonkeyJesterModel.LAYER_LOCATION)), 0.85f);
    }

    @Override
    public ResourceLocation getTextureLocation(MonkeyJesterEntity pEntity) {
        return ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID, "textures/entity/monkey_jester/monkey_jester.png");
    }

    @Override
    public void render(MonkeyJesterEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pPoseStack.scale(0.25f, 0.25f, 0.25f);
        } else {
            pPoseStack.scale(0.75f,0.75f, 0.75f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
