package org.s2n.ddt.common;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.Map;

import org.s2n.ddt.pojo.TestSuite;
import org.s2n.ddt.pojo.input.TestCase;
import org.s2n.ddt.pojo.input.TestStep;
import org.s2n.ddt.pojo.output.TestCaseResult;
import org.s2n.ddt.pojo.output.TestPlan;
import org.s2n.ddt.pojo.output.TestStepResult;
import org.s2n.ddt.pojo.output.TestSuiteResultSummary;

public class DdtCoreUtls {

	public static void stepRslt(TestStepResult stepResult, boolean relt, String summary, String detail) {
		stepResult.setResult(relt);
		stepResult.setComment(summary);

		if (detail != null) {
			StringBuilder sb = new StringBuilder();
			String gg = stepResult.getDetailMsgs();
			if (gg != null) {
				sb.append(gg).append("\n");
			}
			sb.append(detail);
			stepResult.setDetailMsgs(sb.toString());
		}
	}

	public static String getStepFirstObjParamValue(TestStep step) {
		String v = step.getTestStepId().getTestParamDatas().get(0).getParam().getParamName();// TODO
		return v;
	}
	
	public static void stepRslt(TestStepResult stepResult, boolean relt, String summary, String detail, String screen) {
		stepRslt( stepResult,  relt,  summary,  detail,  screen,  null);
	}

	public static void stepRslt(TestStepResult stepResult, boolean relt, String summary, String detail, String screen, Throwable e) {
		stepResult.setResult(relt);
		stepResult.setComment(summary);
		if (screen != null) {
			stepResult.setSnapShot(screen);
		}
		StringBuilder sb = new StringBuilder();
		String gg = stepResult.getDetailMsgs();
		if (gg != null && ((detail != null) || (e != null))) {
			sb.append(gg).append("\n");
		}
		if (detail != null) {
			sb.append(detail);
			stepResult.setDetailMsgs(sb.toString());
		}	
		if (e != null) {
			// move to utils TODO
			java.io.ByteArrayOutputStream bis = new ByteArrayOutputStream();
			PrintStream ps = new PrintStream(bis);
			e.printStackTrace(ps);
			String sq = new String(bis.toByteArray());
			sb.append("\nTrace:\n");
			sb.append(sq);
		}
		stepResult.setDetailMsgs(sb.toString());
	}

	public static void stepFillDetail(TestStepResult stepResult, final String msg, Throwable e) {
		final String s = stepResult.getDetailMsgs();
		StringBuilder sb = new StringBuilder();
		boolean nlTrace = false;
		if (s != null) {
			sb.append(s);
			 nlTrace = true;
		}
		if (msg != null) {
			if (s != null) {
				sb.append("\n");
			}
			sb.append(msg);
			nlTrace = true;
		}
		if (e != null) {
			// move to utils TODO
			java.io.ByteArrayOutputStream bis = new ByteArrayOutputStream();
			PrintStream ps = new PrintStream(bis);
			e.printStackTrace(ps);
			String sq = new String(bis.toByteArray());
			if(nlTrace){
				sb.append("\n");
			}
			sb.append("Trace:\n");
			sb.append(sq);
		}
		stepResult.setDetailMsgs(sb.toString());
	}
	
	public void init(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep step, TestStepResult stepResult,
			TestSuiteResultSummary suiteSummaryResult) {
	
	}
	
	public static String fillTestMethods(Class clz, Map<String, Method> methods){
		String rtn = null;
		Class []params = new Class[]{TestPlan.class, TestSuite.class, TestCase.class, TestCaseResult.class, TestStep.class, TestStepResult.class,
				TestSuiteResultSummary.class};
		rtn = org.s2n.ddt.util.ReflectUtils.fillMethods(clz, params, methods);
		return rtn;
	}
}
