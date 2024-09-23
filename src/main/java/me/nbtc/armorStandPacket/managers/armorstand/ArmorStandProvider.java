package me.nbtc.armorStandPacket.managers.armorstand;

import java.util.Map;

public interface ArmorStandProvider {
    void create(ArmorStandBase base);
    ArmorStandBase getById(int id);
    Map<Integer, ArmorStandBase> getMap();
    void remove(int id);
}
