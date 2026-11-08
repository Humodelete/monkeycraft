package com.clase.monkeycraft.entity.custom;

import com.clase.monkeycraft.entity.ai.MonkeyWizardAttackGoal;
import com.clase.monkeycraft.item.ModItems;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class MonkeyWizardEntity extends TamableAnimal implements RangedAttackMob {
    public final AnimationState attackAnimationState = new AnimationState();
    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(MonkeyWizardEntity.class, EntityDataSerializers.BOOLEAN);
    public int attackAnimationTimeout = 0;

    public MonkeyWizardEntity(EntityType<? extends TamableAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    //wander del mob, eventos como pillar agro cuando recibe un hit
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(1, new MonkeyWizardAttackGoal(this, 1.0D));

        this.goalSelector.addGoal(2, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this){
            @Override
            protected boolean canAttack(@Nullable LivingEntity potentialTarget, TargetingConditions targetingConditions) {
                // if(potentialTarget instanceof MonkeyTankEntity || potentialTarget instanceof MonkeyDartEntity
                // || potentialTarget instanceof MonkeyWizardEntity || potentialTarget instanceof MonkeyJockerEntity) {
                if(potentialTarget instanceof MonkeyTankEntity || potentialTarget instanceof MonkeyDartEntity) {
                    return false;
                }
                return super.canAttack(potentialTarget, targetingConditions);
            }
        });
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this) {
            @Override
            protected boolean canAttack(@Nullable LivingEntity potentialTarget, TargetingConditions targetingConditions) {
                // if(potentialTarget instanceof MonkeyTankEntity || potentialTarget instanceof MonkeyDartEntity
                // || potentialTarget instanceof MonkeyWizardEntity || potentialTarget instanceof MonkeyJockerEntity) {
                if(potentialTarget instanceof MonkeyTankEntity || potentialTarget instanceof MonkeyDartEntity) {
                    return false;
                }
                return super.canAttack(potentialTarget, targetingConditions);
            }
        });
        this.targetSelector.addGoal(3, new HurtByTargetGoal(this) {
            @Override
            protected boolean canAttack(@Nullable LivingEntity potentialTarget, TargetingConditions targetingConditions) {
                // if(potentialTarget instanceof MonkeyTankEntity || potentialTarget instanceof MonkeyDartEntity
                // || potentialTarget instanceof MonkeyWizardEntity || potentialTarget instanceof MonkeyJockerEntity) {
                if(potentialTarget instanceof MonkeyTankEntity || potentialTarget instanceof MonkeyDartEntity) {
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
        return Animal.createLivingAttributes().add(Attributes.MAX_HEALTH, 16)
                .add(Attributes.MOVEMENT_SPEED, 0.5).add(Attributes.FOLLOW_RANGE, 128)
                .add(Attributes.ATTACK_DAMAGE, 6);
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
        if(this.isAttacking()) {
            if(!this.attackAnimationState.isStarted()) {
                this.attackAnimationState.start(this.tickCount);
            }
        } else {
            this.attackAnimationState.stop();
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
    }

    @Override
    public void performRangedAttack(LivingEntity pTarget, float pVelocity) {
        if(pTarget == null) return;

        if(this.getRandom().nextFloat() < 0.2F) { //20% lightning, 80% fireball
            castLightning(pTarget);
        } else {
            shootFireball(pTarget);
        }
    }

    private void castLightning(LivingEntity pTarget) {
        if(this.level() instanceof ServerLevel serverLevel) {
            LightningBolt lightningBolt = EntityType.LIGHTNING_BOLT.create(serverLevel);
            if(lightningBolt != null) {
                lightningBolt.moveTo(Vec3.atBottomCenterOf(pTarget.blockPosition()));
                lightningBolt.setVisualOnly(true);
                this.getTarget().hurt(this.damageSources().lightningBolt(), 8.0F);
                serverLevel.addFreshEntity(lightningBolt);
            }
        }
    }

    private void shootFireball(LivingEntity pTarget) {
        Level level = this.level();

        LivingEntity actualTarget = this.getTarget();
        if (actualTarget == null) return;

        Vec3 targetPos = actualTarget.getEyePosition();
        Vec3 shooterPos = this.getEyePosition();
        Vec3 lookVec = targetPos.subtract(shooterPos);

        SmallFireball fireball = new SmallFireball(EntityType.SMALL_FIREBALL, level);

        fireball.setOwner(this);

        fireball.setDeltaMovement(lookVec.normalize().scale(0.7D));

        double spawnX = this.getX() + lookVec.normalize().x * 0.5D;
        double spawnY = this.getY() + this.getEyeHeight() + lookVec.normalize().y * 0.1D;
        double spawnZ = this.getZ() + lookVec.normalize().z * 0.5D;
        fireball.setPos(spawnX, spawnY, spawnZ);

        level.addFreshEntity(fireball);

        this.playSound(SoundEvents.BLAZE_SHOOT, 1.0F, (this.getRandom().nextFloat() - this.getRandom().nextFloat()) * 0.2F + 1.0F);
    }
}
