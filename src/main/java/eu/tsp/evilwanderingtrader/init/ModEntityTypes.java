package eu.tsp.evilwanderingtrader.init;

import eu.tsp.evilwanderingtrader.EvilWanderingTrader;
import eu.tsp.evilwanderingtrader.common.entities.ThiefLlamaEntity;
import eu.tsp.evilwanderingtrader.common.entities.ThiefTraderLlamaEntity;
import eu.tsp.evilwanderingtrader.common.entities.ThiefWanderingTraderEntity;
import eu.tsp.evilwanderingtrader.common.entities.ThiefEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, EvilWanderingTrader.MOD_ID);
    public static final RegistryObject<EntityType<ThiefEntity>> THIEF = ENTITY_TYPES.register("thief",
            () -> EntityType.Builder.create(ThiefEntity::new, EntityClassification.MONSTER)
                    .size(0.6f, 1.95f)
                    .build(new ResourceLocation(EvilWanderingTrader.MOD_ID, "thief").toString()));
    public static final RegistryObject<EntityType<ThiefWanderingTraderEntity>> THIEF_WANDERING_TRADER = ENTITY_TYPES.register("thief_wandering_trader",
            () -> EntityType.Builder.create(ThiefWanderingTraderEntity::new, EntityClassification.AMBIENT)
                    .size(0.6f, 1.95f)
                    .build(new ResourceLocation(EvilWanderingTrader.MOD_ID, "thief_wandering_trader").toString()));
    public static final RegistryObject<EntityType<ThiefLlamaEntity>> THIEF_LLAMA = ENTITY_TYPES.register("thief_llama",
            () -> EntityType.Builder.create(ThiefLlamaEntity::new, EntityClassification.MONSTER)
                    .size(0.9f, 1.875f)
                    .build(new ResourceLocation(EvilWanderingTrader.MOD_ID, "thief_llama").toString()));
    public static final RegistryObject<EntityType<ThiefTraderLlamaEntity>> THIEF_TRADER_LLAMA = ENTITY_TYPES.register("thief_trader_llama",
            () -> EntityType.Builder.create(ThiefTraderLlamaEntity::new, EntityClassification.AMBIENT)
                    .size(0.9f, 1.875f)
                    .build(new ResourceLocation(EvilWanderingTrader.MOD_ID, "thief_trader_llama").toString()));
}
