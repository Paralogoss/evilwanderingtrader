package eu.tsp.evilwanderingtrader.common.goals;

import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.TargetGoal;

public class ProtectLama extends TargetGoal {

	public ProtectLama(MobEntity mobIn, boolean checkSight) {
		super(mobIn, checkSight);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean shouldExecute() {
		// TODO Auto-generated method stub
		return false;
	}

}
