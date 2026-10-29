package com.clase.monkeycraft.entity.custom;

import com.clase.monkeycraft.entity.ModEntities;
import com.clase.monkeycraft.item.ModItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class MonkeyEntity extends Animal {
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public MonkeyEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    //wander del mob, eventos como pillar agro cuando recibe un hit
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(1, new PanicGoal(this, 2.0));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0));
        //pillar follow del mob, como el cerdo con la zanahoria
        /*this.goalSelector.addGoal(3, new TemptGoal(this, 1.25, stack ->
                stack.is(ModItems.BANANA.get()), false));*/

        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25));

        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.25));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(0, new RandomLookAroundGoal(this));
    }

    //Atributos del mob
    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes().add(Attributes.MAX_HEALTH, 14)
                .add(Attributes.MOVEMENT_SPEED, 1.25).add(Attributes.FOLLOW_RANGE, 240)
                .add(Attributes.ATTACK_DAMAGE, 2);
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return false;
        //return pStack.is(ModItems.BANANA.get());
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return ModEntities.MONKEY.get().create(pLevel);
    }

    private void setupAnimationStates() {
        if(this.idleAnimationTimeout <= 0) {
            //Tiempo de la animación del idle 3.32. Está puesto 6, para intentar que cambie entre idle y quieto
            this.idleAnimationTimeout = 120;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    public void tick() {
        super.tick();

        if(this.level().isClientSide()) {
            this.setupAnimationStates();
        }
    }
}
