package net.rexbr.preludejurassika.entity.reorganized.tenontosaurus;

import net.minecraft.resources.ResourceLocation;
import net.rexbr.preludejurassika.entity.reorganized.tenontosaurus.TenontosaurusEntity;
import net.rexbr.preludejurassika.prelude;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class TenontosaurusModel extends AnimatedGeoModel<TenontosaurusEntity> {
    @Override
    public ResourceLocation getModelLocation(TenontosaurusEntity object) {
        return new ResourceLocation(prelude.MODID, "geo/tenontosaurus.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(TenontosaurusEntity object) {
        return new ResourceLocation(prelude.MODID, "textures/dinossaur/tenontosaurus.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(TenontosaurusEntity animatable) {
        return new ResourceLocation(prelude.MODID, "animations/tenontosaurus.animation.json");
    }
}
