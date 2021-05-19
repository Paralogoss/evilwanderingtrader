package eu.tsp.evilwanderingtrader.common.entities;

import com.google.common.collect.ImmutableList;
import eu.tsp.evilwanderingtrader.common.goals.GypsyAttackGoal;
import eu.tsp.evilwanderingtrader.common.goals.GypsyAttackableTargetGoal;
import eu.tsp.evilwanderingtrader.init.ModEntityTypes;
import eu.tsp.evilwanderingtrader.init.ModSoundEventTypes;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.BossInfo;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;


public class GypsyEntity extends MonsterEntity implements IMob {
    public static final Double MAX_DISTANCE_TO_LLAMAS = 16D;

    // Targeted player
    @Nullable
    PlayerEntity nemesis;

    // Time in ticks (1/20 second) before going back to wandering trader
    // Value is positive only if nemesis has been killed or has an empty inventory.
    int ticksBeforeReconversion = -1;

    // True if Gypsy is converting back into wanderer
    boolean converting = false;

    // Counts the amount of emerald the gypsy has picked up. When 5 have been picked up the gypsy goes back to wanderer.
    int pickedUpEmerald = 0;

    private final ServerBossInfo bossInfo = (ServerBossInfo) (new ServerBossInfo(this.getDisplayName(), BossInfo.Color.RED, BossInfo.Overlay.PROGRESS)).setDarkenSky(true);

