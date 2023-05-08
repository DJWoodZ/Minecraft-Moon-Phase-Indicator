package com.djwoodz.minecraft.moonphaseindicator_fabric.client;

import com.djwoodz.minecraft.moonphaseindicator_fabric.MoonPhaseIndicator;
import com.djwoodz.minecraft.moonphaseindicator_fabric.config.ModConfig;
import com.djwoodz.minecraft.moonphaseindicator_fabric.utils.ShowIndicatorUtils;
import com.djwoodz.minecraft.moonphaseindicator_fabric.event.StatsHandler;
import com.mojang.blaze3d.platform.GlStateManager.class_4534;
import com.mojang.blaze3d.platform.GlStateManager.class_4535;
import com.mojang.blaze3d.systems.RenderSystem;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_327;
import net.minecraft.class_332;
import net.minecraft.class_4587;
import net.minecraft.class_638;
import net.minecraft.class_757;

public class MoonPhaseOverlay implements HudRenderCallback {
    private static final String MOON_PHASE_KEY_PREFIX = "text.autoconfig." + MoonPhaseIndicator.MOD_ID + ".moonPhase.";

    private TextureIdentifierManager textureIdentifierManager = null;

    // scaled in compact mode
    private static final int MAX_TEXT_WIDTH = 82;
    private static final int FONT_GAP = 3;

    // not scaled in compact mode
    private static final int PADDING = 2;

