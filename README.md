<h1>zStand LIB</h1>
<p>This library demonstrates how to create and manage a custom armor stand using packets in Minecraft. 🛡️</p>

<h2>Features 🚀</h2>
<ul>
<li>Only-packets (Can be shown for only one client) ✏️</li>
<li>Interaction handling (right-click, left-click, touch) 👆</li>
<li>Animations (rotate, float, float and rotate) 💫</li>
</ul>

<h2>Contributing 🤝</h2>
<p>Feel free to fork the repository and submit pull requests. Any contributions are welcome! 🌟</p>

<h2>Contact 📬</h2>
<p>For questions or suggestions, please open an issue in this repository. or contact on discord: nb__tc</p>
</body>

<h2>Requirements 💻</h2>
<p>ProtocolLib v5.2.0+</p>

<h2>Usage 📖</h2>
<p>To create a custom armor stand, extend the <code>ArmorStandBase</code> class and implement the necessary methods.</p>
<h3>Example Code 💻</h3>

```java
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
```

<h2>Initialization</h2>

```java
private zStand zStand;

@Override
public void onEnable() {
  zStand = new zStand(this);
}
```


<h3>Spawning an Armor Stand</h3>

```java
Main.INSTANCE.zStand.getProvider().create(new ExampleArmorStand(location));
```

<h2>Installation 🔧</h2>
<ol>

<h1>Maven</h1>

```xml

<repository>
  <id>jitpack.io</id>
  <url>https://jitpack.io</url>
</repository>

<dependency>
  <groupId>com.github.NBT-C</groupId>
  <artifactId>zStand</artifactId>
  <version>VERSION</version>
</dependency>

```
<h1>Gradle</h1>

```java

dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
      mavenCentral()
      maven { url 'https://jitpack.io' }
    }
  }

dependencies {
  implementation 'com.github.NBT-C:zStand:VERSION'
}

```
[![](https://jitpack.io/v/NBT-C/zStand.svg)](https://jitpack.io/#NBT-C/zStand)

</ol>


<h2>License 📄</h2>

```
MIT License

Copyright (c) 2024 zStand/NBTC

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

1. The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

2. THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```


</html>
