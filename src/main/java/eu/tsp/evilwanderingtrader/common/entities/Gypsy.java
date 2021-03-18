package eu.tsp.evilwanderingtrader.common.entities;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.EntitySenses;
import net.minecraft.entity.ai.controller.BodyController;
import net.minecraft.entity.ai.controller.JumpController;
import net.minecraft.entity.ai.controller.LookController;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
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
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class Gypsy extends CreatureEntity {

	protected Gypsy(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public float getBlockPathWeight(BlockPos pos) {
		// TODO Auto-generated method stub
		return super.getBlockPathWeight(pos);
	}

	@Override
	public float getBlockPathWeight(BlockPos pos, IWorldReader worldIn) {
		// TODO Auto-generated method stub
		return super.getBlockPathWeight(pos, worldIn);
	}

	@Override
	public boolean canSpawn(IWorld worldIn, SpawnReason spawnReasonIn) {
		// TODO Auto-generated method stub
		return super.canSpawn(worldIn, spawnReasonIn);
	}

	@Override
	public boolean hasPath() {
		// TODO Auto-generated method stub
		return super.hasPath();
	}

	@Override
	protected void updateLeashedState() {
		// TODO Auto-generated method stub
		super.updateLeashedState();
	}

	@Override
	protected double followLeashSpeed() {
		// TODO Auto-generated method stub
		return super.followLeashSpeed();
	}

	@Override
	protected void onLeashDistance(float distance) {
		// TODO Auto-generated method stub
		super.onLeashDistance(distance);
	}

	@Override
	protected void registerGoals() {
		// TODO Auto-generated method stub
		super.registerGoals();
	}

	@Override
	protected PathNavigator createNavigator(World worldIn) {
		// TODO Auto-generated method stub
		return super.createNavigator(worldIn);
	}

	@Override
	public float getPathPriority(PathNodeType nodeType) {
		// TODO Auto-generated method stub
		return super.getPathPriority(nodeType);
	}

	@Override
	public void setPathPriority(PathNodeType nodeType, float priority) {
		// TODO Auto-generated method stub
		super.setPathPriority(nodeType, priority);
	}

	@Override
	protected BodyController createBodyController() {
		// TODO Auto-generated method stub
		return super.createBodyController();
	}

	@Override
	public LookController getLookController() {
		// TODO Auto-generated method stub
		return super.getLookController();
	}

	@Override
	public MovementController getMoveHelper() {
		// TODO Auto-generated method stub
		return super.getMoveHelper();
	}

	@Override
	public JumpController getJumpController() {
		// TODO Auto-generated method stub
		return super.getJumpController();
	}

	@Override
	public PathNavigator getNavigator() {
		// TODO Auto-generated method stub
		return super.getNavigator();
	}

	@Override
	public EntitySenses getEntitySenses() {
		// TODO Auto-generated method stub
		return super.getEntitySenses();
	}

	@Override
	public LivingEntity getAttackTarget() {
		// TODO Auto-generated method stub
		return super.getAttackTarget();
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
	public void eatGrassBonus() {
		// TODO Auto-generated method stub
		super.eatGrassBonus();
	}

	@Override
	protected void registerData() {
		// TODO Auto-generated method stub
		super.registerData();
	}

	@Override
	public int getTalkInterval() {
		// TODO Auto-generated method stub
		return super.getTalkInterval();
	}

	@Override
	public void playAmbientSound() {
		// TODO Auto-generated method stub
		super.playAmbientSound();
	}

	@Override
	public void baseTick() {
		// TODO Auto-generated method stub
		super.baseTick();
	}

	@Override
	protected void playHurtSound(DamageSource source) {
		// TODO Auto-generated method stub
		super.playHurtSound(source);
	}

	@Override
	protected int getExperiencePoints(PlayerEntity player) {
		// TODO Auto-generated method stub
		return super.getExperiencePoints(player);
	}

	@Override
	public void spawnExplosionParticle() {
		// TODO Auto-generated method stub
		super.spawnExplosionParticle();
	}

	@Override
	public void handleStatusUpdate(byte id) {
		// TODO Auto-generated method stub
		super.handleStatusUpdate(id);
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
	protected Builder getLootContextBuilder(boolean attackedRecently, DamageSource damageSourceIn) {
		// TODO Auto-generated method stub
		return super.getLootContextBuilder(attackedRecently, damageSourceIn);
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
	public boolean canDespawn(double distanceToClosestPlayer) {
		// TODO Auto-generated method stub
		return super.canDespawn(distanceToClosestPlayer);
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
	public void checkDespawn() {
		// TODO Auto-generated method stub
		super.checkDespawn();
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
	public int getVerticalFaceSpeed() {
		// TODO Auto-generated method stub
		return super.getVerticalFaceSpeed();
	}

	@Override
	public int getHorizontalFaceSpeed() {
		// TODO Auto-generated method stub
		return super.getHorizontalFaceSpeed();
	}

	@Override
	public int getFaceRotSpeed() {
		// TODO Auto-generated method stub
		return super.getFaceRotSpeed();
	}

	@Override
	public boolean isNotColliding(IWorldReader worldIn) {
		// TODO Auto-generated method stub
		return super.isNotColliding(worldIn);
	}

	@Override
	public int getMaxSpawnedInChunk() {
		// TODO Auto-generated method stub
		return super.getMaxSpawnedInChunk();
	}

	@Override
	public boolean isMaxGroupSize(int sizeIn) {
		// TODO Auto-generated method stub
		return super.isMaxGroupSize(sizeIn);
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
	protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
		// TODO Auto-generated method stub
		super.setEquipmentBasedOnDifficulty(difficulty);
	}

	@Override
	protected void setEnchantmentBasedOnDifficulty(DifficultyInstance difficulty) {
		// TODO Auto-generated method stub
		super.setEnchantmentBasedOnDifficulty(difficulty);
	}

	@Override
	public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
			ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		// TODO Auto-generated method stub
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	@Override
	public boolean canBeSteered() {
		// TODO Auto-generated method stub
		return super.canBeSteered();
	}

	@Override
	public void enablePersistence() {
		// TODO Auto-generated method stub
		super.enablePersistence();
	}

	@Override
	public void setDropChance(EquipmentSlotType slotIn, float chance) {
		// TODO Auto-generated method stub
		super.setDropChance(slotIn, chance);
	}

	@Override
	public boolean canPickUpLoot() {
		// TODO Auto-generated method stub
		return super.canPickUpLoot();
	}

	@Override
	public void setCanPickUpLoot(boolean canPickup) {
		// TODO Auto-generated method stub
		super.setCanPickUpLoot(canPickup);
	}

	@Override
	public boolean canPickUpItem(ItemStack itemstackIn) {
		// TODO Auto-generated method stub
		return super.canPickUpItem(itemstackIn);
	}

	@Override
	public boolean isNoDespawnRequired() {
		// TODO Auto-generated method stub
		return super.isNoDespawnRequired();
	}

	@Override
	protected void onChildSpawnFromEgg(PlayerEntity playerIn, MobEntity child) {
		// TODO Auto-generated method stub
		super.onChildSpawnFromEgg(playerIn, child);
	}

	@Override
	public boolean isWithinHomeDistanceCurrentPosition() {
		// TODO Auto-generated method stub
		return super.isWithinHomeDistanceCurrentPosition();
	}

	@Override
	public boolean isWithinHomeDistanceFromPosition(BlockPos pos) {
		// TODO Auto-generated method stub
		return super.isWithinHomeDistanceFromPosition(pos);
	}

	@Override
	public void setHomePosAndDistance(BlockPos pos, int distance) {
		// TODO Auto-generated method stub
		super.setHomePosAndDistance(pos, distance);
	}

	@Override
	public BlockPos getHomePosition() {
		// TODO Auto-generated method stub
		return super.getHomePosition();
	}

	@Override
	public float getMaximumHomeDistance() {
		// TODO Auto-generated method stub
		return super.getMaximumHomeDistance();
	}

	@Override
	public boolean detachHome() {
		// TODO Auto-generated method stub
		return super.detachHome();
	}

	@Override
	public void clearLeashed(boolean sendPacket, boolean dropLead) {
		// TODO Auto-generated method stub
		super.clearLeashed(sendPacket, dropLead);
	}

	@Override
	public boolean canBeLeashedTo(PlayerEntity player) {
		// TODO Auto-generated method stub
		return super.canBeLeashedTo(player);
	}

	@Override
	public boolean getLeashed() {
		// TODO Auto-generated method stub
		return super.getLeashed();
	}

	@Override
	public Entity getLeashHolder() {
		// TODO Auto-generated method stub
		return super.getLeashHolder();
	}

	@Override
	public void setLeashHolder(Entity entityIn, boolean sendAttachNotification) {
		// TODO Auto-generated method stub
		super.setLeashHolder(entityIn, sendAttachNotification);
	}

	@Override
	public void setVehicleEntityId(int leashHolderIDIn) {
		// TODO Auto-generated method stub
		super.setVehicleEntityId(leashHolderIDIn);
	}

	@Override
	public boolean startRiding(Entity entityIn, boolean force) {
		// TODO Auto-generated method stub
		return super.startRiding(entityIn, force);
	}

	@Override
	public boolean replaceItemInInventory(int inventorySlot, ItemStack itemStackIn) {
		// TODO Auto-generated method stub
		return super.replaceItemInInventory(inventorySlot, itemStackIn);
	}

	@Override
	public boolean canPassengerSteer() {
		// TODO Auto-generated method stub
		return super.canPassengerSteer();
	}

	@Override
	public boolean isServerWorld() {
		// TODO Auto-generated method stub
		return super.isServerWorld();
	}

	@Override
	public void setNoAI(boolean disable) {
		// TODO Auto-generated method stub
		super.setNoAI(disable);
	}

	@Override
	public void setLeftHanded(boolean leftHanded) {
		// TODO Auto-generated method stub
		super.setLeftHanded(leftHanded);
	}

	@Override
	public void setAggroed(boolean hasAggro) {
		// TODO Auto-generated method stub
		super.setAggroed(hasAggro);
	}

	@Override
	public boolean isAIDisabled() {
		// TODO Auto-generated method stub
		return super.isAIDisabled();
	}

	@Override
	public boolean isLeftHanded() {
		// TODO Auto-generated method stub
		return super.isLeftHanded();
	}

	@Override
	public boolean isAggressive() {
		// TODO Auto-generated method stub
		return super.isAggressive();
	}

	@Override
	public void setChild(boolean childZombie) {
		// TODO Auto-generated method stub
		super.setChild(childZombie);
	}

	@Override
	public HandSide getPrimaryHand() {
		// TODO Auto-generated method stub
		return super.getPrimaryHand();
	}

	@Override
	public boolean canAttack(LivingEntity target) {
		// TODO Auto-generated method stub
		return super.canAttack(target);
	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		// TODO Auto-generated method stub
		return super.attackEntityAsMob(entityIn);
	}

	@Override
	protected boolean isInDaylight() {
		// TODO Auto-generated method stub
		return super.isInDaylight();
	}

	@Override
	protected void handleFluidJump(ITag<Fluid> fluidTag) {
		// TODO Auto-generated method stub
		super.handleFluidJump(fluidTag);
	}

	@Override
	protected void setDead() {
		// TODO Auto-generated method stub
		super.setDead();
	}
	
	
}
