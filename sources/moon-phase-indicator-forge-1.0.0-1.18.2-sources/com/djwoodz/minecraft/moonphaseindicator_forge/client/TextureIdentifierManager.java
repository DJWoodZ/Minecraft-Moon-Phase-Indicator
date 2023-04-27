package com.djwoodz.minecraft.moonphaseindicator_forge.client;

import com.djwoodz.minecraft.moonphaseindicator_forge.config.ModConfig;

import java.io.IOException;
import java.net.URISyntaxException;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;

public class TextureIdentifierManager {
    private ModConfig config = null;

    private ResourceLocation MOON_PHASES_RP = new ResourceLocation("minecraft", "textures/environment/moon_phases.png");
    private ResourceLocation SUN_RP = new ResourceLocation("minecraft", "textures/environment/sun.png");
    private ResourceLocation MOON_PHASES_DEFAULT;
    private ResourceLocation SUN_DEFAULT;
    private ResourceLocation MOON_PHASES_EMOJI;
    private ResourceLocation SUN_EMOJI;

    public TextureIdentifierManager(Minecraft client, ModConfig config) throws IOException, URISyntaxException {
        this.config = config;
        MOON_PHASES_DEFAULT = DynamicTextureIdentifierProvider.getDefaultTextureIdentifier(client, "textures/environment/moon_phases.png");
        SUN_DEFAULT = DynamicTextureIdentifierProvider.getDefaultTextureIdentifier(client, "textures/environment/sun.png");
        MOON_PHASES_EMOJI = getModMoonPhasesTextureIdentifier(client, Style.EMOJI);
        SUN_EMOJI = getModSunTextureIdentifier(client, Style.EMOJI);
    }

    public ResourceLocation getMoonPhasesTextureIdentifier() {
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

    public ResourceLocation getSunTextureIdentifier() {
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

    private ResourceLocation getModMoonPhasesTextureIdentifier(Minecraft client, Style style) throws IOException, URISyntaxException {
        return DynamicTextureIdentifierProvider.getModTextureIdentifier(client, Style.EMOJI.moonPhasesTexture);
    }

    private ResourceLocation getModSunTextureIdentifier(Minecraft client, Style style) throws IOException, URISyntaxException {
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
