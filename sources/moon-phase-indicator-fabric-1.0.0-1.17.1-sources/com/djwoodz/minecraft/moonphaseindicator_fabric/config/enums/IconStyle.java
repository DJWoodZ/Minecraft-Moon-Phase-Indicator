package com.djwoodz.minecraft.moonphaseindicator_fabric.config.enums;

import com.djwoodz.minecraft.moonphaseindicator_fabric.MoonPhaseIndicator;

public enum IconStyle {
    RESOURCE_PACK("resourcePack"),
    DEFAULT("default"),
    EMOJI("emoji");

    private final String label;
    private final String KEY_PREFIX = "text.autoconfig." + MoonPhaseIndicator.MOD_ID + ".option.iconStyle.";

    private IconStyle(String label) {
        this.label = KEY_PREFIX + label;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
