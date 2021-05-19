package eu.tsp.evilwanderingtrader.init;

import eu.tsp.evilwanderingtrader.EvilWanderingTrader;
import eu.tsp.evilwanderingtrader.common.items.ModSpawnEggItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItemTypes {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, EvilWanderingTrader.MOD_ID);

    public static RegistryObject<ModSpawnEggItem> GYPSY_WANDERING_TRADER_SPAWN_EGG = ITEMS.register("gypsy_wandering_trader_spawn_egg",
            () -> new ModSpawnEggItem(ModEntityTypes.GYPSY_WANDERING_TRADER,
                    0x00CFCFCF, 0x0048C048, new Item.Properties().group(EvilWanderingTrader.TAB)));

}
