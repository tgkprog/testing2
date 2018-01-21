package org.s2n.ddt.runner.http;

import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import org.s2n.ddt.bean.UtlConf;
import org.s2n.ddt.common.ParamFromTstGenClz;
import org.s2n.ddt.common.Runner2;
import org.s2n.ddt.common.DdtCoreUtls;
import org.s2n.ddt.common.share.CommonInformation;
import org.s2n.ddt.pojo.TestSuite;
import org.s2n.ddt.pojo.def.Runner;
import org.s2n.ddt.pojo.input.TestCase;
import org.s2n.ddt.pojo.input.TestStep;
import org.s2n.ddt.pojo.output.TestCaseResult;
import org.s2n.ddt.pojo.output.TestPlan;
import org.s2n.ddt.pojo.output.TestStepResult;
import org.s2n.ddt.pojo.output.TestSuiteResultSummary;
import org.s2n.ddt.util.LangUtils;
import org.s2n.ddt.util.http.HttpData;
import org.s2n.ddt.util.http.NetSend;
import org.s2n.ddt.util.https.UtlSslFactory;

public class HttpRunner implements Runner, Runner2 {
	//
	private static final Logger logger = Logger.getLogger(HttpRunner.class);

	// do we need this to be volatile or even needs this or instead go to class
	// every time
	// do not want to use switch statement as there are more than 10 actions and
	// that will take same time as a map look up. plus using map means we can
	// maintain code easier.
	private static volatile Map<String, Method> methods = new HashMap<String, Method>(20);
	private static volatile Map<String, String> savedGlobalStrs = new HashMap<String, String>();

	private static int httpLib;

	private String template;
	private final Map<String, String> replaceStrs = new HashMap<String, String>();

	private HttpEntityEnclosingRequestBase httpReqBase;
	private HttpClient httpClient;
	private HttpContext localContext;
	private HttpResponse response;

	private final String method = "POST";// TODO
	private ParamFromTstGenClz paramFromExt = null;

	// Agent2 agent2 = new Agent2();
	private final ProtocolVersion httpVer = HttpVersion.HTTP_1_1;

	private String responseStr;

	private final Map<String, String> savedStrs = new HashMap<String, String>();

	private CommonInformation ci;
	
	static{
		DdtCoreUtls.fillTestMethods(HttpRunner.class, methods);
	}

	
	public void startTestPlan(TestPlan obj) {

	}

	
	public void startTestSuite(TestPlan obj, TestSuite suite, TestSuiteResultSummary tstSmmryFromRemote) {

	}

	
	public void startTestCase(TestPlan obj, TestSuite suite, TestSuiteResultSummary tstSmmryFromCache) {
		replaceStrs.clear();

	}

	
	public void testStep(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep step, TestStepResult stepResult,
			TestSuiteResultSummary suiteSummaryResult) {
		stepResult.setResult(false);// make it true in each method if it passes
		final String action = step.getActions().getActionName();
		final Method actionMethd = methods.get(action);
		caseResult.setValidationByAgent(true);
		// earlier this also was in sync bloc - bad.
		if (actionMethd != null) {
			try {
				logger.info("Action :" + action);
				actionMethd.invoke(this, plan, suite, testCase, caseResult, step, stepResult, suiteSummaryResult);
			} catch (Exception e) {
				logger.warn("Calling action Err :" + e, e);
				stepResult.setDetailMsgs("Action - ERR :" + e);
			}
		}else{
			stepResult.setDetailMsgs("Action not found :" + action + ".");
		}

	}

	public void endTestCase(TestPlan obj, TestSuite suite, TestCase testCase, TestSuiteResultSummary tstSmmryFromCache, TestStepResult testStep) {

	}

	
	public String getConfigPath() {

		return null;
	}

	
	public void setConfigPath(String configPath) {

	}

	// init(org.s2n.ddt.pojo.output.TestPlan,
	// org.s2n.ddt.pojo.TestSuite, org.s2n.ddt.pojo.input.TestCase,
	// org.s2n.ddt.pojo.input.TestStep
	// , org.s2n.ddt.pojo.output.TestStepResult,
	// org.s2n.ddt.pojo.output.TestSuiteResultSummary
	// TODO ?
	public void init(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep step, TestStepResult stepResult,
			TestSuiteResultSummary suiteSummaryResult) {
		httpReqBase = new HttpPost();
		DdtCoreUtls.stepRslt(stepResult, true, "", null);

	}

