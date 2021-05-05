package eu.tsp.evilwanderingtrader.client.rendering;

import eu.tsp.evilwanderingtrader.EvilWanderingTrader;
import eu.tsp.evilwanderingtrader.client.models.GypsyLlamaModel;
import eu.tsp.evilwanderingtrader.client.models.GypsyModel;
import eu.tsp.evilwanderingtrader.common.entities.Gypsy;
import eu.tsp.evilwanderingtrader.common.entities.GypsyLlama;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class GypsyLlamaRender extends MobRenderer<GypsyLlama, GypsyLlamaModel<GypsyLlama>> {
	private static final ResourceLocation GYPSY_LLAMA_TEXTURES = new ResourceLocation(EvilWanderingTrader.MOD_ID+":textures/entity/llama.png");

	   public GypsyLlamaRender(EntityRendererManager renderManagerIn) {
		   super(renderManagerIn, new GypsyLlamaModel<>(), 0.7F);
	   }

	   @Override
	   public ResourceLocation getEntityTexture(GypsyLlama entity) {
	      return GYPSY_LLAMA_TEXTURES;
	   }
	   
	   public static class RenderFactory implements IRenderFactory<GypsyLlama> {
		   	
			@Override
			public EntityRenderer<? super GypsyLlama> createRenderFor(EntityRendererManager manager) {
				return new GypsyLlamaRender(manager);
			}
		   
	   }
}
