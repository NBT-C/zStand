package me.nbtc.armorStandPacket.v1_20.wrapper.living;

import com.comphenix.protocol.wrappers.Vector3F;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;


public class WrapperEntityArmorStand extends WrappedEntityLiving {
    private boolean small = false;
    private boolean noBasePlate = true;
    private boolean marker = false;
    private boolean arms = false;
    private @Getter @Setter Location location;
    private @Getter @Setter double initialY;

    private static final int armorIndex = 15;
    private static final byte markerMask = 0x10;
    private static final byte armsIndex = 4;

    public WrapperEntityArmorStand(Location location) {
        super(location);
        this.location = location;
        this.initialY = location.getY();
    }

    public void setLeftArmPose(float x, float y, float z) {
        getMetadataManager().setDataWatcherObject(Vector3F.getMinecraftClass(), 18, new Vector3F(x, y, z));
    }

    public void setLeftLegPose(float x, float y, float z) {
        getMetadataManager().setDataWatcherObject(Vector3F.getMinecraftClass(), 20, new Vector3F(x, y, z));
    }

    public void setRightLegPose(float x, float y, float z) {
        getMetadataManager().setDataWatcherObject(Vector3F.getMinecraftClass(), 21, new Vector3F(x, y, z));
    }

    public void setRightArmPose(float x, float y, float z) {
        getMetadataManager().setDataWatcherObject(Vector3F.getMinecraftClass(), 19, new Vector3F(x, y, z));
    }

    public void setBodyPose(float x, float y, float z) {
        getMetadataManager().setDataWatcherObject(Vector3F.getMinecraftClass(), 17, new Vector3F(x, y, z));
    }

    public void setHeadPose(float x, float y, float z) {
        getMetadataManager().setDataWatcherObject(Vector3F.getMinecraftClass(), 16, new Vector3F(x, y, z));
    }

    public void setSmall(boolean small) {
        this.small = small;
        getMetadataManager().setDataWatcherObject(Byte.class, armorIndex, Byte.valueOf((byte) ((small ? 1 : 0) | (this.noBasePlate ? 8 : 0) | (this.marker ? markerMask : 0) | (this.arms ? armsIndex : 0))));
    }

    public void setArms(boolean arms) {
        this.arms = arms;
        getMetadataManager().setDataWatcherObject(Byte.class, armorIndex, Byte.valueOf((byte) ((this.small ? 1 : 0) | (this.noBasePlate ? 8 : 0) | (this.marker ? markerMask : 0) | (arms ? armsIndex : 0))));
    }

    public void setMarker(boolean marker) {
        this.marker = marker;
        getMetadataManager().setDataWatcherObject(Byte.class, armorIndex, Byte.valueOf((byte) ((this.small ? 1 : 0) | (this.noBasePlate ? 8 : 0) | (marker ? markerMask : 0) | (this.arms ? armsIndex : 0))));
    }

    public void setNoBasePlate(boolean noBasePlate) {
        this.noBasePlate = noBasePlate;
        getMetadataManager().setDataWatcherObject(Byte.class, armorIndex, Byte.valueOf((byte) ((this.small ? 1 : 0) | (noBasePlate ? 8 : 0) | (this.marker ? markerMask : 0) | (this.arms ? armsIndex : 0))));
    }


}
