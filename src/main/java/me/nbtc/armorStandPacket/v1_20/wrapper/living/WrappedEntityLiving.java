package me.nbtc.armorStandPacket.v1_20.wrapper.living;

import com.comphenix.protocol.wrappers.*;
import lombok.Getter;
import lombok.Setter;
import me.nbtc.armorStandPacket.v1_20.EIDGen;
import me.nbtc.armorStandPacket.v1_20.wrapper.living.manager.*;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


@Getter
public class WrappedEntityLiving {
    private final EntityPacketManager packetManager;
    private final EquipmentManager equipmentManager;
    private final MetadataManager metadataManager;

    private Location location;
    private final int id;
    private boolean isDespawned;
    private @Setter boolean bounced = false;

    public WrappedEntityLiving(Location initialLocation) {
        this.id = EIDGen.generateEID();
        this.location = initialLocation;

        this.packetManager = new EntityPacketManager(id, location);
        this.equipmentManager = new EquipmentManager(id);
        this.metadataManager = new MetadataManager(id);

        setInvisible(false);
    }

    // Spawn methods
    public void spawn(Player player) {
        if (!isDespawned) {
            packetManager.sendSpawnPacket(player);
            teleport(location, player);
            metadataManager.sendUpdatedMetadata(player);
            equipmentManager.updateEquipment(player);
        }
    }

    // Teleport methods
    public void teleport(Location newLocation, Player player) {
        this.location = newLocation;
        packetManager.sendTeleportPacket(newLocation, player);
    }

    // Despawn
    public void despawn(Player player) {
        packetManager.sendDespawnPacket(player);
        isDespawned = true;
    }

    // Equipment
    public void equip(EnumWrappers.ItemSlot slot, ItemStack item) {
        equipmentManager.equip(slot, item);
    }

    public void equipAndNotify(EnumWrappers.ItemSlot slot, ItemStack item, Player player) {
        equipmentManager.equip(slot, item, player);
    }

    // Custom names and visibility
    public void setCustomName(String name) {
        metadataManager.setCustomName(name);
    }

    public void setCustomNameVisible(boolean visible) {
        metadataManager.setCustomNameVisible(visible);
    }

    public void setInvisible(boolean invisible) {
        metadataManager.setInvisible(invisible);
    }
}

