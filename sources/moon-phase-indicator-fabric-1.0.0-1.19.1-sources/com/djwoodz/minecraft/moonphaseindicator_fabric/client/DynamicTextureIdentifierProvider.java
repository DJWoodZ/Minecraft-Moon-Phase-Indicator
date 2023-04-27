package com.djwoodz.minecraft.moonphaseindicator_fabric.client;

import com.djwoodz.minecraft.moonphaseindicator_fabric.MoonPhaseIndicator;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import net.minecraft.class_1011;
import net.minecraft.class_1043;
import net.minecraft.class_1060;
import net.minecraft.class_2960;
import net.minecraft.class_310;

public class DynamicTextureIdentifierProvider {
    private static class_2960 getTextureIdentifier(Class<?> c, String basePath, String idPrefix, class_310 client, String path) throws IOException, URISyntaxException {
        if (client == null) {
            throw new NullPointerException("client is null");
        }
        InputStream is = null;
        try {
            class_1060 textureManager = client.method_1531();
            String fullPath = basePath + path;
            byte[] bytes = Files.readAllBytes(Paths.get(c.getResource(fullPath).toURI()));
            is = new ByteArrayInputStream(bytes);
            class_1011 ni = class_1011.method_4309(is);
            return textureManager.method_4617(MoonPhaseIndicator.MOD_ID + "/" + idPrefix + fullPath, new class_1043(ni));
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public static class_2960 getDefaultTextureIdentifier(String path) throws IOException, URISyntaxException {
        return getDefaultTextureIdentifier(class_310.method_1551(), path);
    }

    public static class_2960 getDefaultTextureIdentifier(class_310 client, String path) throws IOException, URISyntaxException {
        return getTextureIdentifier(class_310.class, "/assets/minecraft/", "default", client, path);
    }
    
    public static class_2960 getModTextureIdentifier(String path) throws IOException, URISyntaxException {
        return getModTextureIdentifier(class_310.method_1551(), path);
    }

    public static class_2960 getModTextureIdentifier(class_310 client, String path) throws IOException, URISyntaxException {
        return getTextureIdentifier(MoonPhaseIndicator.class, "/assets/moonphaseindicator/", MoonPhaseIndicator.MOD_ID, client, path);
    }
}
