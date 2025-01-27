package com.pandaismyname1.zeapoth.adventure;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.pandaismyname1.zeapoth.ZoomersExtendedApotheosis.MODID;

public class Items {

    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<Item> RADIANT_MATERIAL = ITEMS.register("radiant_material", () -> new Item(new Item.Properties().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> CELESTIAL_MATERIAL = ITEMS.register("celestial_material", () -> new Item(new Item.Properties().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> ANGELIC_MATERIAL = ITEMS.register("angelic_material", () -> new Item(new Item.Properties().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> GODLY_MATERIAL = ITEMS.register("godly_material", () -> new Item(new Item.Properties().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> ENDER_DRAGON_WING = ITEMS.register("ender_dragon_wing", () -> new Item(new Item.Properties().rarity(Rarity.EPIC)));


    public static void bootstrap(IEventBus bus) {
        ITEMS.register(bus);
    }

}
