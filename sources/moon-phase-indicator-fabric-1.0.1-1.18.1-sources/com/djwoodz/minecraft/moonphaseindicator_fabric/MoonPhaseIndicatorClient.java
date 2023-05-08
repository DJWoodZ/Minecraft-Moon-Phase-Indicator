package com.djwoodz.minecraft.moonphaseindicator_fabric;

import com.djwoodz.minecraft.moonphaseindicator_fabric.client.MoonPhaseOverlay;
import com.djwoodz.minecraft.moonphaseindicator_fabric.config.ModConfig;
import com.djwoodz.minecraft.moonphaseindicator_fabric.event.KeyInputHandler;
import com.djwoodz.minecraft.moonphaseindicator_fabric.event.StatsHandler;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class MoonPhaseIndicatorClient implements ClientModInitializer {
  @Override
  public void onInitializeClient() {
    AutoConfig.register(ModConfig.class, JanksonConfigSerializer::new);
    HudRenderCallback.EVENT.register(new MoonPhaseOverlay());
    KeyInputHandler.register();
    StatsHandler.register();
  }
}