	//

	public void initContext(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep step, TestStepResult stepResult,
			TestSuiteResultSummary suiteSummaryResult) {
		localContext = new BasicHttpContext();
		DdtCoreUtls.stepRslt(stepResult, true, "", null);
	}

	// TODO reset cookies
	public void cookieJarInit(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep step, TestStepResult stepResult,
			TestSuiteResultSummary suiteSummaryResult) {
		// CookieStore cookieStore = new BasicClientCookie(
		// httpclient.setCookieStore(cookieStore);
		DdtCoreUtls.stepRslt(stepResult, true, "", null);
	}

	// /** do not call, just to remove IDE warnings without annotation */
	// void ignoreMe() {
	// initSsl(null, null, null, null, null, null);
	// initContext(null, null, null, null, null, null);
	// initContext(null, null, null, null, null, null);
	// cookieJarInit(null, null, null, null, null, null);
	// init(null, null, null, null, null, null);
	// submitReq(null, null, null, null, null, null);
	// parameter(null, null, null, null, null, null);
	// template(null, null, null, null, null, null);
	// }

	public void noop(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep step, TestStepResult stepResult,
			TestSuiteResultSummary suiteSummaryResult) {

		try {
			DdtCoreUtls.stepRslt(stepResult, true, "", null);
		} catch (Exception e) {
			logger.warn("noop:" + e, e);// stepResult.setDetailMsgs
			org.s2n.ddt.common.DdtCoreUtls.stepFillDetail(stepResult, "noop err :" + e, e);

		}

	}

	// TODO not yet implemented
	public void cookiePut(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep step, TestStepResult stepResult,
			TestSuiteResultSummary suiteSummaryResult) {
		try {
			DdtCoreUtls.stepRslt(stepResult, true, "", null);
		} catch (Exception e) {
			logger.warn("cookiePut :" + e, e);// stepResult.setDetailMsgs
			org.s2n.ddt.common.DdtCoreUtls.stepFillDetail(stepResult, "cookiePut err :" + e, e);

		}

	}

	// TODO
	public void headerPut(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep step, TestStepResult stepResult,
			TestSuiteResultSummary suiteSummaryResult) {
		try {
			final int tagSplLen = 4;
			String name = step.getStepParam();
			String v = getStepFirstObjParamValue(step);
			if ((v == null) || (name == null)) {
				org.s2n.ddt.common.DdtCoreUtls.stepFillDetail(stepResult, "header put null value or name.", null);
			}
			int startX = v.indexOf("{|--");
			int endX = -1;
			if (startX > -1) {
				endX = v.indexOf("--|}", startX + tagSplLen);
			}

			while (endX > -1) {
				String key = v.substring(startX + tagSplLen, endX);
				String vv = replaceStrs.get(key);
				v = v.substring(0, startX) + vv + v.substring(endX + tagSplLen);
				startX = v.indexOf("{|--", endX + tagSplLen);
				if (startX > -1) {
					endX = v.indexOf("--|}", startX + tagSplLen);
				} else {
					endX = -1;
				}
			}
			logger.info("val :" + v + ";");
			httpReqBase.setHeader(name, v);
			final String m1 = "Name :" + name + ", value :" + v + ".";
			DdtCoreUtls.stepRslt(stepResult, true, m1, null);
		} catch (Exception e) {
			logger.warn("headerPut :" + e, e);// stepResult.setDetailMsgs
			org.s2n.ddt.common.DdtCoreUtls.stepFillDetail(stepResult, "headerPut err :" + e, e);

		}

	}

	public void template(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep step, TestStepResult stepResult,
			TestSuiteResultSummary suiteSummaryResult) {
		try {
			this.template = step.getStepParam();
			DdtCoreUtls.stepRslt(stepResult, true, "", null);
		} catch (Exception e) {
			logger.warn("template :" + e, e);// stepResult.setDetailMsgs
			org.s2n.ddt.common.DdtCoreUtls.stepFillDetail(stepResult, "template err :" + e, e);

		}

	}

	public String getStepFirstObjParamValue(TestStep step) {
		String v = step.getTestStepId().getTestParamDatas().get(0).getParam().getParamName();// TODO
		return v;
	}

