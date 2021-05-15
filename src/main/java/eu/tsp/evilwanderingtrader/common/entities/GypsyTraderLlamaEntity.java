package eu.tsp.evilwanderingtrader.common.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.passive.horse.TraderLlamaEntity;
import net.minecraft.world.World;

public class GypsyTraderLlamaEntity extends TraderLlamaEntity {

	public GypsyTraderLlamaEntity(EntityType<? extends GypsyTraderLlamaEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_();
    }

}
