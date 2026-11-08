package com.clase.monkeycraft.entity.ai;

import com.clase.monkeycraft.entity.custom.MonkeyJesterEntity;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class MonkeyJesterIdleGoal extends Goal {
    private static final int INTERVAL_TICKS = 1200;
    private static final int IDLE_TICKS = 180;

    private final MonkeyJesterEntity monkeyJester;
    private int animTick = 0;

    public MonkeyJesterIdleGoal(MonkeyJesterEntity monkeyJester) {
        this.monkeyJester = monkeyJester;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public boolean canUse() {
        return monkeyJester.tickCount - monkeyJester.lastActionTick >= INTERVAL_TICKS
                && !monkeyJester.isOrderedToSit()
                && monkeyJester.getTarget() == null
                && monkeyJester.hurtTime == 0
                && monkeyJester.onGround()
                && !monkeyJester.isInWaterOrBubble();
    }

    @Override
    public boolean canContinueToUse() {
        return animTick < IDLE_TICKS
                && !monkeyJester.isOrderedToSit()
                && monkeyJester.hurtTime == 0;
    }

    @Override
    public void start() {
        animTick = 0;
        monkeyJester.getNavigation().stop();
        monkeyJester.setIdleAnim(true);
    }

    @Override
    public void tick() {
        animTick++;
    }

    @Override
    public void stop() {
        monkeyJester.setIdleAnim(false);
        monkeyJester.lastActionTick = monkeyJester.tickCount; // reinicia el minuto
        animTick = 0;
    }
}
