package eu.tsp.evilwanderingtrader.client.rendering;

import eu.tsp.evilwanderingtrader.EvilWanderingTrader;
import eu.tsp.evilwanderingtrader.client.models.ThiefLlamaModel;
import eu.tsp.evilwanderingtrader.common.entities.ThiefLlamaEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class ThiefLlamaRenderer extends MobRenderer<ThiefLlamaEntity, ThiefLlamaModel<ThiefLlamaEntity>> {
    private static final ResourceLocation THIEF_LLAMA_TEXTURES = new ResourceLocation(EvilWanderingTrader.MOD_ID + ":textures/entity/thief_llama.png");

    public ThiefLlamaRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ThiefLlamaModel<>(), 0.7F);
    }

    @Override
    public ResourceLocation getEntityTexture(ThiefLlamaEntity entity) {
        return THIEF_LLAMA_TEXTURES;
    }

    public static class RenderFactory implements IRenderFactory<ThiefLlamaEntity> {

        @Override
        public EntityRenderer<? super ThiefLlamaEntity> createRenderFor(EntityRendererManager manager) {
            return new ThiefLlamaRenderer(manager);
        }

    }
}
