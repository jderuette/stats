package PointCalculator;

import org.junit.Assert;
import org.junit.Test;

import fr.gamedev.stats.PointCalculator;
import fr.gamedev.stats.fixedSizeCoeficient.FscRule;

public class Fsc {
	@Test
		public void testFsc() {
			PointCalculator instance = PointCalculator.getInstance();
			
			FscRule rules = FscRule.fromString("[Accumulated Points : Fixed step coefficient plus firstTime bonus rounded up (accumulated points)] FSC[(100)(up)(accumulated_points-*)500-2|1000-1.5|2000-1|5000-0.75|10000-0.5|50000-0.25|i-0.01]");
			FscRule rules2 = FscRule.fromString("[Accumulated Points : Fixed step coefficient plus firstTime bonus rounded down (accumulated points)] FSC[(100)(down)(accumulated_points-*)500-2|1000-1.5|2000-1|5000-0.75|10000-0.5|50000-0.25|i-0.01]");
			
			int result = instance.fsc(0, false, (short)0, rules);
			int result2 = instance.fsc(20, true, (short)0, rules2);
			
			Assert.assertEquals(0, result);
			Assert.assertEquals(100, result2);
	}
}
