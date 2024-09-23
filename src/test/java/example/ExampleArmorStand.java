package example;

import com.comphenix.protocol.wrappers.EnumWrappers;
import me.nbtc.armorStandPacket.armorstand.ArmorStandBase;
import me.nbtc.armorStandPacket.armorstand.StandAnimation;
import me.nbtc.armorStandPacket.armorstand.StandInteraction;
import me.nbtc.armorStandPacket.configuration.ArmorStandConfigurator;
import me.nbtc.armorStandPacket.equipment.ArmorStandEquipment;
import me.nbtc.armorStandPacket.equipment.EquipmentBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ExampleArmorStand extends ArmorStandBase {

    // Constructor to create the armor stand at a given location
    protected ExampleArmorStand(Location location) {
        super(location);
    }

    // Returns the custom name of the armor stand
    @Override
    public String getCustomName() {
        return "My cool ArmorStand!";
    }

    // Handles player interactions with the armor stand
    @Override
    public void onInteract(Player player, StandInteraction interactionType) {
        switch (interactionType) {
            // When the player right-clicks the armor stand
            case RIGHT_CLICK -> {
                player.sendMessage("Nice! We right-clicked the armor stand. (Destroy)");
                destroyToAll(); // Destroys the armor stand for all players
            }

            // When the player left-clicks the armor stand
            case LEFT_CLICK -> {
                player.sendMessage("Nice! We left-clicked the armor stand. (Name update)");
                updateCustomName("Hi bois"); // Updates the custom name of the armor stand
            }

            // When the player touches the armor stand (moves into it)
            case TOUCH -> {
                player.sendMessage("Nice! We moved into the armor stand :))");
            }
        }
    }

    // Apply the animation to the armor stand. Options are: ROTATE, FLOAT, or FLOAT_AND_ROTATE
    @Override
    public StandAnimation getAnimation() {
        return StandAnimation.FLOAT_AND_ROTATE; // Makes the armor stand float and rotate simultaneously
    }

    // Configure the armor stand's settings. This is crucial for customizing its behavior and appearance.
    @Override
    public void setup() {
        // Set up the armor stand configurator with specific properties
        this.configurator = ArmorStandConfigurator.builder()
                .customNameVisible(true)  // The custom name will be visible above the armor stand
                .invisible(true)          // The armor stand itself will be invisible
                .tick(true)               // Enables ticking for this armor stand (for animations)
                .equipment(EquipmentBuilder.of(
                        ArmorStandEquipment.of(EnumWrappers.ItemSlot.HEAD, new ItemStack(Material.SKELETON_SKULL))
                )) // Equip the armor stand with a skeleton skull on its head
                .build();

        this.applyChanges(); // Apply the changes to the armor stand
        this.revealToAll();  // Reveal the armor stand to all players
    }

    // Processes the armor stand's actions every tick. This only runs if .tick(true) is enabled in the configurator.
    @Override
    public void processTick() {
        // Logic for processing ticks can be added here if needed (e.g., animations or behavior updates)
        // Only for advanced users! This method is executed once every game tick (~20 times per second)
    }
}
