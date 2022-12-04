package net.rexbr.preludejurassika.event;

import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.rexbr.preludejurassika.entity.ModEntityTypes;
import net.rexbr.preludejurassika.entity.custom.*;
import net.rexbr.preludejurassika.prelude;
import net.rexbr.preludejurassika.recipes.AnalyzerRecipe;
import net.rexbr.preludejurassika.recipes.RessonatorRecipe;

@Mod.EventBusSubscriber(modid = prelude.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.JURAVENATOR.get(), JuravenatorEntity.setAttributes());
        event.put(ModEntityTypes.MIMODACTYLUS.get(), mimodactylusEntity.setAttributes());
        event.put(ModEntityTypes.ALBERTOSAURUS.get(), AlbertosaurusEntity.setAttributes());
        event.put(ModEntityTypes.AVACERATOPS.get(), AvaceratopsEntity.setAttributes());
        event.put(ModEntityTypes.PYRORAPTOR.get(), PyroraptorEntity.setAttributes());
        event.put(ModEntityTypes.AMAZONSAURUS.get(), AmazonsaurusEntity.setAttributes());
        event.put(ModEntityTypes.TORVOSAURUS.get(), TorvosaurusEntity.setAttributes());
        event.put(ModEntityTypes.PALEOLAMA.get(), PaleolamaMajorEntity.setAttributes());
        event.put(ModEntityTypes.DRYOSAURUS.get(), DryosaurusEntity.setAttributes());
        event.put(ModEntityTypes.YPUPIARA.get(), YpupiaraEntity.setAttributes());
        event.put(ModEntityTypes.TENONTOSAURUS.get(), TenontosaurusEntity.setAttributes());
        event.put(ModEntityTypes.IRRITATOR.get(), IrritatorEntity.setAttributes());

    }

    @SubscribeEvent
    public static void registerRecipeTypes(final RegistryEvent.Register<RecipeSerializer<?>> event) {
        Registry.register(Registry.RECIPE_TYPE, AnalyzerRecipe.Type.ID, AnalyzerRecipe.Type.INSTANCE);
        Registry.register(Registry.RECIPE_TYPE, RessonatorRecipe.Type.ID, RessonatorRecipe.Type.INSTANCE);
    }

}
