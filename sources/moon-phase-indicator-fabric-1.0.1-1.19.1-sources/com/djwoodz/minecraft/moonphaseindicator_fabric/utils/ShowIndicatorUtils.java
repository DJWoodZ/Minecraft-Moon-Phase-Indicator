package com.djwoodz.minecraft.moonphaseindicator_fabric.utils;

import com.djwoodz.minecraft.moonphaseindicator_fabric.config.ModConfig;

import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.class_310;
import net.minecraft.class_638;

public class ShowIndicatorUtils {
    public static boolean showIndicatorInCurrentDimension(class_310 client) {
        ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

        class_638 world = client.field_1687;

        if(world == null) {
            return false;
        }

        boolean showIndicator = false;
        switch (config.showIndicatorIn) {
            case OVERWORLD:
                showIndicator = world.method_44013().method_29177().toString().equals("minecraft:overworld");
                break;
            case NETHER_AND_END:
                showIndicator = (world.method_44013().method_29177().toString().equals("minecraft:the_nether")
                        || world.method_44013().method_29177().toString().equals("minecraft:the_end"));
                break;
            default:
                showIndicator = true;
                break;
        }

        return showIndicator;
    }
}
