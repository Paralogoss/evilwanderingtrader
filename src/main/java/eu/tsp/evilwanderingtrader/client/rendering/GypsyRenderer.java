package eu.tsp.evilwanderingtrader.client.rendering;

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
public class GypsyRenderer extends MobRenderer<GypsyEntity, GypsyModel<GypsyEntity>> {
    private static final ResourceLocation GYPSY_TEXTURES = new ResourceLocation(EvilWanderingTrader.MOD_ID + ":textures/entity/evil_wandering_trader.png");

    public GypsyRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new GypsyModel<>(), 0.5F);
    }

    @Override
    public ResourceLocation getEntityTexture(GypsyEntity entity) {
        return GYPSY_TEXTURES;
    }

    public static class RenderFactory implements IRenderFactory<GypsyEntity> {

        @Override
        public EntityRenderer<? super GypsyEntity> createRenderFor(EntityRendererManager manager) {
            return new GypsyRenderer(manager);
        }

    }

}
