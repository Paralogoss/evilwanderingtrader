package eu.tsp.evilwanderingtrader.common.entities;

import eu.tsp.evilwanderingtrader.common.goals.HurtNemesisGoal;
import eu.tsp.evilwanderingtrader.init.ModEntityTypes;
import eu.tsp.evilwanderingtrader.init.ModSoundEventTypes;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.horse.AbstractChestedHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;

public class GypsyLlamaEntity extends AbstractChestedHorseEntity implements IMob, IRangedAttackMob {

    @Nullable
    PlayerEntity nemesis;

    public GypsyLlamaEntity(EntityType<? extends AbstractChestedHorseEntity> type, World worldIn) {
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

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.25D, 40, 20.0F));
        this.goalSelector.addGoal(2, new AvoidEntityGoal(this, PlayerEntity.class, 16.0F, 1.2F, 1.8F));
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 32.0F, 0.1F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(9, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.targetSelector.addGoal(1, new HurtNemesisGoal(this));
    }

    public void setNemesis(PlayerEntity player) {
        this.nemesis = player;
    }

    public PlayerEntity getNemesis() {
        return this.nemesis;
    }


    @Override
    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {

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

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return ModSoundEventTypes.GYPSY_LLAMA_AMBIENT.get();
    }

    @Override
    public boolean canEatGrass() {
        return false;
    }

    public void turnBackIntoWandererLlama() {
        if (!this.world.isRemote) {
            this.startConversion((ServerWorld) this.world);
        }
    }

    private void startConversion(ServerWorld serverWorld) {
        GypsyTraderLlamaEntity llama = this.func_233656_b_(ModEntityTypes.GYPSY_TRADER_LLAMA.get(), false);

        llama.setInventory(this.horseChest);
        llama.setOwnerUniqueId(this.getOwnerUniqueId());
        llama.setHorseTamed(this.isTame());


        llama.onInitialSpawn(serverWorld, serverWorld.getDifficultyForLocation(llama.getPosition()),
                SpawnReason.CONVERSION, null, null);
        this.addPotionEffect(new EffectInstance(Effects.STRENGTH, 20 * 3,
                Math.min(this.world.getDifficulty().getId() - 1, 0)));
        this.world.playSound(this.getPosX(), this.getPosYEye(), this.getPosZ(), ModSoundEventTypes.GYPSY_CONVERSION.get(),
                this.getSoundCategory(), 2.0F + this.rand.nextFloat(), this.rand.nextFloat() * 0.7F + 0.3F, false);

        for (int i = 0; i < 20; ++i) {
            double d0 = this.rand.nextGaussian() * 0.02D;
            double d1 = this.rand.nextGaussian() * 0.02D;
            double d2 = this.rand.nextGaussian() * 0.02D;
            serverWorld.spawnParticle(ParticleTypes.ENTITY_EFFECT, this.getPosXWidth(0D) - d0 * 10.0D,
                    this.getPosYRandom() - d1 * 10.0D, this.getPosZRandom(1.0D) - d2 * 10.0D,
                    5, 0, 0, 0, (d0 + d1 + d2) / 3);
        }

        net.minecraftforge.event.ForgeEventFactory.onLivingConvert(this, llama);
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
