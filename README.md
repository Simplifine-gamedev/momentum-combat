# Momentum Combat

A Minecraft Fabric mod that transforms combat by making movement matter. Your damage output scales with how you're moving when you attack.

## Features

- **Movement-Based Damage Scaling**: Your attacks deal more or less damage based on your movement state
- **Action Bar Feedback**: See your current damage multiplier displayed on every hit
- **Encourages Dynamic Combat**: Rewards aggressive, mobile playstyles over stationary fighting

## Damage Multipliers

| Movement State | Damage Multiplier |
|----------------|-------------------|
| Standing Still | 0.5x |
| Walking | 1.0x - 1.2x |
| Sprinting | 1.3x |
| Jumping | 1.5x |
| Falling | Up to 3.0x (scales with fall velocity) |

## Mechanics Explained

### Standing Still (0.5x)
If you're standing completely still when you attack, your damage is halved. This discourages passive combat and camping.

### Walking (1.0x - 1.2x)
Normal movement gives you standard damage with a small bonus based on speed.

### Sprinting (1.3x)
Sprint attacks deal 30% bonus damage. Great for aggressive engagements.

### Jumping (1.5x)
Attack while moving upward (jumping) for a solid 50% damage bonus. Classic jump-attack combat!

### Falling (Up to 3.0x)
The most powerful attacks come from above. Falling attacks scale based on your downward velocity, potentially tripling your damage. Perfect for drop attacks from heights.

## Installation

### Requirements
- Minecraft 1.21.1
- Fabric Loader 0.16.0+
- Fabric API
- Java 21

### Steps
1. Install [Fabric Loader](https://fabricmc.net/use/)
2. Download [Fabric API](https://modrinth.com/mod/fabric-api)
3. Download the latest `momentum-combat-1.0.0.jar` from the releases
4. Place both JARs in your `.minecraft/mods` folder
5. Launch the game with the Fabric profile

## Building from Source

```bash
git clone https://github.com/Simplifine-gamedev/momentum-combat.git
cd momentum-combat
./gradlew build
```

The compiled JAR will be in `build/libs/`.

## Gameplay Tips

- **Ambush tactics**: Drop from above for massive damage
- **Jump-hitting**: Time your swings at the peak of your jump
- **Keep moving**: Never stand still in combat
- **Sprint engagements**: Rush enemies with sprint attacks
- **Combo moves**: Jump > fall > attack for maximum damage

## Compatibility

This mod modifies player attack damage calculations using mixins. Should be compatible with most other mods. May have interactions with other combat-modifying mods.

## License

MIT License - Feel free to use and modify.
