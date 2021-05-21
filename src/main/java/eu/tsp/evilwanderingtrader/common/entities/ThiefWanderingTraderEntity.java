package eu.tsp.evilwanderingtrader.common.entities;

import eu.tsp.evilwanderingtrader.EvilWanderingTrader;
import eu.tsp.evilwanderingtrader.common.goals.EvilThiefWhenHitGoal;
import eu.tsp.evilwanderingtrader.init.ModEntityTypes;
import eu.tsp.evilwanderingtrader.init.ModSoundEventTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.merchant.villager.WanderingTraderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraft.item.MerchantOffers;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class ThiefWanderingTraderEntity extends WanderingTraderEntity {
    // Counts the amount of sales made by the last customer.
    private int lastSales = 0;

    @Nullable
    private PlayerEntity lastCustomer;

    // When true the trader's llamas will be spawned on the next tick
    private boolean spawnLlamas = true;


    public ThiefWanderingTraderEntity(EntityType<? extends ThiefWanderingTraderEntity> type, World worldIn) {
        super(type, worldIn);
        this.setDespawnDelay(48000);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_();
    }

    @Nullable
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        if (reason != SpawnReason.CONVERSION) {
            EvilWanderingTrader.debugMessage(worldIn.getWorld(),"Spawned Thief Wandering Trader");
        } else {
            this.spawnLlamas = false;
            EvilWanderingTrader.debugMessage(worldIn.getWorld(),"Converted back to Wandering Trader");
        }

        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    public void onTrade(MerchantOffer offer) {
        super.onTrade(offer);

        this.lastSales++;
        // When something has been sold by the merchant, we remove it from the llamas inventory
        this.forTraderLlamas((llama) -> {
            llama.removeItemStackFromInventory(offer.getSellingStack());
        });
    }

    @Override
    public void livingTick() {
        if (this.spawnLlamas && !this.world.isRemote) {
            EvilWanderingTrader.debugMessage((ServerWorld) this.world, "Spawning Llamas");
            ThiefTraderLlamaEntity.spawnLlamas(this, this.getPosition(), (ServerWorld)this.world, 2);
            this.spawnLlamas = false;
        }

        if (((this.hasCustomer() && this.lastCustomer != this.getCustomer()) || !this.hasCustomer()) && this.lastCustomer != null) {
            if (this.lastSales <= 0) this.turnIntoThief(this.lastCustomer);
            this.lastSales = 0;
        }
        this.lastCustomer = this.getCustomer();
        super.livingTick();
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new EvilThiefWhenHitGoal(this));
    }

    public void turnIntoThief(PlayerEntity player) {
        if (!this.world.isRemote && net.minecraftforge.event.ForgeEventFactory.canLivingConvert(this,
                ModEntityTypes.THIEF.get(), (timer) -> {
                })) {
            ThiefEntity thief = this.startConversion((ServerWorld) this.world, player);

            this.forTraderLlamas((llama) -> {
                llama.turnIntoThiefLlama(player, thief);
            });
        }
    }

    private ThiefEntity startConversion(ServerWorld serverWorld, PlayerEntity player) {
        ThiefEntity thief = this.func_233656_b_(ModEntityTypes.THIEF.get(), false);
        thief.setNemesis(player);

        thief.onInitialSpawn(serverWorld, serverWorld.getDifficultyForLocation(thief.getPosition()), SpawnReason.CONVERSION, null, null);
        this.addPotionEffect(new EffectInstance(Effects.STRENGTH, 20 * 10, Math.min(this.world.getDifficulty().getId() - 1, 0)));
        this.playSound(ModSoundEventTypes.THIEF_CONVERSION.get(), 2.0F + this.rand.nextFloat(), this.rand.nextFloat() * 0.2F + 0.3F);

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

        //drop des 2 leads
        this.entityDropItem(new ItemStack(Items.LEAD, 2));

        net.minecraftforge.event.ForgeEventFactory.onLivingConvert(this, thief);
        return thief;
    }

    @Override
    protected void populateTradeData() {
        MerchantOffers merchantoffers = this.getOffers();

        this.forTraderLlamas((llama) -> {
            for (int i = 0; i < llama.getInventorySize(); i++) {
                MerchantOffer offer = llama.getMerchantOfferFromInventorySlot(i);
                if (offer != null) merchantoffers.add(offer);
            }
        });

        if (merchantoffers.size() == 0) {
            super.populateTradeData();
        }
    }


    private void forTraderLlamas(Consumer<ThiefTraderLlamaEntity> consumer) {
        if (this.world.isRemote || this.world.getWorldInfo().getDayTime() <= 0 || !this.isAddedToWorld()) return;
        Double maxDistance = eu.tsp.evilwanderingtrader.common.entities.ThiefEntity.MAX_DISTANCE_TO_LLAMAS;
        List<ThiefTraderLlamaEntity> entities = this.world.getEntitiesWithinAABB(
                ThiefTraderLlamaEntity.class,
                new AxisAlignedBB(
                        this.getPosX() - maxDistance, this.getPosYEye() - maxDistance / 2, this.getPosZ() - maxDistance,
                        this.getPosX() + maxDistance, this.getPosYEye() + maxDistance / 2, this.getPosZ() + maxDistance)
        );

        if (!entities.isEmpty()) {
            Iterator<ThiefTraderLlamaEntity> llamas = entities.iterator();
            ThiefTraderLlamaEntity llama;
            while (llamas.hasNext()) {
                llama = llamas.next();
                if (llama.isAlive() && llama.getLeashed() && llama.getLeashHolder().equals(this)) {
                    consumer.accept(llama);
                }
            }
        }
    }

}
