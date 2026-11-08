package com.clase.monkeycraft.entity.ai;

import com.clase.monkeycraft.entity.custom.MonkeyWizardEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.RangedAttackMob;
import java.util.EnumSet;

public class MonkeyWizardAttackGoal extends Goal {
    private final PathfinderMob mob;
    private final RangedAttackMob rangedMob;
    private LivingEntity target;

    // --- SECCIÓN DE TIEMPOS EXACTOS (20 Ticks = 1 Segundo) ---
    private final int ticksTotalesAnimacion = 51; // 2.54 segundos de duración total
    private final int ticksEsperaDisparo = 40;    // 2.0 segundos cargando antes de soltar la bola

    private int ticksUntilNextAttack = 91;
    private boolean shouldCountTillNextAttack;

    private final double speedModifier;
    private int seeTime;
    private final int attackIntervalMin = 20;

    private boolean hasShot = false;

    public MonkeyWizardAttackGoal(RangedAttackMob pMob, double pSpeedModifier) {
        this.rangedMob = pMob;
        this.mob = (PathfinderMob) pMob;
        this.speedModifier = pSpeedModifier;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public void start() {
        super.start();
        this.ticksUntilNextAttack = attackIntervalMin + ticksTotalesAnimacion;
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

        if (this.mob instanceof MonkeyWizardEntity monkeyWizard) {
            monkeyWizard.setAttacking(false);
            monkeyWizard.attackAnimationTimeout = 0;
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

            if (this.mob instanceof MonkeyWizardEntity monkeyWizard) {

                if (this.ticksUntilNextAttack <= ticksTotalesAnimacion && this.ticksUntilNextAttack > 0
                        && !monkeyWizard.isAttacking()) {
                    monkeyWizard.setAttacking(true);
                }

                if (!hasShot && this.ticksUntilNextAttack <= (ticksTotalesAnimacion - ticksEsperaDisparo)
                && monkeyWizard.isAttacking()) {
                    this.mob.swing(InteractionHand.MAIN_HAND);
                    this.rangedMob.performRangedAttack(this.target, 1.0F);
                    hasShot = true;
                }

                if (this.ticksUntilNextAttack <= 0) {
                    monkeyWizard.setAttacking(false);
                    this.resetAttackCooldown();
                    hasShot = false;
                }
            }
        } else {
            this.mob.getNavigation().moveTo(this.target, this.speedModifier);

            if (this.mob instanceof MonkeyWizardEntity monkeyWizard && !monkeyWizard.isAttacking()) {
                this.resetAttackCooldown();
                this.shouldCountTillNextAttack = false;
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
        double extraRange = 24.0;
        double totalRange = radioMob + radioPlayer + extraRange;
        return totalRange * totalRange;
    }

    protected void resetAttackCooldown() {
        this.ticksUntilNextAttack = this.adjustedTickDelay(attackIntervalMin + ticksTotalesAnimacion);
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }
}
