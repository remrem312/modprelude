package net.rexbr.preludejurassika;

import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.rexbr.preludejurassika.block.ModBlocks;
import net.rexbr.preludejurassika.block.TileEntity.ModBlockEntities;
import net.rexbr.preludejurassika.entity.ModEntityTypes;
import net.rexbr.preludejurassika.entity.client.AchillobatorRenderer;
import net.rexbr.preludejurassika.entity.client.DodoRenderer;
import net.rexbr.preludejurassika.entity.client.VelociraptorG2Renderer;
import net.rexbr.preludejurassika.entity.client.cryo.CryoRenderer;
import net.rexbr.preludejurassika.entity.client.giga.GigaRenderer;
import net.rexbr.preludejurassika.entity.client.juravenator.JuravenatorRenderer;
import net.rexbr.preludejurassika.entity.client.trex.TRexRenderer;

import net.rexbr.preludejurassika.item.ModItems;
import net.rexbr.preludejurassika.recipes.ModRecipes;
import net.rexbr.preludejurassika.screen.ModMenuTypes;
import net.rexbr.preludejurassika.screen.slots.AnalyzerScreen;
import net.rexbr.preludejurassika.screen.slots.CleanerScreen;
import net.rexbr.preludejurassika.screen.slots.CultureScreen;
import net.rexbr.preludejurassika.screen.slots.RessonatorScreen;
import net.rexbr.preludejurassika.sound.ModSounds;
import net.rexbr.preludejurassika.villager.ModVillagers;

import org.slf4j.Logger;
import software.bernie.geckolib3.GeckoLib;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(prelude.MODID)
public class prelude {

    public static final String MODID = "prelude";
    public static final preludeConfig CONFIG_OPTIONS = new preludeConfig();
    private static final Logger LOGGER = LogUtils.getLogger();

    // add a comment
    public prelude() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventBus);
        ModBlocks.register(eventBus);

        ModEntityTypes.register(eventBus);
        ModSounds.register(eventBus);

        ModRecipes.register(eventBus);
        ModVillagers.register(eventBus);

        ModBlockEntities.register(eventBus);
        ModMenuTypes.register(eventBus);



        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);


        GeckoLib.initialize();


        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void clientSetup(final FMLClientSetupEvent event) {

        ItemBlockRenderTypes.setRenderLayer(ModBlocks.PLANT1.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.POTTED_PLANT1.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.ZYGOPTERIS.get(), RenderType.cutout());

        EntityRenderers.register(ModEntityTypes.ACHILLOBATOR.get(), AchillobatorRenderer::new);
        EntityRenderers.register(ModEntityTypes.DODO.get(), DodoRenderer::new);
        EntityRenderers.register(ModEntityTypes.TREX.get(), TRexRenderer::new);
        EntityRenderers.register(ModEntityTypes.JURAVENATOR.get(), JuravenatorRenderer::new);
        EntityRenderers.register(ModEntityTypes.GIGANOTOSAURUS.get(), GigaRenderer::new);
        EntityRenderers.register(ModEntityTypes.CRYO.get(), CryoRenderer::new);
        EntityRenderers.register(ModEntityTypes.VELOGII.get(), VelociraptorG2Renderer::new);

        ItemBlockRenderTypes.setRenderLayer(ModBlocks.ANALYZER.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.CULTURE_VAT.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.RESSONATOR.get(), RenderType.translucent());

        MenuScreens.register(ModMenuTypes.ANALYZER_MENU.get(), AnalyzerScreen::new);
        MenuScreens.register(ModMenuTypes.CULTURE_MENU.get(), CultureScreen::new);
        MenuScreens.register(ModMenuTypes.CLEANER_MENU.get(), CleanerScreen::new);
        MenuScreens.register(ModMenuTypes.RESSONATOR_MENU.get(), RessonatorScreen::new);
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.PLANT1.getId(), ModBlocks.POTTED_PLANT1);

            ModVillagers.registerPOIs();
        });
    }


}