package me.nbtc.armorStandPacket.v1_20.wrapper.living.manager;

import lombok.Data;
import me.nbtc.armorStandPacket.v1_20.wrapper.packet.WrapperPlayServerEntityDestroy;
import me.nbtc.armorStandPacket.v1_20.wrapper.packet.WrapperPlayServerEntityTeleport;
import me.nbtc.armorStandPacket.v1_20.wrapper.packet.WrapperPlayServerSpawnEntity;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.UUID;

@Data
public class EntityPacketManager {
    private final int entityId;
    private final WrapperPlayServerSpawnEntity spawnPacket;
    private final WrapperPlayServerEntityDestroy destroyPacket;
    private final WrapperPlayServerEntityTeleport teleportPacket;

    public EntityPacketManager(int entityId, Location initialLocation) {
        this.entityId = entityId;
        this.spawnPacket = createSpawnPacket(initialLocation);
        this.destroyPacket = createDestroyPacket();
        this.teleportPacket = new WrapperPlayServerEntityTeleport();
    }

    private WrapperPlayServerSpawnEntity createSpawnPacket(Location location) {
        WrapperPlayServerSpawnEntity packet = new WrapperPlayServerSpawnEntity();
        packet.setId(entityId);
        packet.setType(EntityType.ARMOR_STAND);
        packet.setUuid(UUID.randomUUID());
        if (location != null) {
            packet.setX(location.getX());
            packet.setY(location.getY());
            packet.setZ(location.getZ());
            packet.setPitch(location.getPitch());
            packet.setYaw(location.getYaw());
        }
        return packet;
    }

    private WrapperPlayServerEntityDestroy createDestroyPacket() {
        WrapperPlayServerEntityDestroy packet = new WrapperPlayServerEntityDestroy();
        packet.setEntityIds(new int[]{entityId});
        return packet;
    }

    public void sendSpawnPacket(Player player) {
        spawnPacket.sendPacket(player);
    }

    public void sendTeleportPacket(Location location, Player player) {
        teleportPacket.setEntityID(entityId);
        teleportPacket.setX(location.getX());
        teleportPacket.setY(location.getY());
        teleportPacket.setZ(location.getZ());
        teleportPacket.setYaw(location.getYaw());
        teleportPacket.setPitch(location.getPitch());
        teleportPacket.sendPacket(player);
    }

    public void sendDespawnPacket(Player player) {
        destroyPacket.sendPacket(player);
    }
}
