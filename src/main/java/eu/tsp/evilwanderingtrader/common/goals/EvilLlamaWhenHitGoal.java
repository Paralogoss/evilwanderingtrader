package eu.tsp.evilwanderingtrader.common.goals;

import eu.tsp.evilwanderingtrader.common.entities.GypsyTraderLlamaEntity;
import eu.tsp.evilwanderingtrader.common.entities.GypsyWanderingTraderEntity;
import eu.tsp.evilwanderingtrader.init.ModEntityTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;

public class EvilLlamaWhenHitGoal extends Goal {
    protected final GypsyTraderLlamaEntity llama;

    public EvilLlamaWhenHitGoal(GypsyTraderLlamaEntity llama) {
        this.llama = llama;
    }

    @Override
    public boolean shouldExecute() {
        return this.llama.getRevengeTarget() != null && this.llama.getRevengeTarget() instanceof PlayerEntity &&
                this.llama.getLeashed() && this.llama.getLeashHolder() instanceof GypsyWanderingTraderEntity;
    }

    @Override
    public void startExecuting() {
        GypsyWanderingTraderEntity wanderer = (GypsyWanderingTraderEntity) this.llama.getLeashHolder();
        if (net.minecraftforge.event.ForgeEventFactory.canLivingConvert(wanderer, ModEntityTypes.GYPSY.get(), (timer) -> {
        })) {
            PlayerEntity player = (PlayerEntity) this.llama.getRevengeTarget();
            wanderer.turnIntoGypsy(player);
        }
    }
}
