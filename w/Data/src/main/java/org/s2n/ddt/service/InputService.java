package org.s2n.ddt.service;

import org.s2n.ddt.exception.ServiceException;
import org.s2n.ddt.pojo.input.IdentifierType;
import org.s2n.ddt.pojo.input.ObjectGroup;
import org.s2n.ddt.pojo.input.ObjectType;
import org.s2n.ddt.pojo.input.Objects;
import org.s2n.ddt.pojo.input.Param;
import org.s2n.ddt.pojo.input.ParamGroup;
import org.s2n.ddt.pojo.input.TestCase;
import org.s2n.ddt.pojo.input.TestCondData;
import org.s2n.ddt.pojo.input.TestParamData;
import org.s2n.ddt.pojo.input.TestStep;
import org.s2n.ddt.pojo.output.TestPlan;

/**
 * Input Service class
 */
public interface InputService {

	long insertIdentifierTypeDetails(final IdentifierType identifierType) throws ServiceException;

	long insertObjectGroupDetails(ObjectGroup objectGroup) throws ServiceException;

	long insertObjectsDetails(Objects objects) throws ServiceException;

	long insertParamDetails(final Param param) throws ServiceException;

	long insertParamGroupDetails(final ParamGroup paramGroup) throws ServiceException;

	long insertTestCaseDetails(final TestCase testcase) throws ServiceException;

	long insertTestCondDataDetails(final TestCondData testCondData) throws ServiceException;

	long insertTestParamDataDetails(final TestParamData testParamData) throws ServiceException;

	long insertTestStepDetails(final TestStep testStep) throws ServiceException;

	long insertObjectsTypeDetails(ObjectType objectType) throws ServiceException;

	TestPlan getDeepTestPlan(int testPlanId) throws ServiceException;

	void insertReadPlanData(String xlsPath) throws ServiceException;

	void insertObjectsData(String xlsPath) throws ServiceException;

	void deleteTestStepDependents(TestCase testCase) throws ServiceException;
}
