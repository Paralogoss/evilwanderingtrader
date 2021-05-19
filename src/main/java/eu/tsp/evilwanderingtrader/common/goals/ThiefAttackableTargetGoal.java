package eu.tsp.evilwanderingtrader.common.goals;

import eu.tsp.evilwanderingtrader.common.entities.ThiefEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class ThiefAttackableTargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
    ThiefEntity thief;

    public ThiefAttackableTargetGoal(ThiefEntity entity, Class<T> targetClass, int targetChance, boolean checkSight, boolean nearbyOnly, @Nullable Predicate<LivingEntity> entitySelector) {
        super(entity, targetClass, targetChance, checkSight, nearbyOnly, entitySelector);
        this.thief = entity;
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
