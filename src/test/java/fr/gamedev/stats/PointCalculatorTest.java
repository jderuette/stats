package fr.gamedev.stats;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.gamedev.stats.fixedSizeCoeficient.FscRule;

public class PointCalculatorTest {

    /** [linear] FSC[(500)(up)(accumulated_points-*)i-1].*/
    private static FscRule LINEAR_SLICES;

    /** [Accumulated Points : Fixed step coefficient plus firstTime bonus rounded up (accumulated points)] FSC[(100)(up)(accumulated_points-*)500-2|1000-1.5|2000-1|5000-0.75|10000-0.5|50000-0.25|i-0.01] */
    private static FscRule EXAMPLE_ONE_SLICE;

    /**[nb repeated action : Fixed step coefficient plus firstTime bonus rounded down (repetition)] : 
     *      FSC[(5)(down)(repetition-*)10-1|50-0.75|100-0.5|i-0.001].*/
    private static FscRule EXAMPLE_TWO_SLICE;

    @BeforeClass
    public static void beforeClass() {
        LINEAR_SLICES = FscRule.fromString("[linear] FSC[(500)(up)(accumulated_points-*)i-1]");
        EXAMPLE_ONE_SLICE = FscRule.fromString(
                "[Accumulated Points : Fixed step coefficient plus firstTime bonus rounded up (accumulated points)] FSC[(100)(up)(accumulated_points-*)500-2|1000-1.5|2000-1|5000-0.75|10000-0.5|50000-0.25|i-0.01]");
        EXAMPLE_TWO_SLICE = FscRule.fromString(
                "[nb repeated action : Fixed step coefficient plus firstTime bonus rounded down (repetition)] FSC[(5)(down)(repetition-*)10-1|50-0.75|100-0.5|i-0.001]");
    }

    @Test
    public void testFscExampleOne() {
        short basePoints = (short) 50;
        //Case A
        int result = PointCalculator.getInstance().fsc(3158, false, basePoints, EXAMPLE_ONE_SLICE);
        Assert.assertEquals("Bad result case A", 38, result);

        //Case B
        result = PointCalculator.getInstance().fsc(0, true, basePoints, EXAMPLE_ONE_SLICE);
        Assert.assertEquals("Bad result for case B", 200, result);

        //Case C
        result = PointCalculator.getInstance().fsc(12571, false, basePoints, EXAMPLE_ONE_SLICE);
        Assert.assertEquals("Bad result case C", 13, result);

        //Case D
        result = PointCalculator.getInstance().fsc(378236, false, basePoints, EXAMPLE_ONE_SLICE);
        Assert.assertEquals("Bad result case D", 1, result);

    }

    @Test
    public void testFscExampleTwo() {
        short basePoints = (short) 50;
        //Case A
        int result = PointCalculator.getInstance().fsc(0, true, basePoints, EXAMPLE_TWO_SLICE);
        Assert.assertEquals("Bad result case A", 55, result);

        //Case B
        result = PointCalculator.getInstance().fsc(1, false, basePoints, EXAMPLE_TWO_SLICE);
        Assert.assertEquals("Bad result case B", 50, result);
        //Case C
        result = PointCalculator.getInstance().fsc(100, false, basePoints, EXAMPLE_TWO_SLICE);
        Assert.assertEquals("Bad result case C", 0, result);
        //Case D
        result = PointCalculator.getInstance().fsc(250, false, basePoints, EXAMPLE_TWO_SLICE);
        Assert.assertEquals("Bad result case D", 0, result);

    }

    @Test
    public void testFscExampleThree() {
        short basePoints = (short) 10;
        //case A
        int result = PointCalculator.getInstance().fsc(851, true, basePoints, LINEAR_SLICES);
        Assert.assertEquals("Bad result", 510, result);
    }

}
