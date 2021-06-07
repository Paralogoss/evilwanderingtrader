package eu.tsp.evilwanderingtrader.common.world.gen;

import eu.tsp.evilwanderingtrader.EvilWanderingTrader;
import eu.tsp.evilwanderingtrader.init.ModEntityTypes;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.MobEntity;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EvilWanderingTrader.MOD_ID)
public class ModEntitySpawns {

    @SubscribeEvent
    public static void spawnEntities(BiomeLoadingEvent event) {
        MobSpawnInfo.Spawners spawnInfo = new MobSpawnInfo.Spawners(ModEntityTypes.THIEF_WANDERING_TRADER.get(),
                1, 1, 1);
        event.getSpawns().getSpawner(EntityClassification.CREATURE).add(spawnInfo);
    }

    public static void entitySpawnPlacementRegistry() {
        EntitySpawnPlacementRegistry.register(ModEntityTypes.THIEF_WANDERING_TRADER.get(),
                EntitySpawnPlacementRegistry.PlacementType.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                MobEntity::canSpawnOn);
    }
}
