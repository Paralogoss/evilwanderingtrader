package eu.tsp.evilwanderingtrader.common.entities;

import eu.tsp.evilwanderingtrader.common.goals.EvilGypsyWhenHitGoal;
import eu.tsp.evilwanderingtrader.init.ModEntityTypes;
import eu.tsp.evilwanderingtrader.init.ModSoundEventTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.merchant.villager.WanderingTraderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;

public class GypsyWanderingTraderEntity extends WanderingTraderEntity {

    public GypsyWanderingTraderEntity(EntityType<? extends GypsyWanderingTraderEntity> type, World worldIn) {
        super(type, worldIn);
        this.setDespawnDelay(48000);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_();
    }

    @Nullable
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        if (!this.world.isRemote) {
            GypsyTraderLlamaEntity.spawnLlamas(this, this.getPosition(), worldIn.getWorld(), 2);
        }

        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new EvilGypsyWhenHitGoal(this));
    }

    public void turnIntoGypsy(PlayerEntity player) {
        if (!this.world.isRemote) {
            this.startConversion((ServerWorld) this.world, player);


            Double maxDistance = GypsyEntity.MAX_DISTANCE_TO_LLAMAS;
            List<GypsyTraderLlamaEntity> entities = this.world.getEntitiesWithinAABB(
                    GypsyTraderLlamaEntity.class,
                    new AxisAlignedBB(
                            this.getPosX() - maxDistance, this.getPosYEye() - maxDistance / 2, this.getPosZ() - maxDistance,
                            this.getPosX() + maxDistance, this.getPosYEye() + maxDistance / 2, this.getPosZ() + maxDistance)
            );

            if (!entities.isEmpty()) {
                Iterator<GypsyTraderLlamaEntity> llamas = entities.iterator();
                GypsyTraderLlamaEntity llama;
                while (llamas.hasNext()) {
                    llama = llamas.next();
                    if (llama.isAlive() && llama.getLeashed() && llama.getLeashHolder().equals(this)) {
                        llama.turnIntoGypsyLlama(player);
                    }
                }
            }
        }
    }

    private void startConversion(ServerWorld serverWorld, PlayerEntity player) {
        GypsyEntity gypsy = this.func_233656_b_(ModEntityTypes.GYPSY.get(), false);
        gypsy.setNemesis(player);

        gypsy.onInitialSpawn(serverWorld, serverWorld.getDifficultyForLocation(gypsy.getPosition()), SpawnReason.CONVERSION, null, null);
        this.addPotionEffect(new EffectInstance(Effects.STRENGTH, 20 * 10, Math.min(this.world.getDifficulty().getId() - 1, 0)));
        this.world.playSound(this.getPosX(), this.getPosYEye(), this.getPosZ(), ModSoundEventTypes.GYPSY_CONVERSION.get(), this.getSoundCategory(), 2.0F + this.rand.nextFloat(), this.rand.nextFloat() * 0.7F + 0.3F, false);

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

        net.minecraftforge.event.ForgeEventFactory.onLivingConvert(this, gypsy);
    }

}
