package io.github.tomtom2211.tomsaddons.utils;

public class ColorUtils {
    public static float hexToRGBA(int hex, String color) {
        return switch (color) {
            case "red" -> ((hex >> 16) & 0xFF) / 255.0f;
            case "green" -> ((hex >> 8) & 0xFF) / 255.0f;
            case "blue" -> (hex & 0xFF) / 255.0f;
            case "alpha" -> 1.0f;
            default -> 0.0f;
        };
    }
}
