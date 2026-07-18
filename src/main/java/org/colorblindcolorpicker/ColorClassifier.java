package org.colorblindcolorpicker;

import java.awt.Color;

public final class ColorClassifier {

    public enum BasicColor {
        BLACK("Black"), WHITE("White"), GRAY("Gray"),

        BROWN("Brown"),

        RED("Red"),
        ORANGE("Orange"),
        YELLOW("Yellow"),
        LIME("Lime"),
        GREEN("Green"),
        TEAL("Teal"),
        CYAN("Cyan"),
        AZURE_BLUE("Azure Blue"),
        BLUE("Blue"),
        VIOLET("Violet"),
        PURPLE("Purple"),
        PINK("Pink");

        private final String name;

        BasicColor(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static BasicColor classify(Color color) {

        float[] hsv = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);

        float hue = hsv[0] * 360f;
        float sat = hsv[1] * 100f;
        float val = hsv[2] * 100f;

        // -----------------------------
        // Neutral colors
        // -----------------------------
        if (val < 15) return BasicColor.BLACK;
        if (sat < 10) {
            if (val > 90) return BasicColor.WHITE;
            return BasicColor.GRAY;
        }

        // -----------------------------
        // Brown
        // -----------------------------
        if (hue >= 15 && hue < 45 && sat > 25 && val >= 20 && val < 65) return BasicColor.BROWN;

        // -----------------------------
        // 30° hue wheel
        // -----------------------------
        if (hue >= 345 || hue < 15) return BasicColor.RED;
        if (hue < 45) return BasicColor.ORANGE;
        if (hue < 75) return BasicColor.YELLOW;
        if (hue < 105) return BasicColor.LIME;
        if (hue < 135) return BasicColor.GREEN;
        if (hue < 165) return BasicColor.TEAL;
        if (hue < 195) return BasicColor.CYAN;
        if (hue < 225) return BasicColor.AZURE_BLUE;
        if (hue < 255) return BasicColor.BLUE;
        if (hue < 285) return BasicColor.VIOLET;
        if (hue < 315) return BasicColor.PURPLE;
        return BasicColor.PINK;
    }
}