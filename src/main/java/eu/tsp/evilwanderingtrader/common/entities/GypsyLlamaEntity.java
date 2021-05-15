package eu.tsp.evilwanderingtrader.common.entities;

import javax.annotation.Nullable;

import eu.tsp.evilwanderingtrader.common.goals.HurtNemesisGoal;
import eu.tsp.evilwanderingtrader.common.init.ModSoundEventTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.horse.AbstractChestedHorseEntity;
import net.minecraft.entity.passive.horse.LlamaEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.entity.ai.goal.RangedAttackGoal;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.LivingEntity;

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
   

    @Override
    protected int getInventorySize() {
        return 15;
    }

    @Override
    public int getInventoryColumns() {
        return 5;
    }

    //TODO rajouter une mise a jour statique de cette info
    public boolean isChestFull() {
        Inventory inv = this.horseChest;
        int i = 0;
        while (i < inv.getSizeInventory() && !inv.getStackInSlot(i).isEmpty()) i++;
        return i == inv.getSizeInventory();
    }

    public boolean addToChest(ItemStack items) {
        if (this.isChestFull()) return false;
        this.horseChest.addItem(items);
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

    /*
     * When a player wants to mount a llama we open the inventory instead (llamas are not rideable)
     */
    @Override
    protected void mountTo(PlayerEntity player) {
        player.openHorseInventory(this, this.horseChest);
    }




}
