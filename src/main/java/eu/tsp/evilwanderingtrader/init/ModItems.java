package eu.tsp.evilwanderingtrader.init;

import eu.tsp.evilwanderingtrader.EvilWanderingTrader;
import eu.tsp.evilwanderingtrader.common.items.ModSpawnEggItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
	
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, EvilWanderingTrader.MOD_ID);
	
	public static RegistryObject<ModSpawnEggItem> GYPSY_SPAWN_EGG = ITEMS.register("gypsy_spawn_egg",
			() -> new ModSpawnEggItem(ModEntityTypes.GYPSY,0x00FFCFCF, 0x00F04848, new Item.Properties().group(EvilWanderingTrader.TAB)));

}