    public GypsyEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
        this.setHealth(this.getMaxHealth());
        this.experienceValue = 100;
        this.nemesis = null;
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 20.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.3D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 4.0D)
                .createMutableAttribute(Attributes.ATTACK_SPEED, 0.1D)
                .createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE, 0.7D)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 80D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.targetSelector.addGoal(1, new GypsyAttackableTargetGoal(this, PlayerEntity.class,
                10, true, false,
                (entity) -> entity instanceof PlayerEntity && entity.equals(this.nemesis)
        ));

        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new GypsyAttackGoal(this, 1.35D, true));
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 16.0F, 0.05F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(9, new WaterAvoidingRandomWalkingGoal(this, 1.0D));

    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        if (!super.attackEntityAsMob(entityIn)) {
            return false;
        }

        if (!(entityIn instanceof PlayerEntity) || !entityIn.equals(this.nemesis)) {
            return false;
        }

        PlayerInventory inv = this.nemesis.inventory;
        List<NonNullList<ItemStack>> allInventories = ImmutableList.of(inv.mainInventory, inv.armorInventory, inv.offHandInventory);
        int itemCount = 0;
        for (List<ItemStack> list : allInventories) {
            for (ItemStack itemStack : list) {
                if (!itemStack.isEmpty()) itemCount++;
            }
        }

        if (itemCount <= 0) {
            this.setDone();
        } else {
            int choice = new Random().nextInt(itemCount);
            itemCount = -1;
            for (List<ItemStack> list : allInventories) {
                for (int i = 0; i < list.size(); i++) {
                    if (!list.get(i).isEmpty()) {
                        itemCount++;
                        if (itemCount == choice) {
                            sendStolenItemStack(list.get(i));
                            list.set(i, ItemStack.EMPTY);
                        }
                    }
                }
            }
        }
        return true;
    }

    protected void sendStolenItemStack(ItemStack items) {
        Double maxDistance = GypsyEntity.MAX_DISTANCE_TO_LLAMAS;
        List<GypsyLlamaEntity> entities = this.world.getEntitiesWithinAABB(
                GypsyLlamaEntity.class,
                new AxisAlignedBB(
                        this.getPosX() - maxDistance, this.getPosYEye() - maxDistance / 2, this.getPosZ() - maxDistance,
                        this.getPosX() + maxDistance, this.getPosYEye() + maxDistance / 2, this.getPosZ() + maxDistance)
        );

        if (!entities.isEmpty()) {
            Iterator<GypsyLlamaEntity> llamas = entities.iterator();
            GypsyLlamaEntity llama = llamas.next();
            Predicate<GypsyLlamaEntity> a = (ll) -> ll.isAlive() && ll.gypsy != null && ll.gypsy.equals(this) && ll.addToChest(items);
            boolean success = a.test(llama);
            while (!success && llamas.hasNext()) {
                llama = llamas.next();
                success = a.test(llama);
            }
            if (success) return;
        }
        this.nemesis.dropItem(items, true, false);
    }

    public void setNemesis(PlayerEntity player) {
        this.nemesis = player;
        this.bossInfo.addPlayer((ServerPlayerEntity) player);
    }

    public boolean isDone() {
        return this.ticksBeforeReconversion >= 0;
    }

    public void setDone() {
        if (!this.isDone()) {
            this.setAggroed(false);
            this.ticksBeforeReconversion = 15 * 20;
            this.nemesis = null;
        }
    }

    public boolean isConverting() {
        return this.converting;
    }

    public void setConverting(boolean converting) {
        this.converting = converting;
    }

    public void turnBackIntoWanderer() {
        if (!this.world.isRemote && !this.isConverting() && net.minecraftforge.event.ForgeEventFactory.canLivingConvert(this,
                ModEntityTypes.GYPSY_WANDERING_TRADER.get(), (timer) -> this.ticksBeforeReconversion = timer)) {
            this.setConverting(true);
            GypsyWanderingTraderEntity wanderer = this.startConversion((ServerWorld) this.world);

            Double maxDistance = GypsyEntity.MAX_DISTANCE_TO_LLAMAS;
            List<GypsyLlamaEntity> entities = this.world.getEntitiesWithinAABB(
                    GypsyLlamaEntity.class,
                    new AxisAlignedBB(
                            this.getPosX() - maxDistance, this.getPosYEye() - maxDistance / 2, this.getPosZ() - maxDistance,
                            this.getPosX() + maxDistance, this.getPosYEye() + maxDistance / 2, this.getPosZ() + maxDistance)
            );

            if (!entities.isEmpty()) {
                Iterator<GypsyLlamaEntity> llamas = entities.iterator();
                GypsyLlamaEntity llama;
                while (llamas.hasNext()) {
                    llama = llamas.next();
                    if (llama.isAlive() && llama.gypsy.equals(this)) {
                        llama.turnBackIntoWandererLlama(wanderer);
                    }
                }
            }
        }
    }

    private GypsyWanderingTraderEntity startConversion(ServerWorld serverWorld) {
        GypsyWanderingTraderEntity wanderer = this.func_233656_b_(ModEntityTypes.GYPSY_WANDERING_TRADER.get(), false);

        wanderer.onInitialSpawn(serverWorld, serverWorld.getDifficultyForLocation(wanderer.getPosition()), SpawnReason.CONVERSION, null, null);
        this.addPotionEffect(new EffectInstance(Effects.STRENGTH, 20 * 3, Math.min(this.world.getDifficulty().getId() - 1, 0)));
        this.playSound(ModSoundEventTypes.GYPSY_CONVERSION.get(), 0.5F + this.rand.nextFloat(), this.rand.nextFloat() * 0.7F + 1.0F);


        for (int i = 0; i < 20; ++i) {
            double d0 = this.rand.nextGaussian() * 0.02D;
            double d1 = this.rand.nextGaussian() * 0.02D;
            double d2 = this.rand.nextGaussian() * 0.02D;
            serverWorld.spawnParticle(ParticleTypes.ENTITY_EFFECT, this.getPosXWidth(0D) - d0 * 10.0D,
                    this.getPosYRandom() - d1 * 10.0D, this.getPosZRandom(1.0D) - d2 * 10.0D,
                    2, 0, 0, 0, (d0 + d1 + d2) / 3);
        }

        net.minecraftforge.event.ForgeEventFactory.onLivingConvert(this, wanderer);
        return wanderer;
    }

    @Override
    public void livingTick() {
        super.livingTick();
        if (!this.world.isRemote && this.isAlive() && this.isAlive() && net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this)) {
            for (ItemEntity itementity : this.world.getEntitiesWithinAABB(ItemEntity.class, this.getBoundingBox().grow(3.0D, 0.0D, 3.0D))) {
                if (itementity.isAlive() && !itementity.getItem().isEmpty() && !itementity.cannotPickup()) {
                    if (itementity.getItem().getItem() == Items.EMERALD) {
                        this.pickedUpEmerald += itementity.getItem().getCount();
                        itementity.remove();
                    }
                    if (this.pickedUpEmerald >= 5) {
                        this.setDone();
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void tick() {
        if (this.ticksBeforeReconversion > 0) this.ticksBeforeReconversion--;
        if (this.ticksBeforeReconversion == 0) turnBackIntoWanderer();

        if (this.nemesis != null && !this.nemesis.isLiving()) this.setDone();
        if (this.nemesis != null && this.nemesis.getDistanceSq(this) > 200D) this.setDone();
        super.tick();
    }

    @Override
    protected float getSoundVolume() {
        return 0.6F;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return ModSoundEventTypes.GYPSY_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return ModSoundEventTypes.GYPSY_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return ModSoundEventTypes.GYPSY_AMBIENT.get();
    }

    @Override
    protected void updateAITasks() {
        super.updateAITasks();
        this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
    }

    @Override
    public void removeTrackingPlayer(ServerPlayerEntity player) {
        super.removeTrackingPlayer(player);
        this.bossInfo.removePlayer(player);
    }

    @Override
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
                                            ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
        this.enablePersistence();
        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
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