	public void parameter(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep step, TestStepResult stepResult,
			TestSuiteResultSummary suiteSummaryResult) {
		try {
			String nm = step.getStepParam();
			String v = getStepFirstObjParamValue(step);
			replaceStrs.put(nm, v);
			DdtCoreUtls.stepRslt(stepResult, true, "", null);
		} catch (Exception e) {
			logger.warn("parameter :" + e, e);// stepResult.setDetailMsgs
			org.s2n.ddt.common.DdtCoreUtls.stepFillDetail(stepResult, "parameter err :" + e, e);

		}

	}

	public void submitReq2(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep step, TestStepResult stepResult,
			TestSuiteResultSummary suiteSummaryResult) {
		StringBuilder detCmt = new StringBuilder();
		try {
			final String urls = step.getStepParam();
			
			String sReq = makeRawReq(step);
			detCmt.append("submitReq2 Request:\n");
			detCmt.append(sReq);
			detCmt.append("\n");
			logger.info(sReq);
			logger.info("---");
			boolean httpWarn = LangUtils.isTrue(UtlConf.getProperty("logging.warn.httprunner", "0"), true);
			long sTime = 0l;
			if (httpWarn) {
				logger.warn("submitReq2 Request:\n" + sReq);
				sTime = System.currentTimeMillis();
			}
		
			Map<String, String> headers = null;
			HttpData hDat = new HttpData();
			hDat.setUrl(urls);
			String resp = NetSend.send(hDat, sReq,  headers);
			long eTime = System.currentTimeMillis();
			logger.info("After req");
			responseStr = resp;
			logger.info("re :" + responseStr);
			if (httpWarn) {
				logger.warn("Response:\n" + responseStr);
				logger.warn("Duration-http:" + (eTime - sTime));
				logger.warn("---");
			}
			detCmt.append("submitReq2 Response:\n");
			detCmt.append(responseStr);
			detCmt.append("\n");

			caseResult.setResponse(responseStr);
			caseResult.setRequest(sReq);
			DdtCoreUtls.stepRslt(stepResult, true, "", null);

		} catch (Exception e) {
			logger.warn("submitReq :" + e, e);// stepResult.setDetailMsgs
			DdtCoreUtls.stepFillDetail(stepResult, "submitReq err :" + e, e);

		}
		// if there was an error after request creation, would see error first
		// then request.
		org.s2n.ddt.common.DdtCoreUtls.stepFillDetail(stepResult, detCmt.toString(), null);
		///logger.warn("detl :" + stepResult.getDetailMsgs());
	
	}
	
