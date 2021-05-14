package eu.tsp.evilwanderingtrader;

import eu.tsp.evilwanderingtrader.common.entities.GypsyEntity;
import eu.tsp.evilwanderingtrader.common.entities.GypsyWanderingTraderEntity;
import eu.tsp.evilwanderingtrader.common.init.ModSoundEventTypes;
import eu.tsp.evilwanderingtrader.init.ModEntityTypes;
import eu.tsp.evilwanderingtrader.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(EvilWanderingTrader.MOD_ID)
public class EvilWanderingTrader {
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "evilwanderingtrader";

    public EvilWanderingTrader() {

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the setup method for modloading
        eventBus.addListener(this::setup);
        // Register the doClientStuff method for modloading
        eventBus.addListener(this::doClientStuff);

        ModSoundEventTypes.SOUND_EVENTS.register(eventBus);
        ModEntityTypes.ENTITY_TYPES.register(eventBus);
        ModItems.ITEMS.register(eventBus);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

    }

    @SuppressWarnings("deprecation")
    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            GlobalEntityTypeAttributes.put(ModEntityTypes.GYPSY.get(), GypsyEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityTypes.GYPSY_LLAMA.get(), GypsyEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityTypes.GYPSY_WANDERING_TRADER.get(), GypsyWanderingTraderEntity.setCustomAttributes().create());
        });
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
        }
    }

    public static final ItemGroup TAB = new ItemGroup("ewt_tab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.GYPSY_SPAWN_EGG.get());
        }
    };

}
