package org.s2n.ddt.excelreader;

public enum ColumnNames{
	SL_NO("Sl No"),
	TEST_CASE_NAME("Test Case name"),
	TEST_CASE_CLASSFICATION_TAGS ("TEST CASE CLASSIFCATION TAGS"),
	TEST_CASE_SCENARIO ("Test Case Scenario"),
	TEST_CASE_TITLE ("Test Case Title"),
	POSTIVE_OR_NEGATIVE ("Positive Or Negative"),
	RUNNER_TYPE ("RUNNER TYPE"),
	STEP_TYPE("Step Type"),
	ACTION ("Action"),
	EXECUTE ("Execute/Skip TEST CASE"),
	DEPENDENCY ("Dependency"),
	USER_COMMENTS ("Comments"),
	PARAM_GROUP_OBJECT ("Param group Object"),
	STEP_PARAM ("Step Param"),
	VALUE ("value(TestParam data)"),
	EXPECTED_RESULT ("Expected  Result"),
	ACTUAL_RESULT ("Actual Result"),
	RESULT ("Result"),
	RUNNER_COMMENTS("Runner Comments"),
	DATE_FORMAT("Date Format");
	private String getTextValue;

	/**
	 * Instantiates a new key val.
	 * 
	 * @param text
	 *            the text
	 */
	ColumnNames(String text) {

		this.getTextValue = text;
	}

	/**
	 * Gets the text.
	 * 
	 * @return the text
	 */
	public String getText() {

		return this.getTextValue;
	}
}