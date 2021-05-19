package eu.tsp.evilwanderingtrader.common.goals;

import eu.tsp.evilwanderingtrader.common.entities.ThiefTraderLlamaEntity;
import eu.tsp.evilwanderingtrader.common.entities.ThiefWanderingTraderEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;

public class EvilLlamaWhenHitGoal extends Goal {
    protected final ThiefTraderLlamaEntity llama;

    public EvilLlamaWhenHitGoal(ThiefTraderLlamaEntity llama) {
        this.llama = llama;
    }

    @Override
    public boolean shouldExecute() {
        return this.llama.getRevengeTarget() != null && this.llama.getRevengeTarget() instanceof PlayerEntity &&
                this.llama.getLeashed() && this.llama.getLeashHolder() instanceof ThiefWanderingTraderEntity;
    }

    @Override
    public void startExecuting() {
        ThiefWanderingTraderEntity wanderer = (ThiefWanderingTraderEntity) this.llama.getLeashHolder();
        PlayerEntity player = (PlayerEntity) this.llama.getRevengeTarget();
        wanderer.turnIntoThief(player);
    }
}
