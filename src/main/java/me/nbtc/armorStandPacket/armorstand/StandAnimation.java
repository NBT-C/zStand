package me.nbtc.armorStandPacket.armorstand;

import org.bukkit.Location;

import java.util.function.Consumer;

public enum StandAnimation {
    NONE(base -> {

    }),
    ROTATE(base -> {
        Location loc = base.getLocation();
        loc.setYaw(base.getLocation().clone().getYaw() + 2F);
        base.teleport(loc);
    }),
    FLOAT(base -> {
        Location loc = base.getLocation();
        double currentY = loc.getY();
        double newY = currentY + (Math.sin(base.getLocation().getWorld().getTime() * 0.1) * 0.05);
        loc.setY(newY);
        base.teleport(loc);
    }),

    FLOAT_AND_ROTATE(base -> {
        Location loc = base.getLocation();

        loc.setYaw(loc.getYaw() + 2F);

        double currentY = loc.getY();
        double newY = currentY + (Math.sin(base.getLocation().getWorld().getTime() * 0.1) * 0.05);
        loc.setY(newY);

        base.teleport(loc);
    });


    private final Consumer<ArmorStandBase> action;
    StandAnimation(Consumer<ArmorStandBase> action){
        this.action = action;
    }
    public void apply(ArmorStandBase base) {
        action.accept(base);
    }
}