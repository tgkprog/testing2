package org.s2n.ddt.test;

import java.math.BigDecimal;

import org.s2n.ddt.pojo.def.TestResultTmp;

public class ResultTmpMock {
	
	private TestResultTmp testResultTmp;
	
	public  TestResultTmp getTestResultTmp(){
		
		testResultTmp.setAgentName("EXIMR-IM-415");
		testResultTmp.setPlanId(new BigDecimal(33333));
		testResultTmp.setRunName("Run1");
		testResultTmp.setScenarioId(new BigDecimal(111));
		testResultTmp.setSuiteId(new BigDecimal(1));
		//testResultTmp.setCaseId();
		return null;
	}
	
	
}
