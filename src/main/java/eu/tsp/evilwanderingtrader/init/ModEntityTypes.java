package eu.tsp.evilwanderingtrader.init;

import eu.tsp.evilwanderingtrader.EvilWanderingTrader;
import eu.tsp.evilwanderingtrader.common.entities.*;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes {
	public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, EvilWanderingTrader.MOD_ID);
	public static final RegistryObject<EntityType<GypsyEntity>> GYPSY = ENTITY_TYPES.register("gypsy",
			() -> EntityType.Builder.create(GypsyEntity::new, EntityClassification.MONSTER)
					.size(0.6f, 1.95f)
					.build(new ResourceLocation(EvilWanderingTrader.MOD_ID, "gypsy").toString()));
	public static final RegistryObject<EntityType<GypsyLlamaEntity>> GYPSY_LLAMA = ENTITY_TYPES.register("gypsy_llama",
			() -> EntityType.Builder.create(GypsyLlamaEntity::new, EntityClassification.MONSTER)
					.size(0.9f, 1.875f)
					.build(new ResourceLocation(EvilWanderingTrader.MOD_ID, "gypsy_llama").toString()));

}
