package net.rexbr.preludejurassika.item;

import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rexbr.preludejurassika.prelude;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, prelude.MODID);

    // items
    public static final RegistryObject<Item> FOSSIL = ITEMS.register("fossil",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.FOSSIL_MOD_ITEMS)));
    //foods
    public static final RegistryObject<Item> DODO_RAW = ITEMS.register("raw_dodo",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.FOSSIL_MOD_ITEMS).food(ModFoods.DODO_RAW)));

    //eggs

    //spawn eggs

    //dna
    public static final RegistryObject<Item> DODO_DNA = ITEMS.register("dodo_dna",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.FOSSIL_MOD_ITEMS).stacksTo(1)));
    public static final RegistryObject<Item> DODO_SYRINGUE = ITEMS.register("dodo_syringue",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.FOSSIL_MOD_ITEMS).stacksTo(1)));

    //weapons
    public static final RegistryObject<Item> FOSSIL_SWORD = ITEMS.register("fossil_sword",
            () -> new SwordItem(ModTiers.FOSSIL, 2, 3f,
                    new Item.Properties().tab(ModCreativeModeTab.FOSSIL_MOD_ITEMS).durability(900)));
    public static final RegistryObject<Item> FOSSIL_PICKAXE = ITEMS.register("fossil_pickaxe",
            () -> new PickaxeItem(ModTiers.FOSSIL, 1, 1f,
                    new Item.Properties().tab(ModCreativeModeTab.FOSSIL_MOD_ITEMS).durability(900)));
    public static final RegistryObject<Item> FOSSIL_SHOVEL = ITEMS.register("fossil_shovel",
            () -> new ShovelItem(ModTiers.FOSSIL, 0, 1f,
                    new Item.Properties().tab(ModCreativeModeTab.FOSSIL_MOD_ITEMS).durability(900)));
    public static final RegistryObject<Item> FOSSIL_AXE = ITEMS.register("fossil_axe",
            () -> new AxeItem(ModTiers.FOSSIL, 4, 0f,
                    new Item.Properties().tab(ModCreativeModeTab.FOSSIL_MOD_ITEMS).durability(900)));
    public static final RegistryObject<Item> FOSSIL_HOE = ITEMS.register("fossil_hoe",
            () -> new HoeItem(ModTiers.FOSSIL, 0, 0f,
                    new Item.Properties().tab(ModCreativeModeTab.FOSSIL_MOD_ITEMS).durability(900)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
