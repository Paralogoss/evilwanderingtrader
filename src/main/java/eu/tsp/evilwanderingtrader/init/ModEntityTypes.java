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
	public static final RegistryObject<EntityType<Gypsy>> LITTLE_GREBE = ENTITY_TYPES.register("gypsy",
			() -> EntityType.Builder.create(Gypsy::new, EntityClassification.CREATURE)
					.size(0.5f, 0.6f)
					.build(new ResourceLocation(EvilWanderingTrader.MOD_ID, "gypsy").toString()));

}
