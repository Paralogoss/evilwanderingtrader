package eu.tsp.evilwanderingtrader;

import eu.tsp.evilwanderingtrader.common.entities.GypsyWanderingTraderEntity;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.tsp.evilwanderingtrader.common.entities.GypsyEntity;
import eu.tsp.evilwanderingtrader.init.ModEntityTypes;
import eu.tsp.evilwanderingtrader.init.ModItems;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(EvilWanderingTrader.MOD_ID)
public class EvilWanderingTrader
{
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    
    public static final String MOD_ID = "evilwanderingtrader";
    
    public EvilWanderingTrader() {
    	
    	IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
    	
        // Register the setup method for modloading
    	eventBus.addListener(this::setup);
        // Register the enqueueIMC method for modloading
    	//eventBus.addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
    	//eventBus.addListener(this::processIMC);
        // Register the doClientStuff method for modloading
    	eventBus.addListener(this::doClientStuff);

    	ModEntityTypes.ENTITY_TYPES.register(eventBus);
    	ModItems.ITEMS.register(eventBus);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SuppressWarnings("deprecation")
	private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
    	event.enqueueWork(() -> {
    		GlobalEntityTypeAttributes.put(ModEntityTypes.GYPSY.get(), GypsyEntity.setCustomAttributes().create());
    		GlobalEntityTypeAttributes.put(ModEntityTypes.GYPSY_LLAMA.get(), GypsyEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityTypes.GYPSY_WANDERING_TRADER.get(), GypsyWanderingTraderEntity.setCustomAttributes().create());
        });
		//ModEntitySpawns.entitySpawnPlacementRegistry();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
    }
    
    /*
    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
    }*/

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
        }
    }
    
    public static final ItemGroup TAB = new ItemGroup("ewt_tab")
	{
		@Override
		public ItemStack createIcon ()
		{
			return new ItemStack(ModItems.GYPSY_SPAWN_EGG.get());
		}
	};
	
}
