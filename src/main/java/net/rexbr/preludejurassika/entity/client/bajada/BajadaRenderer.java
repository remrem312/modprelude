package net.rexbr.preludejurassika.entity.client.bajada;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.rexbr.preludejurassika.entity.custom.BajadasaurusEntity;
import net.rexbr.preludejurassika.prelude;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class BajadaRenderer extends GeoEntityRenderer<BajadasaurusEntity> {
    public BajadaRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new BajadaModel());
        this.shadowRadius = 1.5f;
    }

    @Override
    public ResourceLocation getTextureLocation(BajadasaurusEntity instance) {
        return new ResourceLocation(prelude.MODID, "textures/dinossaur/bajadassaurus.png");
    }

    @Override
    public RenderType getRenderType(BajadasaurusEntity animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        stack.scale(1.8F, 1.8F, 1.8F);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
