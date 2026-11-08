package com.clase.monkeycraft.entity.custom;

import com.clase.monkeycraft.entity.ai.MonkeyDartAttackGoal;
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
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class MonkeyDartEntity extends TamableAnimal implements RangedAttackMob {
    public final AnimationState attackAnimationState = new AnimationState();
    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(MonkeyDartEntity.class, EntityDataSerializers.BOOLEAN);
    public int attackAnimationTimeout = 0;

    public MonkeyDartEntity(EntityType<? extends TamableAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    //wander del mob, eventos como pillar agro cuando recibe un hit
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(1, new MonkeyDartAttackGoal(this, 0.75D));

        this.goalSelector.addGoal(2, new FollowOwnerGoal(this, 0.75D, 10.0F, 2.0F));
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

        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 0.4F));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 0.4F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
    }

    //Atributos del mob
    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes().add(Attributes.MAX_HEALTH, 14)
                .add(Attributes.MOVEMENT_SPEED, 0.6).add(Attributes.FOLLOW_RANGE, 128)
                .add(Attributes.FALL_DAMAGE_MULTIPLIER, 0);
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
            attackAnimationTimeout = 20; // El tiempo de la anim en ticks
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
    public void performRangedAttack(LivingEntity pTarget, float pVelocity) {
        // Crear la flecha
        Arrow arrow = new Arrow(this.level(), this, new ItemStack(Items.ARROW), (ItemStack)null);
        arrow.setBaseDamage(4.0D); // Daño de la flecha

        // Trayectoria
        double d0 = pTarget.getX() - this.getX();
        double d1 = pTarget.getY(0.3333333333333333D) - arrow.getY();
        double d2 = pTarget.getZ() - this.getZ();
        double d3 = Math.sqrt(d0 * d0 + d2 * d2);

        // Disparo con parámetros
        arrow.shoot(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, 12.0F);

        this.playSound(net.minecraft.sounds.SoundEvents.SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level().addFreshEntity(arrow);
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
