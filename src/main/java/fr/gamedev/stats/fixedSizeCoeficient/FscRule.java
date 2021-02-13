package fr.gamedev.stats.fixedSizeCoeficient;

import java.security.InvalidParameterException;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.gamedev.stats.Operator;
import fr.gamedev.stats.RoundingMode;
import fr.gamedev.stats.fixedSizeCoeficient.exception.InvalidRuleException;
import fr.gamedev.stats.fixedSizeCoeficient.exception.InvalidSliceException;

/**
 * Holds data for a Fixed Size Step Coefficient
 *
 * @author djer13.
 */
public class FscRule {

    private static final String FSC_RULE_PATTERN_STRING = "\\[(?<ruleName>.*)\\] FSC\\[\\((?<firstTimeBonus>\\d*)\\)\\((?<roundingMode>.*)\\)\\((?<dataSource>.*)-(?<operator>.*)\\)(?<slicesData>.*)\\]";
    private static final Pattern FSC_RULE_PATTERN = Pattern.compile(FSC_RULE_PATTERN_STRING);

    private String name;
    private short firstTimeBonnus;
    private RoundingMode roundMode;
    private Operator operator;
    private SortedSet<FscSlice> slices;

    /**
     * All data for a Fixed Step Coefficient rule.
     * @param aName
     * @param aBasePoints
     * @param aFirstTimeBonnus
     * @param aRoundMode
     * @param anOperator
     * @param allSlices
     */
    public FscRule(String aName, short aFirstTimeBonnus, RoundingMode aRoundMode, Operator anOperator,
            SortedSet<FscSlice> allSlices) {
        super();
        this.name = aName;
        this.firstTimeBonnus = aFirstTimeBonnus;
        this.roundMode = aRoundMode;
        this.operator = anOperator;
        this.slices = allSlices;
    }

    public static FscRule fromString(String fscRuleString) {
        Matcher matcher = FSC_RULE_PATTERN.matcher(fscRuleString);
        boolean match = matcher.matches();

        if (!match) {
            throw new InvalidParameterException("Invalid rules definition : '" + fscRuleString
                    + "' dosen't match the pattern : " + FSC_RULE_PATTERN_STRING);
        }
        String name = matcher.group("ruleName");
        short firstTimeBonnus = Short.valueOf(matcher.group("firstTimeBonus"));
        RoundingMode roundMode = RoundingMode.from(matcher.group("roundingMode"));
        String dataSource = matcher.group("dataSource");
        Operator operator = Operator.from(matcher.group("operator"));

        String slicesData = matcher.group("slicesData");

        String[] stepsData;
        if (slicesData.contains("|")) {
            stepsData = slicesData.split("\\|");
        } else {
            stepsData = new String[1];
            stepsData[0] = slicesData;
        }

        SortedSet<FscSlice> slices = new TreeSet<FscSlice>();

        for (String sliceData : stepsData) {
            try {
                slices.add(FscSlice.fromString(sliceData));
            } catch (InvalidSliceException ise) {
                throw new InvalidRuleException("Error creating FSC rule from String '" + fscRuleString
                        + "' while analysing slcices '" + slicesData + "' fro the specific slice '" + sliceData + "'",
                        ise);
            }
        }

        return new FscRule(name, firstTimeBonnus, roundMode, operator, slices);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the firstTimeBonnus
     */
    public short getFirstTimeBonnus() {
        return firstTimeBonnus;
    }

    /**
     * @return the roundMode
     */
    public RoundingMode getRoundMode() {
        return roundMode;
    }

    /**
     * @return the operator
     */
    public Operator getOperator() {
        return operator;
    }

    /**
     * @return the slices
     */
    public SortedSet<FscSlice> getSlices() {
        return slices;
    }

}
