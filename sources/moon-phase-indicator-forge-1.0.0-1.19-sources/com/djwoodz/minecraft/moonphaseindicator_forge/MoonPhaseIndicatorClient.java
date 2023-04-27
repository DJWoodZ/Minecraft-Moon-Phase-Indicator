package com.djwoodz.minecraft.moonphaseindicator_forge;

import com.djwoodz.minecraft.moonphaseindicator_forge.client.MoonPhaseOverlay;
import com.djwoodz.minecraft.moonphaseindicator_forge.config.ModConfig;
import com.djwoodz.minecraft.moonphaseindicator_forge.event.KeyInputHandler;
import com.djwoodz.minecraft.moonphaseindicator_forge.event.StatsHandler;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = MoonPhaseIndicator.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MoonPhaseIndicatorClient {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        AutoConfig.register(ModConfig.class, JanksonConfigSerializer::new);
        ModMenuIntegration.integrate();
        StatsHandler.register();
    }

    @SubscribeEvent
    public static void onKeyRegister(RegisterKeyMappingsEvent event) {
        KeyInputHandler.register(event);
    }

    @SubscribeEvent
    public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
        MoonPhaseOverlay.register(event);
    }
}