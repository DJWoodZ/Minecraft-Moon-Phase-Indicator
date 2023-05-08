# Moon Phase Indicator (Minecraft Mod)

A Fabric and Forge Minecraft mod that displays information about the moon phase.

![Screenshot of Moon Phase Indicator (Minecraft Mod)](screenshot.png)

## Features

* Displays the following information:
  * Moon icon (moon texture)
  * Moon phase (name)
  * Moon size (percentage)
  * Relative size of next moon phase (arrow)
  * Real time until next moon phase (mm:ss)
  * Phantom spawn chance (percentage)
  * Game days since last rest (number)
* Keybindings (Default: `I` to toggle indicator and `U` to show configuration screen)
* Configuration options (availiable via keybinding or [Mod Menu](https://modrinth.com/mod/modmenu/) for Fabric, if installed):
  * General
    * Show indicator (`Yes` or `No`)
    * Show indicator in (`All dimensions`, `Overworld only` or `Nether and End only`)
    * Indicator position  (`Bottom Right`, `Top Left`, etc.)
    * Compact mode (`Yes` or `No`)
    * Horizontal offset (`0%` - `50%`)
    * Vertical offset (`0%` - `50%`)
    * Background opacity (`0` - `255`)
    * Background color (`#000000` - `#ffffff`)
  * Icon
    * Icon style (`Resource Pack`, `Default / Vanilla` or `Emoji`)
    * Icon size (`Small`, `Medium`, etc.)
    * Show sun icon (`Yes` or `No`)
    * Zoomed icon (`Yes` or `No`)
  * Phantom
    * Show phantom statistics (`Yes` or `No`)
    * Statistics polling interval (`1` - `5`)

## Download

You can download this mod from the following websites:

* [CurseForge](https://www.curseforge.com/minecraft/mc-mods/moon-phase-indicator)
* [Modrinth](https://modrinth.com/mod/moon-phase-indicator)

## Dependencies

This project was developed against the following libraries:

### Fabric

#### v1.19.4 (Fabric)

Required |Mod Name        |Mod ID       |Version Required |Description
---------|----------------|-------------|-----------------|-----------
Required |[Fabric API](https://modrinth.com/mod/fabric-api/)|fabric-api|>=0.79.0+1.19.4|Essential hooks for modding with Fabric.
Required |[Cloth Config API](https://modrinth.com/mod/cloth-config/)|cloth-config|>=10.0.96 <11.0.0-0|An API for config screens.
Optional |[Mod Menu](https://modrinth.com/mod/modmenu/)|modmenu|>=6.1.0 <7.0.0-0|Adds a mod menu to view the list of mods you have installed.

#### v1.19.3 (Fabric)

Required |Mod Name        |Mod ID       |Version Required |Description
---------|----------------|-------------|-----------------|-----------
Required |[Fabric API](https://modrinth.com/mod/fabric-api/)|fabric-api|>=0.76.1+1.19.3|Essential hooks for modding with Fabric.
Required |[Cloth Config API](https://modrinth.com/mod/cloth-config/)|cloth-config|>=9.0.94 <10.0.0-0|An API for config screens.
Optional |[Mod Menu](https://modrinth.com/mod/modmenu/)|modmenu|>=5.0.0 <6.0.0-0|Adds a mod menu to view the list of mods you have installed.

#### v1.19.2 (Fabric)

Required |Mod Name        |Mod ID       |Version Required |Description
---------|----------------|-------------|-----------------|-----------
Required |[Fabric API](https://modrinth.com/mod/fabric-api/)|fabric-api|>=0.59.0+1.19.2|Essential hooks for modding with Fabric.
Required |[Cloth Config API](https://modrinth.com/mod/cloth-config/)|cloth-config|>=8.0.75 <9.0.0-0|An API for config screens.
Optional |[Mod Menu](https://modrinth.com/mod/modmenu/)|modmenu|>=4.0.6 <5.0.0-0|Adds a mod menu to view the list of mods you have installed.

#### v1.19.1 (Fabric)

Required |Mod Name        |Mod ID       |Version Required |Description
---------|----------------|-------------|-----------------|-----------
Required |[Fabric API](https://modrinth.com/mod/fabric-api/)|fabric|>=0.58.5+1.19.1|Essential hooks for modding with Fabric.
Required |[Cloth Config API](https://modrinth.com/mod/cloth-config/)|cloth-config|>=8.0.75 <9.0.0-0|An API for config screens.
Optional |[Mod Menu](https://modrinth.com/mod/modmenu/)|modmenu|>=4.0.5 <5.0.0-0|Adds a mod menu to view the list of mods you have installed.

#### v1.19 (Fabric)

Required |Mod Name        |Mod ID       |Version Required |Description
---------|----------------|-------------|-----------------|-----------
Required |[Fabric API](https://modrinth.com/mod/fabric-api/)|fabric|>=0.55.2+1.19|Essential hooks for modding with Fabric.
Required |[Cloth Config API](https://modrinth.com/mod/cloth-config/)|cloth-config|>=8.0.75 <9.0.0-0|An API for config screens.
Optional |[Mod Menu](https://modrinth.com/mod/modmenu/)|modmenu|>=4.0.0 <5.0.0-0|Adds a mod menu to view the list of mods you have installed.

#### v1.18.2 (Fabric)

Required |Mod Name        |Mod ID       |Version Required |Description
---------|----------------|-------------|-----------------|-----------
Required |[Fabric API](https://modrinth.com/mod/fabric-api/)|fabric|>=0.47.8+1.18.2|Essential hooks for modding with Fabric.
Required |[Cloth Config API](https://modrinth.com/mod/cloth-config/)|cloth-config|>=6.2.57 <7.0.0-0|An API for config screens.
Optional |[Mod Menu](https://modrinth.com/mod/modmenu/)|modmenu|>=3.1.0 <4.0.0-0|Adds a mod menu to view the list of mods you have installed.

#### v1.18.1 (Fabric)

Required |Mod Name        |Mod ID       |Version Required |Description
---------|----------------|-------------|-----------------|-----------
Required |[Fabric API](https://modrinth.com/mod/fabric-api/)|fabric|>=0.44.0+1.18|Essential hooks for modding with Fabric.
Required |[Cloth Config API](https://modrinth.com/mod/cloth-config/)|cloth-config|>=6.2.57 <7.0.0-0|An API for config screens.
Optional |[Mod Menu](https://modrinth.com/mod/modmenu/)|modmenu|>=3.0.1 <4.0.0-0|Adds a mod menu to view the list of mods you have installed.

#### v1.18 (Fabric)

Required |Mod Name        |Mod ID       |Version Required |Description
---------|----------------|-------------|-----------------|-----------
Required |[Fabric API](https://modrinth.com/mod/fabric-api/)|fabric|>=0.44.0+1.18|Essential hooks for modding with Fabric.
Required |[Cloth Config API](https://modrinth.com/mod/cloth-config/)|cloth-config|>=6.2.57 <7.0.0-0|An API for config screens.
Optional |[Mod Menu](https://modrinth.com/mod/modmenu/)|modmenu|>=3.0.0 <4.0.0-0|Adds a mod menu to view the list of mods you have installed.

#### v1.17.1 (Fabric)

Required |Mod Name        |Mod ID       |Version Required |Description
---------|----------------|-------------|-----------------|-----------
Required |[Fabric API](https://modrinth.com/mod/fabric-api/)|fabric|>=0.37.0+1.17|Essential hooks for modding with Fabric.
Required |[Cloth Config API](https://modrinth.com/mod/cloth-config/)|cloth-config2|>=5.3.58 <6.0.0-0|An API for config screens.
Optional |[Mod Menu](https://modrinth.com/mod/modmenu/)|modmenu|>=2.0.2 <3.0.0-0|Adds a mod menu to view the list of mods you have installed.

#### v1.17 (Fabric)

Required |Mod Name        |Mod ID       |Version Required |Description
---------|----------------|-------------|-----------------|-----------
Required |[Fabric API](https://modrinth.com/mod/fabric-api/)|fabric|>=0.34.9+1.17|Essential hooks for modding with Fabric.
Required |[Cloth Config API](https://modrinth.com/mod/cloth-config/)|cloth-config2|>=5.3.58 <6.0.0-0|An API for config screens.
Optional |[Mod Menu](https://modrinth.com/mod/modmenu/)|modmenu|>=2.0.2 <3.0.0-0|Adds a mod menu to view the list of mods you have installed.

### Forge

#### v1.19.4 (Forge)

Required |Mod Name        |Mod ID       |Version Required |Description
---------|----------------|-------------|-----------------|-----------
Required |[Cloth Config API](https://modrinth.com/mod/cloth-config/)|cloth_config|>=10.0.96 <11.0.0-0|An API for config screens.

#### v1.19.3 (Forge)

Required |Mod Name        |Mod ID       |Version Required |Description
---------|----------------|-------------|-----------------|-----------
Required |[Cloth Config API](https://modrinth.com/mod/cloth-config/)|cloth_config|>=9.0.94 <10.0.0-0|An API for config screens.

#### v1.19.2 (Forge)

Required |Mod Name        |Mod ID       |Version Required |Description
---------|----------------|-------------|-----------------|-----------
Required |[Cloth Config API](https://modrinth.com/mod/cloth-config/)|cloth_config|>=8.0.75 <9.0.0-0|An API for config screens.

#### v1.19.1 (Forge)

Required |Mod Name        |Mod ID       |Version Required |Description
---------|----------------|-------------|-----------------|-----------
Required |[Cloth Config API](https://modrinth.com/mod/cloth-config/)|cloth_config|>=8.0.75 <9.0.0-0|An API for config screens.

#### v1.19 (Forge)

Required |Mod Name        |Mod ID       |Version Required |Description
---------|----------------|-------------|-----------------|-----------
Required |[Cloth Config API](https://modrinth.com/mod/cloth-config/)|cloth_config|>=8.0.75 <9.0.0-0|An API for config screens.

#### v1.18.2 (Forge)

Required |Mod Name        |Mod ID       |Version Required |Description
---------|----------------|-------------|-----------------|-----------
Required |[Cloth Config API](https://modrinth.com/mod/cloth-config/)|cloth_config|>=6.2.57 <7.0.0-0|An API for config screens.

#### v1.18.1 (Forge)

Required |Mod Name        |Mod ID       |Version Required |Description
---------|----------------|-------------|-----------------|-----------
Required |[Cloth Config API](https://modrinth.com/mod/cloth-config/)|cloth_config|>=6.2.57 <7.0.0-0|An API for config screens.

#### v1.18 (Forge)

Required |Mod Name        |Mod ID       |Version Required |Description
---------|----------------|-------------|-----------------|-----------
Required |[Cloth Config API](https://modrinth.com/mod/cloth-config/)|cloth_config|>=6.2.57 <7.0.0-0|An API for config screens.

#### v1.17.1 (Forge)

Required |Mod Name        |Mod ID       |Version Required |Description
---------|----------------|-------------|-----------------|-----------
Required |[Cloth Config API](https://modrinth.com/mod/cloth-config/)|cloth_config|>=5.3.58 <6.0.0-0|An API for config screens.

## License

This project is available under the [MIT license](./LICENSE).