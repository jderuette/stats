package fr.gamedev.stats;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.security.InvalidParameterException;

import org.junit.Test;

import fr.gamedev.stats.fixedSizeCoeficient.FscRule;
import fr.gamedev.stats.fixedSizeCoeficient.FscSlice;
import fr.gamedev.stats.fixedSizeCoeficient.exception.InvalidRuleException;

/**
 * Test the FscRule DTO.
 * @author djer13
 */
public class FscRuleTest {

    @Test
    public void testFromStringValidLinear() {
        String fcsRuleDescription = "[linear test] FSC[(500)(up)(accumulated_points-*)i-1]";
        FscRule rule = FscRule.fromString(fcsRuleDescription);

        assertNotNull("fscRule not initialized !", rule);
        assertNotNull("Name not reteived from String !", rule.getName());
        assertNotNull("First time bonus not reteived from String !", rule.getFirstTimeBonnus());
        assertNotNull("Rounfing mode not reteived from String !", rule.getRoundMode());
        assertNotNull("Operator not reteived from String !", rule.getOperator());
        assertNotNull("Slices not reteived from String !", rule.getSlices());

        assertEquals("Rule's name is not correct", "linear test", rule.getName());
        assertEquals("Rule's first time bonnus is not correct", 500, rule.getFirstTimeBonnus());
        assertEquals("Rule's rounding mode is not correct", RoundingMode.UP, rule.getRoundMode());
        assertEquals("Rule's operator is not correct", Operator.multiply, rule.getOperator());
        assertEquals("Rule's number of slices is not correct", 1, rule.getSlices().size());

        FscSlice firstSlice = rule.getSlices().first();

        assertEquals("Rule's first slice upper bound is not correct", Integer.MAX_VALUE, firstSlice.getUpperBound());
        assertEquals("Rule's first slice wight is not correct", 1f, firstSlice.getWeight(), 0);

    }

    @Test(expected = InvalidParameterException.class)
    public void testFromStringInvalidMissingName() {
        String fcsRuleDescription = "FSC[(500)(up)(accumulated_points-*)i-1]";
        FscRule rule = FscRule.fromString(fcsRuleDescription);
        assertNull("Rules is not valid, should throw an exception", rule);
    }

    @Test(expected = InvalidParameterException.class)
    public void testFromStringInvalidMissingBonnus() {
        String fcsRuleDescription = "[linear no bonus] FSC[(up)(accumulated_points-*)i-1]";
        FscRule rule = FscRule.fromString(fcsRuleDescription);

        assertNull("Rules is not valid, should throw an exception", rule);
    }

    @Test(expected = InvalidParameterException.class)
    public void testFromStringInvalidMissingRoundingMode() {
        String fcsRuleDescription = "[linear no rounding mode] FSC[(500)(accumulated_points-*)i-1]";
        FscRule rule = FscRule.fromString(fcsRuleDescription);

        assertNull("Rules is not valid, should throw an exception", rule);
    }

    @Test(expected = InvalidParameterException.class)
    public void testFromStringInvalidMissingDataSource() {
        String fcsRuleDescription = "[linear no dataSource] FSC[(500)(up)(*)i-1]";
        FscRule rule = FscRule.fromString(fcsRuleDescription);

        assertNull("Rules is not valid, should throw an exception", rule);
    }

    @Test(expected = InvalidParameterException.class)
    public void testFromStringInvalidMissingOperator() {
        String fcsRuleDescription = "[linear no Operator] FSC[(500)(up)(accumulated_points)i-1]";
        FscRule rule = FscRule.fromString(fcsRuleDescription);

        assertNull("Rules is not valid, should throw an exception", rule);
    }

    @Test(expected = InvalidRuleException.class)
    public void testFromStringInvalidMissingSlices() {
        String fcsRuleDescription = "[linear test] FSC[(500)(up)(accumulated_points-*)i-1]";
        FscRule rule = FscRule.fromString(fcsRuleDescription);

        assertNull("Rules is not valid, should throw an exception", rule);
    }

    @Test
    public void testFromStringValidComplexe() {
        String fcsRuleDescription = "[Accumulated Points : Fixed step coefficient plus firstTime bonus rounded up (accumulated points)] FSC[(100)(up)(accumulated_points-*)500-2|1000-1.5|2000-1|5000-0.75|10000-0.5|50000-0.25|i-0.01]";
        FscRule rule = FscRule.fromString(fcsRuleDescription);

        assertNotNull("fscRule not initialized !", rule);
        assertNotNull("Name not reteived from String !", rule.getName());
        assertNotNull("First time bonus not reteived from String !", rule.getFirstTimeBonnus());
        assertNotNull("Rounfing mode not reteived from String !", rule.getRoundMode());
        assertNotNull("Operator not reteived from String !", rule.getOperator());
        assertNotNull("Slices not reteived from String !", rule.getSlices());

        assertEquals("Rule's name is not correct",
                "Accumulated Points : Fixed step coefficient plus firstTime bonus rounded up (accumulated points)",
                rule.getName());
        assertEquals("Rule's first time bonnus is not correct", 100, rule.getFirstTimeBonnus());
        assertEquals("Rule's rounding mode is not correct", RoundingMode.UP, rule.getRoundMode());
        assertEquals("Rule's operator is not correct", Operator.multiply, rule.getOperator());
        assertEquals("Rule's number of slices is not correct", 7, rule.getSlices().size());

        FscSlice firstSlice = rule.getSlices().first();

        assertEquals("Rule's first slice upper bound is not correct", 500, firstSlice.getUpperBound());
        assertEquals("Rule's first slice wight is not correct", 2f, firstSlice.getWeight(), 0);

    }

}
