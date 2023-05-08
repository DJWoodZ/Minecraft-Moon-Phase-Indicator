package com.djwoodz.minecraft.moonphaseindicator_fabric.event;

import com.djwoodz.minecraft.moonphaseindicator_fabric.config.ModConfig;
import com.djwoodz.minecraft.moonphaseindicator_fabric.MoonPhaseIndicator;
import com.djwoodz.minecraft.moonphaseindicator_fabric.utils.ShowIndicatorUtils;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.class_2561;
import net.minecraft.class_304;
import net.minecraft.class_3675;
import net.minecraft.class_746;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    private static final String KEY_CATEGORY = "key.category." + MoonPhaseIndicator.MOD_ID;
    private static final String KEY_TOGGLE_INDICATOR = "key." + MoonPhaseIndicator.MOD_ID + ".toggleIndicator";
    private static final String KEY_SHOW_CONFIG_SCREEN = "key." + MoonPhaseIndicator.MOD_ID + ".showConfigScreen";

    private static class_304 toggleIndicatorKey;
    private static class_304 showConfigScreenKey;

    private static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            class_746 player = client.field_1724;
            if (toggleIndicatorKey.method_1436() && player != null) {
                ConfigHolder<ModConfig> configHolder = AutoConfig.getConfigHolder(ModConfig.class);
                ModConfig config = configHolder.getConfig();
                if (!ShowIndicatorUtils.showIndicatorInCurrentDimension(client)) {
                    player.method_7353(class_2561.method_43471(config.showIndicatorIn.toMessage()), true);
                    if (!config.indicatorVisibility) {
                        config.indicatorVisibility = true;
                        configHolder.save();
                    }
                } else {
                    config.indicatorVisibility = !config.indicatorVisibility;
                    configHolder.save();
                }
            }
            if (showConfigScreenKey.method_1436()) {
                client.method_1507(AutoConfig.getConfigScreen(ModConfig.class, client.field_1755).get());
            }
        });
    }

    public static void register() {
        toggleIndicatorKey = KeyBindingHelper.registerKeyBinding(
                new class_304(KEY_TOGGLE_INDICATOR, class_3675.class_307.field_1668, GLFW.GLFW_KEY_I, KEY_CATEGORY));
        showConfigScreenKey = KeyBindingHelper.registerKeyBinding(
                new class_304(KEY_SHOW_CONFIG_SCREEN, class_3675.class_307.field_1668, GLFW.GLFW_KEY_U, KEY_CATEGORY));
        registerKeyInputs();
    }
}
