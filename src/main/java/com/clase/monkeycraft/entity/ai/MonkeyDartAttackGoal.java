package com.clase.monkeycraft.entity.ai;

import com.clase.monkeycraft.entity.custom.MonkeyDartEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.RangedAttackMob;

import java.util.EnumSet;

public class MonkeyDartAttackGoal extends Goal {
    private final PathfinderMob mob;
    private final RangedAttackMob rangedMob;
    private LivingEntity target;

    // Tus variables de control de tiempo
    private int attackDelay = 5; // Ticks que tarda en lanzar el proyectil desde que empieza la animación
    private int ticksUntilNextAttack = 15;
    private boolean shouldCountTillNextAttack;

    private final double speedModifier;
    private int seeTime;
    private final int attackIntervalMin = 20; // Cooldown total base entre ataques

    public MonkeyDartAttackGoal(RangedAttackMob pMob, double pSpeedModifier) {
        this.rangedMob = pMob;
        this.mob = (PathfinderMob) pMob;
        this.speedModifier = pSpeedModifier;

        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public void start() {
        super.start();
        this.attackDelay = 5; // Tick de animacion donde lanza el objeto
        this.ticksUntilNextAttack = 15;
        this.seeTime = 0;
    }

    @Override
    public boolean canUse() {
        LivingEntity livingentity = this.mob.getTarget();
        if (livingentity != null && livingentity.isAlive()) {
            this.target = livingentity;
            return true;
        }
        return false;
    }

    @Override
    public boolean canContinueToUse() {
        return this.canUse() || !this.mob.getNavigation().isDone();
    }

    @Override
    public void stop() {
        this.target = null;
        this.seeTime = 0;
        this.resetAttackCooldown();
        this.shouldCountTillNextAttack = false;

        // Avisar a la entidad que termine la animacipn
        if (this.mob instanceof MonkeyDartEntity monkeyDart) {
            monkeyDart.setAttacking(false);
            monkeyDart.attackAnimationTimeout = 0;
        }
        super.stop();
    }

    @Override
    public void tick() {
        if (this.target == null) return;

        this.mob.getLookControl().setLookAt(this.target, 30.0F, 30.0F);

        boolean canSee = this.mob.getSensing().hasLineOfSight(this.target);
        if (canSee) {
            this.seeTime++;
        } else {
            this.seeTime = 0;
        }

        if (isEnemyWithinAttackDistance(this.target) && this.seeTime >= 5) {
            this.mob.getNavigation().stop();
            this.shouldCountTillNextAttack = true;

            if (this.mob instanceof MonkeyDartEntity monkeyDart) {
                if (isTimeToStartAttackAnimation()) {
                    monkeyDart.setAttacking(true);
                }

                if (isTimeToAttack()) {
                    performRangedAttack(this.target, monkeyDart);
                }
            }
        } else {
            this.mob.getNavigation().moveTo(this.target, this.speedModifier);

            this.resetAttackCooldown();
            this.shouldCountTillNextAttack = false;

            if (this.mob instanceof MonkeyDartEntity monkeyDart) {
                monkeyDart.setAttacking(false);
                monkeyDart.attackAnimationTimeout = 0;
            }
        }

        if (shouldCountTillNextAttack) {
            this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
        }
    }

    private boolean isEnemyWithinAttackDistance(LivingEntity pEnemy) {
        double distance = this.mob.distanceToSqr(pEnemy.getX(), pEnemy.getY(), pEnemy.getZ());
        double maxRange = this.getCustomAttackReachSqr(pEnemy);
        return distance <= maxRange;
    }

    protected double getCustomAttackReachSqr(LivingEntity pEnemy) {
        double radioMob = this.mob.getBbWidth() / 2.0;
        double radioPlayer = pEnemy.getBbWidth() / 2.0;

        // Rango del ataque
        double extraRange = 16.0;

        double totalRange = radioMob + radioPlayer + extraRange;
        return totalRange * totalRange;
    }

    protected void resetAttackCooldown() {
        // Cooldown
        this.ticksUntilNextAttack = this.adjustedTickDelay(attackIntervalMin + attackDelay);
    }

    protected boolean isTimeToAttack() {
        return this.ticksUntilNextAttack <= 0;
    }

    protected boolean isTimeToStartAttackAnimation() {
        return this.ticksUntilNextAttack <= attackDelay;
    }

    protected void performRangedAttack(LivingEntity pEnemy, MonkeyDartEntity pMonkeyDart) {
        this.resetAttackCooldown();
        this.mob.swing(InteractionHand.MAIN_HAND);

        this.rangedMob.performRangedAttack(pEnemy, 1.0F);

        pMonkeyDart.setAttacking(false);
    }
}
