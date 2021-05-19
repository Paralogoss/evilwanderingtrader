package eu.tsp.evilwanderingtrader.common.goals;

import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.controller.LookController;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.PathNodeType;

import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;

public class FollowEntityGoal extends Goal {
    private final MobEntity follower;
    private final Predicate<MobEntity> followPredicate;
    private MobEntity followingEntity;
    private final double speedModifier;
    private final PathNavigator navigation;
    private int timeToRecalcPath;
    private final float stopDistance;
    private float oldWaterCost;
    private final float areaSize;

    public FollowEntityGoal(MobEntity follower, double speedModifier, float stopDistance, float areaSize, Predicate<MobEntity> followPredicate) {
        this.follower = follower;
        this.followPredicate = followPredicate;
        this.speedModifier = speedModifier;
        this.navigation = follower.getNavigator();
        this.stopDistance = stopDistance;
        this.areaSize = areaSize;
        this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        if (!(follower.getNavigator() instanceof GroundPathNavigator) && !(follower.getNavigator() instanceof FlyingPathNavigator)) {
            throw new IllegalArgumentException("Unsupported mob type for FollowEntityGoal");
        }
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean shouldExecute() {
        List<MobEntity> list = this.follower.world.getEntitiesWithinAABB(MobEntity.class, this.follower.getBoundingBox().grow(this.areaSize), this.followPredicate);
        if (!list.isEmpty()) {
            for (MobEntity mobentity : list) {
                if (!mobentity.isInvisible()) {
                    this.followingEntity = mobentity;
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting() {
        return this.followingEntity != null && !this.navigation.noPath() && this.follower.getDistanceSq(this.followingEntity) > (double) (this.stopDistance * this.stopDistance);
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        this.timeToRecalcPath = 0;
        this.oldWaterCost = this.follower.getPathPriority(PathNodeType.WATER);
        this.follower.setPathPriority(PathNodeType.WATER, 0.0F);
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask() {
        this.followingEntity = null;
        this.navigation.clearPath();
        this.follower.setPathPriority(PathNodeType.WATER, this.oldWaterCost);
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        if (this.followingEntity != null && !this.follower.getLeashed()) {
            this.follower.getLookController().setLookPositionWithEntity(this.followingEntity, 10.0F, (float) this.follower.getVerticalFaceSpeed());
            if (--this.timeToRecalcPath <= 0) {
                this.timeToRecalcPath = 10;
                double d0 = this.follower.getPosX() - this.followingEntity.getPosX();
                double d1 = this.follower.getPosY() - this.followingEntity.getPosY();
                double d2 = this.follower.getPosZ() - this.followingEntity.getPosZ();
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                if (!(d3 <= (double) (this.stopDistance * this.stopDistance))) {
                    this.navigation.tryMoveToEntityLiving(this.followingEntity, this.speedModifier);
                } else {
                    this.navigation.clearPath();
                    LookController lookcontroller = this.followingEntity.getLookController();
                    if (d3 <= (double) this.stopDistance || lookcontroller.getLookPosX() == this.follower.getPosX() && lookcontroller.getLookPosY() == this.follower.getPosY() && lookcontroller.getLookPosZ() == this.follower.getPosZ()) {
                        double d4 = this.followingEntity.getPosX() - this.follower.getPosX();
                        double d5 = this.followingEntity.getPosZ() - this.follower.getPosZ();
                        this.navigation.tryMoveToXYZ(this.follower.getPosX() - d4, this.follower.getPosY(), this.follower.getPosZ() - d5, this.speedModifier);
                    }

                }
            }
        }
    }
}

