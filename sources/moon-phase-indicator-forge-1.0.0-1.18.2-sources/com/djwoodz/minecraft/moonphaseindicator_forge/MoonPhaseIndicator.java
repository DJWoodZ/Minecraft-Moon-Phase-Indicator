package com.djwoodz.minecraft.moonphaseindicator_forge;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(MoonPhaseIndicator.MOD_ID)
public class MoonPhaseIndicator {
    public static final String MOD_ID = "moonphaseindicator";
    public static final Logger LOGGER = LogManager.getLogger();

    public MoonPhaseIndicator() {
        MinecraftForge.EVENT_BUS.register(this);

        LOGGER.info("Initialised");
    }
}
