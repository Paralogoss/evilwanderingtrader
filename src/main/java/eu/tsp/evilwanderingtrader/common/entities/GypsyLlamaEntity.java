package eu.tsp.evilwanderingtrader.common.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.horse.AbstractChestedHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class GypsyLlamaEntity extends AbstractChestedHorseEntity implements IMob {

	public GypsyLlamaEntity(EntityType<? extends AbstractChestedHorseEntity> type, World worldIn) {
		super(type, worldIn);
		this.setChested(true);
		this.initHorseChest();
	}
	
	public static AttributeModifierMap.MutableAttribute setCustomAttributes()
    {
        return MobEntity.func_233666_p_()
        		.createMutableAttribute( Attributes.MAX_HEALTH,1.0D )
        		.createMutableAttribute( Attributes.MOVEMENT_SPEED,0.3D )
        		.createMutableAttribute( Attributes.ATTACK_DAMAGE,16.0D )
        		.createMutableAttribute( Attributes.ATTACK_SPEED,0.1D )
        		.createMutableAttribute( Attributes.KNOCKBACK_RESISTANCE,0.7D );
    }

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(2, new AvoidEntityGoal(this, PlayerEntity.class, 16.0F, 1.2F, 1.8F));
		this.goalSelector.addGoal(4, new SwimGoal(this));
		this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 32.0F, 0.1F));
		this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
		this.goalSelector.addGoal(9, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
	}


	@Override
	protected int getInventorySize() {
		return 17;
	}

	@Override
	public int getInventoryColumns() {
		return 5;
	}

	protected void dropInventory() {
		this.setChested(false);
		super.dropInventory();
	}

		@Override
	public boolean canEatGrass() {
		return false;
	}

	/*
	 * When a player wants to mount a llama we open the inventory instead (llamas are not rideable)
	 */
	protected void mountTo(PlayerEntity player) {
		player.openHorseInventory(this, this.horseChest);
	}
}
