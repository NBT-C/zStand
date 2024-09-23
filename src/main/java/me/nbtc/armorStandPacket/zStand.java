package me.nbtc.armorStandPacket;

import lombok.Getter;
import me.nbtc.armorStandPacket.listener.PacketListener;
import me.nbtc.armorStandPacket.managers.armorstand.ArmorStandManager;
import me.nbtc.armorStandPacket.managers.armorstand.ArmorStandProvider;
import me.nbtc.armorStandPacket.managers.tasks.ArmorStandTask;
import org.bukkit.plugin.Plugin;

@Getter
public final class zStand {
    public static @Getter zStand instance;
    private @Getter Plugin plugin;
    private final ArmorStandProvider provider;

    public zStand(Plugin plugin){
        instance = this;
        this.plugin = plugin;
        this.provider = new ArmorStandManager();

        new PacketListener(plugin);
        ArmorStandTask.start(plugin);
    }
}