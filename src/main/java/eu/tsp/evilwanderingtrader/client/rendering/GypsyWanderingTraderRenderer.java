package eu.tsp.evilwanderingtrader.client.rendering;

import eu.tsp.evilwanderingtrader.common.entities.GypsyWanderingTraderEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.WanderingTraderRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class GypsyWanderingTraderRenderer extends WanderingTraderRenderer {

    public GypsyWanderingTraderRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
    }

    public static class RenderFactory implements IRenderFactory<GypsyWanderingTraderEntity> {

        @Override
        public EntityRenderer<? super GypsyWanderingTraderEntity> createRenderFor(EntityRendererManager manager) {
            return new GypsyWanderingTraderRenderer(manager);
        }

    }
}
