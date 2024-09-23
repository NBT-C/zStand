package me.nbtc.armorStandPacket;

import lombok.Getter;
import me.nbtc.armorStandPacket.listener.PacketListener;
import me.nbtc.armorStandPacket.armorstand.ArmorStandManager;
import me.nbtc.armorStandPacket.armorstand.ArmorStandProvider;
import me.nbtc.armorStandPacket.tasks.ArmorStandTask;
import org.bukkit.plugin.Plugin;

@Getter
public final class ZStand {
    public static @Getter ZStand instance;
    private @Getter Plugin plugin;
    private final ArmorStandProvider provider;

    public ZStand(Plugin plugin){
        instance = this;
        this.plugin = plugin;
        this.provider = new ArmorStandManager();

        new PacketListener(plugin);
        ArmorStandTask.start(plugin);
    }
}