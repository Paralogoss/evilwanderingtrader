package eu.tsp.evilwanderingtrader.common.goals;

import eu.tsp.evilwanderingtrader.common.entities.GypsyLlamaEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;

public class HurtNemesisGoal extends HurtByTargetGoal {

	public HurtNemesisGoal(CreatureEntity creatureIn) {
		super(creatureIn);
		//EvilWanderingTrader.LOGGER.info("GypsyAttackGoal performed");
	}
	@Override
	public void startExecuting() {
		if (this.goalOwner instanceof GypsyLlamaEntity) {
			this.goalOwner.setAttackTarget(((GypsyLlamaEntity)this.goalOwner).getNemesis());
			this.target = this.goalOwner.getAttackTarget();
//			this.revengeTimerOld = this.goalOwner.getRevengeTimer();
			this.unseenMemoryTicks = 300;
		}
		super.startExecuting();
	}

}

