package fr.gamedev.stats.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.gamedev.stats.PointCalculator;
import fr.gamedev.stats.fixedSizeCoeficient.FscRule;

@RestController
public class FscController {
	
	FscRule rule1 = FscRule.fromString( "[Accumulated Points : Fixed step coefficient plus firstTime bonus rounded up (accumulated points)] FSC[(100)(up)(accumulated_points-*)500-2|1000-1.5|2000-1|5000-0.75|10000-0.5|50000-0.25|i-0.01]");
	FscRule rule2 = FscRule.fromString( "[Test 2] FSC[(10000)(up)(accumulated_points-*)500-2|1000-1.5|2000-1|5000-0.75|10000-0.5|50000-0.25|i-0.01]");
	

	@RequestMapping("/stats/fsc")
	public int CalculPointFsc(@RequestParam() int currentPoint, boolean firstTime, String ruleName) {
		
		FscRule rule;
		
		if(ruleName.equals("r1")) {
			rule = rule1;
		}else {
			rule = rule2;
		}
		
		int res = PointCalculator.getInstance().fsc(currentPoint, firstTime, (short)10, rule);
		return res;
	}
}
