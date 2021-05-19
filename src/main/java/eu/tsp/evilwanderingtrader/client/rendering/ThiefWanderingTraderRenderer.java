package eu.tsp.evilwanderingtrader.client.rendering;

import eu.tsp.evilwanderingtrader.common.entities.ThiefWanderingTraderEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.WanderingTraderRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class ThiefWanderingTraderRenderer extends WanderingTraderRenderer {

    public ThiefWanderingTraderRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
    }

    public static class RenderFactory implements IRenderFactory<ThiefWanderingTraderEntity> {

        @Override
        public EntityRenderer<? super ThiefWanderingTraderEntity> createRenderFor(EntityRendererManager manager) {
            return new ThiefWanderingTraderRenderer(manager);
        }

    }
}
