package eu.tsp.evilwanderingtrader.common.entities;

import eu.tsp.evilwanderingtrader.init.ModEntityTypes;
import eu.tsp.evilwanderingtrader.init.ModSoundEventTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.passive.horse.TraderLlamaEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class GypsyTraderLlamaEntity extends TraderLlamaEntity {

    public GypsyTraderLlamaEntity(EntityType<? extends GypsyTraderLlamaEntity> type, World worldIn) {
        super(type, worldIn);
        this.setChested(true);
        this.initHorseChest();
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_();
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

        gypsyLlama.onInitialSpawn(serverWorld, serverWorld.getDifficultyForLocation(gypsyLlama.getPosition()),
                SpawnReason.CONVERSION, null, null);
        this.addPotionEffect(new EffectInstance(Effects.STRENGTH, 20 * 10,
                Math.min(this.world.getDifficulty().getId() - 1, 0)));
        this.world.playSound(this.getPosX(), this.getPosYEye(), this.getPosZ(), ModSoundEventTypes.GYPSY_CONVERSION.get(),
                this.getSoundCategory(), 2.0F + this.rand.nextFloat(), this.rand.nextFloat() * 0.7F + 0.3F, false);

        for (int i = 0; i < 100; ++i) {
            double d0 = this.rand.nextGaussian() * 0.2D;
            double d1 = this.rand.nextGaussian() * 0.2D;
            double d2 = this.rand.nextGaussian() * 0.2D;
            serverWorld.spawnParticle(ParticleTypes.DRAGON_BREATH, this.getPosXWidth(0D) - d0 * 10.0D,
                    this.getPosYRandom() - d1 * 10.0D, this.getPosZRandom(1.0D) - d2 * 10.0D,
                    5, 0, 0, 0, (d0 + d1 + d2) / 3);
            serverWorld.spawnParticle(ParticleTypes.EXPLOSION_EMITTER, this.getPosXWidth(0D) - d0 * 10.0D,
                    this.getPosYRandom() - d1 * 10.0D, this.getPosZRandom(1.0D) - d2 * 10.0D,
                    5, 0, 0, 0, (d0 + d1 + d2) / 3);
            serverWorld.spawnParticle(ParticleTypes.EXPLOSION, this.getPosXWidth(0D) - d0 * 10.0D,
                    this.getPosYRandom() - d1 * 10.0D, this.getPosZRandom(1.0D) - d2 * 10.0D,
                    5, 0, 0, 0, (d0 + d1 + d2) / 3);
        }

        net.minecraftforge.event.ForgeEventFactory.onLivingConvert(this, gypsyLlama);
    }

}
