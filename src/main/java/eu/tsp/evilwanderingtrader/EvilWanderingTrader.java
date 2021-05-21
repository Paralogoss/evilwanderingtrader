package eu.tsp.evilwanderingtrader;

import eu.tsp.evilwanderingtrader.common.entities.ThiefLlamaEntity;
import eu.tsp.evilwanderingtrader.common.entities.ThiefTraderLlamaEntity;
import eu.tsp.evilwanderingtrader.common.entities.ThiefWanderingTraderEntity;
import eu.tsp.evilwanderingtrader.common.entities.ThiefEntity;
import eu.tsp.evilwanderingtrader.common.world.gen.ModEntitySpawns;
import eu.tsp.evilwanderingtrader.init.ModEntityTypes;
import eu.tsp.evilwanderingtrader.init.ModItemTypes;
import eu.tsp.evilwanderingtrader.init.ModSoundEventTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.*;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.TextComponent;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.tools.picocli.CommandLine;

import java.awt.*;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(EvilWanderingTrader.MOD_ID)
public class EvilWanderingTrader {
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "evilwanderingtrader";

    private static IFormattableTextComponent PREFIX = new StringTextComponent("[Int. Test.] ")
            .setStyle(Style.EMPTY.setBold(true).setColor(Color.fromTextFormatting(TextFormatting.DARK_AQUA)));

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
            GlobalEntityTypeAttributes.put(ModEntityTypes.THIEF.get(), ThiefEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityTypes.THIEF_LLAMA.get(), ThiefLlamaEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityTypes.THIEF_WANDERING_TRADER.get(), ThiefWanderingTraderEntity.setCustomAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityTypes.THIEF_TRADER_LLAMA.get(), ThiefTraderLlamaEntity.setCustomAttributes().create());
        });

        //ModEntitySpawns.entitySpawnPlacementRegistry();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
    }

    public static void debugMessage(ServerWorld world, String text) {
        debugMessage(world, new StringTextComponent(text));
    }

    public static void debugMessage(ServerWorld world, ITextComponent text) {
        PlayerEntity player = world.getRandomPlayer();
        if (player == null) return;

        IFormattableTextComponent textComponent = new StringTextComponent("").append(EvilWanderingTrader.PREFIX);

        textComponent.append(text);
        // Debug is only used in single player then getRandomPlayer returns the only player
        player.sendStatusMessage(textComponent, false);
    }

    public static final ItemGroup TAB = new ItemGroup("ewt_tab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItemTypes.THIEF_WANDERING_TRADER_SPAWN_EGG.get());
        }
    };

}
