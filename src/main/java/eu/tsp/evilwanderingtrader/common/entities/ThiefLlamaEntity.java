package eu.tsp.evilwanderingtrader.common.entities;

import eu.tsp.evilwanderingtrader.EvilWanderingTrader;
import eu.tsp.evilwanderingtrader.common.goals.FollowEntityGoal;
import eu.tsp.evilwanderingtrader.init.ModEntityTypes;
import eu.tsp.evilwanderingtrader.init.ModSoundEventTypes;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.horse.LlamaEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.LlamaSpitEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;

public class ThiefLlamaEntity extends LlamaEntity implements IMob, IRangedAttackMob {

    @Nullable
    PlayerEntity nemesis;

    boolean didSpit = false;

    Entity thief;
    
    private Goal escape = null;

    public ThiefLlamaEntity(EntityType<? extends LlamaEntity> type, World worldIn) {
        super(type, worldIn);
        this.setChested(true);
        this.initHorseChest();
        this.nemesis = null;
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 20.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.3D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 16.0D)
                .createMutableAttribute(Attributes.ATTACK_SPEED, 0.1D)
                .createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE, 0.7D);
    }

    @Nullable
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        if (reason == SpawnReason.CONVERSION) {
            EvilWanderingTrader.debugMessage(worldIn.getWorld(), "Converted llama to LlamaThief");
        }

        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        
        this.escape = new AvoidEntityGoal(this, PlayerEntity.class, 8.0F, 1.2F, 1.8F);
        
        this.targetSelector.addGoal(1, new FollowEntityGoal(this, 1.0D, 4.0F,
                16.0F, (entity) -> entity.equals(this.thief)
        ));
        
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.25D, 40, 20.0F));
        this.goalSelector.addGoal(2, this.escape);
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 32.0F, 0.1F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(9, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
    }

    public void setNemesis(PlayerEntity player) {
        this.nemesis = player;
    }

    public PlayerEntity getNemesis() {
        return this.nemesis;
    }

    @Override
    protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
        super.dropSpecialItems(source, looting, recentlyHitIn);

        Entity entity = source.getTrueSource();
        if (entity instanceof PlayerEntity) {
            ItemStack bones = new ItemStack(Items.BONE, this.rand.nextInt(6));
            this.entityDropItem(bones);
        }
    }

    @Override
    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {
        if (target.equals(this.nemesis)) this.spit(target);
    }

    private void spit(LivingEntity target) {
        LlamaSpitEntity llamaspitentity = new LlamaSpitEntity(this.world, this);
        double d0 = target.getPosX() - this.getPosX();
        double d1 = target.getPosYHeight(0.3333333333333333D) - llamaspitentity.getPosY();
        double d2 = target.getPosZ() - this.getPosZ();
        float f = MathHelper.sqrt(d0 * d0 + d2 * d2) * 0.2F;
        llamaspitentity.shoot(d0, d1 + (double) f, d2, 1.5F, 10.0F);
        if (!this.isSilent()) {
            this.playSound(SoundEvents.ENTITY_LLAMA_SPIT, 1.0F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
        }

        this.world.addEntity(llamaspitentity);
        this.didSpit = true;
    }

    public void setInventory(Inventory inventory) {
        if (inventory.getSizeInventory() <= this.getInventorySize()) this.horseChest = inventory;
    }

    @Override
    protected int getInventorySize() {
        return 17;
    }

    @Override
    public int getInventoryColumns() {
        return 5;
    }

    //TODO rajouter une mise a jour statique de cette info
    public int chestFirstFreeSlot() {
        Inventory inv = this.horseChest;
        int i = 2; // la case d'inventaire 0 correspond a la selle et 1 a l'armure (pas affichee mais heritee du cheval)
        while (i < inv.getSizeInventory() && !inv.getStackInSlot(i).isEmpty()) i++;
        return i;
    }

    public boolean addToChest(ItemStack items) {
        int i = chestFirstFreeSlot();
        if (i == this.horseChest.getSizeInventory()) return false;
        this.horseChest.setInventorySlotContents(i, items);
        return true;

    }

    @Override
    protected void dropInventory() {
        this.setChested(false);
        super.dropInventory();
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        if (this.thief != null) {
            compound.putInt("ThiefUUID", this.thief.getEntityId());
        }
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        if (compound.contains("ThiefUUID")) {
            this.thief = this.world.getEntityByID(compound.getInt("ThiefUUID"));
        }
    }

    @Override
    protected float getSoundPitch() {
        return super.getSoundPitch() * 0.5F;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return ModSoundEventTypes.THIEF_LLAMA_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_LLAMA_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_LLAMA_DEATH;
    }

    @Override
    protected SoundEvent getAngrySound() {
        return SoundEvents.ENTITY_LLAMA_ANGRY;
    }

    @Override
    public boolean canEatGrass() {
        return false;
    }

    public void turnBackIntoWandererLlama(ThiefWanderingTraderEntity trader) {
        if (!this.world.isRemote) {
            this.startConversion((ServerWorld) this.world, trader);
        }
    }

    private void startConversion(ServerWorld serverWorld, ThiefWanderingTraderEntity trader) {
        ThiefTraderLlamaEntity llama = this.func_233656_b_(ModEntityTypes.THIEF_TRADER_LLAMA.get(), false);

        llama.setInventory(this.horseChest);
        llama.setOwnerUniqueId(this.getOwnerUniqueId());
        llama.setHorseTamed(this.isTame());
        llama.setLeashHolder(trader, true);

        llama.onInitialSpawn(serverWorld, serverWorld.getDifficultyForLocation(llama.getPosition()),
                SpawnReason.CONVERSION, null, null);
        this.addPotionEffect(new EffectInstance(Effects.STRENGTH, 20 * 3,
                Math.min(this.world.getDifficulty().getId() - 1, 0)));
        this.playSound(ModSoundEventTypes.THIEF_CONVERSION.get(), 0.5F + this.rand.nextFloat(), this.rand.nextFloat() * 0.7F + 1.0F);

        for (int i = 0; i < 20; ++i) {
            double d0 = this.rand.nextGaussian() * 0.02D;
            double d1 = this.rand.nextGaussian() * 0.02D;
            double d2 = this.rand.nextGaussian() * 0.02D;
            serverWorld.spawnParticle(ParticleTypes.ENTITY_EFFECT, this.getPosXWidth(0D) - d0 * 10.0D,
                    this.getPosYRandom() - d1 * 10.0D, this.getPosZRandom(1.0D) - d2 * 10.0D,
                    2, 0, 0, 0, (d0 + d1 + d2) / 3);
        }

        net.minecraftforge.event.ForgeEventFactory.onLivingConvert(this, llama);
    }

    @Override
	public boolean setTamedBy(PlayerEntity player) {
    	if(escape != null) {
    		this.goalSelector.removeGoal(escape); //TODO make this working :/
    		this.escape = null;
    	}    		
		return super.setTamedBy(player);
	}

	@Override
    public boolean isNoDespawnRequired() {
        return true;
    }

    @Override
    public boolean canDespawn(double distanceToClosestPlayer) {
        return false;
    }

}
