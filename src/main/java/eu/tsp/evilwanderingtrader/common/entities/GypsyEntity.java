package eu.tsp.evilwanderingtrader.common.entities;

import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;

import eu.tsp.evilwanderingtrader.EvilWanderingTrader;
import eu.tsp.evilwanderingtrader.common.goals.GypsyAttackGoal;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.EntitySenses;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.BodyController;
import net.minecraft.entity.ai.controller.JumpController;
import net.minecraft.entity.ai.controller.LookController;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.ai.goal.ZombieAttackGoal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext.Builder;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.tags.ITag;
import net.minecraft.util.DamageSource;
import net.minecraft.util.HandSide;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.thread.SidedThreadGroups;

public class GypsyEntity extends MonsterEntity implements IMob {
	protected final int experienceValue = 5000;
	//@Nullable
	//final PlayerEntity nemesis; //personne qu'il cible
	protected boolean missionAccomplie; //s'il est prêt à revenir un wanderingtrader

	public GypsyEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		missionAccomplie = false;
		//nemesis = this.world.getClosestPlayer(this.getPosX(), this.getPosYEye(), this.getPosZ(), 64d, (new EntityPredicate()).setDistance(64d).setCustomPredicate((@Nullable Predicate<LivingEntity>)null));
		//EvilWanderingTrader.LOGGER.info("ok");
	}
	
	public static AttributeModifierMap.MutableAttribute setCustomAttributes()
    {
        return MobEntity.func_233666_p_()
        		.createMutableAttribute( Attributes.MAX_HEALTH,1.0D )
        		.createMutableAttribute( Attributes.MOVEMENT_SPEED,0.3D )
        		.createMutableAttribute( Attributes.ATTACK_DAMAGE,4.0D )
        		.createMutableAttribute( Attributes.ATTACK_SPEED,0.1D )
        		.createMutableAttribute( Attributes.KNOCKBACK_RESISTANCE,0.7D );
    }

	@Override
	protected void registerGoals() {
		super.registerGoals();
		
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));

	    this.goalSelector.addGoal(2, new GypsyAttackGoal(this, 1.35D, true));
	    this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 16.0F, 0.05F));
	    this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
	    this.goalSelector.addGoal(9, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
	    	    
	}
	
	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		if (super.attackEntityAsMob(entityIn)) {
			if (entityIn instanceof PlayerEntity) {
				PlayerEntity target = (PlayerEntity) entityIn;
				PlayerInventory inv = ((PlayerEntity)entityIn).inventory;
				List<NonNullList<ItemStack>> allInventories = ImmutableList.of(inv.mainInventory, inv.armorInventory, inv.offHandInventory);
				int nbItems = -1;
				for(List<ItemStack> list : allInventories) {
					for (int i=0; i<list.size(); i++) {
						if(!list.get(i).isEmpty()) {
							nbItems++;
						}
					}
				}
				if(nbItems<0) {
					this.missionAccomplie = true;
					this.setAggroed(false);
				} else {
					int choix = (nbItems==0)?0:new Random().nextInt(nbItems);
					nbItems = -1;
					for(List<ItemStack> list : allInventories) {
						for (int i=0; i<list.size(); i++) {
							if(!list.get(i).isEmpty()) {
								nbItems++;
								if(nbItems == choix) {
									target.dropItem(list.get(i), true, false);
						            list.set(i, ItemStack.EMPTY);
								}
							}
						}
					}
					
				}
				
				
				
				//EvilWanderingTrader.LOGGER.info("");
				
			}
			return true;
		} else {
			return false;
		}
	}	
	

	




	@Override
	public void addTrackingPlayer(ServerPlayerEntity player) {
		EvilWanderingTrader.LOGGER.info("addTrackingPlayer ",player.getName());
		super.addTrackingPlayer(player);
	}


	@Override
	public void setAttackTarget(LivingEntity entitylivingbaseIn) {
		// TODO Auto-generated method stub
		super.setAttackTarget(entitylivingbaseIn);
	}

	@Override
	public boolean canAttack(EntityType<?> typeIn) {
		// TODO Auto-generated method stub
		return super.canAttack(typeIn);
	}



	@Override
	public void baseTick() {
		// TODO Auto-generated method stub
		super.baseTick();
	}


	@Override
	public void tick() {
		// TODO Auto-generated method stub
		super.tick();
	}

	@Override
	protected void updateMovementGoalFlags() {
		// TODO Auto-generated method stub
		super.updateMovementGoalFlags();
	}

	@Override
	protected float updateDistance(float p_110146_1_, float p_110146_2_) {
		// TODO Auto-generated method stub
		return super.updateDistance(p_110146_1_, p_110146_2_);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		// TODO Auto-generated method stub
		return super.getAmbientSound();
	}

	@Override
	public void writeAdditional(CompoundNBT compound) {
		// TODO Auto-generated method stub
		super.writeAdditional(compound);
	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		// TODO Auto-generated method stub
		super.readAdditional(compound);
	}

	@Override
	protected void dropLoot(DamageSource damageSourceIn, boolean attackedRecently) {
		// TODO Auto-generated method stub
		super.dropLoot(damageSourceIn, attackedRecently);
	}


	@Override
	protected ResourceLocation getLootTable() {
		// TODO Auto-generated method stub
		return super.getLootTable();
	}

	@Override
	public void setMoveForward(float amount) {
		// TODO Auto-generated method stub
		super.setMoveForward(amount);
	}

	@Override
	public void setMoveVertical(float amount) {
		// TODO Auto-generated method stub
		super.setMoveVertical(amount);
	}

	@Override
	public void setMoveStrafing(float amount) {
		// TODO Auto-generated method stub
		super.setMoveStrafing(amount);
	}

	@Override
	public void setAIMoveSpeed(float speedIn) {
		// TODO Auto-generated method stub
		super.setAIMoveSpeed(speedIn);
	}

	@Override
	public void livingTick() {
		// TODO Auto-generated method stub
		super.livingTick();
	}

	@Override
	protected void updateEquipmentIfNeeded(ItemEntity itemEntity) {
		// TODO Auto-generated method stub
		super.updateEquipmentIfNeeded(itemEntity);
	}

	@Override
	protected boolean shouldExchangeEquipment(ItemStack candidate, ItemStack existing) {
		// TODO Auto-generated method stub
		return super.shouldExchangeEquipment(candidate, existing);
	}

	@Override
	public boolean canEquipItem(ItemStack stack) {
		// TODO Auto-generated method stub
		return super.canEquipItem(stack);
	}


	@Override
	public boolean preventDespawn() {
		// TODO Auto-generated method stub
		return super.preventDespawn();
	}

	@Override
	protected boolean isDespawnPeaceful() {
		// TODO Auto-generated method stub
		return super.isDespawnPeaceful();
	}


	@Override
	protected void sendDebugPackets() {
		// TODO Auto-generated method stub
		super.sendDebugPackets();
	}

	@Override
	protected void updateAITasks() {
		// TODO Auto-generated method stub
		super.updateAITasks();
	}

	@Override
	public boolean isNotColliding(IWorldReader worldIn) {
		// TODO Auto-generated method stub
		return super.isNotColliding(worldIn);
	}
	

	@Override
	public int getMaxFallHeight() {
		// TODO Auto-generated method stub
		return super.getMaxFallHeight();
	}

	@Override
	public Iterable<ItemStack> getHeldEquipment() {
		// TODO Auto-generated method stub
		return super.getHeldEquipment();
	}

	@Override
	public Iterable<ItemStack> getArmorInventoryList() {
		// TODO Auto-generated method stub
		return super.getArmorInventoryList();
	}

	@Override
	public ItemStack getItemStackFromSlot(EquipmentSlotType slotIn) {
		// TODO Auto-generated method stub
		return super.getItemStackFromSlot(slotIn);
	}

	@Override
	public void setItemStackToSlot(EquipmentSlotType slotIn, ItemStack stack) {
		// TODO Auto-generated method stub
		super.setItemStackToSlot(slotIn, stack);
	}

	@Override
	protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
		// TODO Auto-generated method stub
		super.dropSpecialItems(source, looting, recentlyHitIn);
	}

	@Override
	protected float getDropChance(EquipmentSlotType slotIn) {
		// TODO Auto-generated method stub
		return super.getDropChance(slotIn);
	}
                                                

	@Override
	public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
			ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		this.enablePersistence();
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}


	@Override
	public boolean isNoDespawnRequired() {
		return true; //super.isNoDespawnRequired();
	}

	@Override
	protected void onChildSpawnFromEgg(PlayerEntity playerIn, MobEntity child) {
		super.onChildSpawnFromEgg(playerIn, child);
	}

	
}
