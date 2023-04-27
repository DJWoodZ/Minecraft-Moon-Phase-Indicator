package com.djwoodz.minecraft.moonphaseindicator_forge.event;

import com.djwoodz.minecraft.moonphaseindicator_forge.MoonPhaseIndicator;
import com.djwoodz.minecraft.moonphaseindicator_forge.config.ModConfig;

import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.protocol.game.ServerboundClientCommandPacket;
import net.minecraft.world.InteractionResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;

public class StatsHandler {
    private static int timeAwake = 0;
    private static long lastUpdate = 0;

    @Mod.EventBusSubscriber(modid = MoonPhaseIndicator.MOD_ID, value = Dist.CLIENT)
    public static class ClientJoinEvent {
        @SubscribeEvent
        public static void onPlayerLoggedInEvent(PlayerLoggedInEvent event) {
            timeAwake = 0;
            lastUpdate = 0;
        }
    }

    @Mod.EventBusSubscriber(modid = MoonPhaseIndicator.MOD_ID, value = Dist.CLIENT)
    public static class ClientTickEvent {
        @SubscribeEvent
        public static void onClientTick(TickEvent.ClientTickEvent event) {
            if (event.phase == TickEvent.Phase.END) {
                ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

                if (config.showPhantomStats) {
                    Minecraft client = Minecraft.getInstance();
                    LocalPlayer player = client.player;
                    ClientPacketListener connection = client.getConnection();
                    ClientLevel level = client.level;
                    if (player != null && connection != null && level != null) {
                        long gameTime = level.getGameTime();
                        if (gameTime > lastUpdate + (config.statsPollIntervalSeconds * 20)) {
                            connection.send(new ServerboundClientCommandPacket(
                                    ServerboundClientCommandPacket.Action.REQUEST_STATS));
                            lastUpdate = gameTime;
                        }
                    }
                } else {
                    timeAwake = 0;
                    lastUpdate = 0;
                }
            }
        }
    }

    public static void register() {
        AutoConfig.getConfigHolder(ModConfig.class).registerSaveListener((manager, data) -> {
            if (data.showPhantomStats) {
                Minecraft client = Minecraft.getInstance();
                ClientLevel level = client.level;
                if (client != null && level != null) {
                    ClientPacketListener connection = client.getConnection();
                    if (connection != null) {
                        long gameTime = level.getGameTime();
                        connection.send(new ServerboundClientCommandPacket(
                                    ServerboundClientCommandPacket.Action.REQUEST_STATS));
                        lastUpdate = gameTime;
                    }
                }
            } else {
                timeAwake = 0;
                lastUpdate = 0;
            }

            return InteractionResult.SUCCESS;
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
