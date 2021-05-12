package eu.tsp.evilwanderingtrader.client.rendering;

import com.mojang.blaze3d.matrix.MatrixStack;

import eu.tsp.evilwanderingtrader.EvilWanderingTrader;
import eu.tsp.evilwanderingtrader.client.models.GypsyModel;
import eu.tsp.evilwanderingtrader.common.entities.GypsyEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class GypsyRender extends MobRenderer<GypsyEntity, GypsyModel<GypsyEntity>> {
   private static final ResourceLocation GYPSY_TEXTURES = new ResourceLocation(EvilWanderingTrader.MOD_ID+":textures/entity/evil_wandering_trader.png");

   public GypsyRender(EntityRendererManager renderManagerIn) {
	   super(renderManagerIn, new GypsyModel<>(), 0.5F);
   }

   /*protected void preRenderCallback(WanderingTraderEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
      float f = 0.9375F;
      matrixStackIn.scale(0.9375F, 0.9375F, 0.9375F);
   }
   
   @Override
   protected float getDeathMaxRotation(Gypsy entityLivingBaseIn) {
      return 180.0F;
   }*/

   @Override
   public ResourceLocation getEntityTexture(GypsyEntity entity) {
      return GYPSY_TEXTURES;
   }
   
   public static class RenderFactory implements IRenderFactory<GypsyEntity> {
	   	
		@Override
		public EntityRenderer<? super GypsyEntity> createRenderFor(EntityRendererManager manager) {
			return new GypsyRender(manager);
		}
	   
   }

}