	public void submitReq(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep step, TestStepResult stepResult,
			TestSuiteResultSummary suiteSummaryResult) {
		if(HttpRunner.httpLib == 1){
			submitReq2(plan, suite, testCase, caseResult, step, stepResult, suiteSummaryResult);
			return;
		}
		StringBuilder detCmt = new StringBuilder();
		try {
			final String urls = step.getStepParam();
			AbstractHttpEntity enReq = null;
			final String sendType = getStepFirstObjParamValue(step);
			if ("r".equals(sendType)) {
				logger.info("raw");
				String re = makeRawReq(step);
				enReq = new StringEntity(re, "UTF8");
				enReq.setContentEncoding("UTF8");// TODO param
				enReq.setContentType("text/xml");// TODO param

				/*
				 * InputStream is9 = enReq.getContent(); int ii = -1; ArrayList<Byte> dt = new ArrayList<Byte>(); while ((ii = is9.read()) > -1) {
				 * dt.add((byte) ii); } Byte[] bb = (Byte[]) dt.toArray(new Byte[] {}); byte[] bb1 = new byte[bb.length]; for (int j = 0; j < bb.length;
				 * j++) { bb1[j] = bb[j]; } logger.info(new String(bb1));// enReq
				 */

			}else{
				//TODO POST, GET
				detCmt.append("ERROR :Not yet implemented other types use r.");
				final String dds = detCmt.toString();
				logger.info(dds);
				DdtCoreUtls.stepFillDetail(stepResult, dds, null);
				return;
			}
			logger.info("--- sendType " + sendType + " ---");
			String sReq = EntityUtils.toString(enReq);
			detCmt.append("Request:\n");
			detCmt.append(sReq);
			detCmt.append("\n");
			logger.info(sReq);
			logger.info("---");
			// HttpPost httpost = null;// todo correct method
			// HttpEntity resp = this.httpclient.e
			// use this
			// HttpEntityEnclosingRequest reqEntity = new
			// BasicHttpEntityEnclosingRequest(method, urls, httpVer);
			// reqEntity.setEntity(enReq);

			// HttpRequest reqEntity = new BasicHttpRequest(method, urls,
			// httpVer);

			httpReqBase.setEntity(enReq);
			URI uri = new URI(urls);
			httpReqBase.setURI(uri);

			URL urlTo = new URL(urls);
			String hostNm = urlTo.getHost();
			int port = urlTo.getPort();
			String sche = urlTo.getProtocol();
			logger.info("scheme/ proto :" + sche + ", url :" + urlTo);
			HttpHost httpHost = new HttpHost(hostNm, port, sche);
			//
			boolean httpWarn = LangUtils.isTrue(UtlConf.getProperty("logging.warn.httprunner", "0"), true);
			long sTime = 0l;
			if (httpWarn) {
				logger.warn("Request:\n" + sReq);
				sTime = System.currentTimeMillis();
			}
			response = this.httpClient.execute(httpHost, httpReqBase, localContext);
			long eTime = System.currentTimeMillis();
			logger.info("After req");
			responseStr = EntityUtils.toString(response.getEntity(), "UTF8");
			logger.info("re :" + responseStr);
			if (httpWarn) {
				logger.warn("Response:\n" + responseStr);
				logger.warn("Duration-http:" + (eTime - sTime));
				logger.warn("---");
			}
			detCmt.append("Response:\n");
			detCmt.append(responseStr);
			detCmt.append("\n");

			caseResult.setResponse(responseStr);
			caseResult.setRequest(sReq);
			DdtCoreUtls.stepRslt(stepResult, true, "", null);

		} catch (Exception e) {
			logger.warn("submitReq :" + e, e);// stepResult.setDetailMsgs
			DdtCoreUtls.stepFillDetail(stepResult, "submitReq err :" + e, e);

		}
		// if there was an error after request creation, would see error first
		// then request.
		org.s2n.ddt.common.DdtCoreUtls.stepFillDetail(stepResult, detCmt.toString(), null);

	}

	public String makeRawReq(final TestStep step) {
		final StringBuilder req = new StringBuilder().append(template);
		for (final String key : replaceStrs.keySet()) {
			final String fnd = "{|--" + key + "--|}";
			int i = req.indexOf(fnd);
			while (i > -1) {
				final String val = replaceStrs.get(key);
				if (val != null) {
					req.replace(i, i + fnd.length(), val);
					i = req.indexOf(fnd, i + val.length());
				} else {
					i = -1;
					logger.warn("replace val not found for :" + key);
				}

			}
		}
		return req.toString();
	}

	public void initSsl(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep step, TestStepResult stepResult,
			TestSuiteResultSummary suiteSummaryResult) {
		try {
			StringBuilder det = new StringBuilder();
			String refreshEveryS = UtlConf.getProperty("http.initSsl.refreshEvery", "0");
			boolean refreshEvery = LangUtils.isTrue(refreshEveryS, false);
			if((!refreshEvery) && this.httpClient != null){
				DdtCoreUtls.stepRslt(stepResult, true, "Http Client already set up", null);
				return;
			}
			KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
			trustStore.load(null, null);

			UtlSslFactory sf = new UtlSslFactory(trustStore, true);

			BasicHttpParams params = new BasicHttpParams();
			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
			params.setParameter("", "");//TODO
			SchemeRegistry registry = new SchemeRegistry();
			registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
			registry.register(new Scheme("https", sf, 443));
			//org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager.ThreadSafeClientConnManager(HttpParams, SchemeRegistry)
			ThreadSafeClientConnManager ccm = new ThreadSafeClientConnManager(params, registry);
			
			String enableSetDefaultMaxPerRouteS = UtlConf.getProperty("http.initSsl.enableSetDefaultMaxPerRoute", "0");
			boolean enableSetDefaultMaxPerRoute = LangUtils.isTrue(enableSetDefaultMaxPerRouteS, false);
			if(enableSetDefaultMaxPerRoute){
				int cnt = 10;
				String cntSetDefaultMaxPerRouteS = UtlConf.getProperty("http.initSsl.cntDefaultMaxPerRoute", "20");
				if(cntSetDefaultMaxPerRouteS != null && cntSetDefaultMaxPerRouteS.length() > 0){
					cnt = LangUtils.getInt(cntSetDefaultMaxPerRouteS, cnt, "Count Max per route connection http"); 
				}
				ccm.setDefaultMaxPerRoute(cnt);
				det.append("set Count Max per route connection http " + cnt);
			}
			this.httpClient = new DefaultHttpClient(ccm, params);
			String sDet = null;
			if(det.length() ==0){
				det = null;
			}else{
				sDet = det.toString();
			}
			DdtCoreUtls.stepRslt(stepResult, true, "Ssl accept all (only for lan & trusted sites use)", sDet);
		} catch (Exception e) {
			logger.warn("initSsl :" + e, e);// stepResult.setDetailMsgs
			org.s2n.ddt.common.DdtCoreUtls.stepFillDetail(stepResult, "initSsl err :" + e, e);
		}
	}

