package eu.tsp.evilwanderingtrader.common.goals;

import eu.tsp.evilwanderingtrader.common.entities.GypsyWanderingTraderEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;

public class EvilGypsyWhenHitGoal extends Goal {
    protected final GypsyWanderingTraderEntity wanderingTrader;

    public EvilGypsyWhenHitGoal(GypsyWanderingTraderEntity wanderingTrader) {
        this.wanderingTrader = wanderingTrader;
    }

    @Override
    public boolean shouldExecute() {
        return this.wanderingTrader.getRevengeTarget() != null && this.wanderingTrader.getRevengeTarget() instanceof PlayerEntity;
    }

    @Override
    public void startExecuting() {
        PlayerEntity player = (PlayerEntity) this.wanderingTrader.getRevengeTarget();
        wanderingTrader.turnIntoGypsy(player);
    }
}
