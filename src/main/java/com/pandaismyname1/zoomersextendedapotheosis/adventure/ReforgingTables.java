package com.pandaismyname1.zoomersextendedapotheosis.adventure;

import dev.shadowsoffire.apotheosis.adventure.affix.reforging.ReforgingTableBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.pandaismyname1.zoomersextendedapotheosis.ZoomersExtendedApotheosis.MODID;

public class ReforgingTables {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<ReforgingTableBlock> ARTISANAL_REFORGING_TABLE = BLOCKS.register("artisanal_reforging_table",
            () -> new ReforgingTableBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(2, 20F), 5));
    public static final RegistryObject<ReforgingTableBlock> ENHANCED_REFORGING_TABLE = BLOCKS.register("enhanced_reforging_table",
            () -> new ReforgingTableBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(2, 20F), 6));
    public static final RegistryObject<ReforgingTableBlock> ULTIMATE_REFORGING_TABLE = BLOCKS.register("ultimate_reforging_table",
        () -> new ReforgingTableBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(4, 1000F), 7));
    public static final RegistryObject<ReforgingTableBlock> GOD_FORGE = BLOCKS.register("god_reforging_table",
        () -> new ReforgingTableBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(4, 1000F), 8));

    public static final RegistryObject<Item> ARTISANAL_REFORGING_TABLE_ITEM = ITEMS.register("artisanal_reforging_table", () -> new BlockItem(ARTISANAL_REFORGING_TABLE.get(), new Item.Properties().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> ENHANCED_REFORGING_TABLE_ITEM = ITEMS.register("enhanced_reforging_table", () -> new BlockItem(ENHANCED_REFORGING_TABLE.get(), new Item.Properties().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> ULTIMATE_REFORGING_TABLE_ITEM = ITEMS.register("ultimate_reforging_table", () -> new BlockItem(ULTIMATE_REFORGING_TABLE.get(), new Item.Properties().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> GOD_FORGE_ITEM = ITEMS.register("god_reforging_table", () -> new BlockItem(GOD_FORGE.get(), new Item.Properties().rarity(Rarity.EPIC)));

    public static void bootstrap(IEventBus bus) {
        BLOCKS.register(bus);
        ITEMS.register(bus);
    }
}
