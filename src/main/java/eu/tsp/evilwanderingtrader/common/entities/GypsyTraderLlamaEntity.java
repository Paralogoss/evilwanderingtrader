package eu.tsp.evilwanderingtrader.common.entities;

import eu.tsp.evilwanderingtrader.common.goals.EvilLlamaWhenHitGoal;
import eu.tsp.evilwanderingtrader.init.ModEntityTypes;
import eu.tsp.evilwanderingtrader.init.ModSoundEventTypes;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.TargetGoal;
import net.minecraft.entity.merchant.villager.WanderingTraderEntity;
import net.minecraft.entity.passive.horse.TraderLlamaEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.spawner.WorldEntitySpawner;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class GypsyTraderLlamaEntity extends TraderLlamaEntity {

    public GypsyTraderLlamaEntity(EntityType<? extends GypsyTraderLlamaEntity> type, World worldIn) {
        super(type, worldIn);
        this.setHealth(this.getMaxHealth());
        this.setChested(true);
        this.initHorseChest();
        this.canGallop = false;
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 20.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.1D)
                .createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE, 0.7D);
    }
    
    

    protected void registerGoals() {
        super.registerGoals();
        this.targetSelector.addGoal(1, new GypsyTraderLlamaEntity.FollowGypsyTraderGoal(this));
        this.goalSelector.addGoal(1, new EvilLlamaWhenHitGoal(this));
    }

    @Nullable
    private static BlockPos llamaSpawnPos(BlockPos pos, ServerWorld world, int radius) {
        BlockPos blockpos = null;
        for (int i = 0; i < 10; ++i) {
            int j = pos.getX() + world.getRandom().nextInt(radius * 2) - radius;
            int k = pos.getZ() + world.getRandom().nextInt(radius * 2) - radius;
            int l = world.getHeight(Heightmap.Type.WORLD_SURFACE, j, k);
            BlockPos blockpos1 = new BlockPos(j, l, k);
            if (WorldEntitySpawner.canCreatureTypeSpawnAtLocation(EntitySpawnPlacementRegistry.PlacementType.NO_RESTRICTIONS,
                    world, blockpos1, ModEntityTypes.GYPSY_TRADER_LLAMA.get())) {
                blockpos = blockpos1;
                break;
            }
        }
        return blockpos;
    }

    public static void spawnLlamas(GypsyWanderingTraderEntity trader, BlockPos pos, ServerWorld world, int count) {
        if (count <= 0) return;
        BlockPos randPos = GypsyTraderLlamaEntity.llamaSpawnPos(pos, world, 4);
        if (randPos != null) {
            GypsyTraderLlamaEntity llama = ModEntityTypes.GYPSY_TRADER_LLAMA.get().spawn(world, null,
                    null, null, randPos, SpawnReason.NATURAL, false, false);
            if (llama != null) llama.setLeashHolder(trader, true);
        }

        GypsyTraderLlamaEntity.spawnLlamas(trader, pos, world, count - 1);
    }

    @Override
    protected int getInventorySize() {
        return 17;
    }

    @Override
    public int getInventoryColumns() {
        return 5;
    }

    @Override
    protected void dropInventory() {
        this.setChested(false);
        super.dropInventory();
    }

    public void setInventory(Inventory inventory) {
        if (inventory.getSizeInventory() <= this.getInventorySize()) this.horseChest = inventory;
    }

    public void turnIntoGypsyLlama(PlayerEntity player) {
        if (!this.world.isRemote) {
            this.startConversion((ServerWorld) this.world, player);
        }
    }

    private void startConversion(ServerWorld serverWorld, PlayerEntity player) {
        GypsyLlamaEntity gypsyLlama = this.func_233656_b_(ModEntityTypes.GYPSY_LLAMA.get(), false);
        gypsyLlama.setNemesis(player);

        gypsyLlama.setInventory(this.horseChest);
        gypsyLlama.setOwnerUniqueId(this.getOwnerUniqueId());
        gypsyLlama.setHorseTamed(this.isTame());
        gypsyLlama.trader = this.getLeashHolder();

        gypsyLlama.onInitialSpawn(serverWorld, serverWorld.getDifficultyForLocation(gypsyLlama.getPosition()),
                SpawnReason.CONVERSION, null, null);
        this.addPotionEffect(new EffectInstance(Effects.STRENGTH, 20 * 10,
                Math.min(this.world.getDifficulty().getId() - 1, 0)));
        this.world.playSound(this.getPosX(), this.getPosYEye(), this.getPosZ(), ModSoundEventTypes.GYPSY_CONVERSION.get(),
                this.getSoundCategory(), 2.0F + this.rand.nextFloat(), this.rand.nextFloat() * 0.7F + 0.3F, false);

        for (int i = 0; i < 60; ++i) {
            double d0 = this.rand.nextGaussian() * 0.2D;
            double d1 = this.rand.nextGaussian() * 0.2D;
            double d2 = this.rand.nextGaussian() * 0.2D;
            serverWorld.spawnParticle(ParticleTypes.DRAGON_BREATH, this.getPosXWidth(0D) - d0 * 10.0D,
                    this.getPosYRandom() - d1 * 10.0D, this.getPosZRandom(1.0D) - d2 * 10.0D,
                    1, 0, 0, 0, (d0 + d1 + d2) / 3);
            serverWorld.spawnParticle(ParticleTypes.EXPLOSION_EMITTER, this.getPosXWidth(0D) - d0 * 10.0D,
                    this.getPosYRandom() - d1 * 10.0D, this.getPosZRandom(1.0D) - d2 * 10.0D,
                    1, 0, 0, 0, (d0 + d1 + d2) / 3);
            serverWorld.spawnParticle(ParticleTypes.EXPLOSION, this.getPosXWidth(0D) - d0 * 10.0D,
                    this.getPosYRandom() - d1 * 10.0D, this.getPosZRandom(1.0D) - d2 * 10.0D,
                    1, 0, 0, 0, (d0 + d1 + d2) / 3);
        }

        net.minecraftforge.event.ForgeEventFactory.onLivingConvert(this, gypsyLlama);
    }

    @Override
    public boolean canDespawn(double distanceToClosestPlayer) {
        Inventory inv = this.horseChest;
        int i = 2; // la case d'inventaire 0 correspond a la selle et 1 a l'armure (pas affichee mais heritee du cheval)
        while (i < inv.getSizeInventory() && inv.getStackInSlot(i).isEmpty()) i++;
        return i == this.getInventorySize();
    }

    @Override
    protected void mountTo(PlayerEntity player) {
        Entity entity = this.getLeashHolder();
        if (!(entity instanceof GypsyWanderingTraderEntity)) {
            super.mountTo(player);
        }
    }

    public class FollowGypsyTraderGoal extends TargetGoal {
        private final GypsyTraderLlamaEntity llama;
        private LivingEntity target;
        private int revengeTimer;

        public FollowGypsyTraderGoal(GypsyTraderLlamaEntity llama) {
            super(llama, false);
            this.llama = llama;
            this.setMutexFlags(EnumSet.of(Goal.Flag.TARGET));
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            if (!this.llama.getLeashed()) {
                return false;
            } else {
                Entity entity = this.llama.getLeashHolder();
                if (!(entity instanceof WanderingTraderEntity)) {
                    return false;
                } else {
                    WanderingTraderEntity wanderingtraderentity = (WanderingTraderEntity) entity;
                    this.target = wanderingtraderentity.getRevengeTarget();
                    int i = wanderingtraderentity.getRevengeTimer();
                    return i != this.revengeTimer && this.isSuitableTarget(this.target, EntityPredicate.DEFAULT);
                }
            }
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting() {
            this.goalOwner.setAttackTarget(this.target);
            Entity entity = this.llama.getLeashHolder();
            if (entity instanceof WanderingTraderEntity) {
                this.revengeTimer = ((WanderingTraderEntity) entity).getRevengeTimer();
            }

            super.startExecuting();
        }
    }

}
