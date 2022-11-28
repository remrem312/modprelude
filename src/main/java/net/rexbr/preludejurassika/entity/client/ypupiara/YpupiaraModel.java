package net.rexbr.preludejurassika.entity.client.ypupiara;

import net.minecraft.resources.ResourceLocation;
import net.rexbr.preludejurassika.entity.custom.YpupiaraEntity;
import net.rexbr.preludejurassika.prelude;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class YpupiaraModel extends AnimatedGeoModel<YpupiaraEntity> {
    @Override
    public ResourceLocation getModelLocation(YpupiaraEntity object) {
        return new ResourceLocation(prelude.MODID, "geo/ypupiara.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(YpupiaraEntity object) {
        return new ResourceLocation(prelude.MODID, "textures/dinossaur/ypupiara.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(YpupiaraEntity animatable) {
        return new ResourceLocation(prelude.MODID, "animations/ypupiara.animation.json");
    }
}
