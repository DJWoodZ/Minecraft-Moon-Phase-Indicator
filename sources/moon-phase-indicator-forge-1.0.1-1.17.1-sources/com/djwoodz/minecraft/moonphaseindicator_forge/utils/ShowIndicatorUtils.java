package com.djwoodz.minecraft.moonphaseindicator_forge.utils;

import com.djwoodz.minecraft.moonphaseindicator_forge.config.ModConfig;

import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;

public class ShowIndicatorUtils {
    public static boolean showIndicatorInCurrentDimension(Minecraft client) {
        ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
        ClientLevel level = client.level;

        if(level == null) {
            return false;
        }

        boolean showIndicator = false;
        switch (config.showIndicatorIn) {
            case OVERWORLD:
                showIndicator = level.dimension().location().toString().equals("minecraft:overworld");
                break;
            case NETHER_AND_END:
                showIndicator = (level.dimension().location().toString().equals("minecraft:the_nether")
                        || level.dimension().location().toString().equals("minecraft:the_end"));
                break;
            default:
                showIndicator = true;
                break;
        }

        return showIndicator;
    }
}
