package io.github.tomtom2211.tomsaddons.utils;

public class Formatting {
    public static String shortFormat (long value) {
        if (value >= 1_000_000_000) return String.format("%.1fB", value / 1_000_000_000.0);
        if (value >= 1_000_000) return String.format("%.1fM", value / 1_000_000.0);
        if (value >= 1_000) return String.format("%.1fK", value / 1_000.0);
        return String.valueOf(value);
    }
}
