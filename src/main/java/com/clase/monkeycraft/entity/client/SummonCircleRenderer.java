package com.clase.monkeycraft.entity.client;

import com.clase.monkeycraft.MonkeyCraft;
import com.clase.monkeycraft.entity.custom.SummonCircleEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix4f;

public class SummonCircleRenderer extends EntityRenderer<SummonCircleEntity> {

    private static final ResourceLocation CIRCLE_BASE = ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID, "textures/entity/summon_circle/dart_outside_rune.png");
    private static final ResourceLocation CIRCLE_CORE = ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID, "textures/entity/summon_circle/dart_inside_rune.png");

    public SummonCircleRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(SummonCircleEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();

        poseStack.translate(0.0D, 0.05D, 0.0D);

        poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));

        float size = 1.5F;

        VertexConsumer coreConsumer = buffer.getBuffer(RenderType.entityTranslucentCull(CIRCLE_CORE));
        drawQuad(poseStack.last().pose(), coreConsumer, size, packedLight);

        poseStack.pushPose();

        float time = entity.tickCount + partialTick;
        float angle = time * 5.0F;

        poseStack.mulPose(Axis.ZP.rotationDegrees(angle));

        poseStack.translate(0.0D, 0.0D, -0.01D);

        VertexConsumer baseConsumer = buffer.getBuffer(RenderType.entityTranslucentCull(CIRCLE_BASE));
        drawQuad(poseStack.last().pose(), baseConsumer, size, packedLight);

        poseStack.popPose();
        poseStack.popPose();

        super.render(entity, entityYaw, partialTick, poseStack, buffer, packedLight);
    }

    private void drawQuad(Matrix4f pose, VertexConsumer consumer, float size, int light) {
        consumer.addVertex(pose, -size, -size, 0.0F).setColor(255, 255, 255, 255).setUv(0.0F, 0.0F).setOverlay(OverlayTexture.NO_OVERLAY).setLight(light).setNormal(0.0F, 0.0F, 1.0F);
        consumer.addVertex(pose, -size, size, 0.0F).setColor(255, 255, 255, 255).setUv(0.0F, 1.0F).setOverlay(OverlayTexture.NO_OVERLAY).setLight(light).setNormal(0.0F, 0.0F, 1.0F);
        consumer.addVertex(pose, size, size, 0.0F).setColor(255, 255, 255, 255).setUv(1.0F, 1.0F).setOverlay(OverlayTexture.NO_OVERLAY).setLight(light).setNormal(0.0F, 0.0F, 1.0F);
        consumer.addVertex(pose, size, -size, 0.0F).setColor(255, 255, 255, 255).setUv(1.0F, 0.0F).setOverlay(OverlayTexture.NO_OVERLAY).setLight(light).setNormal(0.0F, 0.0F, 1.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(SummonCircleEntity entity) {
        return CIRCLE_BASE;
    }
}