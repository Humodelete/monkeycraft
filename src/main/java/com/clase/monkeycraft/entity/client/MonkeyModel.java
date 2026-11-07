package com.clase.monkeycraft.entity.client;

import com.clase.monkeycraft.MonkeyCraft;
import com.clase.monkeycraft.entity.custom.MonkeyEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class MonkeyModel<T extends MonkeyEntity> extends HierarchicalModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID, "monkey"), "main");
    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart left_arm;
    private final ModelPart right_arm;
    private final ModelPart Dardo;
    private final ModelPart Punta;
    private final ModelPart right_arm3;
    private final ModelPart right_arm5;
    private final ModelPart right_arm4;
    private final ModelPart right_arm2;
    private final ModelPart tail;
    private final ModelPart left_leg;
    private final ModelPart right_leg;

    public MonkeyModel(ModelPart root) {
        this.root = root.getChild("root");
        this.body = this.root.getChild("body");
        this.head = this.body.getChild("head");
        this.left_arm = this.body.getChild("left_arm");
        this.right_arm = this.body.getChild("right_arm");
        this.Dardo = this.right_arm.getChild("Dardo");
        this.Punta = this.Dardo.getChild("Punta");
        this.right_arm3 = this.Dardo.getChild("right_arm3");
        this.right_arm5 = this.right_arm3.getChild("right_arm5");
        this.right_arm4 = this.Dardo.getChild("right_arm4");
        this.right_arm2 = this.Dardo.getChild("right_arm2");
        this.tail = this.body.getChild("tail");
        this.left_leg = this.root.getChild("left_leg");
        this.right_leg = this.root.getChild("right_leg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(1, 23).addBox(-5.5F, -13.0F, -5.5F, 11.0F, 13.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -12.0F, 3.0F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(1, 3).addBox(-6.0F, -8.0F, -4.0F, 11.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(1, 60).addBox(-9.0F, -6.0F, 1.0F, 3.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(7, 60).addBox(5.0F, -6.0F, 1.0F, 3.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(19, 60).addBox(-1.0F, -11.0F, 1.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(58, 23).addBox(0.5F, -6.45F, -4.25F, 4.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(58, 25).addBox(-5.5F, -6.45F, -4.25F, 4.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, -15.0F, -3.0F));

        PartDefinition head_r1 = head.addOrReplaceChild("head_r1", CubeListBuilder.create().texOffs(13, 60).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -9.5F, 1.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(1, 43).addBox(0.0F, -2.0F, -2.5F, 4.0F, 12.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(5.5F, -11.0F, -2.0F));

        PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(19, 43).addBox(-5.0F, -2.0F, -2.5F, 4.0F, 12.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.5F, -11.0F, -2.0F));

        PartDefinition Dardo = right_arm.addOrReplaceChild("Dardo", CubeListBuilder.create(), PartPose.offset(-3.262F, 10.3286F, 1.5766F));

        PartDefinition Punta = Dardo.addOrReplaceChild("Punta", CubeListBuilder.create(), PartPose.offset(-0.238F, -11.9737F, -0.88F));

        PartDefinition right_arm3 = Dardo.addOrReplaceChild("right_arm3", CubeListBuilder.create(), PartPose.offset(0.017F, -0.1175F, 0.57F));

        PartDefinition right_arm5 = right_arm3.addOrReplaceChild("right_arm5", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.4688F, 0.0F, 0.0F, 0.0F, -3.1416F));

        PartDefinition right_arm4 = Dardo.addOrReplaceChild("right_arm4", CubeListBuilder.create(), PartPose.offset(-0.308F, 0.0513F, 0.57F));

        PartDefinition right_arm2 = Dardo.addOrReplaceChild("right_arm2", CubeListBuilder.create(), PartPose.offset(0.317F, 0.0513F, 0.57F));

        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(37, 59).addBox(-1.0F, -14.5F, 3.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(25, 60).addBox(-1.0F, -18.5F, 6.5F, 1.0F, 1.0F, 2.5F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 12.0F, -3.0F));

        PartDefinition body_r1 = tail.addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(31, 60).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -17.5F, 6.0F, 1.5708F, 0.0F, 0.0F));

        PartDefinition body_r2 = tail.addOrReplaceChild("body_r2", CubeListBuilder.create().texOffs(49, 59).addBox(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -15.5F, 8.5F, 1.5708F, 0.0F, 0.0F));

        PartDefinition left_leg = root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(37, 23).addBox(-2.0F, 0.0F, -3.0F, 5.0F, 12.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -12.0F, 1.0F));

        PartDefinition right_leg = root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(37, 41).addBox(-4.0F, 0.0F, -3.0F, 5.0F, 12.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -12.0F, 1.0F));

        return LayerDefinition.create(meshdefinition, 68, 68);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch);

        this.animateWalk(MonkeyAnimations.ANIM_MONKEY_WALK, limbSwing, limbSwingAmount, 1.5F, 4.0F);
        this.animate(entity.attackAnimationState, MonkeyAnimations.ANIM_MONKEY_ATTACK, ageInTicks, 1F);
    }

    private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch) {
        pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
        pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

        this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        root.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return root;
    }
}
