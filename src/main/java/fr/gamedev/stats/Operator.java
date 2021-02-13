package fr.gamedev.stats;

import java.security.InvalidParameterException;
import java.util.Arrays;

/**
 * 
 * @author djer13
 */
public enum Operator {
    plus('+') {
        @Override
        public double apply(double v1, double v2) {
            return v1 + v2;
        }
    },
    minus('-') {
        @Override
        public double apply(double v1, double v2) {
            return v1 - v2;
        }
    },
    multiply('*') {
        @Override
        public double apply(double v1, double v2) {
            return v1 * v2;
        }
    },
    divide('/') {
        @Override
        public double apply(double v1, double v2) {
            return v1 / v2;
        }
    };

    private final char text;

    private Operator(char text) {
        this.text = text;
    }

    public static Operator from(String str) {
        if (null == str || str.length() != 1) {
            throw new InvalidParameterException("String : '" + str + "' should contain only one character");
        }
        return from(str.charAt(0));
    }

    public static Operator from(char aChar) {
        return Arrays.stream(values()).filter(o -> o.text == aChar).findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public abstract double apply(double x1, double x2);

    @Override
    public String toString() {
        return Character.toString(text);
    }
}
