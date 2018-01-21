package org.s2n.ddt.pojo.output;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.s2n.ddt.pojo.BinaryFileInfo;
import org.s2n.ddt.pojo.input.TestStep;

/** TestStepResult entity */
public class TestStepResult implements Serializable, Cloneable {

	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Any len detail msg with trace and other var values.
	 * */
	private String detailMsgs = "";	
	private boolean result;

	/**
	 * If result is false this should have a value with some information on why
	 * step failed. Try to keep it short max 2-3 sentences/ 200 characters.
	 * */
	private String comment;
	private int testStepResultId;
	private int testCaseResultId;
	private int testStepId;
	public int getTestStepResultId() {
		return testStepResultId;
	}

	public void setTestStepResultId(int testStepResultId) {
		this.testStepResultId = testStepResultId;
	}

	public int getTestCaseResultId() {
		return testCaseResultId;
	}

	public void setTestCaseResultId(int testCaseResultId) {
		this.testCaseResultId = testCaseResultId;
	}

	public int getTestStepId() {
		return testStepId;
	}

	public void setTestStepId(int testStepId) {
		this.testStepId = testStepId;
	}

	private TestStep testStep;
	private long timeTaken;
	private String response;
	private String request;
	private HashMap<String, String> reqMap = new HashMap<String, String>();
	private HashMap<String, String> saveResult = new HashMap<String, String>();
	private List<BinaryFileInfo> binaryFileInfos = null;//From Runner or agent, optional list of files for report/debugging
	private String snapShot = null;

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public HashMap<String, String> getReqMap() {
		return reqMap;
	}

	public void setReqMap(HashMap<String, String> reqMap) {
		this.reqMap = reqMap;
	}

	public HashMap<String, String> getSaveResult() {
		return saveResult;
	}

	public void setSaveResult(HashMap<String, String> saveResult) {
		this.saveResult = saveResult;
	}

	/**
	 * If result is false this can have a value - of any length
	 * */
	

	public TestStepResult() {
		super();
	}

	/** Full constructor */
	public TestStepResult(String comment, boolean result, TestStep testStep, long timeTaken) {
		super();
		this.comment = comment;
		this.result = result;
		this.testStep = testStep;
		this.timeTaken = timeTaken;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean getResult() {
		return result;
	}

	public void setResult(boolean b) {
		this.result = b;
	}

	public TestStep getTestStep() {
		return testStep;
	}

	public void setTestStep(TestStep testStep) {
		this.testStep = testStep;
	}

	public void setTimeTaken(long timeTaken) {
		this.timeTaken = timeTaken;
	}

	public long getTimeTaken() {
		return timeTaken;
	}

	/**
	 * toString will return String object representing the state of this
	 * valueObject. This is useful during application development, and possibly
	 * when application is writing object states in textlog.
	 */
	@Override
	public String toString() {
		return "TestStepResult [comment=" + comment + ", result=" + result + ", detailMsgs=" + detailMsgs + "]";
	}

	public String getDetailMsgs() {
		return detailMsgs;
	}

	public void setDetailMsgs(String detailMsgs) {
		this.detailMsgs = detailMsgs;
	}
	
	public void addBinaryFileInfo(BinaryFileInfo bfi) {
		initIfNull();
		binaryFileInfos.add(bfi);
	}
	
	public void initIfNull() {
		if( binaryFileInfos == null) {
			binaryFileInfos = new ArrayList<BinaryFileInfo>();
		}
	}

	public List<BinaryFileInfo> getBinaryFileInfos() {
		return binaryFileInfos;
	}

	public void setBinaryFileInfos(List<BinaryFileInfo> binaryFileInfos) {
		this.binaryFileInfos = binaryFileInfos;
	}

	public String getSnapShot() {
		return snapShot;
	}

	public void setSnapShot(String snapShot) {
		this.snapShot = snapShot;
	}
	
	public static final String getVersion() {
		return "1.6.10b";
	}
}