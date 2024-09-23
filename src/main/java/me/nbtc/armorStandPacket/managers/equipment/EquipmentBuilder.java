package me.nbtc.armorStandPacket.managers.equipment;

public record EquipmentBuilder(ArmorStandEquipment[] equips) {
    public static EquipmentBuilder of(ArmorStandEquipment... equipments) {
        return new EquipmentBuilder(equipments);
    }
}

