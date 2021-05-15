package eu.tsp.evilwanderingtrader.init;

import eu.tsp.evilwanderingtrader.EvilWanderingTrader;
import eu.tsp.evilwanderingtrader.common.items.ModSpawnEggItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItemTypes {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, EvilWanderingTrader.MOD_ID);

    public static RegistryObject<ModSpawnEggItem> GYPSY_SPAWN_EGG = ITEMS.register("gypsy_spawn_egg",
            () -> new ModSpawnEggItem(ModEntityTypes.GYPSY, 0x00FFCFCF, 0x00F04848, new Item.Properties().group(EvilWanderingTrader.TAB)));
    public static RegistryObject<ModSpawnEggItem> GYPSY_LLAMA_SPAWN_EGG = ITEMS.register("gypsy_llama_spawn_egg",
            () -> new ModSpawnEggItem(ModEntityTypes.GYPSY_LLAMA, 0x00CFFFCF, 0x0048F048, new Item.Properties().group(EvilWanderingTrader.TAB)));
    public static RegistryObject<ModSpawnEggItem> GYPSY_TRADER_LLAMA_SPAWN_EGG = ITEMS.register("gypsy_trader_llama_spawn_egg",
            () -> new ModSpawnEggItem(ModEntityTypes.GYPSY_TRADER_LLAMA, 0x40C4FFCF, 0x404810F8, new Item.Properties().group(EvilWanderingTrader.TAB)));
    public static RegistryObject<ModSpawnEggItem> GYPSY_WANDERING_TRADER_SPAWN_EGG = ITEMS.register("gypsy_wandering_trader_spawn_egg",
            () -> new ModSpawnEggItem(ModEntityTypes.GYPSY_WANDERING_TRADER,
                    0x00CFCFCF, 0x0048C048, new Item.Properties().group(EvilWanderingTrader.TAB)));

}
