package eu.tsp.evilwanderingtrader.init;

import eu.tsp.evilwanderingtrader.EvilWanderingTrader;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModSoundEventTypes {

    public static DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, EvilWanderingTrader.MOD_ID);

    public static final RegistryObject<SoundEvent> GYPSY_DEATH = SOUND_EVENTS.register("entity.gypsy.death", () ->
            new SoundEvent(new ResourceLocation(EvilWanderingTrader.MOD_ID, "entity.gypsy.death"))
    );

    public static final RegistryObject<SoundEvent> GYPSY_HURT = SOUND_EVENTS.register("entity.gypsy.hurt", () ->
            new SoundEvent(new ResourceLocation(EvilWanderingTrader.MOD_ID, "entity.gypsy.hurt"))
    );

    public static final RegistryObject<SoundEvent> GYPSY_AMBIENT = SOUND_EVENTS.register("entity.gypsy.ambient", () ->
            new SoundEvent(new ResourceLocation(EvilWanderingTrader.MOD_ID, "entity.gypsy.ambient"))
    );
    
    public static final RegistryObject<SoundEvent> GYPSY_CONVERSION = SOUND_EVENTS.register("entity.gypsy.conversion", () ->
    new SoundEvent(new ResourceLocation(EvilWanderingTrader.MOD_ID, "entity.gypsy.conversion"))
    );
    
    public static final RegistryObject<SoundEvent> GYPSY_LLAMA_AMBIENT = SOUND_EVENTS.register("entity.gypsy_llama.ambient", () ->
    new SoundEvent(new ResourceLocation(EvilWanderingTrader.MOD_ID, "entity.gypsy_llama.ambient"))
    );


}
