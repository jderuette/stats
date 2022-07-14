package fr.gamedev.stats;

import java.util.Set;
import java.util.SortedSet;

import fr.gamedev.stats.fixedSizeCoeficient.FscRule;
import fr.gamedev.stats.fixedSizeCoeficient.FscSlice;

/**
 * Allow to calculate earned points based on rules.
 * 
 * @author djer13
 */
public class PointCalculator {
    private static PointCalculator INSTANCE = new PointCalculator();

    /**
     * Singleton ==> Hide constructor
     */
    private PointCalculator() {

    }

    public static PointCalculator getInstance() {
        return INSTANCE;
    }

    public int fsc(int dataSource, boolean isFirstTime, short basePoints, FscRule afscRule) {
        return fsc(dataSource, isFirstTime, basePoints, afscRule.getFirstTimeBonnus(), afscRule.getRoundMode(),
                afscRule.getOperator(), afscRule.getSlices());
    }

    private int fsc(int dataSource, boolean isFirstTime, short basePoints, short firstTimeBonus, RoundingMode roundMode,
            Operator operator, SortedSet<FscSlice> slices) {

        FscSlice sliceFound = getValidSlice(slices, dataSource);
        
        double result = operator.apply(basePoints, sliceFound.getWeight());
        
        if(isFirstTime) {
        	result += firstTimeBonus;
        }
        
        int resultat = 0;
        
        if(roundMode.equals(RoundingMode.UP)) {
        	resultat = (int)Math.ceil(result);
        } else if(roundMode.equals(RoundingMode.DOWN)) {
        	resultat = (int)Math.floor(result);
        }

        return resultat;
    }

    private FscSlice getValidSlice(Set<FscSlice> slices, int value) {
        FscSlice sliceFound = null;
        for (FscSlice slice : slices) {
            if (value < slice.getUpperBound()) {
                sliceFound = slice;
                break;
            }
        }

        return sliceFound;
    }

}