    @Override
    public void onHudRender(class_4587 matrixStack, float tickDelta) {
        class_310 client = class_310.method_1551();
        ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

        class_638 world = null;
        if (client.field_1687 != null) {
            world = client.field_1687;
        }

        if (client != null && world != null && config.indicatorVisibility) {
            if (ShowIndicatorUtils.showIndicatorInCurrentDimension(client)) {
                if (textureIdentifierManager == null) {
                    try {
                        textureIdentifierManager = new TextureIdentifierManager(client, config);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                class_327 textRenderer = client.field_1772;
                int width = client.method_22683().method_4486();
                int height = client.method_22683().method_4502();
                double scaleFactor = client.method_22683().method_4495();
                float scale = (config.compactMode && scaleFactor % 2 == 0 ? 0.5f : 1f);

                int scaledTextHeight = (int) ((scale * ((textRenderer.field_2000 * 3) + (FONT_GAP * 2))));
                int scaledTextWidth = (int) (scale * (MAX_TEXT_WIDTH));
                int scaledIconSize = (int) (scale * config.iconSize.getSize());
                int scaledIconY = (scaledIconSize < scaledTextHeight ? (scaledTextHeight - scaledIconSize) / 2 : 0);
                int scaledTextY = (scaledTextHeight < scaledIconSize ? (scaledIconSize - scaledTextHeight) / 2 : 0);

                int scaledTotalWidth = (int) ((PADDING * 3) + scaledIconSize + scaledTextWidth);
                int scaledTotalHeight = (int) ((PADDING * 2) + Math.max(scaledTextHeight, scaledIconSize));

                int phase = world.method_30273();
                String percentage = String.valueOf((int) (world.method_30272() * 100) + "%");

                long totalSecondsRemaining = (24000 - (world.method_8532() % 24000)) / 20;
                long minutesRemaining = totalSecondsRemaining / 60;
                long secondsRemaining = totalSecondsRemaining % 60;
                String timeRemaining = String.format("%02d:%02d", minutesRemaining, secondsRemaining);

                String direction = (phase < 4 ? "↓" : "↑");
                int directionColour = (phase < 4 ? 0xff0000 : 0x00ff00);
                int redValue = (int) ((Math.min((1 - world.method_30272()) * 2, 1)) * 255);
                int greenValue = (int) ((Math.min(world.method_30272() * 2, 1)) * 255);
                int percentageColour = (1 << 24) + (redValue << 16) + (greenValue << 8);
                boolean daytime = config.showSunInDay && (world.method_8532() % 24000) < 12000;

                float phantomPercentageDecimal = StatsHandler.getPhantomSpawnPercentage();
                String phantomPercentage = String.valueOf(Math.round(phantomPercentageDecimal * 100) + "%");
                int phantomRedValue = (int) ((Math.min((1 - phantomPercentageDecimal) * 2, 1)) * 255);
                int phantomGreenValue = (int) ((Math.min(phantomPercentageDecimal * 2, 1)) * 255);
                int phantomPercentageColour = (1 << 24) + (phantomRedValue << 16) + (phantomGreenValue << 8);

                RenderSystem.setShader(class_757::method_34542);
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

                if (daytime) {
                    RenderSystem.setShaderTexture(0, textureIdentifierManager.getSunTextureIdentifier());
                } else {
                    RenderSystem.setShaderTexture(0, textureIdentifierManager.getMoonPhasesTextureIdentifier());
                }

                matrixStack.method_22903();

                int horizontalOffset = (int) (((width / 2) - (scaledTotalWidth / 2)) * (config.horizontalOffset / 50f));
                int verticalOffset = (int) (((height / 2) - (scaledTotalHeight / 2)) * (config.verticalOffset / 50f));

                switch (config.indicatorPosition) {
                    case TOP_RIGHT:
                        matrixStack.method_46416(width - scaledTotalWidth - horizontalOffset, verticalOffset, 0);
                        break;
                    case BOTTOM_LEFT:
                        matrixStack.method_46416(horizontalOffset, height - scaledTotalHeight - verticalOffset, 0);
                        break;
                    case BOTTOM_RIGHT:
                        matrixStack.method_46416(width - scaledTotalWidth - horizontalOffset, height - scaledTotalHeight - verticalOffset, 0);
                        break;
                    default:
                        // top left
                        matrixStack.method_46416(horizontalOffset, verticalOffset, 0);
                        break;
                }

                class_332.method_25294(matrixStack, 0, 0, scaledTotalWidth, scaledTotalHeight, (config.backgroundOpacity << 24) + config.backgroundColour);

                matrixStack.method_46416(PADDING, PADDING, 0);

                if (daytime) {
                    boolean blend = textureIdentifierManager.sunTextureShouldBeBlended();
                    class_332.method_25294(matrixStack, 0, scaledIconY, scaledIconSize, scaledIconY + scaledIconSize,
                        0xFF78A7FF);

                    if (blend) {
                        RenderSystem.enableBlend();
                        RenderSystem.blendFunc(class_4535.ONE, class_4534.DST_ALPHA);
                    }

                    if (config.zoomIcon) {
                        class_332.method_25290(matrixStack, 0, scaledIconY, scaledIconSize / 2, scaledIconSize / 2,
                                scaledIconSize, scaledIconSize, -scaledIconSize * 2, scaledIconSize * 2);
                    } else {
                        class_332.method_25290(matrixStack, 0, scaledIconY, 0, 0, scaledIconSize, scaledIconSize,
                                -scaledIconSize, scaledIconSize);
                    }

                    if (blend) {
                        RenderSystem.defaultBlendFunc();
                        RenderSystem.disableBlend();
                    }
                } else {
                    class_332.method_25294(matrixStack, 0, scaledIconY, scaledIconSize, scaledIconY + scaledIconSize,
                        0xFF000000);
                    if (config.zoomIcon) {
                        class_332.method_25290(matrixStack, 0, scaledIconY,
                                (((3 - phase) % 4) * scaledIconSize * 2) + (scaledIconSize / 2),
                                ((phase / 4) * scaledIconSize * 2) + (scaledIconSize / 2), scaledIconSize,
                                scaledIconSize,
                                -scaledIconSize * 4 * 2, scaledIconSize * 2 * 2);
                    } else {
                        class_332.method_25290(matrixStack, 0, scaledIconY, ((3 - phase) % 4) * scaledIconSize,
                                (phase / 4) * scaledIconSize, scaledIconSize, scaledIconSize, -scaledIconSize * 4,
                                scaledIconSize * 2);
                    }
                }

                matrixStack.method_46416(scaledIconSize + PADDING, scaledTextY, 0);

                matrixStack.method_22905(scale, scale, scale);
                class_332.method_27535(matrixStack, textRenderer, class_2561.method_43471(MOON_PHASE_KEY_PREFIX + phase), 0, 0, 0xffffff);

                class_332.method_27535(matrixStack, textRenderer, class_2561.method_30163(percentage), 0,
                        textRenderer.field_2000 + FONT_GAP, percentageColour);
                if (config.showPhantomStats) {
                    class_332.method_27535(matrixStack, textRenderer, class_2561.method_30163(phantomPercentage), MAX_TEXT_WIDTH / 2,
                        textRenderer.field_2000 + FONT_GAP, phantomPercentageColour);
                }

                class_332.method_27535(matrixStack, textRenderer, class_2561.method_30163(direction), 0,
                        (textRenderer.field_2000 + FONT_GAP) * 2, directionColour);
                class_332.method_27535(matrixStack, textRenderer, class_2561.method_30163(timeRemaining), 9,
                        (textRenderer.field_2000 + FONT_GAP) * 2, 0xffffff);
                if (config.showPhantomStats) {
                    class_332.method_27535(matrixStack, textRenderer, class_2561.method_30163(StatsHandler.getTimeAwakeFormatted()), MAX_TEXT_WIDTH / 2,
                        (textRenderer.field_2000 + FONT_GAP) * 2, 0xffffff);
                }

                matrixStack.method_22909();
            }
        }
    }
}
