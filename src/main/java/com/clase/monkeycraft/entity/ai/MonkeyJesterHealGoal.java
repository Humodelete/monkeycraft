package com.clase.monkeycraft.entity.ai;

import com.clase.monkeycraft.entity.custom.MonkeyJesterEntity;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;

import java.util.EnumSet;
import java.util.List;

public class MonkeyJesterHealGoal extends Goal {
    private static final double HEAL_RADIUS = 12.0D;
    private static final float HEAL_AMOUNT = 6.0F;
    private static final int COOLDOWN_TICKS = 100;

    private static final int CAST_TOTAL_TICKS = 46; // duración de la animación x 20
    private static final int HEAL_TICK = 25;        // momento de la cura x 20

    private final MonkeyJesterEntity monkeyJester;
    private int castTick = 0;
    private int nextHealTick = 0;

    public MonkeyJesterHealGoal(MonkeyJesterEntity monkeyJester) {
        this.monkeyJester = monkeyJester;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE,  Goal.Flag.LOOK));
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true; // para que el contador vaya a 20 ticks/s
    }

    private List<LivingEntity> findTargets() {
        return monkeyJester.level().getEntitiesOfClass(LivingEntity.class,
                monkeyJester.getBoundingBox().inflate(HEAL_RADIUS),
                e -> e.isAlive()
                        && e.getHealth() < e.getMaxHealth()
                        && monkeyJester.distanceToSqr(e) <= HEAL_RADIUS * HEAL_RADIUS
                        && isAlly(e));
    }

    private boolean isAlly(LivingEntity e) {
        if (e == monkeyJester) return false;
        if (e instanceof Player p) return p.getUUID().equals(monkeyJester.getOwnerUUID());
        return e instanceof TamableAnimal pet
                && pet.isTame()
                && monkeyJester.getOwnerUUID().equals(pet.getOwnerUUID());
    }

    @Override
    public boolean canUse() {
        if (!monkeyJester.isTame() || monkeyJester.isOrderedToSit() || monkeyJester.tickCount < nextHealTick) return false;
        if (monkeyJester.getOwnerUUID() == null) return false;
        return !findTargets().isEmpty();
    }

    @Override
    public boolean canContinueToUse() {
        return castTick < CAST_TOTAL_TICKS && !monkeyJester.isOrderedToSit();
    }

    @Override
    public void start() {
        castTick = 0;
        monkeyJester.getNavigation().stop();
        monkeyJester.setCasting(true);
    }

    @Override
    public void tick() {
        castTick++;

        LivingEntity owner = monkeyJester.getOwner();
        if(owner != null) {
            monkeyJester.getLookControl().setLookAt(owner, 30.F, 30.F);
        }

        if (castTick == HEAL_TICK) {
            healTargets();
        }
    }

    private void healTargets() {
        monkeyJester.lastActionTick = monkeyJester.tickCount;
        for (LivingEntity e : findTargets()) { // se buscan ahora, no al inicio
            e.heal(HEAL_AMOUNT);
            if (monkeyJester.level() instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(ParticleTypes.HEART,
                        e.getX(), e.getY() + e.getBbHeight() + 0.3D, e.getZ(),
                        5, 0.3D, 0.3D, 0.3D, 0.0D);
            }
        }
        monkeyJester.playSound(SoundEvents.AMETHYST_BLOCK_CHIME, 1.0F, 1.2F);
    }

    @Override
    public void stop() {
        monkeyJester.setCasting(false);
        castTick = 0;
        nextHealTick = monkeyJester.tickCount + COOLDOWN_TICKS;
    }
}
