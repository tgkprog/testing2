package org.s2n.ddt.grxHelpers;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import org.s2n.ddt.bean.UtlProps;
import org.s2n.ddt.common.Runner2;
import org.s2n.ddt.common.DdtCoreUtls;
import org.s2n.ddt.common.share.CommonInformation;
import org.s2n.ddt.pojo.TestSuite;
import org.s2n.ddt.pojo.def.Runner;
import org.s2n.ddt.pojo.def.RunnerAdapter;
import org.s2n.ddt.pojo.input.TestCase;
import org.s2n.ddt.pojo.input.TestStep;
import org.s2n.ddt.pojo.output.TestCaseResult;
import org.s2n.ddt.pojo.output.TestPlan;
import org.s2n.ddt.pojo.output.TestStepResult;
import org.s2n.ddt.pojo.output.TestSuiteResultSummary;

public class DeviceIdRunner extends RunnerAdapter implements Runner, Runner2 {
	//
	private static final Logger logger = Logger.getLogger(DeviceIdRunner.class);
	private static String firstPropsPath;
	private static UtlProps firstProps;
	private CommonInformation ci;
	//private org.s2n.ddt.grxHelpers.DeviceIdGenerator dig = new DeviceIdGenerator();
	 
	private Map<String, IdGen> digs = new HashMap<String, IdGen>(1);


	public void testStep(TestPlan obj, TestSuite suite, TestCase tcase, TestCaseResult caseResult, TestStep step, TestStepResult stepResult,
			TestSuiteResultSummary suiteSumamryResult) {
		genOne(obj, suite, tcase, caseResult, step, stepResult, suiteSumamryResult);
	}

//DivIdSimpleRange
	
	public void genOne(TestPlan obj, TestSuite suite, TestCase tcase, TestCaseResult caseResult, TestStep step, TestStepResult stepResult,
			TestSuiteResultSummary suiteSumamryResult) {
		String detail = "did start";
		String path = step.getStepParam();
		String summary = "";
		UtlProps props;
		if(path.equals(firstPropsPath) && firstProps != null){
			props = firstProps;
		}else {
			firstPropsPath = path;
			props = new UtlProps();
			props.initialize(new File(path));
			firstProps = props;
		}
		String clz = props.getProperty("clz", null);
		IdGen dig = digs.get(clz);
		if(dig == null){
			try {
				dig = (IdGen) Class.forName(clz).newInstance();
				dig.init(props);
				digs.put(clz, dig);
			} catch (Throwable e) {
				detail = "Could not init Device id geb :" + clz + ", " + e;
				logger.error(detail, e);
				DdtCoreUtls.stepRslt(stepResult, false, summary, detail, null);
				return;
			}
		}
		String did = dig.getOne();
		String key = props.getProperty("toParam", "deviceId");
		String ss = "Did :" + did + ", key " + key + ".";
		logger.trace(ss);
		ci.setParamValue(key, did);
		detail = ss;
		DdtCoreUtls.stepRslt(stepResult, true, summary, detail, null);
	}

	public void setCommonInformationProvider(CommonInformation ci) {
		this.ci = ci;
		
	}


	public void setRunnersInformation(Map<String, Runner> runners) {
		// TODO Auto-generated method stub
		
	}

}
