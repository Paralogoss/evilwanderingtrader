package eu.tsp.evilwanderingtrader.client;

import eu.tsp.evilwanderingtrader.EvilWanderingTrader;
import eu.tsp.evilwanderingtrader.client.rendering.GypsyLlamaRenderer;
import eu.tsp.evilwanderingtrader.client.rendering.GypsyRenderer;
import eu.tsp.evilwanderingtrader.client.rendering.GypsyTraderLlamaRenderer;
import eu.tsp.evilwanderingtrader.client.rendering.GypsyWanderingTraderRenderer;
import eu.tsp.evilwanderingtrader.common.items.ModSpawnEggItem;
import eu.tsp.evilwanderingtrader.init.ModEntityTypes;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = EvilWanderingTrader.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.GYPSY.get(), new GypsyRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.GYPSY_LLAMA.get(), new GypsyLlamaRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.GYPSY_WANDERING_TRADER.get(), new GypsyWanderingTraderRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.GYPSY_TRADER_LLAMA.get(), new GypsyTraderLlamaRenderer.RenderFactory());
    }

    @SubscribeEvent
    public static void onRegisterEntities(final RegistryEvent.Register<EntityType<?>> event) {
        ModSpawnEggItem.initSpawnEggs();
    }
}
