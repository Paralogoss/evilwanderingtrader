package eu.tsp.evilwanderingtrader.client.rendering;

import eu.tsp.evilwanderingtrader.common.entities.GypsyTraderLlamaEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LlamaRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class GypsyTraderLlamaRender extends LlamaRenderer {

	public GypsyTraderLlamaRender(EntityRendererManager renderManagerIn) {
		super(renderManagerIn);
	}
	
    public static class RenderFactory implements IRenderFactory<GypsyTraderLlamaEntity> {

        @Override
        public EntityRenderer<? super GypsyTraderLlamaEntity> createRenderFor(EntityRendererManager manager) {
            return new GypsyTraderLlamaRender(manager);
        }

    }
}
