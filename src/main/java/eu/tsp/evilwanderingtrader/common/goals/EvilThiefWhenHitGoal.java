package eu.tsp.evilwanderingtrader.common.goals;

import eu.tsp.evilwanderingtrader.common.entities.ThiefWanderingTraderEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;

public class EvilThiefWhenHitGoal extends Goal {
    protected final ThiefWanderingTraderEntity wanderingTrader;

    public EvilThiefWhenHitGoal(ThiefWanderingTraderEntity wanderingTrader) {
        this.wanderingTrader = wanderingTrader;
    }

    @Override
    public boolean shouldExecute() {
        return this.wanderingTrader.getRevengeTarget() != null && this.wanderingTrader.getRevengeTarget() instanceof PlayerEntity;
    }

    @Override
    public void startExecuting() {
        PlayerEntity player = (PlayerEntity) this.wanderingTrader.getRevengeTarget();
        wanderingTrader.turnIntoThief(player);
    }
}
