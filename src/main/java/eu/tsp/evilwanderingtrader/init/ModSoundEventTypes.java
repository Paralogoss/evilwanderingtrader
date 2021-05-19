package eu.tsp.evilwanderingtrader.init;

import eu.tsp.evilwanderingtrader.EvilWanderingTrader;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModSoundEventTypes {

    public static DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, EvilWanderingTrader.MOD_ID);

    public static final RegistryObject<SoundEvent> THIEF_DEATH = SOUND_EVENTS.register("entity.thief.death", () ->
            new SoundEvent(new ResourceLocation(EvilWanderingTrader.MOD_ID, "entity.thief.death"))
    );

    public static final RegistryObject<SoundEvent> THIEF_HURT = SOUND_EVENTS.register("entity.thief.hurt", () ->
            new SoundEvent(new ResourceLocation(EvilWanderingTrader.MOD_ID, "entity.thief.hurt"))
    );

    public static final RegistryObject<SoundEvent> THIEF_AMBIENT = SOUND_EVENTS.register("entity.thief.ambient", () ->
            new SoundEvent(new ResourceLocation(EvilWanderingTrader.MOD_ID, "entity.thief.ambient"))
    );

    public static final RegistryObject<SoundEvent> THIEF_CONVERSION = SOUND_EVENTS.register("entity.thief.conversion", () ->
            new SoundEvent(new ResourceLocation(EvilWanderingTrader.MOD_ID, "entity.thief.conversion"))
    );

    public static final RegistryObject<SoundEvent> THIEF_LLAMA_AMBIENT = SOUND_EVENTS.register("entity.thief_llama.ambient", () ->
            new SoundEvent(new ResourceLocation(EvilWanderingTrader.MOD_ID, "entity.thief_llama.ambient"))
    );


}
