package com.clase.monkeycraft.entity.client;

import com.clase.monkeycraft.MonkeyCraft;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.WaterPatchModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.vehicle.Boat;
import org.joml.Quaternionf;

public class ModBoatRenderer extends EntityRenderer<Boat> {
    private final ResourceLocation texture;
    private final ListModel<Boat> model;

    public ModBoatRenderer(EntityRendererProvider.Context pContext, boolean hasChest) {
        super(pContext);
        this.shadowRadius = 0.8F;
        if (hasChest) {
            this.texture = ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID, "textures/entity/chest_boat/palm_tree.png");
            this.model = new ChestBoatModel(pContext.bakeLayer(
                    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID, "chest_boat/palm_tree"), "main")));
        } else {
            this.texture = ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID, "textures/entity/boat/palm_tree.png");
            this.model = new BoatModel(pContext.bakeLayer(
                    new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID, "boat/palm_tree"), "main")));
        }
    }

    @Override
    public ResourceLocation getTextureLocation(Boat pEntity) {
        return this.texture;
    }

    @Override
    public void render(Boat pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.pushPose();
        pPoseStack.translate(0.0F, 0.375F, 0.0F);
        pPoseStack.mulPose(Axis.YP.rotationDegrees(180.0F - pEntityYaw));
        float f = (float)pEntity.getHurtTime() - pPartialTicks;
        float f1 = pEntity.getDamage() - pPartialTicks;
        if (f1 < 0.0F) {
            f1 = 0.0F;
        }

        if (f > 0.0F) {
            pPoseStack.mulPose(Axis.XP.rotationDegrees(Mth.sin(f) * f * f1 / 10.0F * (float)pEntity.getHurtDir()));
        }

        float f2 = pEntity.getBubbleAngle(pPartialTicks);
        if (!Mth.equal(f2, 0.0F)) {
            pPoseStack.mulPose(new Quaternionf().setAngleAxis(pEntity.getBubbleAngle(pPartialTicks) * ((float)Math.PI / 180F), 1.0F, 0.0F, 1.0F));
        }

        pPoseStack.scale(-1.0F, -1.0F, 1.0F);
        pPoseStack.mulPose(Axis.YP.rotationDegrees(90.0F));
        this.model.setupAnim(pEntity, pPartialTicks, 0.0F, -0.1F, 0.0F, 0.0F);
        VertexConsumer vertexconsumer = pBuffer.getBuffer(this.model.renderType(this.getTextureLocation(pEntity)));

        this.model.renderToBuffer(pPoseStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF);

        if (!pEntity.isUnderWater()) {
            VertexConsumer vertexconsumer1 = pBuffer.getBuffer(RenderType.waterMask());
            if (this.model instanceof WaterPatchModel waterpatchmodel) {
                waterpatchmodel.waterPatch().render(pPoseStack, vertexconsumer1, pPackedLight, OverlayTexture.NO_OVERLAY);
            }
        }

        pPoseStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}