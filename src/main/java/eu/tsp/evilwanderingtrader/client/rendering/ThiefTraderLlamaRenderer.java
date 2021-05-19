package eu.tsp.evilwanderingtrader.client.rendering;

import eu.tsp.evilwanderingtrader.common.entities.ThiefTraderLlamaEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LlamaRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class ThiefTraderLlamaRenderer extends LlamaRenderer {

    public ThiefTraderLlamaRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
    }

    public static class RenderFactory implements IRenderFactory<ThiefTraderLlamaEntity> {

        @Override
        public EntityRenderer<? super ThiefTraderLlamaEntity> createRenderFor(EntityRendererManager manager) {
            return new ThiefTraderLlamaRenderer(manager);
        }

    }
}
