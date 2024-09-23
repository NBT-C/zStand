package me.nbtc.armorStandPacket.listener;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.WrappedEnumEntityUseAction;
import me.nbtc.armorStandPacket.ZStand;
import me.nbtc.armorStandPacket.armorstand.ArmorStandBase;
import me.nbtc.armorStandPacket.armorstand.StandInteraction;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class PacketListener {
    private final ProtocolManager protocolManager;
    private final Plugin plugin;

    public PacketListener(Plugin plugin) {
        this.plugin = plugin;
        this.protocolManager = ProtocolLibrary.getProtocolManager();
        registerPacketListener();
    }

    private void registerPacketListener() {
        protocolManager.addPacketListener(new PacketAdapter(plugin, ListenerPriority.HIGH, PacketType.Play.Client.USE_ENTITY) {
            @Override
            public void onPacketReceiving(PacketEvent event) {
                PacketContainer packet = event.getPacket();
                if (packet == null) {
                    return;
                }

                Player player = event.getPlayer();
                if (packet.getType() != PacketType.Play.Client.USE_ENTITY) return;
                int entityID = packet.getIntegers().read(0);

                final WrappedEnumEntityUseAction wrappedAction = packet.getEnumEntityUseActions().read(0);
                final EnumWrappers.EntityUseAction action = wrappedAction.getAction();

                ArmorStandBase base = ZStand.getInstance().getProvider().getById(entityID);
                if (base == null) return;

                if (action.equals(EnumWrappers.EntityUseAction.ATTACK)) {
                    base.onInteract(player, StandInteraction.LEFT_CLICK);
                } else {
                    base.onInteract(player, StandInteraction.RIGHT_CLICK);
                }
            }
        });
    }
}
