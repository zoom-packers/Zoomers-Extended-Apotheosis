package com.pandaismyname1.zoomersextendedapotheosis.mixin;

import com.pandaismyname1.zoomersextendedapotheosis.EnchantmentMenuAccessMap;
import dev.shadowsoffire.apotheosis.ench.table.ApothEnchantmentMenu;
import dev.shadowsoffire.apotheosis.ench.table.RealEnchantmentHelper;
import harmonised.pmmo.api.events.EnchantEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashMap;
import java.util.List;

@Mixin(ApothEnchantmentMenu.class)
public abstract class ApothEnchantmentMenuMixin {

    @Shadow
    protected abstract List<EnchantmentInstance> getEnchantmentList(ItemStack stack, int enchantSlot, int level);
    @Shadow
    protected ApothEnchantmentMenu.TableStats stats;

    private HashMap<Player, List<EnchantmentInstance>> enchantMappings = new HashMap<>();

    @Inject(method = "clickMenuButton", at = @At(value = "HEAD", remap = false), remap = false)
    public void clickMenuButtonHead(Player player, int id, CallbackInfoReturnable<Boolean> cir) {
        int slot = id;
        var apothEnchantMenu = (ApothEnchantmentMenu) (Object) this;
        var access = EnchantmentMenuAccessMap.accessMap.get(apothEnchantMenu);
        var enchantSlots = EnchantmentMenuAccessMap.enchantmentSlots.get(apothEnchantMenu);
        int level = apothEnchantMenu.costs[slot];
        ItemStack toEnchant = enchantSlots.getItem(0);
        access.execute((world, pos) -> {
            List<EnchantmentInstance> enchantmentInstances = this.getEnchantmentList(toEnchant, slot, level);
            enchantMappings.put(player, enchantmentInstances);
        });
    }

    @Inject(method = "clickMenuButton", at = @At(value = "TAIL"))
    public void clickMenuButtonTail(Player player, int id, CallbackInfoReturnable<Boolean> cir) {
        var cirValue = cir.getReturnValueZ();
        if (!cirValue) {
            return;
        }
        if (!enchantMappings.containsKey(player)) {
            return;
        }
        var apothEnchantMenu = (ApothEnchantmentMenu) (Object) this;
        ItemStack toEnchant = apothEnchantMenu.slots.get(id).getItem();
        var enchantmentInstances = enchantMappings.get(player);
        for (var enchantmentInstance : enchantmentInstances) {
            MinecraftForge.EVENT_BUS.post(new EnchantEvent(player, toEnchant, enchantmentInstance));
        }
    }

}
