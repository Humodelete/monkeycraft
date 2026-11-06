package com.clase.monkeycraft.entity.client;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class MonkeyTankAnimations {
    public static final AnimationDefinition ANIM_MONKEY_TANK_EMERGE = AnimationDefinition.Builder.withLength(6.64F)
            .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.52F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -22.5F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.2F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -7.5F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.68F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 10.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.8F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 10.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.28F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 10.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.88F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 10.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(3.76F, KeyframeAnimations.degreeVec(25.0F, 0.0F, -7.5F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(3.92F, KeyframeAnimations.degreeVec(35.0F, 0.0F, -7.5F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(4.08F, KeyframeAnimations.degreeVec(25.0F, 0.0F, -7.5F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(4.44F, KeyframeAnimations.degreeVec(47.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(4.56F, KeyframeAnimations.degreeVec(47.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(4.68F, KeyframeAnimations.degreeVec(47.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(5.0F, KeyframeAnimations.degreeVec(70.0F, 0.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(5.8F, KeyframeAnimations.degreeVec(70.0F, 0.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(6.64F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION,
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -63.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.52F, KeyframeAnimations.posVec(0.0F, -56.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.2F, KeyframeAnimations.posVec(0.0F, -32.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.68F, KeyframeAnimations.posVec(0.0F, -32.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.8F, KeyframeAnimations.posVec(0.0F, -32.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.28F, KeyframeAnimations.posVec(0.0F, -32.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.88F, KeyframeAnimations.posVec(0.0F, -32.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(3.16F, KeyframeAnimations.posVec(0.0F, -27.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(3.76F, KeyframeAnimations.posVec(0.0F, -14.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(3.92F, KeyframeAnimations.posVec(0.0F, -11.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(4.08F, KeyframeAnimations.posVec(0.0F, -14.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(4.44F, KeyframeAnimations.posVec(0.0F, -6.0F, -3.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(4.56F, KeyframeAnimations.posVec(0.0F, -4.0F, -3.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(4.68F, KeyframeAnimations.posVec(0.0F, -6.0F, -3.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(5.0F, KeyframeAnimations.posVec(0.0F, -3.0F, -4.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(5.8F, KeyframeAnimations.posVec(0.0F, -3.0F, -4.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(6.64F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.52F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.92F, KeyframeAnimations.degreeVec(0.74F, 0.0F, -40.38F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.16F, KeyframeAnimations.degreeVec(-67.5F, 0.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.24F, KeyframeAnimations.degreeVec(-67.5F, 0.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.32F, KeyframeAnimations.degreeVec(-47.5F, 0.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.4F, KeyframeAnimations.degreeVec(-67.5F, 0.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.68F, KeyframeAnimations.degreeVec(-67.5F, 0.0F, 15.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.76F, KeyframeAnimations.degreeVec(-67.5F, 0.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.84F, KeyframeAnimations.degreeVec(-52.5F, 0.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.92F, KeyframeAnimations.degreeVec(-67.5F, 0.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.64F, KeyframeAnimations.degreeVec(-17.5F, 0.0F, -10.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(3.76F, KeyframeAnimations.degreeVec(70.0F, 0.0F, 12.5F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(4.04F, KeyframeAnimations.degreeVec(70.0F, 0.0F, 12.5F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(4.12F, KeyframeAnimations.degreeVec(80.0F, 0.0F, 12.5F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(4.24F, KeyframeAnimations.degreeVec(70.0F, 0.0F, 12.5F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(5.0F, KeyframeAnimations.degreeVec(77.5F, 0.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(6.64F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.POSITION,
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.52F, KeyframeAnimations.posVec(-8.0F, -11.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.92F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.24F, KeyframeAnimations.posVec(0.0F, 0.47F, -0.95F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.32F, KeyframeAnimations.posVec(0.0F, 0.47F, -0.95F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.4F, KeyframeAnimations.posVec(0.0F, 0.47F, -0.95F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.68F, KeyframeAnimations.posVec(0.0F, 1.0F, -2.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.76F, KeyframeAnimations.posVec(0.0F, 1.0F, -2.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.84F, KeyframeAnimations.posVec(0.0F, 1.0F, -2.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.92F, KeyframeAnimations.posVec(0.0F, 1.0F, -2.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.64F, KeyframeAnimations.posVec(0.0F, -2.0F, -2.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(3.76F, KeyframeAnimations.posVec(0.0F, -4.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(4.04F, KeyframeAnimations.posVec(0.0F, -1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(4.12F, KeyframeAnimations.posVec(0.0F, -1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(4.24F, KeyframeAnimations.posVec(0.0F, -1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(5.0F, KeyframeAnimations.posVec(0.0F, -1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(6.64F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.52F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.2F, KeyframeAnimations.degreeVec(-152.5F, 2.5F, 7.5F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.68F, KeyframeAnimations.degreeVec(-180.0F, 12.5F, -10.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.8F, KeyframeAnimations.degreeVec(-90.0F, 12.5F, -10.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.28F, KeyframeAnimations.degreeVec(-90.0F, 12.5F, -10.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.88F, KeyframeAnimations.degreeVec(-90.0F, 12.5F, -10.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(3.08F, KeyframeAnimations.degreeVec(-95.0F, 12.5F, -10.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(3.24F, KeyframeAnimations.degreeVec(-83.93F, 3.93F, 5.71F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(3.36F, KeyframeAnimations.degreeVec(-80.0F, 7.5F, 17.5F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(3.76F, KeyframeAnimations.degreeVec(-67.5F, 2.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(4.08F, KeyframeAnimations.degreeVec(-67.5F, 2.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(4.44F, KeyframeAnimations.degreeVec(-55.0F, 2.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(4.56F, KeyframeAnimations.degreeVec(-60.0F, 2.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(4.68F, KeyframeAnimations.degreeVec(-55.0F, 2.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(5.0F, KeyframeAnimations.degreeVec(-67.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(5.56F, KeyframeAnimations.degreeVec(-50.45F, 0.0F, 2.69F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(6.08F, KeyframeAnimations.degreeVec(-62.72F, 0.0F, 4.3F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(6.64F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.POSITION,
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.52F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.2F, KeyframeAnimations.posVec(0.0F, -21.0F, 9.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.68F, KeyframeAnimations.posVec(2.0F, -2.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.8F, KeyframeAnimations.posVec(2.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.28F, KeyframeAnimations.posVec(2.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.88F, KeyframeAnimations.posVec(2.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(3.08F, KeyframeAnimations.posVec(2.0F, -2.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(3.24F, KeyframeAnimations.posVec(2.0F, 2.71F, 3.86F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(3.36F, KeyframeAnimations.posVec(2.0F, 1.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(3.76F, KeyframeAnimations.posVec(2.0F, 3.0F, 3.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(4.08F, KeyframeAnimations.posVec(2.0F, 3.0F, 3.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(4.44F, KeyframeAnimations.posVec(2.67F, 4.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(4.56F, KeyframeAnimations.posVec(2.67F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(4.68F, KeyframeAnimations.posVec(2.67F, 4.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(5.0F, KeyframeAnimations.posVec(0.67F, 3.0F, 4.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(6.64F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.12F, KeyframeAnimations.degreeVec(-167.5F, -17.5F, -7.5F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.6F, KeyframeAnimations.degreeVec(-167.5F, -17.5F, -7.5F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.88F, KeyframeAnimations.degreeVec(-175.0F, -17.5F, 15.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.16F, KeyframeAnimations.degreeVec(-190.0F, -17.5F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.28F, KeyframeAnimations.degreeVec(-90.0F, -5.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.68F, KeyframeAnimations.degreeVec(-90.0F, -17.5F, -12.5F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.8F, KeyframeAnimations.degreeVec(-90.0F, -17.5F, -12.5F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.28F, KeyframeAnimations.degreeVec(-90.0F, -17.5F, -12.5F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.88F, KeyframeAnimations.degreeVec(-90.0F, -17.5F, -12.5F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(3.04F, KeyframeAnimations.degreeVec(-81.29F, -10.64F, -14.21F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(3.16F, KeyframeAnimations.degreeVec(-83.5F, -5.5F, -15.5F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(3.76F, KeyframeAnimations.degreeVec(-62.5F, -7.5F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(3.92F, KeyframeAnimations.degreeVec(-58.75F, -3.75F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(4.08F, KeyframeAnimations.degreeVec(-55.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(4.44F, KeyframeAnimations.degreeVec(-52.5F, 0.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(4.56F, KeyframeAnimations.degreeVec(-50.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(4.68F, KeyframeAnimations.degreeVec(-52.5F, 0.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(5.0F, KeyframeAnimations.degreeVec(-72.5F, -2.5F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(5.56F, KeyframeAnimations.degreeVec(-57.5F, -4.54F, 2.99F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(6.08F, KeyframeAnimations.degreeVec(-70.99F, -5.77F, 1.78F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(6.64F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.POSITION,
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.12F, KeyframeAnimations.posVec(0.0F, -8.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.6F, KeyframeAnimations.posVec(0.0F, -8.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.88F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.2F, KeyframeAnimations.posVec(-2.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.68F, KeyframeAnimations.posVec(-4.0F, 3.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.8F, KeyframeAnimations.posVec(-4.0F, 3.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.28F, KeyframeAnimations.posVec(-4.0F, 3.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.88F, KeyframeAnimations.posVec(-4.0F, 3.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(3.04F, KeyframeAnimations.posVec(-3.23F, 5.7F, 4.97F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(3.16F, KeyframeAnimations.posVec(-1.49F, 2.22F, 5.25F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(3.76F, KeyframeAnimations.posVec(-1.14F, 1.71F, 1.86F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(3.92F, KeyframeAnimations.posVec(-1.14F, 1.21F, 3.86F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(4.08F, KeyframeAnimations.posVec(-1.14F, 2.71F, 4.86F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(4.44F, KeyframeAnimations.posVec(-1.0F, 1.0F, 3.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(4.56F, KeyframeAnimations.posVec(0.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(4.68F, KeyframeAnimations.posVec(0.0F, 1.0F, 3.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(5.0F, KeyframeAnimations.posVec(-2.0F, 0.0F, 4.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(6.64F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.52F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.28F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.88F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(3.36F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(4.32F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(4.48F, KeyframeAnimations.degreeVec(55.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(4.6F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(5.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(5.8F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(6.64F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -63.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.52F, KeyframeAnimations.posVec(0.0F, -56.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.2F, KeyframeAnimations.posVec(0.0F, -32.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.68F, KeyframeAnimations.posVec(0.0F, -32.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.8F, KeyframeAnimations.posVec(0.0F, -32.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.28F, KeyframeAnimations.posVec(0.0F, -32.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.88F, KeyframeAnimations.posVec(0.0F, -32.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(3.36F, KeyframeAnimations.posVec(0.0F, -22.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(3.76F, KeyframeAnimations.posVec(0.0F, -12.28F, 2.48F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(3.92F, KeyframeAnimations.posVec(0.0F, -9.28F, 2.48F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(4.08F, KeyframeAnimations.posVec(0.0F, -12.28F, 2.48F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(4.32F, KeyframeAnimations.posVec(0.0F, -4.14F, 4.14F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(4.48F, KeyframeAnimations.posVec(0.0F, -0.57F, -8.43F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(4.6F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(5.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(5.8F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(6.64F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.52F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.28F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.88F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(3.36F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(3.84F, KeyframeAnimations.degreeVec(20.0F, 0.0F, -17.5F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(4.68F, KeyframeAnimations.degreeVec(20.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(4.84F, KeyframeAnimations.degreeVec(10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(5.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(5.8F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(6.64F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -63.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.52F, KeyframeAnimations.posVec(0.0F, -56.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.2F, KeyframeAnimations.posVec(0.0F, -32.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.68F, KeyframeAnimations.posVec(0.0F, -32.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.8F, KeyframeAnimations.posVec(0.0F, -32.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.28F, KeyframeAnimations.posVec(0.0F, -32.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.88F, KeyframeAnimations.posVec(0.0F, -32.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(3.36F, KeyframeAnimations.posVec(0.0F, -22.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(3.84F, KeyframeAnimations.posVec(-4.0F, 2.0F, -7.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(4.0F, KeyframeAnimations.posVec(-4.0F, 0.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(4.68F, KeyframeAnimations.posVec(-4.0F, 0.0F, -9.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(4.84F, KeyframeAnimations.posVec(-2.0F, 2.0F, -3.5F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(5.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(5.8F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(6.64F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .build();

    public static final AnimationDefinition ANIM_MONKEY_TANK_WALK = AnimationDefinition.Builder.withLength(0.625F).looping()
		.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(20.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-20.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-20.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(20.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .build();

    public static final AnimationDefinition ANIM_MONKEY_TANK_SONIC_BOOM = AnimationDefinition.Builder.withLength(3.0F)
            .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.0833F, KeyframeAnimations.degreeVec(47.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.625F, KeyframeAnimations.degreeVec(55.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.9167F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.0F, KeyframeAnimations.degreeVec(-32.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.4583F, KeyframeAnimations.degreeVec(-32.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.7083F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.875F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION,
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.0833F, KeyframeAnimations.posVec(0.0F, -3.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.625F, KeyframeAnimations.posVec(0.0F, -4.0F, -1.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.9167F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.7083F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.875F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.0F, KeyframeAnimations.degreeVec(67.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.75F, KeyframeAnimations.degreeVec(80.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.9167F, KeyframeAnimations.degreeVec(-45.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.5F, KeyframeAnimations.degreeVec(-45.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.7083F, KeyframeAnimations.degreeVec(-45.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.875F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.POSITION,
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.9167F, KeyframeAnimations.posVec(0.0F, 0.0F, -3.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.5F, KeyframeAnimations.posVec(0.0F, 0.0F, -3.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.7083F, KeyframeAnimations.posVec(0.0F, 0.0F, -3.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.875F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.875F, KeyframeAnimations.degreeVec(-42.2866F, -32.6981F, -5.0083F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.1667F, KeyframeAnimations.degreeVec(-29.8376F, -35.3963F, -45.2809F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.3333F, KeyframeAnimations.degreeVec(-29.8376F, -35.3963F, -45.2809F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.6667F, KeyframeAnimations.degreeVec(-72.2866F, -32.6981F, -5.0083F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.9583F, KeyframeAnimations.degreeVec(-200.0F, 6.9376F, -1.4651F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.3333F, KeyframeAnimations.degreeVec(-194.7107F, -10.945F, -25.2629F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.POSITION,
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(3.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.875F, KeyframeAnimations.degreeVec(-33.8069F, 32.3106F, 6.88F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.1667F, KeyframeAnimations.degreeVec(-17.8783F, 34.6212F, 49.0243F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.3333F, KeyframeAnimations.degreeVec(-17.8783F, 34.6212F, 49.0243F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.6667F, KeyframeAnimations.degreeVec(-51.3069F, 32.3106F, 6.88F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(1.9583F, KeyframeAnimations.degreeVec(-200.0F, 6.9497F, -2.1276F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(2.3333F, KeyframeAnimations.degreeVec(-200.3033F, 9.4746F, 16.4257F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.POSITION,
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(3.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .build();

    public static final AnimationDefinition ANIM_MONKEY_TANK_ATTACK = AnimationDefinition.Builder.withLength(0.4583F)
            .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.125F, KeyframeAnimations.degreeVec(-22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.2917F, KeyframeAnimations.degreeVec(22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.4167F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION,
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.125F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.2917F, KeyframeAnimations.posVec(0.0F, -1.0F, -2.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.4167F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.125F, KeyframeAnimations.degreeVec(22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.3333F, KeyframeAnimations.degreeVec(-30.1749F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.4167F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.POSITION,
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.125F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.3333F, KeyframeAnimations.posVec(0.0F, -2.0F, -2.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.4167F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.125F, KeyframeAnimations.degreeVec(-120.3612F, 40.7895F, -20.941F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.25F, KeyframeAnimations.degreeVec(-90.0F, -45.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.4167F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.POSITION,
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.125F, KeyframeAnimations.posVec(4.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.25F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.4167F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.125F, KeyframeAnimations.degreeVec(-120.3612F, -40.7895F, 20.941F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.25F, KeyframeAnimations.degreeVec(-61.1632F, 42.8588F, 11.5242F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.4167F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.POSITION,
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.125F, KeyframeAnimations.posVec(-4.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.25F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
            new Keyframe(0.4167F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
            ))
            .build();
}
