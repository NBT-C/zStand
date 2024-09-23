package me.nbtc.armorStandPacket.managers.equipment;

import com.comphenix.protocol.wrappers.EnumWrappers;
import org.bukkit.inventory.ItemStack;

public record ArmorStandEquipment(EnumWrappers.ItemSlot slot, ItemStack itemStack) {
    public static ArmorStandEquipment of(EnumWrappers.ItemSlot slot, ItemStack itemStack) {
        return new ArmorStandEquipment(slot, itemStack);
    }
}
