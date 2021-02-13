package fr.gamedev.stats;

import java.util.Arrays;

/**
 * 
 * @author djer13
 */
public enum RoundingMode {
    UP("up"), DOWN("down");

    private String text;

    private RoundingMode(String aText) {
        this.text = aText;
    }

    public static RoundingMode from(String strMode) {
        return Arrays.stream(values()).filter(o -> o.text.equals(strMode)).findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
