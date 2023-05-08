package com.djwoodz.minecraft.moonphaseindicator_forge.client;

import com.djwoodz.minecraft.moonphaseindicator_forge.MoonPhaseIndicator;

import com.mojang.blaze3d.platform.NativeImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.ResourceLocation;

public class DynamicTextureIdentifierProvider {
    private static ResourceLocation getTextureIdentifier(Class<?> c, String basePath, String idPrefix, Minecraft client, String path) throws IOException, URISyntaxException {
        if (client == null) {
            throw new NullPointerException("client is null");
        }
        InputStream is = null;
        try {
            TextureManager textureManager = client.getTextureManager();
            String fullPath = basePath + path;
            byte[] bytes = Files.readAllBytes(Paths.get(c.getResource(fullPath).toURI()));
            is = new ByteArrayInputStream(bytes);
            NativeImage ni = NativeImage.read(is);
            return textureManager.register(MoonPhaseIndicator.MOD_ID + "/" + idPrefix + fullPath, new DynamicTexture(ni));
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public static ResourceLocation getDefaultTextureIdentifier(String path) throws IOException, URISyntaxException {
        return getDefaultTextureIdentifier(Minecraft.getInstance(), path);
    }

    public static ResourceLocation getDefaultTextureIdentifier(Minecraft client, String path) throws IOException, URISyntaxException {
        return getTextureIdentifier(Minecraft.class, "/assets/minecraft/", "default", client, path);
    }
    
    public static ResourceLocation getModTextureIdentifier(String path) throws IOException, URISyntaxException {
        return getModTextureIdentifier(Minecraft.getInstance(), path);
    }

    public static ResourceLocation getModTextureIdentifier(Minecraft client, String path) throws IOException, URISyntaxException {
        return getTextureIdentifier(MoonPhaseIndicator.class, "/assets/moonphaseindicator/", MoonPhaseIndicator.MOD_ID, client, path);
    }
}
