package me.nbtc.armorStandPacket.managers.armorstand;

import lombok.Getter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public class ArmorStandManager implements ArmorStandProvider {
    private final Map<Integer, ArmorStandBase> armorStandBaseMap = new ConcurrentHashMap<>();


    @Override
    public void create(ArmorStandBase base) {
        armorStandBaseMap.put(base.getArmorStand().getId(), base);
        base.setup();
    }

    @Override
    public ArmorStandBase getById(int id) {
        return armorStandBaseMap.get(id);
    }

    @Override
    public Map<Integer, ArmorStandBase> getMap() {
        return armorStandBaseMap;
    }

    @Override
    public void remove(int id) {
        ArmorStandBase stand = getById(id);
        stand.destroyToAll();
        armorStandBaseMap.remove(id);
    }
}
