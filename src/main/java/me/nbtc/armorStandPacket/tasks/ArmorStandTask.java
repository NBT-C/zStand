package me.nbtc.armorStandPacket.tasks;

import me.nbtc.armorStandPacket.ZStand;
import me.nbtc.armorStandPacket.armorstand.ArmorStandBase;
import me.nbtc.armorStandPacket.armorstand.StandAnimation;
import me.nbtc.armorStandPacket.armorstand.StandInteraction;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class ArmorStandTask {

    public static void start(Plugin plugin) {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (ArmorStandBase base : ZStand.getInstance().getProvider().getMap().values()) {
                    try {
                        if (base.getConfigurator() == null)
                            continue;
                        if (!base.isAlive()) {
                            ZStand.getInstance().getProvider().getMap().remove(base.getArmorStand().getId());
                            continue;
                        }
                        if (base.getAnimation() == null || base.getAnimation() == StandAnimation.NONE)
                            continue;

                        base.getAnimation().apply(base);

                        if (!base.getConfigurator().isTick())
                            continue;

                        Bukkit.getScheduler().runTask(plugin, () -> {
                            base.getLocation().getWorld().getNearbyEntities(base.getLocation(), 0.23, 0.23, 0.23).forEach(e -> {
                                if (!(e instanceof Player player)) return;
                                if (!base.getTouched().contains(player.getUniqueId())) {
                                    base.getTouched().add(player.getUniqueId());
                                    base.onInteract(player, StandInteraction.TOUCH);
                                }
                            });
                        });

                        base.getTouched().removeIf(uuid -> {
                            Player player = Bukkit.getPlayer(uuid);
                            if (player == null || !player.isOnline()) return true;
                            return !player.getLocation().getWorld().equals(base.getLocation().getWorld()) ||
                                    player.getLocation().distance(base.getLocation()) > 1;
                        });

                        base.processTick();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.runTaskTimerAsynchronously(plugin, 0, 1);
    }
}
