package eu.tsp.evilwanderingtrader.client.rendering;

import com.mojang.blaze3d.matrix.MatrixStack;

import eu.tsp.evilwanderingtrader.client.models.GypsyModel;
import eu.tsp.evilwanderingtrader.common.entities.Gypsy;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CrossedArmsItemLayer;
import net.minecraft.client.renderer.entity.layers.HeadLayer;
import net.minecraft.client.renderer.entity.model.VillagerModel;
import net.minecraft.entity.merchant.villager.WanderingTraderEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class GypsyRender extends MobRenderer<Gypsy, VillagerModel<Gypsy>> {
   private static final ResourceLocation GYPSY_TEXTURES = new ResourceLocation("textures/entity/evil_wandering_trader.png");

   public GypsyRender(EntityRendererManager renderManagerIn) {
	   super(renderManagerIn, new VillagerModel<>(0.0F), 0.5F);
	   this.addLayer(new HeadLayer<>(this));
	   this.addLayer(new CrossedArmsItemLayer<>(this));
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
   public ResourceLocation getEntityTexture(Gypsy entity) {
      return GYPSY_TEXTURES;
   }
   
   public static class RenderFactory implements IRenderFactory<Gypsy> {
	   	
		@Override
		public EntityRenderer<? super Gypsy> createRenderFor(EntityRendererManager manager) {
			return new GypsyRender(manager);
		}
	   
   }

}
