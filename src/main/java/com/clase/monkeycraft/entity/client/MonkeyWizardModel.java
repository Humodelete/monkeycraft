package com.clase.monkeycraft.entity.client;

import com.clase.monkeycraft.entity.custom.MonkeyWizardEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class MonkeyWizardModel<T extends MonkeyWizardEntity> extends HierarchicalModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("modid", "monkey_wizard"), "main");
    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart left_arm;
    private final ModelPart right_arm;
    private final ModelPart tail;
    private final ModelPart left_leg;
    private final ModelPart right_leg;

    public MonkeyWizardModel(ModelPart root) {
        this.root = root.getChild("root");
        this.body = this.root.getChild("body");
        this.head = this.body.getChild("head");
        this.left_arm = this.body.getChild("left_arm");
        this.right_arm = this.body.getChild("right_arm");
        this.tail = this.body.getChild("tail");
        this.left_leg = this.root.getChild("left_leg");
        this.right_leg = this.root.getChild("right_leg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(42, 41).addBox(-4.6667F, -9.45F, -4.8333F, 9.0F, 10.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(1, 57).addBox(-4.6667F, -9.45F, -5.3333F, 9.0F, 16.0F, 7.0F, new CubeDeformation(0.5F)), PartPose.offset(-0.3333F, -10.55F, 2.3333F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(2, 38).addBox(-5.0F, -4.0F, -4.0F, 9.0F, 8.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(77, 51).addBox(-8.0F, -3.0F, 1.0F, 3.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(77, 45).addBox(4.0F, -3.0F, 1.0F, 3.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(3, 20).addBox(-6.0F, -6.0F, -5.0F, 11.0F, 2.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(48, 24).addBox(-10.0F, -4.0F, -9.0F, 19.0F, 0.0F, 19.0F, new CubeDeformation(0.0F))
                .texOffs(35, 41).addBox(1.0F, -4.0F, -4.5F, 3.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(4, 21).addBox(-5.0F, -4.0F, -4.5F, 3.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.3333F, -13.45F, -2.3333F));

        PartDefinition head_r1 = head.addOrReplaceChild("head_r1", CubeListBuilder.create().texOffs(95, 46).addBox(-2.5F, -2.5F, 0.0F, 5.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -8.0241F, 8.4843F, 0.1745F, 0.0F, 0.0F));

        PartDefinition head_r2 = head.addOrReplaceChild("head_r2", CubeListBuilder.create().texOffs(89, 46).addBox(-1.0F, -0.5993F, -36.2604F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, -1.789F, 0.0F, 0.0F));

        PartDefinition head_r3 = head.addOrReplaceChild("head_r3", CubeListBuilder.create().texOffs(76, 59).addBox(-2.0F, -22.2776F, -29.8205F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, -1.0472F, 0.0F, 0.0F));

        PartDefinition head_r4 = head.addOrReplaceChild("head_r4", CubeListBuilder.create().texOffs(77, 72).addBox(-3.0F, -30.8923F, -17.7942F, 5.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, -0.5236F, 0.0F, 0.0F));

        PartDefinition head_r5 = head.addOrReplaceChild("head_r5", CubeListBuilder.create().texOffs(65, 11).addBox(-4.0F, -32.3727F, -8.17F, 7.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

        PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(56, 62).mirror().addBox(-2.125F, -2.15F, -2.5F, 4.0F, 12.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(89, 1).mirror().addBox(-1.375F, -1.85F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(6.4583F, -7.6F, -1.8333F));

        PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(35, 62).addBox(-1.875F, -2.15F, -2.5F, 4.0F, 12.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(56, 1).addBox(-1.625F, -1.85F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.7917F, -7.6F, -1.8333F));

        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(43, 21).addBox(-1.0F, -12.5F, 3.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(91, 54).addBox(-1.0F, -15.5F, 7.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.3333F, 10.55F, -2.3333F));

        PartDefinition body_r1 = tail.addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(91, 59).addBox(0.0F, 6.0F, 10.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(90, 64).addBox(0.0F, 8.0F, 8.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -3.5F, 0.0F, 1.5708F, 0.0F, 0.0F));

        PartDefinition left_leg = root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(12, 1).mirror().addBox(-2.0F, 2.0F, -2.0F, 4.0F, 10.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.0F, -12.0F, 0.0F));

        PartDefinition right_leg = root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(36, 1).addBox(-3.0F, 2.0F, -2.0F, 4.0F, 10.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -12.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 106, 85);
    }

    @Override
    public void setupAnim(MonkeyWizardEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch);

        this.animateWalk(MonkeyWizardAnimations.ANIM_MONKEY_WIZARD_WALK, limbSwing, limbSwingAmount, 1.5F, 4.0F);
        this.animate(entity.attackAnimationState, MonkeyWizardAnimations.ANIM_MONKEY_WIZARD_CAST, ageInTicks, 1F);
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
