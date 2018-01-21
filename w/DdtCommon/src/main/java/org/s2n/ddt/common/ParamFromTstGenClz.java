package org.s2n.ddt.common;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import org.s2n.ddt.pojo.TestSuite;
import org.s2n.ddt.pojo.def.Runner;
import org.s2n.ddt.pojo.input.TestCase;
import org.s2n.ddt.pojo.input.TestStep;
import org.s2n.ddt.pojo.output.TestCaseResult;
import org.s2n.ddt.pojo.output.TestPlan;
import org.s2n.ddt.pojo.output.TestStepResult;
import org.s2n.ddt.pojo.output.TestSuiteResultSummary;

//
public class ParamFromTstGenClz implements Runner {
	//
	private static final Logger logger = Logger.getLogger(ParamFromTstGenClz.class);
	
	
	private static Map<String, Object> beans = new HashMap<String, Object>();
	private final org.s2n.ddt.bean.UtlProps utlProps = new org.s2n.ddt.bean.UtlProps();
	private Object objectRemote = null;
	private Method objectMehod;

	static {
		try {
			//for now in static later via beans and setters
			//init();
		} catch (Throwable e) {
			logger.warn("ParamFromExternalCode static init :" + e, e);
		}
	}

	public void init(String props) {
		if(objectRemote != null) {
			return;
		}
		File filePath = new File(props);
		utlProps.initialize(filePath );
		logger.info(filePath);
		try {
			// utlProps.getProperty("clz", "") + " Method " +  utlProps.getProperty("method", "")
			String clzNm = utlProps.getProperty("clz", "");
			Class c = Class.forName(clzNm);
			String methodName = utlProps.getProperty("method", "");
			objectMehod = c.getMethod(methodName);
			
			objectRemote = c.newInstance();
			Method p = c.getMethod("setProps", Properties.class);
			p.invoke(objectRemote, utlProps.getProps());
		} catch (Throwable e) {
			logger.warn("ParamFromExternalCode init :" + e, e);
		}
		
		
	}
	
	
	

	public void testStep(TestPlan obj, TestSuite suite, TestCase tcase, TestCaseResult caseResult, TestStep step, TestStepResult stepResult,
			TestSuiteResultSummary suiteSumamryResult) {
		
	}
	
	public void testStep(TestPlan obj, TestSuite suite, TestCase tcase, TestCaseResult caseResult, TestStep step, TestStepResult stepResult,
			TestSuiteResultSummary suiteSumamryResult, Map<String, String> putin) {
		String nm = null;
		try {
			
			String path = step.getStepParam();
					
			init(path);
			 nm = utlProps.getProperty("toParam");
			//String v = getStepFirstObjParamValue(step);
			String val = (String) objectMehod.invoke(objectRemote);
			putin.put(nm, val);
			DdtCoreUtls.stepRslt(stepResult, true, "To param :" + nm + ", put :" + val + ".", "");
		} catch (Throwable e) {
			logger.warn("testStep putin  :" + e, e);
			final String err = "param from class err :" + e + ", To param :" + nm + ", from class :" + utlProps.getProperty("clz", "") + ", Method " +  utlProps.getProperty("method", "") + ".";
			DdtCoreUtls.stepFillDetail(stepResult, err, e);

		}
	}
	
	public void startTestPlan(TestPlan plan) {
		
		
	}
	
	public void startTestSuite(TestPlan plan, TestSuite suite, TestSuiteResultSummary tstSmmryFromRemote) {
		
		
	}

	
	public void startTestCase(TestPlan plan, TestSuite suite, TestSuiteResultSummary suiteSumamryResult) {
		
		
	}

	

	
	public void endTestCase(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStepResult lastStep) {
		
		
	}

	
	public String getConfigPath() {
		
		return null;
	}

	
	public void setConfigPath(String configPath) {
		
		
	}

	
	public Runner clone2() throws CloneNotSupportedException {
		
		return null;
	}
	
	public static String getStepFirstObjParamValue(TestStep step) {
		String v = step.getTestStepId().getTestParamDatas().get(0).getParam().getParamName();// TODO
		return v;
	}
	
	public static void main(String[] args) {
		String s = "/data/ddt/config/grx/generate/deviceId_gen.pro";
	}

}
