package com.djwoodz.minecraft.moonphaseindicator_forge;

import com.djwoodz.minecraft.moonphaseindicator_forge.config.ModConfig;

import java.util.function.BiFunction;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.fml.ModLoadingContext;

public class ModMenuIntegration {
    public static void integrate() {
        ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () -> new ConfigScreenHandler.ConfigScreenFactory(new BiFunction<Minecraft, Screen, Screen>() {
            @Override
            public Screen apply(Minecraft client, Screen parent) {
                return AutoConfig.getConfigScreen(ModConfig.class, parent).get();
            }
        }));
    }
}