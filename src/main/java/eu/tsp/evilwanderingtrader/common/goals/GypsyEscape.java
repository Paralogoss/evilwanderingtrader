package eu.tsp.evilwanderingtrader.common.goals;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

public class GypsyEscape extends MoveToBlockGoal {

	public GypsyEscape(CreatureEntity creature, double speedIn, int length) {
		super(creature, speedIn, length);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean shouldMoveTo(IWorldReader worldIn, BlockPos pos) {
		// TODO Auto-generated method stub
		return false;
	}

}
