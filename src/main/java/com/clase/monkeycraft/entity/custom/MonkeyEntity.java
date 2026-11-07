package com.clase.monkeycraft.entity.custom;

import com.clase.monkeycraft.entity.ModEntities;
import com.clase.monkeycraft.entity.ai.MonkeyAttackGoal;
import com.clase.monkeycraft.item.ModItems;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class MonkeyEntity extends Animal {
    public final AnimationState attackAnimationState = new AnimationState();
    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(MonkeyEntity.class, EntityDataSerializers.BOOLEAN);
    public int attackAnimationTimeout = 0;

    public MonkeyEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    //wander del mob, eventos como pillar agro cuando recibe un hit
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(1, new LeapAtTargetGoal(this, 0.6F));
        this.goalSelector.addGoal(2, new MonkeyAttackGoal(this, 0.75D, true));
        this.goalSelector.addGoal(3, new HurtByTargetGoal(this));

        //pillar follow del mob, como el cerdo con la zanahoria
        /*this.goalSelector.addGoal(3, new TemptGoal(this, 1.25, stack ->
                stack.is(ModItems.BANANA.get()), false));*/

        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 0.4F));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 0.4F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
    }

    //Atributos del mob
    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes().add(Attributes.MAX_HEALTH, 14)
                .add(Attributes.MOVEMENT_SPEED, 0.6).add(Attributes.FOLLOW_RANGE, 240)
                .add(Attributes.ATTACK_DAMAGE, 6).add(Attributes.FALL_DAMAGE_MULTIPLIER, 0);
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.is(ModItems.BANANA.get());
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return ModEntities.MONKEY.get().create(pLevel);
    }

    private void setupAnimationStates() {
        // Animacion de attack
        if(this.isAttacking() && attackAnimationTimeout <= 0) {
            attackAnimationTimeout = 28; // El tiempo de la anim en ticks
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
}
