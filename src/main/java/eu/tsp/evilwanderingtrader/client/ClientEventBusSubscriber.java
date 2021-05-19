package eu.tsp.evilwanderingtrader.client;

import eu.tsp.evilwanderingtrader.EvilWanderingTrader;
import eu.tsp.evilwanderingtrader.client.rendering.ThiefLlamaRenderer;
import eu.tsp.evilwanderingtrader.client.rendering.ThiefRenderer;
import eu.tsp.evilwanderingtrader.client.rendering.ThiefTraderLlamaRenderer;
import eu.tsp.evilwanderingtrader.client.rendering.ThiefWanderingTraderRenderer;
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
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.THIEF.get(), new ThiefRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.THIEF_LLAMA.get(), new ThiefLlamaRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.THIEF_WANDERING_TRADER.get(), new ThiefWanderingTraderRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.THIEF_TRADER_LLAMA.get(), new ThiefTraderLlamaRenderer.RenderFactory());
    }

    @SubscribeEvent
    public static void onRegisterEntities(final RegistryEvent.Register<EntityType<?>> event) {
        ModSpawnEggItem.initSpawnEggs();
    }
}
