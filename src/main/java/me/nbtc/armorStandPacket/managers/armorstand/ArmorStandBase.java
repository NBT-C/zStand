package me.nbtc.armorStandPacket.managers.armorstand;

import lombok.Getter;
import lombok.Setter;
import me.nbtc.armorStandPacket.managers.configuration.ArmorStandConfigurator;
import me.nbtc.armorStandPacket.v1_20.wrapper.living.WrapperEntityArmorStand;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.*;

@Getter
public abstract class ArmorStandBase {
    public final Set<UUID> touched = new HashSet<>();
    private final WrapperEntityArmorStand armorStand;
    protected @Setter ArmorStandConfigurator configurator;
    private final List<UUID> viewers;
    private Location location;

    protected ArmorStandBase(Location location) {
        this.viewers = new ArrayList<>();
        this.armorStand = new WrapperEntityArmorStand(location);
        this.location = location;

        armorStand.setCustomName(getCustomName());
    }

    public abstract String getCustomName();
    public abstract StandAnimation getAnimation();
    public abstract void onInteract(Player player, StandInteraction interactionType);
    public abstract void setup();
    public abstract void processTick();

    public void applyChanges() {
        configurator.applyTo(armorStand);
    }
    public void revealTo(Player source) {
        armorStand.spawn(source);
        viewers.add(source.getUniqueId());
    }
    public void revealToAll() {
        Bukkit.getOnlinePlayers().forEach(this::revealTo);
    }
    public void destroyTo(Player source) {
        armorStand.despawn(source);
        viewers.remove(source.getUniqueId());
    }
    public void destroyToAll(){
        Bukkit.getOnlinePlayers().forEach(this::destroyTo);
    }
    public boolean isAlive(){
        return !armorStand.isDespawned();
    }
    public void setLocation(Location location){
        this.armorStand.setLocation(location);
        this.location = location;
    }
    public void teleport(Location location, Player source){
        this.armorStand.teleport(location, source);
    }
    public void teleport(Location location){
        Bukkit.getOnlinePlayers().forEach(p ->teleport(location,p));
    }
    public List<Player> getViewers(){
        List<Player> view = new ArrayList<>();
        for (UUID uuid : viewers){
            Player player = Bukkit.getPlayer(uuid);
            if (player == null || !player.isOnline()) continue;
            view.add(player);
        }
        return view;
    }
    public void updateCustomName(String customName){
        this.armorStand.setCustomName(customName);
        update();
    }
    public void update() {
        getViewers().forEach(p -> armorStand.getMetadataManager().sendUpdatedMetadata(p));
    }
}
