package eu.tsp.evilwanderingtrader.common.entities;

import com.google.common.collect.ImmutableList;
import eu.tsp.evilwanderingtrader.common.goals.GypsyAttackGoal;
import eu.tsp.evilwanderingtrader.common.init.ModSoundEventTypes;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.BossInfo;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class GypsyEntity extends MonsterEntity implements IMob {
    // Targeted player
    @Nullable
    PlayerEntity nemesis;

    // Time in ticks (1/20 second) before going back to wandering trader
    // Value is positive only if nemesis has been killed or has an empty inventory.
    int ticksBeforeReconversion = -1;

    private final ServerBossInfo bossInfo = (ServerBossInfo) (new ServerBossInfo(this.getDisplayName(), BossInfo.Color.BLUE, BossInfo.Overlay.PROGRESS)).setDarkenSky(true);

    public GypsyEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
        this.setHealth(this.getMaxHealth());
        this.experienceValue = 100;
        this.nemesis = null;
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 1.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.3D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 4.0D)
                .createMutableAttribute(Attributes.ATTACK_SPEED, 0.1D)
                .createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE, 0.7D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));

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
            this.setDone(true);
            this.setAggroed(false);
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
        double maxDistance = 16D; //au sens de la norme infinie
        List<GypsyLlamaEntity> entities = this.world.getEntitiesWithinAABB(
                GypsyLlamaEntity.class,
                new AxisAlignedBB(
                        this.getPosX() - maxDistance, this.getPosYEye() - maxDistance, this.getPosZ() - maxDistance,
                        this.getPosX() + maxDistance, this.getPosYEye() + maxDistance, this.getPosZ() + maxDistance)
        );

        if (!entities.isEmpty()) {
            Iterator<GypsyLlamaEntity> llamas = entities.iterator();
            GypsyLlamaEntity llama = llamas.next();
            boolean success = llama.isAlive() && llama.addToChest(items);
            while (!success && llamas.hasNext()) {
                llama = llamas.next();
                success = llama.isAlive() && llama.addToChest(items);
            }
            if (success) return;
        }
        this.nemesis.dropItem(items, true, false);
    }

    public void setNemesis(PlayerEntity player) {
        this.nemesis = player;
    }

    public boolean isDone() {
        return this.ticksBeforeReconversion >= 0;
    }

    public void setDone(boolean done) {
        if (done && !this.isDone()) {
            this.ticksBeforeReconversion = 15 * 20;
        } else if (!done && this.isDone()) {
            this.ticksBeforeReconversion = -1;
        }
    }

    @Override
    public void addTrackingPlayer(ServerPlayerEntity player) {
        //EvilWanderingTrader.LOGGER.info("addTrackingPlayer ",player.getName());
        super.addTrackingPlayer(player);
        this.bossInfo.addPlayer(player);
    }

    @Override
    public void removeTrackingPlayer(ServerPlayerEntity player) {
        super.removeTrackingPlayer(player);
        this.bossInfo.removePlayer(player);
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
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
                                            ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
        this.enablePersistence();
        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    public boolean isNoDespawnRequired() {
        return true;
    }
}
