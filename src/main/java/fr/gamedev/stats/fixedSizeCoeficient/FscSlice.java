package fr.gamedev.stats.fixedSizeCoeficient;

import fr.gamedev.stats.fixedSizeCoeficient.exception.InvalidSliceException;

/**
 * Represent a data slice for Fixed Size Coefficient.
 * 
 * @author djer13
 */
public class FscSlice implements Comparable<FscSlice> {

    int upperBound;
    float weight;

    /**
     * @param upperBound
     * @param weight
     */
    public FscSlice(int anUpperBound, float aWeight) {
        super();
        this.upperBound = anUpperBound;
        this.weight = aWeight;
    }

    /** Expected format : 100-1.5 OR i-0.75*/
    public static FscSlice fromString(String sliceString) {
        if (null == sliceString || sliceString.isEmpty()) {
            throw new InvalidSliceException(
                    "Slice data '" + sliceString + "' is not valid, exepected {integer}-{float}");
        }
        String[] stepData = sliceString.split("-");

        if (stepData.length != 2) {
            throw new InvalidSliceException("Slice data '" + sliceString + "' is not valid, should contain one '-'");
        }

        int upperBound;

        if ("i".equals(stepData[0])) {
            upperBound = Integer.MAX_VALUE;
        } else {
            upperBound = Integer.valueOf(stepData[0]);
        }

        float weight = Float.valueOf(stepData[1]);

        return new FscSlice(upperBound, weight);
    }

    @Override
    public int compareTo(FscSlice other) {
        if (this.upperBound > other.getUpperBound()) {
            return 1;
        } else if (this.upperBound < other.getUpperBound()) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + upperBound;
        result = prime * result + Float.floatToIntBits(weight);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FscSlice other = (FscSlice) obj;
        if (upperBound != other.upperBound)
            return false;
        if (Float.floatToIntBits(weight) != Float.floatToIntBits(other.weight))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "FscSlice [upperBound=" + upperBound + ", weight=" + weight + "]";
    }

    /**
     * @return the upperBound
     */
    public int getUpperBound() {
        return upperBound;
    }

    /**
     * @return the weight
     */
    public float getWeight() {
        return weight;
    }

}
