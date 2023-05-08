package com.djwoodz.minecraft.moonphaseindicator_fabric.client;

import com.djwoodz.minecraft.moonphaseindicator_fabric.config.ModConfig;

import java.io.IOException;
import java.net.URISyntaxException;
import net.minecraft.class_2960;
import net.minecraft.class_310;

public class TextureIdentifierManager {
    private ModConfig config = null;

    private class_2960 MOON_PHASES_RP = new class_2960("minecraft", "textures/environment/moon_phases.png");
    private class_2960 SUN_RP = new class_2960("minecraft", "textures/environment/sun.png");
    private class_2960 MOON_PHASES_DEFAULT;
    private class_2960 SUN_DEFAULT;
    private class_2960 MOON_PHASES_EMOJI;
    private class_2960 SUN_EMOJI;

    public TextureIdentifierManager(class_310 client, ModConfig config) throws IOException, URISyntaxException {
        this.config = config;
        MOON_PHASES_DEFAULT = DynamicTextureIdentifierProvider.getDefaultTextureIdentifier(client, "textures/environment/moon_phases.png");
        SUN_DEFAULT = DynamicTextureIdentifierProvider.getDefaultTextureIdentifier(client, "textures/environment/sun.png");
        MOON_PHASES_EMOJI = getModMoonPhasesTextureIdentifier(client, Style.EMOJI);
        SUN_EMOJI = getModSunTextureIdentifier(client, Style.EMOJI);
    }

    public class_2960 getMoonPhasesTextureIdentifier() {
        switch (config.iconStyle) {
            case RESOURCE_PACK:
                return MOON_PHASES_RP;
            case DEFAULT:
                return MOON_PHASES_DEFAULT;
            case EMOJI:
                return MOON_PHASES_EMOJI;
            default:
                return null;
        }
    }

    public class_2960 getSunTextureIdentifier() {
        switch (config.iconStyle) {
            case RESOURCE_PACK:
                return SUN_RP;
            case DEFAULT:
                return SUN_DEFAULT;
            case EMOJI:
                return SUN_EMOJI;
            default:
                return null;
        }
    }

    public boolean sunTextureShouldBeBlended() {
        switch(config.iconStyle) {
            case RESOURCE_PACK:
            case DEFAULT:
                return true;
            default:
                return false;
        }
    }

    private class_2960 getModMoonPhasesTextureIdentifier(class_310 client, Style style) throws IOException, URISyntaxException {
        return DynamicTextureIdentifierProvider.getModTextureIdentifier(client, Style.EMOJI.moonPhasesTexture);
    }

    private class_2960 getModSunTextureIdentifier(class_310 client, Style style) throws IOException, URISyntaxException {
        return DynamicTextureIdentifierProvider.getModTextureIdentifier(client, Style.EMOJI.sunTexture);
    }

    public enum Style {
        EMOJI("mod/textures/emoji/moon_phases.png", "mod/textures/emoji/sun.png");

        public final String moonPhasesTexture;
        public final String sunTexture;

        private Style(String moonPhasesTexture, String sunTexture) {
            this.sunTexture = sunTexture;
            this.moonPhasesTexture = moonPhasesTexture;
        }
    }
}
