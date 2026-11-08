package com.clase.monkeycraft.entity.custom;

import com.clase.monkeycraft.entity.ai.MonkeyJesterHealGoal;
import com.clase.monkeycraft.entity.ai.MonkeyJesterIdleGoal;
import com.clase.monkeycraft.item.ModItems;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class MonkeyJesterEntity extends TamableAnimal {
    public final AnimationState castAnimationState = new AnimationState();
    private static final EntityDataAccessor<Boolean> CASTING =
            SynchedEntityData.defineId(MonkeyJesterEntity.class, EntityDataSerializers.BOOLEAN);

    public final AnimationState idleAnimationStates = new AnimationState();
    private static final EntityDataAccessor<Boolean> IDLE =
            SynchedEntityData.defineId(MonkeyJesterEntity.class, EntityDataSerializers.BOOLEAN);
    public int lastActionTick = 0;

    public MonkeyJesterEntity(EntityType<? extends TamableAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    //wander del mob, eventos como pillar agro cuando recibe un hit
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(1, new MonkeyJesterHealGoal(this));
        this.goalSelector.addGoal(2, new MonkeyJesterIdleGoal(this));

        this.goalSelector.addGoal(2, new FollowOwnerGoal(this, 0.75D, 10.0F, 2.0F));

        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 0.3F));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 0.3F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
    }

    //Atributos del mob
    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes().add(Attributes.MAX_HEALTH, 18)
                .add(Attributes.MOVEMENT_SPEED, 0.6).add(Attributes.FOLLOW_RANGE, 128);
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.is(ModItems.BANANA.get());
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return null;
    }

    private void setupAnimationStates() {
        // Animacion de cast
        if(this.isCasting()) {
            if(!this.castAnimationState.isStarted()) {
                this.castAnimationState.start(this.tickCount);
            }
        } else {
            this.castAnimationState.stop();
        }

        if(this.isIdleAnim()) {
            if(!this.idleAnimationStates.isStarted()) {
                this.idleAnimationStates.start(this.tickCount);
            }
        } else {
            this.idleAnimationStates.stop();
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(CASTING, false);
        builder.define(IDLE, false);
    }

    public void setCasting(boolean casting) {
        this.entityData.set(CASTING, casting);
    }

    public boolean isCasting() {
        return this.entityData.get(CASTING);
    }

    public void setIdleAnim(boolean idling) { this.entityData.set(IDLE, idling); }

    public boolean isIdleAnim() { return this.entityData.get(IDLE); }

    @Override
    public void tick() {
        super.tick();

        if(this.level().isClientSide()) {
            this.setupAnimationStates();
        }
    }
}
