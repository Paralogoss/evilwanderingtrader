package eu.tsp.evilwanderingtrader.client.rendering;

import eu.tsp.evilwanderingtrader.EvilWanderingTrader;
import eu.tsp.evilwanderingtrader.client.models.ThiefModel;
import eu.tsp.evilwanderingtrader.common.entities.ThiefEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class ThiefRenderer extends MobRenderer<ThiefEntity, ThiefModel<ThiefEntity>> {
    private static final ResourceLocation THIEF_TEXTURES = new ResourceLocation(EvilWanderingTrader.MOD_ID + ":textures/entity/evil_wandering_trader.png");

    public ThiefRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ThiefModel<>(), 0.5F);
    }

    @Override
    public ResourceLocation getEntityTexture(ThiefEntity entity) {
        return THIEF_TEXTURES;
    }

    public static class RenderFactory implements IRenderFactory<ThiefEntity> {

        @Override
        public EntityRenderer<? super ThiefEntity> createRenderFor(EntityRendererManager manager) {
            return new ThiefRenderer(manager);
        }

    }

}
