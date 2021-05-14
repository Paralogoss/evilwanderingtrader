package eu.tsp.evilwanderingtrader.common.entities;

import eu.tsp.evilwanderingtrader.common.goals.EvilGypsyWhenHitGoal;
import eu.tsp.evilwanderingtrader.init.ModEntityTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.merchant.villager.WanderingTraderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class GypsyWanderingTraderEntity extends WanderingTraderEntity {

    public GypsyWanderingTraderEntity(EntityType<? extends GypsyWanderingTraderEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_();
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new EvilGypsyWhenHitGoal(this));
    }

    public void turnIntoGypsy(PlayerEntity player) {
        if (!this.world.isRemote) {
            this.startConversion((ServerWorld) this.world, player);
        }
    }

    private void startConversion(ServerWorld serverWorld, PlayerEntity player) {
        GypsyEntity gypsy = this.func_233656_b_(ModEntityTypes.GYPSY.get(), false);
        gypsy.setNemesis(player);

        gypsy.onInitialSpawn(serverWorld, serverWorld.getDifficultyForLocation(gypsy.getPosition()), SpawnReason.CONVERSION, null, null);
        this.addPotionEffect(new EffectInstance(Effects.STRENGTH, 20 * 3, Math.min(this.world.getDifficulty().getId() - 1, 0)));

        if (!this.isSilent()) {
            serverWorld.playEvent(null, 1027, this.getPosition(), 0);
        }
        net.minecraftforge.event.ForgeEventFactory.onLivingConvert(this, gypsy);
    }

}
