package me.nbtc.armorStandPacket.v1_20.wrapper.living.manager;

import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.Pair;
import lombok.Data;
import me.nbtc.armorStandPacket.v1_20.wrapper.packet.WrapperPlayServerEntityEquipment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class EquipmentManager {
    private final int entityId;
    private final Map<EnumWrappers.ItemSlot, Pair<EnumWrappers.ItemSlot, ItemStack>> equipmentMap = new HashMap<>();
    private final WrapperPlayServerEntityEquipment packet;

    public EquipmentManager(int entityId) {
        this.entityId = entityId;
        packet = new WrapperPlayServerEntityEquipment();
        packet.setEntity(entityId);
    }

    public void equip(EnumWrappers.ItemSlot slot, ItemStack item) {
        equipmentMap.put(slot, new Pair<>(slot, item));
    }

    public void equip(EnumWrappers.ItemSlot slot, ItemStack item, Player player) {
        equip(slot, item);
        sendEquipmentPacket(player);
    }

    public void updateEquipment(Player player) {
        sendEquipmentPacket(player);
    }

    private void sendEquipmentPacket(Player player) {
        if (!equipmentMap.isEmpty()) {
            packet.setEntity(entityId);
            List<Pair<EnumWrappers.ItemSlot, ItemStack>> equipmentList = new ArrayList<>(equipmentMap.values());
            packet.setSlots(equipmentList);
            packet.sendPacket(player);
        }
    }
}
