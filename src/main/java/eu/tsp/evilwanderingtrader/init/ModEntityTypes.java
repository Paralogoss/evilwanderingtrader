package eu.tsp.evilwanderingtrader.init;

import eu.tsp.evilwanderingtrader.EvilWanderingTrader;
import eu.tsp.evilwanderingtrader.common.entities.GypsyEntity;
import eu.tsp.evilwanderingtrader.common.entities.GypsyLlamaSpitEntity;
import eu.tsp.evilwanderingtrader.common.entities.GypsyLlamaEntity;
import eu.tsp.evilwanderingtrader.common.entities.GypsyTraderLlamaEntity;
import eu.tsp.evilwanderingtrader.common.entities.GypsyWanderingTraderEntity;
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
    public static final RegistryObject<EntityType<GypsyWanderingTraderEntity>> GYPSY_WANDERING_TRADER = ENTITY_TYPES.register("gypsy_wandering_trader",
            () -> EntityType.Builder.create(GypsyWanderingTraderEntity::new, EntityClassification.AMBIENT)
                    .size(0.6f, 1.95f)
                    .build(new ResourceLocation(EvilWanderingTrader.MOD_ID, "gypsy_wandering_trader").toString()));
    public static final RegistryObject<EntityType<GypsyLlamaEntity>> GYPSY_LLAMA = ENTITY_TYPES.register("gypsy_llama",
            () -> EntityType.Builder.create(GypsyLlamaEntity::new, EntityClassification.MONSTER)
                    .size(0.9f, 1.875f)
                    .build(new ResourceLocation(EvilWanderingTrader.MOD_ID, "gypsy_llama").toString()));
    public static final RegistryObject<EntityType<GypsyTraderLlamaEntity>> GYPSY_TRADER_LLAMA = ENTITY_TYPES.register("gypsy_trader_llama",
            () -> EntityType.Builder.create(GypsyTraderLlamaEntity::new, EntityClassification.AMBIENT)
                    .size(0.9f, 1.875f)
                    .build(new ResourceLocation(EvilWanderingTrader.MOD_ID, "gypsy_trader_llama").toString()));
   
    public static final RegistryObject<EntityType<GypsyLlamaSpitEntity>> GYPSY_LLAMA_SPIT = ENTITY_TYPES.register("gypsy_llama_spit",
            () -> EntityType.Builder.create(GypsyLlamaSpitEntity::new, EntityClassification.MISC)
                    .size(0.9f, 1.875f)
                    .build(new ResourceLocation(EvilWanderingTrader.MOD_ID, "gypsy__llama_spit").toString()));


  

}
