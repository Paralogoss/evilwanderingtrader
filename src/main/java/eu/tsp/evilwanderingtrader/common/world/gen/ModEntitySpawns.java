package eu.tsp.evilwanderingtrader.common.world.gen;

import eu.tsp.evilwanderingtrader.EvilWanderingTrader;
import eu.tsp.evilwanderingtrader.init.ModEntityTypes;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.MobEntity;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EvilWanderingTrader.MOD_ID)
public class ModEntitySpawns {

    public static void entitySpawnPlacementRegistry() {
        EntitySpawnPlacementRegistry.register(ModEntityTypes.GYPSY_WANDERING_TRADER.get(),
                EntitySpawnPlacementRegistry.PlacementType.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                MobEntity::canSpawnOn);

    }
}
