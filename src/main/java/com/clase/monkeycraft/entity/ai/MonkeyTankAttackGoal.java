package com.clase.monkeycraft.entity.ai;

import com.clase.monkeycraft.entity.custom.MonkeyTankEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class MonkeyTankAttackGoal extends MeleeAttackGoal {
    private final MonkeyTankEntity entity;
    private int attackDelay = 10;
    private int ticksUntilNextAttack = 10;
    private boolean shouldCountTillNextAttack;

    public MonkeyTankAttackGoal(PathfinderMob pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
        super(pMob, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
        entity = (MonkeyTankEntity) pMob;
    }

    @Override
    public void start() {
        super.start();
        attackDelay = 10;
        ticksUntilNextAttack = 10;
    }

    @Override
    protected void checkAndPerformAttack(LivingEntity pEnemy) {
        boolean withinDistance = isEnemyWithinAttackDistance(pEnemy);

        if (withinDistance || (shouldCountTillNextAttack && !isTimeToAttack())) {
            shouldCountTillNextAttack = true;

            if(isTimeToStartAttackAnimation()) {
                entity.setAttacking(true);
            }

            if(isTimeToAttack()) {
                this.mob.getLookControl().setLookAt(pEnemy.getX(), pEnemy.getEyeY(), pEnemy.getZ());
                performAttack(pEnemy);
            }
        } else {
            resetAttackCooldown();
            shouldCountTillNextAttack = false;
            entity.setAttacking(false);
            entity.attackAnimationTimeout = 0;
        }
    }

    private boolean isEnemyWithinAttackDistance(LivingEntity pEnemy) {
        // Distancia entre entidades
        double distance = this.mob.distanceToSqr(pEnemy.getX(), pEnemy.getY(), pEnemy.getZ());

        // Rango adaptado a tamaño de mob
        double maxRange = this.getCustomAttackReachSqr(pEnemy);

        return distance <= maxRange;
    }

    protected double getCustomAttackReachSqr(LivingEntity pEnemy) {
        // Distancia minima para hit
        double radioMob = this.mob.getBbWidth() / 2.0;
        double radioPlayer = pEnemy.getBbWidth() / 2.0;

        // Bloques exactos de rango de la entidad
        double extraRange = 3.5;

        double totalRange = radioMob + radioPlayer + extraRange;

        // Al cuadrado porque el propio metodo sqr va al cuadrado
        return totalRange * totalRange;
    }


    protected void resetAttackCooldown() {
        this.ticksUntilNextAttack = this.adjustedTickDelay(attackDelay * 2);
    }

    protected boolean isTimeToAttack() {
        return this.ticksUntilNextAttack <= 0;
    }

    protected boolean isTimeToStartAttackAnimation() {
        return this.ticksUntilNextAttack <= attackDelay;
    }

    protected int getTicksUntilNextAttack() {
        return this.ticksUntilNextAttack;
    }


    protected void performAttack(LivingEntity pEnemy) {
        this.resetAttackCooldown();
        this.mob.swing(InteractionHand.MAIN_HAND);
        this.mob.doHurtTarget(pEnemy);
    }

    @Override
    public void tick() {
        super.tick();
        if(shouldCountTillNextAttack) {
            this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);;
        }
    }

    @Override
    public void stop() {
        entity.setAttacking(false);
        super.stop();
    }
}
