package com.djwoodz.minecraft.moonphaseindicator_forge.event;

import com.djwoodz.minecraft.moonphaseindicator_forge.config.ModConfig;
import com.djwoodz.minecraft.moonphaseindicator_forge.MoonPhaseIndicator;
import com.djwoodz.minecraft.moonphaseindicator_forge.utils.ShowIndicatorUtils;

import com.mojang.blaze3d.platform.InputConstants;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    private static final String KEY_CATEGORY = "key.category." + MoonPhaseIndicator.MOD_ID;
    private static final String KEY_TOGGLE_INDICATOR = "key." + MoonPhaseIndicator.MOD_ID + ".toggleIndicator";
    private static final String KEY_SHOW_CONFIG_SCREEN = "key." + MoonPhaseIndicator.MOD_ID + ".showConfigScreen";

    private static KeyMapping toggleIndicatorKey  = new KeyMapping(KEY_TOGGLE_INDICATOR, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_I, KEY_CATEGORY);
    private static KeyMapping showConfigScreenKey = new KeyMapping(KEY_SHOW_CONFIG_SCREEN, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_U, KEY_CATEGORY);

    @Mod.EventBusSubscriber(modid = MoonPhaseIndicator.MOD_ID, value = Dist.CLIENT)
    public static class KeyInputEvent {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            Minecraft client = Minecraft.getInstance();
            LocalPlayer player = client.player;

            if (toggleIndicatorKey.consumeClick() && player != null) {
                ConfigHolder<ModConfig> configHolder = AutoConfig.getConfigHolder(ModConfig.class);
                ModConfig config = configHolder.getConfig();
                if (!ShowIndicatorUtils.showIndicatorInCurrentDimension(client)) {
                    player.displayClientMessage(Component.translatable(config.showIndicatorIn.toMessage()), true);
                    if (!config.indicatorVisibility) {
                        config.indicatorVisibility = true;
                        configHolder.save();
                    }
                } else {
                    config.indicatorVisibility = !config.indicatorVisibility;
                    configHolder.save();
                }
            }
            if (showConfigScreenKey.consumeClick()) {
                client.setScreen(AutoConfig.getConfigScreen(ModConfig.class, client.screen).get());
            }
        }
    }

    public static void register(RegisterKeyMappingsEvent event) {
        event.register(toggleIndicatorKey);
        event.register(showConfigScreenKey);
    }
}
