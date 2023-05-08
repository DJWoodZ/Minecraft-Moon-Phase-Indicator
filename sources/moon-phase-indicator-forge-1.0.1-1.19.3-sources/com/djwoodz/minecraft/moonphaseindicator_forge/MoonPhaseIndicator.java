package com.djwoodz.minecraft.moonphaseindicator_forge;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod(MoonPhaseIndicator.MOD_ID)
public class MoonPhaseIndicator {
    public static final String MOD_ID = "moonphaseindicator";
    public static final Logger LOGGER = LogUtils.getLogger();

    public MoonPhaseIndicator() {
        MinecraftForge.EVENT_BUS.register(this);

        LOGGER.info("Initialised");
    }
}
