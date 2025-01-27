package com.pandaismyname1.zeapoth.mixin;

import com.pandaismyname1.zeapoth.EnchantmentMenuAccessMap;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.EnchantmentMenu;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EnchantmentMenu.class)
public abstract class EnchantmentMenuMixin
{
    @Shadow 
    @Final
    private ContainerLevelAccess access;

    @Shadow
    @Final
    private Container enchantSlots;

    @Inject(method = "<init>(ILnet/minecraft/world/entity/player/Inventory;Lnet/minecraft/world/inventory/ContainerLevelAccess;)V", at = @At("RETURN"))
    private void onInit(CallbackInfo ci)
    {
        if (access != null) {
            EnchantmentMenuAccessMap.accessMap.put((EnchantmentMenu)(Object)this, access);
            EnchantmentMenuAccessMap.enchantmentSlots.put((EnchantmentMenu) (Object) this, enchantSlots);
        }
    }
}

