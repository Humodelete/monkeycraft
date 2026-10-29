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

public class MonkeyModel<T extends MonkeyEntity> extends HierarchicalModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID, "monkey"), "main");
    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart right_tendril;
    private final ModelPart left_tendril;
    private final ModelPart right_arm;
    private final ModelPart left_arm;
    private final ModelPart right_leg;
    private final ModelPart left_leg;

    public MonkeyModel(ModelPart root) {
        this.root = root.getChild("root");
        this.body = this.root.getChild("body");
        this.head = this.body.getChild("head");
        this.right_tendril = this.head.getChild("right_tendril");
        this.left_tendril = this.head.getChild("left_tendril");
        this.right_arm = this.body.getChild("right_arm");
        this.left_arm = this.body.getChild("left_arm");
        this.right_leg = this.root.getChild("right_leg");
        this.left_leg = this.root.getChild("left_leg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(59, 5).addBox(-9.0F, -13.0F, -6.0F, 18.0F, 23.0F, 15.0F, new CubeDeformation(0.0F))
                .texOffs(105, 87).addBox(-1.0F, 7.0F, 9.0F, 2.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(114, 118).addBox(-1.0F, 1.0F, 13.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -21.0F, 0.0F));

        PartDefinition body_r1 = body.addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(99, 119).addBox(-1.0F, 13.0F, 16.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(102, 101).addBox(-1.0F, 17.0F, 12.0F, 2.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 21.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(4, 9).addBox(-7.0F, -13.0F, -5.0F, 14.0F, 13.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(57, 71).addBox(-3.0F, -18.0F, 1.0F, 6.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(2, 11).addBox(-7.0F, -13.0F, -6.0F, 5.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(49, 11).addBox(2.0F, -13.0F, -6.0F, 5.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -13.0F, 0.0F));

        PartDefinition head_r1 = head.addOrReplaceChild("head_r1", CubeListBuilder.create().texOffs(105, 71).addBox(-2.0F, -52.0F, 0.0F, 6.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 34.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition right_tendril = head.addOrReplaceChild("right_tendril", CubeListBuilder.create().texOffs(93, 78).addBox(-4.0F, 2.0F, 1.0F, 5.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, -12.0F, 0.0F));

        PartDefinition left_tendril = head.addOrReplaceChild("left_tendril", CubeListBuilder.create().texOffs(71, 78).addBox(-1.0F, 2.0F, 1.0F, 5.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, -12.0F, 0.0F));

        PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(59, 91).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(11, 42).addBox(-5.0F, 22.0F, -5.0F, 10.0F, 6.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(-13.0F, -13.0F, 1.0F));

        PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(15, 91).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(11, 68).addBox(-5.0F, 22.0F, -5.0F, 10.0F, 6.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(13.0F, -13.0F, 1.0F));

        PartDefinition right_leg = root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(94, 51).addBox(-3.1F, 2.0F, -3.0F, 6.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.9F, -13.0F, 0.0F));

        PartDefinition left_leg = root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(64, 49).addBox(-2.9F, 2.0F, -3.0F, 6.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(5.9F, -13.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(MonkeyEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        this.animateWalk(MonkeyAnimations.ANIM_MONKEY_MOVE, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.animate(entity.idleAnimationState, MonkeyAnimations.ANIM_MONKEY_IDLE, ageInTicks, 1f);
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
