package com.clase.monkeycraft.entity.client;

import com.clase.monkeycraft.entity.custom.MonkeyJesterEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class MonkeyJesterModel<T extends MonkeyJesterEntity> extends HierarchicalModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("modid", "monkey_jester"), "main");
    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart Buffon_tail_1;
    private final ModelPart Buffon_tail_2;
    private final ModelPart Buffon_tail_3;
    private final ModelPart Buffon_tail_4;
    private final ModelPart left_arm;
    private final ModelPart right_arm;
    private final ModelPart baston;
    private final ModelPart tail;
    private final ModelPart left_leg;
    private final ModelPart right_leg;
    private final ModelPart baston2;

    public MonkeyJesterModel(ModelPart root) {
        this.root = root.getChild("root");
        this.body = this.root.getChild("body");
        this.head = this.body.getChild("head");
        this.Buffon_tail_1 = this.head.getChild("Buffon_tail_1");
        this.Buffon_tail_2 = this.head.getChild("Buffon_tail_2");
        this.Buffon_tail_3 = this.head.getChild("Buffon_tail_3");
        this.Buffon_tail_4 = this.head.getChild("Buffon_tail_4");
        this.left_arm = this.body.getChild("left_arm");
        this.right_arm = this.body.getChild("right_arm");
        this.baston = this.right_arm.getChild("baston");
        this.tail = this.body.getChild("tail");
        this.left_leg = this.root.getChild("left_leg");
        this.right_leg = this.root.getChild("right_leg");
        this.baston2 = this.root.getChild("baston2");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 43).addBox(-4.8333F, -9.5934F, -2.6667F, 9.0F, 10.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 20).addBox(-4.8333F, -9.5934F, -3.1667F, 9.0F, 16.0F, 7.0F, new CubeDeformation(0.5F)), PartPose.offset(-0.1667F, -10.4066F, 0.1667F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(32, 20).addBox(-5.0F, -4.0F, -4.0F, 9.0F, 8.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-6.0F, -5.6F, -5.0F, 11.0F, 9.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(32, 37).addBox(-5.0F, -7.6F, -4.0F, 9.0F, 2.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(62, 16).addBox(-8.0F, -3.0F, 1.0F, 3.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(42, 73).addBox(4.0F, -3.0F, 1.0F, 3.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(56, 17).addBox(1.0F, -4.0F, -4.5F, 3.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(68, 46).addBox(-5.0F, -4.2F, -4.5F, 3.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.1667F, -13.5934F, -0.1667F));

        PartDefinition Buffon_tail_1 = head.addOrReplaceChild("Buffon_tail_1", CubeListBuilder.create(), PartPose.offset(-0.5F, -8.6F, 0.0F));

        PartDefinition Buffon_tail_1_r1 = Buffon_tail_1.addOrReplaceChild("Buffon_tail_1_r1", CubeListBuilder.create().texOffs(18, 65).addBox(4.1569F, -4.4142F, -1.5F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.8F, 0.0F, 0.6F, 0.4597F, 0.4176F, 0.8845F));

        PartDefinition Buffon_tail_1_r2 = Buffon_tail_1.addOrReplaceChild("Buffon_tail_1_r2", CubeListBuilder.create().texOffs(68, 16).addBox(2.0391F, -8.7996F, -1.25F, 3.0F, 3.0F, 3.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.8F, 0.0F, 0.6F, 0.582F, 0.1975F, 1.281F));

        PartDefinition Buffon_tail_1_r3 = Buffon_tail_1.addOrReplaceChild("Buffon_tail_1_r3", CubeListBuilder.create().texOffs(68, 22).addBox(-4.0401F, -11.0255F, -1.125F, 3.0F, 3.0F, 3.25F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.8F, 0.0F, 0.6F, 0.582F, -0.1975F, 1.8606F));

        PartDefinition Buffon_tail_1_r4 = Buffon_tail_1.addOrReplaceChild("Buffon_tail_1_r4", CubeListBuilder.create().texOffs(68, 40).addBox(-10.0464F, -8.8972F, -1.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.8F, 0.0F, 0.6F, 0.3368F, -0.5198F, 2.5276F));

        PartDefinition Buffon_tail_1_r5 = Buffon_tail_1.addOrReplaceChild("Buffon_tail_1_r5", CubeListBuilder.create().texOffs(76, 10).addBox(-13.15F, -4.7F, -0.875F, 3.0F, 2.0F, 2.75F, new CubeDeformation(0.0F))
                .texOffs(76, 62).addBox(-13.15F, -6.7F, -0.75F, 3.0F, 2.0F, 2.5F, new CubeDeformation(0.0F))
                .texOffs(38, 79).addBox(-12.55F, -8.7F, -0.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.8F, 0.0F, 0.6F, 0.0F, -0.6109F, -3.1416F));

        PartDefinition Buffon_tail_2 = head.addOrReplaceChild("Buffon_tail_2", CubeListBuilder.create(), PartPose.offset(-0.5F, -11.6F, 0.0F));

        PartDefinition Buffon_tail_2_r1 = Buffon_tail_2.addOrReplaceChild("Buffon_tail_2_r1", CubeListBuilder.create().texOffs(62, 63).addBox(-8.5711F, -1.5858F, -1.5F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4597F, -0.4176F, -0.8845F));

        PartDefinition Buffon_tail_2_r2 = Buffon_tail_2.addOrReplaceChild("Buffon_tail_2_r2", CubeListBuilder.create().texOffs(66, 56).addBox(-7.5161F, -6.8339F, -1.25F, 3.0F, 3.0F, 3.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.582F, -0.1975F, -1.281F));

        PartDefinition Buffon_tail_2_r3 = Buffon_tail_2.addOrReplaceChild("Buffon_tail_2_r3", CubeListBuilder.create().texOffs(68, 28).addBox(-2.1039F, -11.1589F, -1.125F, 3.0F, 3.0F, 3.25F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.582F, 0.1975F, -1.8606F));

        PartDefinition Buffon_tail_2_r4 = Buffon_tail_2.addOrReplaceChild("Buffon_tail_2_r4", CubeListBuilder.create().texOffs(68, 34).addBox(4.7237F, -11.0203F, -1.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3368F, 0.5198F, -2.5276F));

        PartDefinition Buffon_tail_2_r5 = Buffon_tail_2.addOrReplaceChild("Buffon_tail_2_r5", CubeListBuilder.create().texOffs(76, 6).addBox(9.2F, -7.7F, -0.875F, 3.0F, 2.0F, 2.75F, new CubeDeformation(0.0F))
                .texOffs(76, 66).addBox(9.2F, -9.7F, -0.75F, 3.0F, 2.0F, 2.5F, new CubeDeformation(0.0F))
                .texOffs(30, 79).addBox(9.8F, -11.7F, -0.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.6109F, -3.1416F));

        PartDefinition Buffon_tail_3 = head.addOrReplaceChild("Buffon_tail_3", CubeListBuilder.create(), PartPose.offset(-0.5F, -8.6F, 0.0F));

        PartDefinition Buffon_tail_3_r1 = Buffon_tail_3.addOrReplaceChild("Buffon_tail_3_r1", CubeListBuilder.create().texOffs(32, 65).addBox(4.1569F, -4.4142F, -1.5F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.6155F, -0.5236F, 0.9553F));

        PartDefinition Buffon_tail_3_r2 = Buffon_tail_3.addOrReplaceChild("Buffon_tail_3_r2", CubeListBuilder.create().texOffs(30, 73).addBox(2.0391F, -8.7996F, -1.25F, 3.0F, 3.0F, 3.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.7543F, -0.2443F, 1.3189F));

        PartDefinition Buffon_tail_3_r3 = Buffon_tail_3.addOrReplaceChild("Buffon_tail_3_r3", CubeListBuilder.create().texOffs(18, 73).addBox(-4.0401F, -11.0255F, -1.125F, 3.0F, 3.0F, 3.25F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.7543F, 0.2443F, 1.8227F));

        PartDefinition Buffon_tail_3_r4 = Buffon_tail_3.addOrReplaceChild("Buffon_tail_3_r4", CubeListBuilder.create().texOffs(62, 71).addBox(-10.0464F, -8.8972F, -1.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4636F, 0.6591F, 2.4569F));

        PartDefinition Buffon_tail_3_r5 = Buffon_tail_3.addOrReplaceChild("Buffon_tail_3_r5", CubeListBuilder.create().texOffs(72, 77).addBox(-13.15F, -4.7F, -0.875F, 3.0F, 2.0F, 2.75F, new CubeDeformation(0.0F))
                .texOffs(62, 77).addBox(-13.15F, -6.7F, -0.75F, 3.0F, 2.0F, 2.5F, new CubeDeformation(0.0F))
                .texOffs(46, 79).addBox(-12.55F, -8.7F, -0.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, -3.1416F));

        PartDefinition Buffon_tail_4 = head.addOrReplaceChild("Buffon_tail_4", CubeListBuilder.create(), PartPose.offset(-0.5F, -11.6F, 0.0F));

        PartDefinition Buffon_tail_4_r1 = Buffon_tail_4.addOrReplaceChild("Buffon_tail_4_r1", CubeListBuilder.create().texOffs(74, 71).addBox(-2.1039F, -11.1589F, -1.125F, 3.0F, 3.0F, 3.25F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7F, 0.0F, 0.7F, -0.7543F, -0.2443F, -1.8227F));

        PartDefinition Buffon_tail_4_r2 = Buffon_tail_4.addOrReplaceChild("Buffon_tail_4_r2", CubeListBuilder.create().texOffs(66, 48).addBox(-8.5711F, -1.5858F, -1.5F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7F, 0.0F, 0.7F, -0.6155F, 0.5236F, -0.9553F));

        PartDefinition Buffon_tail_4_r3 = Buffon_tail_4.addOrReplaceChild("Buffon_tail_4_r3", CubeListBuilder.create().texOffs(76, 0).addBox(-7.5161F, -6.8339F, -1.25F, 3.0F, 3.0F, 3.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7F, 0.0F, 0.7F, -0.7543F, 0.2443F, -1.3189F));

        PartDefinition Buffon_tail_4_r4 = Buffon_tail_4.addOrReplaceChild("Buffon_tail_4_r4", CubeListBuilder.create().texOffs(0, 74).addBox(4.7237F, -11.0203F, -1.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7F, 0.0F, 0.7F, -0.4636F, -0.6591F, -2.4569F));

        PartDefinition Buffon_tail_4_r5 = Buffon_tail_4.addOrReplaceChild("Buffon_tail_4_r5", CubeListBuilder.create().texOffs(12, 79).addBox(9.2F, -7.7F, -0.875F, 3.0F, 2.0F, 2.75F, new CubeDeformation(0.0F))
                .texOffs(54, 79).addBox(9.8F, -11.7F, -0.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7F, 0.0F, 0.7F, 0.0F, -0.7854F, -3.1416F));

        PartDefinition Buffon_tail_4_r6 = Buffon_tail_4.addOrReplaceChild("Buffon_tail_4_r6", CubeListBuilder.create().texOffs(78, 56).addBox(9.2F, -9.7F, -0.75F, 3.0F, 2.0F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, -3.1416F));

        PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(44, 0).addBox(-2.125F, -0.15F, -2.5F, 4.0F, 12.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(62, 0).addBox(-1.375F, 0.15F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(6.2917F, -9.7434F, 0.3333F));

        PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(30, 48).addBox(-1.8333F, 0.5917F, -2.092F, 4.0F, 12.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(48, 63).addBox(-1.5833F, 0.8917F, -1.592F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -10.2288F, -0.008F, -1.0036F, 0.0F, 0.0F));

        PartDefinition baston = right_arm.addOrReplaceChild("baston", CubeListBuilder.create(), PartPose.offset(-0.0833F, 15.1486F, 1.4537F));

        PartDefinition baston_r1 = baston.addOrReplaceChild("baston_r1", CubeListBuilder.create().texOffs(86, 70).addBox(-1.0F, -11.0F, -1.0F, 2.0F, 22.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.0036F, 0.0F, 0.0F));

        PartDefinition baston_r2 = baston.addOrReplaceChild("baston_r2", CubeListBuilder.create().texOffs(71, 85).addBox(-1.5F, 3.0F, -2.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.1569F, -13.5458F, 1.0036F, 0.0F, 0.0F));

        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(18, 59).addBox(-1.0F, -12.5F, 3.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(44, 17).addBox(-1.0F, -15.5F, 7.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.1667F, 10.4066F, -0.1667F));

        PartDefinition body_r1 = tail.addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(50, 17).addBox(0.0F, 6.0F, 10.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(22, 79).addBox(0.0F, 8.0F, 8.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -3.5F, 0.0F, 1.5708F, 0.0F, 0.0F));

        PartDefinition left_leg = root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(48, 48).addBox(-2.0F, 2.0F, -2.0F, 4.0F, 10.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -12.0F, 0.0F));

        PartDefinition right_leg = root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 59).addBox(-3.0F, 2.0F, -2.0F, 4.0F, 10.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -12.0F, 0.0F));

        PartDefinition baston2 = root.addOrReplaceChild("baston2", CubeListBuilder.create().texOffs(71, 85).addBox(0.5F, -13.1331F, -1.5457F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(86, 70).addBox(1.0F, -10.7881F, -1.0F, 2.0F, 22.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-9.25F, -11.4869F, -11.85F));

        return LayerDefinition.create(meshdefinition, 94, 94);
    }

    @Override
    public void setupAnim(MonkeyJesterEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch);

        this.animateWalk(MonkeyJesterAnimations.ANIM_MONKEY_JESTER_WALK, limbSwing, limbSwingAmount, 1.5F, 4.0F);
        this.animate(entity.castAnimationState, MonkeyJesterAnimations.ANIM_MONKEY_JESTER_CAST, ageInTicks, 1F);
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
