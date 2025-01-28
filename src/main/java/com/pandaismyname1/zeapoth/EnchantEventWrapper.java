package com.pandaismyname1.zeapoth;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;
import org.spongepowered.asm.mixin.throwables.ClassMetadataNotFoundException;

import java.lang.reflect.InvocationTargetException;

public class EnchantEventWrapper {
    public static void onEnchantEvent(Player player, ItemStack toEnchant, EnchantmentInstance enchantmentInstance) {
        try {
            Class.forName("harmonised.pmmo.ProjectMMO");
            var evClass = Class.forName("harmonised.pmmo.api.events.EnchantEvent");
            var ctor = evClass.getConstructor(Player.class, ItemStack.class, EnchantmentInstance.class);
            var event =ctor.newInstance(player, toEnchant, enchantmentInstance);
            MinecraftForge.EVENT_BUS.post((Event) event);
        } catch (NoClassDefFoundError | ClassNotFoundException | ClassMetadataNotFoundException | NoSuchFieldError |
                 NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            ZoomersExtendedApotheosis.LOGGER.info("harmonised.pmmo.api.events.EnchantEvent not found. Skipping event.");
        }
    }

    public static void bootstrap() {

    }
}