	// TODO make a clone that has new member objects for ones that cannot be
	// shared	
	public Runner clone2() throws CloneNotSupportedException {

		return (Runner) clone();
	}

	
	public void endTestCase(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStepResult lastStep) {
		// TODO Auto-generated method stub

	}

	public void outputParam(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep step, TestStepResult stepResult,
			TestSuiteResultSummary suiteSummaryResult) {
		try {
			DdtCoreUtls.stepRslt(stepResult, true, "", null);
		} catch (Exception e) {
			logger.warn("outputParam:" + e, e);// stepResult.setDetailMsgs
			org.s2n.ddt.common.DdtCoreUtls.stepFillDetail(stepResult, "outputParam err :" + e, e);

		}

	}

	public void applySaved(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep step, TestStepResult stepResult,
			TestSuiteResultSummary suiteSummaryResult) {
		String sp = step.getStepParam();
		String op = getStepFirstObjParamValue(step);
		try {

			StringTokenizer st = new StringTokenizer(sp, "|");
			String from = null;
			if (st.countTokens() > 1) {
				from = st.nextToken();
			}
			if (from == null) {
				from = "l";
			}
			String key = st.nextToken();
			// String s = replaceStrs.get(sp);
			String val = null;
			if ("g".equals(from)) {
				val = savedGlobalStrs.get(key);
			} else if ("c".equals(from)) {
				val = ci.getParamValue(key);
			} else {
				val = savedStrs.get(key);
			}
			String old = replaceStrs.put(op, val);
			final String log1 = "applySaved For param :" + op + "; got " + val + "; old :" + old + "; key :" + key + " from " + from;
			logger.info(log1);
			DdtCoreUtls.stepRslt(stepResult, true, "", log1);

		} catch (Exception e) {
			// log details so even if test plan is changed we have detail params
			final String log2 = "applySaved:" + e + ", sp :" + sp + ", op :" + op;
			logger.warn(log2, e);// stepResult.setDetailMsgs
			org.s2n.ddt.common.DdtCoreUtls.stepFillDetail(stepResult, log2, e);

		}

	}

