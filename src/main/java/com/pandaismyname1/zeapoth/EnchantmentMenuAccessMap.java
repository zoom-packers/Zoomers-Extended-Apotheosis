package com.pandaismyname1.zeapoth;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.EnchantmentMenu;

import java.util.HashMap;
import java.util.Map;

public class EnchantmentMenuAccessMap {
    public static final Map<EnchantmentMenu, ContainerLevelAccess> accessMap = new HashMap<>();
    public static final Map<EnchantmentMenu, Container> enchantmentSlots = new HashMap<>();
}
