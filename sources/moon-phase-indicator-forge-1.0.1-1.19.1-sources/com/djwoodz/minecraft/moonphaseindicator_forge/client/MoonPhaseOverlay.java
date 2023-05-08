package com.djwoodz.minecraft.moonphaseindicator_forge.client;

import com.djwoodz.minecraft.moonphaseindicator_forge.MoonPhaseIndicator;
import com.djwoodz.minecraft.moonphaseindicator_forge.config.ModConfig;
import com.djwoodz.minecraft.moonphaseindicator_forge.event.StatsHandler;
import com.djwoodz.minecraft.moonphaseindicator_forge.utils.ShowIndicatorUtils;

import com.mojang.blaze3d.platform.GlStateManager.DestFactor;
import com.mojang.blaze3d.platform.GlStateManager.SourceFactor;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class MoonPhaseOverlay implements IGuiOverlay {
    private static final String MOON_PHASE_KEY_PREFIX = "text.autoconfig." + MoonPhaseIndicator.MOD_ID + ".moonPhase.";

    private TextureIdentifierManager textureIdentifierManager = null;

    // scaled in compact mode
    private static final int MAX_TEXT_WIDTH = 82;
    private static final int FONT_GAP = 3;

    // not scaled in compact mode
    private static final int PADDING = 2;

    @Override
    public void render(ForgeGui gui, PoseStack poseStack, float partialTick, int screenWidth, int screenHeight) {
        Minecraft client = Minecraft.getInstance();
        ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

        ClientLevel level = null;
        if (client.level != null) {
            level = client.level;
        }

        if (client != null && level != null && config.indicatorVisibility) {
            if (ShowIndicatorUtils.showIndicatorInCurrentDimension(client)) {
                if (textureIdentifierManager == null) {
                    try {
                        textureIdentifierManager = new TextureIdentifierManager(client, config);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                Font textRenderer = client.font;
                int width = client.getWindow().getGuiScaledWidth();
                int height = client.getWindow().getGuiScaledHeight();
                double scaleFactor = client.getWindow().getGuiScale();
                float scale = (config.compactMode && scaleFactor % 2 == 0 ? 0.5f : 1f);

                int scaledTextHeight = (int) ((scale * ((textRenderer.lineHeight * 3) + (FONT_GAP * 2))));
                int scaledTextWidth = (int) (scale * (MAX_TEXT_WIDTH));
                int scaledIconSize = (int) (scale * config.iconSize.getSize());
                int scaledIconY = (scaledIconSize < scaledTextHeight ? (scaledTextHeight - scaledIconSize) / 2 : 0);
                int scaledTextY = (scaledTextHeight < scaledIconSize ? (scaledIconSize - scaledTextHeight) / 2 : 0);

                int scaledTotalWidth = (int) ((PADDING * 3) + scaledIconSize + scaledTextWidth);
                int scaledTotalHeight = (int) ((PADDING * 2) + Math.max(scaledTextHeight, scaledIconSize));

                int phase = level.getMoonPhase();
                String percentage = String.valueOf((int) (level.getMoonBrightness() * 100) + "%");

                long totalSecondsRemaining = (24000 - (level.getDayTime() % 24000)) / 20;
                long minutesRemaining = totalSecondsRemaining / 60;
                long secondsRemaining = totalSecondsRemaining % 60;
                String timeRemaining = String.format("%02d:%02d", minutesRemaining, secondsRemaining);

                String direction = (phase < 4 ? "↓" : "↑");
                int directionColour = (phase < 4 ? 0xff0000 : 0x00ff00);
                int redValue = (int) ((Math.min((1 - level.getMoonBrightness()) * 2, 1)) * 255);
                int greenValue = (int) ((Math.min(level.getMoonBrightness() * 2, 1)) * 255);
                int percentageColour = (1 << 24) + (redValue << 16) + (greenValue << 8);
                boolean daytime = config.showSunInDay && (level.getDayTime() % 24000) < 12000;

                float phantomPercentageDecimal = StatsHandler.getPhantomSpawnPercentage();
                String phantomPercentage = String.valueOf(Math.round(phantomPercentageDecimal * 100) + "%");
                int phantomRedValue = (int) ((Math.min((1 - phantomPercentageDecimal) * 2, 1)) * 255);
                int phantomGreenValue = (int) ((Math.min(phantomPercentageDecimal * 2, 1)) * 255);
                int phantomPercentageColour = (1 << 24) + (phantomRedValue << 16) + (phantomGreenValue << 8);

                RenderSystem.setShader(GameRenderer::getPositionTexShader);
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

                if (daytime) {
                    RenderSystem.setShaderTexture(0, textureIdentifierManager.getSunTextureIdentifier());
                } else {
                    RenderSystem.setShaderTexture(0, textureIdentifierManager.getMoonPhasesTextureIdentifier());
                }

                poseStack.pushPose();

                int horizontalOffset = (int) (((width / 2) - (scaledTotalWidth / 2)) * (config.horizontalOffset / 50f));
                int verticalOffset = (int) (((height / 2) - (scaledTotalHeight / 2)) * (config.verticalOffset / 50f));

                switch (config.indicatorPosition) {
                    case TOP_RIGHT:
                        poseStack.translate(width - scaledTotalWidth - horizontalOffset, verticalOffset, 0);
                        break;
                    case BOTTOM_LEFT:
                        poseStack.translate(horizontalOffset, height - scaledTotalHeight - verticalOffset, 0);
                        break;
                    case BOTTOM_RIGHT:
                        poseStack.translate(width - scaledTotalWidth - horizontalOffset, height - scaledTotalHeight - verticalOffset, 0);
                        break;
                    default:
                        // top left
                        poseStack.translate(horizontalOffset, verticalOffset, 0);
                        break;
                }

                GuiComponent.fill(poseStack, 0, 0, scaledTotalWidth, scaledTotalHeight, (config.backgroundOpacity << 24) + config.backgroundColour);

                poseStack.translate(PADDING, PADDING, 0);

                if (daytime) {
                    boolean blend = textureIdentifierManager.sunTextureShouldBeBlended();
                    GuiComponent.fill(poseStack, 0, scaledIconY, scaledIconSize, scaledIconY + scaledIconSize,
                        0xFF78A7FF);

                    if (blend) {
                        RenderSystem.enableBlend();
                        RenderSystem.blendFunc(SourceFactor.ONE, DestFactor.DST_ALPHA);
                    }

                    if (config.zoomIcon) {
                        GuiComponent.blit(poseStack, 0, scaledIconY, scaledIconSize / 2, scaledIconSize / 2,
                                scaledIconSize, scaledIconSize, -scaledIconSize * 2, scaledIconSize * 2);
                    } else {
                        GuiComponent.blit(poseStack, 0, scaledIconY, 0, 0, scaledIconSize, scaledIconSize,
                                -scaledIconSize, scaledIconSize);
                    }

                    if (blend) {
                        RenderSystem.defaultBlendFunc();
                        RenderSystem.disableBlend();
                    }
                } else {
                    GuiComponent.fill(poseStack, 0, scaledIconY, scaledIconSize, scaledIconY + scaledIconSize,
                        0xFF000000);
                    if (config.zoomIcon) {
                        GuiComponent.blit(poseStack, 0, scaledIconY,
                                (((3 - phase) % 4) * scaledIconSize * 2) + (scaledIconSize / 2),
                                ((phase / 4) * scaledIconSize * 2) + (scaledIconSize / 2), scaledIconSize,
                                scaledIconSize,
                                -scaledIconSize * 4 * 2, scaledIconSize * 2 * 2);
                    } else {
                        GuiComponent.blit(poseStack, 0, scaledIconY, ((3 - phase) % 4) * scaledIconSize,
                                (phase / 4) * scaledIconSize, scaledIconSize, scaledIconSize, -scaledIconSize * 4,
                                scaledIconSize * 2);
                    }
                }

                poseStack.translate(scaledIconSize + PADDING, scaledTextY, 0);

                poseStack.scale(scale, scale, scale);
                GuiComponent.drawString(poseStack, textRenderer, Component.translatable(MOON_PHASE_KEY_PREFIX + phase), 0, 0, 0xffffff);

                GuiComponent.drawString(poseStack, textRenderer, Component.nullToEmpty(percentage), 0,
                        textRenderer.lineHeight + FONT_GAP, percentageColour);
                if (config.showPhantomStats) {
                    GuiComponent.drawString(poseStack, textRenderer, Component.nullToEmpty(phantomPercentage), MAX_TEXT_WIDTH / 2,
                        textRenderer.lineHeight + FONT_GAP, phantomPercentageColour);
                }
                
                GuiComponent.drawString(poseStack, textRenderer, Component.nullToEmpty(direction), 0,
                        (textRenderer.lineHeight + FONT_GAP) * 2, directionColour);
                GuiComponent.drawString(poseStack, textRenderer, Component.nullToEmpty(timeRemaining), 9,
                        (textRenderer.lineHeight + FONT_GAP) * 2, 0xffffff);
                if (config.showPhantomStats) {
                    GuiComponent.drawString(poseStack, textRenderer, Component.nullToEmpty(StatsHandler.getTimeAwakeFormatted()), MAX_TEXT_WIDTH / 2,
                        (textRenderer.lineHeight + FONT_GAP) * 2, 0xffffff);
                }

                poseStack.popPose();
            }
        }
    }

    public static void register(RegisterGuiOverlaysEvent event) {
        event.registerAboveAll(MoonPhaseIndicator.MOD_ID, new MoonPhaseOverlay());
    }
}
