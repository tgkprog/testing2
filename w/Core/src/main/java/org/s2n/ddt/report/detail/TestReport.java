package org.s2n.ddt.report.detail;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

import org.s2n.ddt.pojo.output.TestPlanId;

public class TestReport {

	public static void main(String arg[]) throws ResourceNotFoundException, ParseErrorException, Exception{
		
		TestPlanId testPlanId = new TestPlanId();
		testPlanId.setTestPlanId(new BigDecimal(33333));
		Reports rp = new Reports();
		
		//rp.planEnd(testPlanId);
		System.out.println("done");
		
		
	}
}