	// savedStrs
	public void saveResponse(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep step, TestStepResult stepResult,
			TestSuiteResultSummary suiteSummaryResult) {
		try {
			String stpPrm = step.getStepParam();
			String objParam = getStepFirstObjParamValue(step);
			StringTokenizer st = new StringTokenizer(stpPrm, "|");
			List<String> toks = new ArrayList<String>();
			while (st.hasMoreTokens()) {
				toks.add(st.nextToken());
			}
			int x1 = 0, x2 = 0, x3 = 0;
			String val = null;
			String endTag = null;
			String first = null;
			String find = null;
			String ele1 = null;
			boolean saved = false;
			String saveTo = null;
			if (toks.size() >= 2) {
				first = toks.get(0);
				find = toks.get(1);
				if (toks.size() >= 3) {
					saveTo = toks.get(2);
				}
				if ("x".equals(first)) {
					ele1 = "<" + find;
					x1 = responseStr.indexOf(find);
					if (x1 > -1) {
						x2 = responseStr.indexOf(">", x1 + find.length());
						if (x2 > -1) {
							endTag = "</" + find;
							x3 = responseStr.indexOf(endTag, x2 + 1);
							if (x3 > x2) {
								val = responseStr.substring(x2 + 1, x3);
								saved = true;
							}

						}
					}
				} else if ("j".equals(first)) {
					try {
						logger.info("trying json save");
						JSONObject json = new JSONObject(responseStr);
						val = json.getString(find);
						saved = true;
					} catch (Exception e) {
						logger.warn("Json fail (will try mnaul " + e, e);
						ele1 = "\"" + find + "\"";
						x1 = responseStr.indexOf(ele1);
						if (x1 > -1) {
							x2 = responseStr.indexOf("\n", x1 + 1);
							if (x2 > (x1 + ele1.length())) {
								String line = responseStr.substring(x1 + ele1.length(), x2);
								x2 = line.indexOf('\"');
								x3 = line.lastIndexOf('\"');
								if ((x2 > -1) && (x3 > x2)) {
									val = line.substring(x2 + 1, x3);
									saved = true;
								}
							}
						}
					}

				}
			}

			if (saved) {
				if (saveTo == null) {
					saveTo = "l";
				}
				if ("g".equals(saveTo)) {
					savedGlobalStrs.put(objParam, val);
				} else if ("c".equals(saveTo)) {
					val = ci.setParamValue(objParam, val);
				} else {
					savedStrs.put(objParam, val);
				}
				logger.info("saved key :" + objParam + "; v :" + val + "; save to :" + saveTo);
				DdtCoreUtls.stepRslt(stepResult, true, "okay :" + val, null);
			} else {
				logger.info("not saved stpPrm :" + stpPrm + "]");
				logger.info("objp :" + objParam + "]");

				logger.info("endTag :" + endTag + "]");
				logger.info("first :" + first + "]");
				logger.info("find :" + find + "]");

				logger.info("x1 :" + x1 + "]");
				logger.info("x2 :" + x2 + "]");
				logger.info("x3 :" + x3 + "]");
				logger.info("ele1 :" + ele1 + "]");

				// /
				StringBuilder sb = new StringBuilder();
				sb.append("not saved sp :" + stpPrm + "]");
				sb.append("op :" + objParam + "]");

				sb.append("endTag :" + endTag + "]");
				sb.append("first :" + first + "]");
				sb.append("find :" + find + "]");

				sb.append("x1 :" + x1 + "]");
				sb.append("x2 :" + x2 + "]");
				sb.append("x3 :" + x3 + "]");
				sb.append("ele1 :" + ele1 + "]");
				DdtCoreUtls.stepRslt(stepResult, false, "Not found", sb.toString());
			}
		} catch (Exception e) {
			logger.warn("saveResponse :" + e, e);// stepResult.setDetailMsgs
			org.s2n.ddt.common.DdtCoreUtls.stepFillDetail(stepResult, "saveResponse err :" + e, e);

		}

	}

	public void paramFromGenerator(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep step, TestStepResult stepResult,
			TestSuiteResultSummary suiteSummaryResult) {
		if (paramFromExt == null) {
			paramFromExt = new ParamFromTstGenClz();
		}
		paramFromExt.testStep(plan, suite, testCase, caseResult, step, stepResult, suiteSummaryResult, replaceStrs);
		// String s = replaceStrs.get("deviceId");
	}

	// TODO move to core
	public void snooze(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep step, TestStepResult stepResult,
			TestSuiteResultSummary suiteSummaryResult) {
		org.s2n.ddt.common.DdtCoreUtls.stepFillDetail(stepResult, "snooze deprecated use Core", null);

	}

	/**
	 * Do not need these now. Useful if we want to make runners who know about each other. Example: SeleniumRunner2 which can take browser and some other
	 * information from original SeleniumRunner
	 * */
	
	public void setRunnersInformation(Map<String, Runner> runners) {

	}

	
	public void setCommonInformationProvider(CommonInformation ci) {
		this.ci = ci;

	}

	public static final String getVersion() {
		return "1.6.10";
	}
	
	public static void main(String[] args) {
		
	}


	public static int getHttpLib() {
		return httpLib;
	}


	public static void setHttpLib(int httpLib) {
		HttpRunner.httpLib = httpLib;
	}

}