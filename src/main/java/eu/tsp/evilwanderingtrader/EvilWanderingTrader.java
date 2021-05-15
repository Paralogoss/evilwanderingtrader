package eu.tsp.evilwanderingtrader;

import eu.tsp.evilwanderingtrader.common.entities.GypsyEntity;
import eu.tsp.evilwanderingtrader.common.entities.GypsyTraderLlamaEntity;
import eu.tsp.evilwanderingtrader.common.entities.GypsyWanderingTraderEntity;
import eu.tsp.evilwanderingtrader.init.ModEntityTypes;
import eu.tsp.evilwanderingtrader.init.ModItemTypes;
import eu.tsp.evilwanderingtrader.init.ModSoundEventTypes;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
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
        ModItemTypes.ITEMS.register(eventBus);
        
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

    }

    @SuppressWarnings("deprecation")
    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            GlobalEntityTypeAttributes.put(ModEntityTypes.GYPSY.get(), GypsyEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityTypes.GYPSY_LLAMA.get(), GypsyEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityTypes.GYPSY_WANDERING_TRADER.get(), GypsyWanderingTraderEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityTypes.GYPSY_TRADER_LLAMA.get(), GypsyTraderLlamaEntity.setCustomAttributes().create());
        });
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
    }


    public static final ItemGroup TAB = new ItemGroup("ewt_tab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItemTypes.GYPSY_SPAWN_EGG.get());
        }
    };

}
