package com.djwoodz.minecraft.moonphaseindicator_fabric.config;

import com.djwoodz.minecraft.moonphaseindicator_fabric.config.enums.IconSize;
import com.djwoodz.minecraft.moonphaseindicator_fabric.config.enums.IconStyle;
import com.djwoodz.minecraft.moonphaseindicator_fabric.config.enums.IndicatorPosition;
import com.djwoodz.minecraft.moonphaseindicator_fabric.config.enums.ShowIndicatorIn;
import com.djwoodz.minecraft.moonphaseindicator_fabric.MoonPhaseIndicator;

import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.ConfigData;

@Config(name = MoonPhaseIndicator.MOD_ID)
@Config.Gui.Background("minecraft:textures/block/stone.png")
public class ModConfig implements ConfigData {
    @ConfigEntry.Category("General")
    @ConfigEntry.Gui.Tooltip
    public boolean indicatorVisibility = true;

    @ConfigEntry.Category("General")
    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
    public ShowIndicatorIn showIndicatorIn = ShowIndicatorIn.ALL_DIMENSIONS;

    @ConfigEntry.Category("General")
    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
    public IndicatorPosition indicatorPosition = IndicatorPosition.BOTTOM_RIGHT;

    @ConfigEntry.Category("General")
    @ConfigEntry.Gui.Tooltip
    public boolean compactMode = true;

    @ConfigEntry.Category("General")
    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.BoundedDiscrete(min = 0, max = 50)
    public int horizontalOffset = 0;

    @ConfigEntry.Category("General")
    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.BoundedDiscrete(min = 0, max = 50)
    public int verticalOffset = 0;

    @ConfigEntry.Category("General")
    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.BoundedDiscrete(min = 0, max = 255)
    public int backgroundOpacity = 128;

    @ConfigEntry.Category("General")
    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.ColorPicker
    public int backgroundColour = 0x000000;

    @ConfigEntry.Category("Icon")
    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
    public IconStyle iconStyle = IconStyle.RESOURCE_PACK;

    @ConfigEntry.Category("Icon")
    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
    public IconSize iconSize = IconSize.MEDIUM;

    @ConfigEntry.Category("Icon")
    @ConfigEntry.Gui.Tooltip
    public boolean showSunInDay = false;

    @ConfigEntry.Category("Icon")
    @ConfigEntry.Gui.Tooltip
    public boolean zoomIcon = false;

    @ConfigEntry.Category("Phantom")
    @ConfigEntry.Gui.Tooltip
    public boolean showPhantomStats = true;

    @ConfigEntry.Category("Phantom")
    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.BoundedDiscrete(min = 1, max = 5)
    public int statsPollIntervalSeconds = 1;
}