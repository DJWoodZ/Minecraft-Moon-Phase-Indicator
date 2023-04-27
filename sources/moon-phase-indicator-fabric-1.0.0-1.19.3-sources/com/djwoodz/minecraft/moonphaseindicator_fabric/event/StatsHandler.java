package com.djwoodz.minecraft.moonphaseindicator_fabric.event;

import com.djwoodz.minecraft.moonphaseindicator_fabric.config.ModConfig;

import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.minecraft.class_1269;
import net.minecraft.class_2799;
import net.minecraft.class_310;
import net.minecraft.class_634;
import net.minecraft.class_638;
import net.minecraft.class_746;

public class StatsHandler {
    private static int timeAwake = 0;
    private static long lastUpdate = 0;

    public static void register() {
        ClientPlayConnectionEvents.JOIN.register((clientPlayNetworkHandler, packetSender, minecraftClient) -> {
            timeAwake = 0;
            lastUpdate = 0;
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

            if (config.showPhantomStats) {
                class_746 player = client.field_1724;
                class_634 connection = client.method_1562();
                class_638 world = client.field_1687;
                if (player != null && connection != null && world != null) {
                    long gameTime = world.method_8510();
                    if (gameTime > lastUpdate + (config.statsPollIntervalSeconds * 20)) {
                        connection.method_2883(new class_2799(class_2799.class_2800.field_12775));
                        lastUpdate = gameTime;
                    }
                }
            } else {
                timeAwake = 0;
                lastUpdate = 0;
            }
        });

        AutoConfig.getConfigHolder(ModConfig.class).registerSaveListener((manager, data) -> {
            if (data.showPhantomStats) {
                class_310 client = class_310.method_1551();
                class_638 world = client.field_1687;
                if (client != null && world != null) {
                    class_634 connection = client.method_1562();
                    if (connection != null) {
                        long gameTime = world.method_8510();
                        connection.method_2883(new class_2799(class_2799.class_2800.field_12775));
                        lastUpdate = gameTime;
                    }
                }
            } else {
                timeAwake = 0;
                lastUpdate = 0;
            }

            return class_1269.field_5812;
        });
    }

    public static void setTimeAwake(int timeAwake) {
        StatsHandler.timeAwake = timeAwake;
    }

    private static int getTimeAwakeSeconds() {
        return timeAwake / 20;
    }

    public static String getTimeAwakeFormatted() {
        float gameDaysAwake = getTimeAwakeSeconds() / 1200f;
        if (gameDaysAwake < 100) {
            return String.format("%.2f", gameDaysAwake);
        }
        return "99.99";
    }

    public static float getPhantomSpawnPercentage() {
        float timeAwakeSeconds = getTimeAwakeSeconds();
        if (timeAwakeSeconds <= (72000 / 20)) {
            return 0;
        }

        return ((timeAwakeSeconds - (72000f / 20f)) / timeAwakeSeconds);
    }
}
