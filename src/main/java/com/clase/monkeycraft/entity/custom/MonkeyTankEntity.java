package com.clase.monkeycraft.entity.custom;

import com.clase.monkeycraft.entity.ai.MonkeyTankAttackGoal;
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

public class MonkeyTankEntity extends TamableAnimal {
    public final AnimationState attackAnimationState = new AnimationState();
    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(MonkeyTankEntity.class, EntityDataSerializers.BOOLEAN);
    public int attackAnimationTimeout = 0;

    public MonkeyTankEntity(EntityType<? extends TamableAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(1, new MonkeyTankAttackGoal(this, 1.0D, false));

        this.goalSelector.addGoal(2, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this){
            @Override
            protected boolean canAttack(@Nullable LivingEntity potentialTarget, TargetingConditions targetingConditions) {
                if(potentialTarget instanceof MonkeyTankEntity || potentialTarget instanceof MonkeyDartEntity
                        || potentialTarget instanceof MonkeyWizardEntity || potentialTarget instanceof MonkeyJesterEntity
                        || potentialTarget instanceof Wolf || potentialTarget instanceof Cat) {
                    return false;
                }
                return super.canAttack(potentialTarget, targetingConditions);
            }
        });
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this) {
            @Override
            protected boolean canAttack(@Nullable LivingEntity potentialTarget, TargetingConditions targetingConditions) {
                if(potentialTarget instanceof MonkeyTankEntity || potentialTarget instanceof MonkeyDartEntity
                        || potentialTarget instanceof MonkeyWizardEntity || potentialTarget instanceof MonkeyJesterEntity
                        || potentialTarget instanceof Wolf || potentialTarget instanceof Cat) {
                    return false;
                }
                return super.canAttack(potentialTarget, targetingConditions);
            }
        });
        this.targetSelector.addGoal(3, new HurtByTargetGoal(this) {
            @Override
            protected boolean canAttack(@Nullable LivingEntity potentialTarget, TargetingConditions targetingConditions) {
                if(potentialTarget instanceof MonkeyTankEntity || potentialTarget instanceof MonkeyDartEntity
                        || potentialTarget instanceof MonkeyWizardEntity || potentialTarget instanceof MonkeyJesterEntity
                        || potentialTarget instanceof Wolf || potentialTarget instanceof Cat) {
                    return false;
                }
                return super.canAttack(potentialTarget, targetingConditions);
            }
        }.setAlertOthers());

        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 0.3F));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 0.3F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
    }

    //Atributos del mob
    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes().add(Attributes.MAX_HEALTH, 80)
                .add(Attributes.MOVEMENT_SPEED, 0.3).add(Attributes.FOLLOW_RANGE, 32)
                .add(Attributes.ATTACK_DAMAGE, 8).add(Attributes.KNOCKBACK_RESISTANCE, 2)
                .add(Attributes.ATTACK_KNOCKBACK, 2);
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
        // Animacion de attack
        if(this.isAttacking() && attackAnimationTimeout <= 0) {
            attackAnimationTimeout = 9; // El tiempo de la anim en ticks
            attackAnimationState.start(this.tickCount);
        } else {
            --this.attackAnimationTimeout;
        }

        if(!this.isAttacking()) {
            attackAnimationState.stop();
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(ATTACKING, false);
    }

    public void setAttacking(boolean attacking) {
        this.entityData.set(ATTACKING, attacking);
    }

    public boolean isAttacking() {
        return this.entityData.get(ATTACKING);
    }

    @Override
    public void tick() {
        super.tick();

        if(this.level().isClientSide()) {
            this.setupAnimationStates();
        }

        if (!this.level().isClientSide() && !this.onGround() && this.getTarget() != null) {
            if (this.distanceToSqr(this.getTarget()) <= 16.0D) {
                this.setAttacking(true);
            }
        }
    }
    @Override
    public float getVoicePitch() {
        return 0.60F;
    }

    @Override
    public void playAmbientSound() {
        if (this.hurtTime == 0 && !this.isAttacking()) {
            super.playAmbientSound();
        }
    }

    @Override
    public int getAmbientSoundInterval() {
        return 200;
    }
    @Override
    protected net.minecraft.sounds.SoundEvent getDeathSound() {
        return net.minecraft.sounds.SoundEvents.FOX_DEATH;
    }

    @Override
    protected net.minecraft.sounds.SoundEvent getHurtSound(net.minecraft.world.damagesource.DamageSource damageSource) {
        return com.clase.monkeycraft.sound.ModSounds.MONKEY_HURT.get();
    }
}