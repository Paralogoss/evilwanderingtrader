package eu.tsp.evilwanderingtrader.common.goals;

import eu.tsp.evilwanderingtrader.common.entities.GypsyEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class GypsyAttackableTargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
    GypsyEntity gypsy;

    public GypsyAttackableTargetGoal(GypsyEntity entity, Class<T> targetClass, int targetChance, boolean checkSight, boolean nearbyOnly, @Nullable Predicate<LivingEntity> entitySelector) {
        super(entity, targetClass, targetChance, checkSight, nearbyOnly, entitySelector);
        this.gypsy = entity;
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
