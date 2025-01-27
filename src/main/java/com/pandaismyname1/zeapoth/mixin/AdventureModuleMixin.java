package com.pandaismyname1.zeapoth.mixin;

import com.google.common.collect.ImmutableSet;
import com.pandaismyname1.zeapoth.adventure.ReforgingTables;
import dev.shadowsoffire.apotheosis.adventure.Adventure;
import dev.shadowsoffire.apotheosis.adventure.AdventureModule;
import dev.shadowsoffire.apotheosis.adventure.affix.augmenting.AugmentingTableTile;
import dev.shadowsoffire.apotheosis.adventure.affix.reforging.ReforgingTableTile;
import dev.shadowsoffire.apotheosis.adventure.affix.salvaging.SalvagingTableTile;
import dev.shadowsoffire.apotheosis.adventure.boss.BossSpawnerBlock;
import dev.shadowsoffire.placebo.block_entity.TickingBlockEntityType;
import dev.shadowsoffire.placebo.registry.RegistryEvent;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AdventureModule.class)
public class AdventureModuleMixin {

    @Inject(method = "tiles", at = @At("HEAD"), cancellable = true, remap = false)
    public void tiles(RegistryEvent.Register<BlockEntityType<?>> e, CallbackInfo ci) {
        e.getRegistry().register(new TickingBlockEntityType<>(BossSpawnerBlock.BossSpawnerTile::new, ImmutableSet.of(Adventure.Blocks.BOSS_SPAWNER.get()), false, true), "boss_spawner");
        e.getRegistry().register(new TickingBlockEntityType<>(ReforgingTableTile::new, ImmutableSet.of(
                Adventure.Blocks.SIMPLE_REFORGING_TABLE.get(),
                Adventure.Blocks.REFORGING_TABLE.get(),
                ReforgingTables.ARTISANAL_REFORGING_TABLE.get(),
                ReforgingTables.ENHANCED_REFORGING_TABLE.get(),
                ReforgingTables.ULTIMATE_REFORGING_TABLE.get(),
                ReforgingTables.GOD_FORGE.get()
        ), true, false), "reforging_table");
        e.getRegistry().register(new BlockEntityType<>(SalvagingTableTile::new, ImmutableSet.of(Adventure.Blocks.SALVAGING_TABLE.get()), null), "salvaging_table");
        e.getRegistry().register(new TickingBlockEntityType<>(AugmentingTableTile::new, ImmutableSet.of(Adventure.Blocks.AUGMENTING_TABLE.get()), true, false), "augmenting_table");
        ci.cancel();
    }
}
