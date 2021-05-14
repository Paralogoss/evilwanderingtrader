package eu.tsp.evilwanderingtrader.client.rendering;

import eu.tsp.evilwanderingtrader.EvilWanderingTrader;
import eu.tsp.evilwanderingtrader.client.models.GypsyLlamaModel;
import eu.tsp.evilwanderingtrader.common.entities.GypsyLlamaEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class GypsyLlamaRender extends MobRenderer<GypsyLlamaEntity, GypsyLlamaModel<GypsyLlamaEntity>> {
    private static final ResourceLocation GYPSY_LLAMA_TEXTURES = new ResourceLocation(EvilWanderingTrader.MOD_ID + ":textures/entity/llama.png");

    public GypsyLlamaRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new GypsyLlamaModel<>(), 0.7F);
    }

    @Override
    public ResourceLocation getEntityTexture(GypsyLlamaEntity entity) {
        return GYPSY_LLAMA_TEXTURES;
    }

    public static class RenderFactory implements IRenderFactory<GypsyLlamaEntity> {

        @Override
        public EntityRenderer<? super GypsyLlamaEntity> createRenderFor(EntityRendererManager manager) {
            return new GypsyLlamaRender(manager);
        }

    }
}
