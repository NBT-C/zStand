package me.nbtc.armorStandPacket.v1_20.wrapper.living.manager;

import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.comphenix.protocol.wrappers.WrappedDataValue;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import com.comphenix.protocol.wrappers.WrappedWatchableObject;
import lombok.Data;
import me.nbtc.armorStandPacket.v1_20.wrapper.packet.WrapperPlayServerEntityMetadata;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
public class MetadataManager {
    private final int entityId;
    private final WrappedDataWatcher dataWatcher = new WrappedDataWatcher();
    private final WrapperPlayServerEntityMetadata packet;

    public MetadataManager(int entityId) {
        this.entityId = entityId;
        this.packet = new WrapperPlayServerEntityMetadata();
        this.packet.setId(entityId);
    }

    public void setDataWatcherObject(Class<?> type, int objectIndex, Object object) {
        WrappedDataWatcher.WrappedDataWatcherObject watcherObject = new WrappedDataWatcher.WrappedDataWatcherObject(objectIndex, WrappedDataWatcher.Registry.get(type));
        dataWatcher.setObject(watcherObject, object);
    }

    public void setCustomName(String name) {
        final WrappedDataWatcher.Serializer chatSerializer =
                WrappedDataWatcher.Registry.getChatComponentSerializer(true);

        final WrappedDataWatcher.WrappedDataWatcherObject optChatFieldWatcher =
                new WrappedDataWatcher.WrappedDataWatcherObject(2, chatSerializer);

        final Optional<Object> optChatField =
                Optional.of(WrappedChatComponent.fromChatMessage(name)[0].getHandle());

        dataWatcher.setObject(optChatFieldWatcher, optChatField);
    }

    public void setCustomNameVisible(boolean visible) {
        setDataWatcherObject(Boolean.class, 3, visible);
    }
    public void setInvisible(boolean invisible) {
        setDataWatcherObject(Byte.class, 0, Byte.valueOf((byte) (invisible ? 32 : 0)));
    }

    public void sendUpdatedMetadata(Player player) {
        final List<WrappedDataValue> wrappedDataValueList = new ArrayList<>();

        for(final WrappedWatchableObject entry : dataWatcher.getWatchableObjects()) {
            if (entry == null) continue;

            final WrappedDataWatcher.WrappedDataWatcherObject watcherObject = entry.getWatcherObject();
            wrappedDataValueList.add(
                    new WrappedDataValue(
                            watcherObject.getIndex(),
                            watcherObject.getSerializer(),
                            entry.getRawValue()
                    )
            );
        }
        this.packet.setPackedItems(wrappedDataValueList);
        this.packet.sendPacket(player);
    }
}
