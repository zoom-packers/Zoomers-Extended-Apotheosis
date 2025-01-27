package com.pandaismyname1.zeapoth.adventure;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.shadowsoffire.apotheosis.Apotheosis;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.pandaismyname1.zeapoth.ZoomersExtendedApotheosis.MODID;

public class EnderDragonLootModifier extends LootModifier {

    private static final DeferredRegister<Codec<? extends IGlobalLootModifier>> CODECS = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, MODID);

    public static final Codec<EnderDragonLootModifier> CODEC = RecordCodecBuilder.create(inst -> codecStart(inst).apply(inst, EnderDragonLootModifier::new));
    public static final ResourceLocation ENDER_DRAGON_TABLE_ID = new ResourceLocation("minecraft", "entities/ender_dragon");

    public EnderDragonLootModifier(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> loot, LootContext ctx) {
        if (Apotheosis.enableAdventure && ENDER_DRAGON_TABLE_ID.equals(ctx.getQueriedLootTableId())) {
            loot.add(new ItemStack(Items.ENDER_DRAGON_WING.get(), 1));
        }
        return loot;
    }

    public static void bootstrap(IEventBus bus) {
        CODECS.register("ender_dragon_wings", () -> CODEC);
        CODECS.register(bus);
    }

}
