package me.nbtc.armorStandPacket.configuration;

import lombok.Builder;
import lombok.Getter;
import me.nbtc.armorStandPacket.equipment.ArmorStandEquipment;
import me.nbtc.armorStandPacket.equipment.EquipmentBuilder;
import me.nbtc.armorStandPacket.v1_20.wrapper.living.WrapperEntityArmorStand;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@Builder
@Getter
public class ArmorStandConfigurator {

    private final float leftArmX, leftArmY, leftArmZ;
    private final float rightArmX, rightArmY, rightArmZ;
    private final float leftLegX, leftLegY, leftLegZ;
    private final float rightLegX, rightLegY, rightLegZ;
    private final float bodyX, bodyY, bodyZ;
    private final float headX, headY, headZ;
    private final boolean small;
    private final boolean arms;
    private final boolean marker;
    private final boolean noBasePlate;
    private final boolean customNameVisible;
    private final boolean invisible;
    private final boolean tick;
    private EquipmentBuilder equipment;

    public void applyTo(WrapperEntityArmorStand armorStand) {
        armorStand.setLeftArmPose(leftArmX, leftArmY, leftArmZ);
        armorStand.setRightArmPose(rightArmX, rightArmY, rightArmZ);
        armorStand.setLeftLegPose(leftLegX, leftLegY, leftLegZ);
        armorStand.setRightLegPose(rightLegX, rightLegY, rightLegZ);
        armorStand.setBodyPose(bodyX, bodyY, bodyZ);
        armorStand.setHeadPose(headX, headY, headZ);
        armorStand.setSmall(small);
        armorStand.setArms(arms);
        armorStand.setMarker(marker);
        armorStand.setNoBasePlate(noBasePlate);
        armorStand.setCustomNameVisible(customNameVisible);
        armorStand.setInvisible(invisible);

        if (equipment != null && equipment.equips() != null)
            for (ArmorStandEquipment equip : equipment.equips())
                armorStand.equip(equip.slot(), equip.itemStack());
        for (Player player : Bukkit.getOnlinePlayers())
            armorStand.getMetadataManager().sendUpdatedMetadata(player);
    }
}
