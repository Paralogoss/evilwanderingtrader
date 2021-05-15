package eu.tsp.evilwanderingtrader.common.goals;

import eu.tsp.evilwanderingtrader.common.entities.GypsyEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;

public class GypsyAttackGoal extends MeleeAttackGoal {
    private final GypsyEntity gypsy;

    public GypsyAttackGoal(GypsyEntity gypsyIn, double speedIn, boolean useLongMemory) {
        super(gypsyIn, speedIn, useLongMemory);
        gypsy = gypsyIn;
    }

    /**
     * Returns whether execution should begin.
     */
    @Override
    public boolean shouldExecute() {
        return !this.gypsy.isDone() && super.shouldExecute();
    }

    /**
     * Returns whether execution should continue.
     */
    @Override
    public boolean shouldContinueExecuting() {
        return !this.gypsy.isDone() && super.shouldContinueExecuting();
    }

}
