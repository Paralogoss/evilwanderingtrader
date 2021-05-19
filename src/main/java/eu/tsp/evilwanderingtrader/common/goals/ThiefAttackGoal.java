package eu.tsp.evilwanderingtrader.common.goals;

import eu.tsp.evilwanderingtrader.common.entities.ThiefEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;

public class ThiefAttackGoal extends MeleeAttackGoal {
    private final ThiefEntity thief;

    public ThiefAttackGoal(ThiefEntity thiefIn, double speedIn, boolean useLongMemory) {
        super(thiefIn, speedIn, useLongMemory);
        thief = thiefIn;
    }

    /**
     * Returns whether execution should begin.
     */
    @Override
    public boolean shouldExecute() {
        return !this.thief.isDone() && super.shouldExecute();
    }

    /**
     * Returns whether execution should continue.
     */
    @Override
    public boolean shouldContinueExecuting() {
        return !this.thief.isDone() && super.shouldContinueExecuting();
    }

}
