package org.s2n.ddt.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import org.s2n.ddt.dao.ActionsDao;
import org.s2n.ddt.dao.ApplicationDao;
import org.s2n.ddt.dao.FeatureDao;
import org.s2n.ddt.dao.FunctionalDao;
import org.s2n.ddt.dao.RunnerDao;
import org.s2n.ddt.dao.ScreenDao;
import org.s2n.ddt.dao.SuiteScenarioMappingDao;
import org.s2n.ddt.dao.TestPlanScenarioMapDao;
import org.s2n.ddt.dao.TestSuiteDao;
import org.s2n.ddt.dao.input.IdentifierTypeDao;
import org.s2n.ddt.dao.input.ObjectGroupDao;
import org.s2n.ddt.dao.input.ObjectTypeDao;
import org.s2n.ddt.dao.input.ObjectsDao;
import org.s2n.ddt.dao.input.ParamDao;
import org.s2n.ddt.dao.input.ParamGroupDao;
import org.s2n.ddt.dao.input.TestCaseDao;
import org.s2n.ddt.dao.input.TestCondDataDao;
import org.s2n.ddt.dao.input.TestParamDataDao;
import org.s2n.ddt.dao.input.TestStepDao;
import org.s2n.ddt.dao.input.TestStepParamGroupMappingDao;
import org.s2n.ddt.dao.output.TestPlanDao;
import org.s2n.ddt.dao.output.TestScenarioDao;
import org.s2n.ddt.dao.output.TestScenarioParamDataDao;
import org.s2n.ddt.excelreader.ReadObjectsData;
import org.s2n.ddt.excelreader.ReadPlan;
import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.exception.ServiceException;
import org.s2n.ddt.pojo.Actions;
import org.s2n.ddt.pojo.Application;
import org.s2n.ddt.pojo.Feature;
import org.s2n.ddt.pojo.Functional;
import org.s2n.ddt.pojo.Runner;
import org.s2n.ddt.pojo.Screen;
import org.s2n.ddt.pojo.SuiteScenarioMapping;
import org.s2n.ddt.pojo.TestSuite;
import org.s2n.ddt.pojo.TestSuiteId;
import org.s2n.ddt.pojo.TestplanTestscenarioMap;
import org.s2n.ddt.pojo.input.IdentifierType;
import org.s2n.ddt.pojo.input.ObjectGroup;
import org.s2n.ddt.pojo.input.ObjectType;
import org.s2n.ddt.pojo.input.ObjectTypeId;
import org.s2n.ddt.pojo.input.Objects;
import org.s2n.ddt.pojo.input.ObjectsId;
import org.s2n.ddt.pojo.input.Param;
import org.s2n.ddt.pojo.input.ParamGroup;
import org.s2n.ddt.pojo.input.ParamGroupId;
import org.s2n.ddt.pojo.input.TestCase;
import org.s2n.ddt.pojo.input.TestCondData;
import org.s2n.ddt.pojo.input.TestCondDataId;
import org.s2n.ddt.pojo.input.TestParamData;
import org.s2n.ddt.pojo.input.TestParamDataId;
import org.s2n.ddt.pojo.input.TestStep;
import org.s2n.ddt.pojo.input.TestStepId;
import org.s2n.ddt.pojo.input.TestStepParamGroupMapping;
import org.s2n.ddt.pojo.output.TestPlan;
import org.s2n.ddt.pojo.output.TestPlanId;
import org.s2n.ddt.pojo.output.TestScenario;
import org.s2n.ddt.pojo.output.TestscenarioParamdataMap;
import org.s2n.ddt.service.InputService;
import org.s2n.ddt.utils.DataConstants;

/**
 * Data Module primary business object.
 * 
 * <p>
 * This object makes use of DAO objects, decoupling it from the details of
 * working with persistence APIs. So although this application uses Spring Data
 * for data access, a different persistence tool could be dropped in without
 * breaking this class.
 * 
 * <p>
 * The DAOs are made available to the instance of this object using Dependency
 * Injection. (The DAOs are in turn configured using Dependency Injection
 * themselves.) We use Setter Injection here, exposing JavaBean setter methods
 * for each DAO. This means there is a JavaBean property for each DAO. In the
 * present case, the properties are write-only: there are no corresponding
 * getter methods. Getter methods for configuration properties are optional:
 * Implement them only if you want to expose those properties to other business
 * objects.
 * 
 * <p>
 * There is one instance of this class in the Data module application. In Spring
 * terminology, it is a "singleton", referring to a per-Application Context
 * singleton. The factory creates a single instance; there is no need for a
 * private constructor, static factory method etc as in the traditional
 * implementation of the Singleton Design Pattern.
 * 
 * <p>
 * This is a POJO. It does not depend on any Spring APIs. It's usable outside a
 * Spring container, and can be instantiated using new in a JUnit test. However,
 * we can still apply declarative transaction management to it using Spring AOP.
 * 
 * <p>
 * This class defines a default transaction annotation for all methods.
 * 
 */
@Transactional
public class InputServiceImpl implements InputService {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(InputServiceImpl.class);

	private IdentifierTypeDao identifierTypeDao;

	private ObjectGroupDao objectGroupDao;

	private ObjectsDao objectsDao;

	private ObjectTypeDao objectTypeDao;

	private ParamDao paramDao;

	private ParamGroupDao paramGroupDao;

	private TestCaseDao testCaseDao;

	private TestCondDataDao testCondDataDao;

	private TestParamDataDao testParamDataDao;

	private TestStepDao testStepDao;

	private TestPlanDao testPlanDao;

	private SuiteScenarioMappingDao suiteScenarioMappingDao;

	private TestPlanScenarioMapDao testPlanScenarioMapDao;

	private TestSuiteDao testSuiteDao;

	private RunnerDao runnerDao;

	private ActionsDao actionsDao;

	private TestScenarioDao testScenarioDao;

	private Map<String, Object> cacheMap = new HashMap<String, Object>();

	private ScreenDao screenDao;

	private ApplicationDao applicationDao;

	private FeatureDao featureDao;

	private FunctionalDao functionalDao;

	private TestStepParamGroupMappingDao stepParamGroupMappingDao;

	private TestScenarioParamDataDao testScenarioParamDataDao;

	// -------------------------------------------------------------------------
	// Setter methods for dependency injection
	// -------------------------------------------------------------------------

	public void setFeatureDao(FeatureDao featureDao) {
		this.featureDao = featureDao;
	}

	public void setTestScenarioParamDataDao(TestScenarioParamDataDao testScenarioParamDataDao) {
		this.testScenarioParamDataDao = testScenarioParamDataDao;
	}

	public void setScreenDao(ScreenDao screenDao) {
		this.screenDao = screenDao;
	}

	public void setStepParamGroupMappingDao(TestStepParamGroupMappingDao stepParamGroupMappingDao) {
		this.stepParamGroupMappingDao = stepParamGroupMappingDao;
	}

	public void setFunctionalDao(FunctionalDao functionalDao) {
		this.functionalDao = functionalDao;
	}

	public void setTestPlanDao(TestPlanDao testPlanDao) {
		this.testPlanDao = testPlanDao;
	}

	public void setTestScenarioDao(TestScenarioDao testScenarioDao) {
		this.testScenarioDao = testScenarioDao;
	}

	public void setRunnerDao(RunnerDao runnerDao) {
		this.runnerDao = runnerDao;
	}

	public void setActionsDao(ActionsDao actionsDao) {
		this.actionsDao = actionsDao;
	}

	public void setIdentifierTypeDao(IdentifierTypeDao identifierTypeDao) {
		this.identifierTypeDao = identifierTypeDao;
	}

	public void setObjectGroupDao(ObjectGroupDao objectGroupDao) {
		this.objectGroupDao = objectGroupDao;
	}

	public void setObjectsDao(ObjectsDao objectsDao) {
		this.objectsDao = objectsDao;
	}

	public void setObjectTypeDao(ObjectTypeDao objectTypeDao) {
		this.objectTypeDao = objectTypeDao;
	}

	public void setParamDao(ParamDao paramDao) {
		this.paramDao = paramDao;
	}

	public void setParamGroupDao(ParamGroupDao paramGroupDao) {
		this.paramGroupDao = paramGroupDao;
	}

	public void setTestCaseDao(TestCaseDao testCaseDao) {
		this.testCaseDao = testCaseDao;
	}

	public void setTestCondDataDao(TestCondDataDao testCondDataDao) {
		this.testCondDataDao = testCondDataDao;
	}

	public void setTestParamDataDao(TestParamDataDao testParamDataDao) {
		this.testParamDataDao = testParamDataDao;
	}

	public void setTestStepDao(TestStepDao testStepDao) {
		this.testStepDao = testStepDao;
	}

	public void setSuiteScenarioMappingDao(SuiteScenarioMappingDao suiteScenarioMappingDao) {
		this.suiteScenarioMappingDao = suiteScenarioMappingDao;
	}

	public void setTestPlanScenarioMapDao(TestPlanScenarioMapDao testPlanScenarioMapDao) {
		this.testPlanScenarioMapDao = testPlanScenarioMapDao;
	}

	public void setTestSuiteDao(TestSuiteDao testSuiteDao) {
		this.testSuiteDao = testSuiteDao;
	}

	public void setApplicationDao(ApplicationDao applicationDao) {
		this.applicationDao = applicationDao;
	}

	// -------------------------------------------------------------------------
	// Operation methods, implementing the DataModuleService interface
	// -------------------------------------------------------------------------

	public long insertIdentifierTypeDetails(IdentifierType identifierType) throws ServiceException {
		try {
			int id = this.identifierTypeDao.getIdentifierTypeIdByName(identifierType);
			long result = 0L;
			if (id == 0) {
				result = this.identifierTypeDao.insertIdentifierTypeDetails(identifierType);
			} else {
				identifierType.setIdentifierTypeId(new BigDecimal(id));
				result = this.identifierTypeDao.updateIdentifierType(identifierType);
			}
			return result;
		} catch (DataAccessException e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		}
	}

	public long insertObjectGroupDetails(ObjectGroup objectGroup) throws ServiceException {
		try {
			int id = this.objectGroupDao.getObjectGroupsIdByAppId(objectGroup);
			long result = 0L;
			if (id == 0) {
				result = this.objectGroupDao.insertObjectGroupDetails(objectGroup);
			} else {
				objectGroup.setObjectGroupId(new BigDecimal(id));
				result = this.objectGroupDao.updateObjectGroups(objectGroup);
			}
			return result;
		} catch (DataAccessException e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		}
	}

	public long insertObjectsDetails(Objects objects) throws ServiceException {
		try {
			int id = this.objectsDao.getObjectsIdByGroupId(objects);
			long result = 0L;
			if (id == 0) {
				result = this.objectsDao.insertObjectDetails(objects);
			} else {
				ObjectsId objectsId = objects.getObjectsId();
				objectsId.setObjectId(new BigDecimal(id));
				objects.setObjectsId(objectsId);
				result = this.objectsDao.updateObjects(objects);
			}
			return result;
		} catch (DataAccessException e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		}
	}

	public long insertObjectsTypeDetails(ObjectType objectType) throws ServiceException {
		try {
			int id = this.objectTypeDao.getObjectTypeIdByName(objectType);
			long result = 0L;
			if (id == 0) {
				result = this.objectTypeDao.insertObjectTypeDetails(objectType);
			} else {
				ObjectTypeId objectTypeId = objectType.getObjectTypeId();
				objectTypeId.setObjectTypeId(new BigDecimal(id));
				objectType.setObjectTypeId(objectTypeId);
				result = this.objectTypeDao.updateObjects(objectType);
			}
			return result;
		} catch (DataAccessException e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		}
	}

	public long insertParamDetails(final Param param) throws ServiceException {
		try {
			int id = this.paramDao.getParamIdDetailsByName(param);
			long result = 0L;
			if (id == 0) {
				result = this.paramDao.insertParamDetails(param);
			} else {
				param.setParamId(new BigDecimal(id));
				result = this.paramDao.updateParam(param);
			}
			return result;
		} catch (DataAccessException e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		}
	}

	public long insertParamGroupDetails(final ParamGroup paramGroup) throws ServiceException {
		try {
			int id = this.paramGroupDao.getParamGroupIdDetailsByName(paramGroup);
			long result = 0L;
			if (id == 0) {
				result = this.paramGroupDao.insertParamGroupDetails(paramGroup);
			} else {
				ParamGroupId paramGroupId = paramGroup.getParamGroupId();
				paramGroupId.setParamGroupId(new BigDecimal(id));
				paramGroup.setParamGroupId(paramGroupId);
				result = this.paramGroupDao.updateParamGroup(paramGroup);
			}
			return result;
		} catch (DataAccessException e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		}
	}

	public long insertTestCondDataDetails(final TestCondData testCondData) throws ServiceException {
		try {
			int id = this.testCondDataDao.getTestCondDataByCondId(testCondData);
			long result = 0L;
			if (id == 0) {
				result = this.testCondDataDao.insertTestCondDataDetails(testCondData);
			} else {
				TestCondDataId testCondDataId = testCondData.getTestCondDataId();
				testCondDataId.setTestCondDataId(new BigDecimal(id));
				testCondData.setTestCondDataId(testCondDataId);
				result = this.testCondDataDao.updateCondData(testCondData);
			}
			return result;
		} catch (DataAccessException e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		}

	}

	public long insertTestParamDataDetails(final TestParamData testParamData) throws ServiceException {
		try {
			int id = this.testParamDataDao.getTestParamDataIdByAppId(testParamData);
			long result = 0L;
			if (id == 0) {
				result = this.testParamDataDao.insertTestParamDataDetails(testParamData);
			} else {
				TestParamDataId testParamDataId = testParamData.getTestParamDataId();
				testParamDataId.setTestParamDataId(new BigDecimal(id));
				testParamData.setTestParamDataId(testParamDataId);
				result = this.testParamDataDao.updateParamData(testParamData);
			}
			return result;
		} catch (DataAccessException e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		}
	}

	public long insertTestStepDetails(final TestStep testStep) throws ServiceException {
		try {
			int id = this.testStepDao.getTestStepByName(testStep);
			long result = 0L;
			if (id == 0) {
				result = this.testStepDao.insertTestStepDetails(testStep);
			} else {
				TestStepId testStepId = testStep.getTestStepId();
				testStepId.setTestStepId(new BigDecimal(id));
				testStep.setTestStepId(testStepId);
				result = this.testStepDao.updateTestStep(testStep);
			}
			return result;
		} catch (DataAccessException e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		}
	}

	public long insertTestCaseDetails(final TestCase testcase) throws ServiceException {
		try {
			int id = this.testCaseDao.getTestCaseIdByRunnerId(testcase);
			long result = 0L;
			if (id == 0) {
				result = this.testCaseDao.insertTestCaseDetails(testcase);
			} else {
				testcase.setTestCaseId(new BigDecimal(id));
				result = this.testCaseDao.updateTestCase(testcase);
			}
			return result;
		} catch (DataAccessException e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		}
	}

	private void loadParamGroupDependentDetails(ParamGroup paramGroup) throws ServiceException {
		try {
			List<TestParamData> testParamDatasList = new ArrayList<TestParamData>();
			List<Param> paramsList = new ArrayList<Param>();
			BigDecimal groupid = paramGroup.getParamGroupId().getParamGroupId();
			List<Param> params = this.paramDao.getParamDetailsByParamGroupId(groupid.intValue());
			Iterator<Param> iterator4 = params.iterator();
			while (iterator4.hasNext()) {
				Param param = (Param) iterator4.next();
				BigDecimal paramid = param.getParamId();
				BigDecimal objId = param.getObjectId();
				Objects objects = this.objectsDao.getObjectsDetailsById(objId.intValue());
				if (objects != null) {
					BigDecimal objGrpId = objects.getObjectsId().getObjectGroupId();
					ObjectGroup objectGroup = this.objectGroupDao.getObjectGroupDetailsById(objGrpId.intValue());
					objects.setObjectGroup(objectGroup);
				}
				param.setObjects(objects);
				List<TestParamData> tData = this.testParamDataDao.getTestParamDataDetailsByParamId(paramid.intValue());
				Iterator<TestParamData> iterator5 = tData.iterator();
				while (iterator5.hasNext()) {
					TestParamData testParamData = (TestParamData) iterator5.next();
					testParamDatasList.add(testParamData);
					param.setTestParamDatas(testParamDatasList);
				}
				paramsList.add(param);
			}
			ParamGroupId paramGroupId = paramGroup.getParamGroupId();
			paramGroupId.setParamsList(paramsList);
			paramGroup.setParamGroupId(paramGroupId);
		} catch (DataAccessException e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		}
	}

	private void loadTestStepDependentDetails(TestStep testStep) throws ServiceException {
		try {
			List<ParamGroup> paramGroupsList = new ArrayList<ParamGroup>();
			BigDecimal stepid = testStep.getTestStepId().getTestStepId();
			List<TestParamData> testParamDatas = new ArrayList<TestParamData>(0);
			BigDecimal runnerId = testStep.getTestStepId().getRunnerId();
			Runner runner = this.runnerDao.getRunnerById(runnerId.intValue());
			BigDecimal actionId = testStep.getTestStepId().getActionsId();
			Actions actions = this.actionsDao.getActionsById(actionId.intValue());
			List<TestStepParamGroupMapping> stepParamGroupMappings = this.stepParamGroupMappingDao.getRecordsByTestStepId(stepid.intValue());
			Iterator<TestStepParamGroupMapping> iterator = stepParamGroupMappings.iterator();
			while (iterator.hasNext()) {
				TestStepParamGroupMapping testStepParamGroupMapping = (TestStepParamGroupMapping) iterator.next();
				ParamGroup paramGroup = this.paramGroupDao.getParamGroupDetailsById(testStepParamGroupMapping.getParamGroupId().intValue());
				loadParamGroupDependentDetails(paramGroup);
				List<Param> params = paramGroup.getParamGroupId().getParamsList();
				for (Param param : params) {
					List<TestParamData> testParamDatas1 = param.getTestParamDatas();
					for (TestParamData testParamData : testParamDatas1) {
						testParamData.setParam(param);
						testParamDatas.add(testParamData);
					}
				}
				paramGroupsList.add(paramGroup);
			}
			testStep.setActions(actions);
			testStep.setRunner(runner);
			TestStepId testStepId = testStep.getTestStepId();
			testStepId.setParamGroups(paramGroupsList);
			testStepId.setTestParamDatas(testParamDatas);
			testStep.setTestStepId(testStepId);
		} catch (DataAccessException e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		}
	}

	private void loadTestCaseDetailsBySuiteId(TestSuite testSuite) throws ServiceException {
		try {
			List<TestStep> testStepsList = new ArrayList<TestStep>();
			List<TestCase> testCasesList = new ArrayList<TestCase>();

			BigDecimal suiteId = testSuite.getTestSuiteId().getTestSuiteId();
			List<TestCase> testCases = this.testCaseDao.getTestSuiteDataByTestCaseId(suiteId.intValue());
			Iterator<TestCase> iterator1 = testCases.iterator();
			while (iterator1.hasNext()) {
				TestCase testCase = (TestCase) iterator1.next();
				List<TestStep> testSteps = this.testStepDao.getTestStepDetailsByTestcaseId(testCase.getTestCaseId().intValue());
				Iterator<TestStep> iterator2 = testSteps.iterator();
				while (iterator2.hasNext()) {
					TestStep testStep = (TestStep) iterator2.next();
					loadTestStepDependentDetails(testStep);
					testStepsList.add(testStep);
					logger.info("testStepsList after appending testStep with Actions, Runner & paramGroupsList : " + testStepsList);
				}
				testCase.setTestSteps(testStepsList);
				testCasesList.add(testCase);
				logger.info("testCasesList after appending testStepsList : " + testCasesList);
			}
			TestSuiteId testSuiteId = testSuite.getTestSuiteId();
			testSuiteId.setTestCases(testCases);
			testSuite.setTestSuiteId(testSuiteId);
		} catch (DataAccessException e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public TestPlan getDeepTestPlan(int testPlanId) throws ServiceException {
		try {
			TestPlan testPlan1 = (TestPlan) cacheMap.get(DataConstants.DEEP_TEST_PLAN);
			if (testPlan1 != null) {
				return testPlan1;
			}
			List<TestplanTestscenarioMap> planScenarioList = new ArrayList<TestplanTestscenarioMap>();
			List<Functional> functionals = new ArrayList<Functional>();
			List<Feature> features = new ArrayList<Feature>();
			List<TestScenario> testScenarios = new ArrayList<TestScenario>();
			List<TestSuite> testSuites = new ArrayList<TestSuite>();
			TestPlan testPlan = this.testPlanDao.getTestPlanDetailsById(testPlanId);
			if (testPlan != null) {
				BigDecimal planId = testPlan.getTestPlanId().getTestPlanId();
				List<TestplanTestscenarioMap> maps = this.testPlanScenarioMapDao.getTestplanTestscenarioMapByPlanId(planId.intValue());
				Iterator<TestplanTestscenarioMap> iterator = maps.iterator();
				while (iterator.hasNext()) {
					TestplanTestscenarioMap testplanTestscenarioMap = (TestplanTestscenarioMap) iterator.next();
					BigDecimal scenarioId = testplanTestscenarioMap.getTestScenarioId();
					TestScenario testScenario = this.testScenarioDao.getTestScenarioDetailsById(scenarioId.intValue());
					List<SuiteScenarioMapping> mappings = this.suiteScenarioMappingDao.getSuiteScenarioMappingBytestScenarioId(scenarioId.intValue());
					Iterator<SuiteScenarioMapping> iterator2 = mappings.iterator();
					while (iterator2.hasNext()) {
						SuiteScenarioMapping mapping = (SuiteScenarioMapping) iterator2.next();
						TestSuite testSuite = this.testSuiteDao.getTestSuiteDetailsById(mapping.getTestSuiteId().intValue());
						int functionalId = testSuite.getTestSuiteId().getFunctionalId().intValue();
						int featureId = testSuite.getTestSuiteId().getFeatureId().intValue();
						Functional functional = this.functionalDao.getFunctionalDetailsById(functionalId);
						Feature feature = this.featureDao.getFeatureDetailsById(featureId);
						loadTestCaseDetailsBySuiteId(testSuite);
						testSuites.add(testSuite);
						testScenario.setTestSuites(testSuites);
						testScenarios.add(testScenario);
						feature.setTestScenarios(testScenarios);
						features.add(feature);
						functional.setFeatures(features);
						functionals.add(functional);
						testSuite.setFunctional(functional);
						testSuite.setFeature(feature);
					}
					testplanTestscenarioMap.setTestScenario(testScenario);
					planScenarioList.add(testplanTestscenarioMap);
					TestPlanId testPlanId2 = testPlan.getTestPlanId();
					testPlanId2.setPlanScenarioMappingList(maps);
					testPlanId2.setFunctionals(functionals);
					testPlan.setTestPlanId(testPlanId2);
				}
			} else {
				logger.error("error occured due to testplan is not loaded with given id ");
			}
			cacheMap.put(DataConstants.DEEP_TEST_PLAN, testPlan);
			return testPlan;
		} catch (DataAccessException e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void deleteTestStepDependents(TestCase testCase) throws ServiceException {
		try {
			logger.info("deleting the records related to testcase with id : " + testCase.getTestCaseId().intValue());
			List<TestStep> testSteps = this.testStepDao.getTestStepDetailsByTestcaseId(testCase.getTestCaseId().intValue());
			Iterator<TestStep> iterator = testSteps.iterator();
			while (iterator.hasNext()) {
				TestStep testStep = (TestStep) iterator.next();
				int testStepId = testStep.getTestStepId().getTestStepId().intValue();
				this.testParamDataDao.deleteTestParamDataByStepId(testStepId);
				this.stepParamGroupMappingDao.deleteRecordsByStepId(testStepId);
			}
			this.testStepDao.deleteTestStepByCaseId(testCase.getTestCaseId().intValue());
			logger.info("done with deleting the records");
		} catch (DataAccessException e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		}
	}

	private void insertTestCaseDependents(TestCase testCase) throws ServiceException {
		try {
			List<TestStep> testSteps = testCase.getTestSteps();
			Iterator<TestStep> testStepItr = testSteps.iterator();
			while (testStepItr.hasNext()) {
				TestStep testStep = (TestStep) testStepItr.next();
				Actions actions = testStep.getActions();
				String actionName = actions.getActionName();
				logger.info("actions name is : " + actionName);
				if (actionName != null) {
					int actionsId = this.actionsDao.getActionIdByActionName(actionName);
					actions.setUpdatedBy(System.getenv(DataConstants.DEFAULT_USER));
					actions.setUpdatedDateTime(new Date());
					if (actionsId == 0) {
						actions.setCreatedBy(System.getenv(DataConstants.DEFAULT_USER));
						actions.setCreatedDateTime(new Date());
						actionsId = this.actionsDao.insertActionsGetKey(actions);
						logger.info("actions details were inserted with id : " + actionsId);
					} else {
						actions.setActionId(new BigDecimal(actionsId));
						this.actionsDao.updateActions(actions);
						logger.info("actions details were updated with id : " + actionsId);
					}
					TestStepId stepId = testStep.getTestStepId();
					long stepOrderBy = stepId.getOrderBy();
					int testStepId = 0;
					logger.info("teststep order by value is : " + stepOrderBy);
					if (stepOrderBy != 0 && stepOrderBy > 0) {
						logger.info("Currently test step is inserting based on step order by value > 0");
						stepId.setCreatedBy(System.getenv(DataConstants.DEFAULT_USER));
						stepId.setCreatedDateTime(new Date());
						stepId.setUpdatedBy(System.getenv(DataConstants.DEFAULT_USER));
						stepId.setUpdatedDateTime(new Date());
						stepId.setActionsId(new BigDecimal(actionsId));
						stepId.setRunnerId(testCase.getRunnerId());
						stepId.setTestCaseId(testCase.getTestCaseId());
						testStep.setTestStepId(stepId);
						testStepId = this.testStepDao.insertTestStepGetKey(testStep);
						logger.info("testStep details were inserted with id : " + testStepId);
					}
					stepId.setTestStepId(new BigDecimal(testStepId));
					testStep.setTestStepId(stepId);
					testStep.setTestCase(testCase);
					insertTestStepDependents(testStep);
				} else {
					logger.error("Please specify the action name.Otherwise insertion can not be proceeded!!!");
					throw new DataAccessException("Please specify the action name.Otherwise insertion can not be proceeded!!!");
				}
			}
		} catch (DataAccessException e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		}
	}

	private void insertTestStepDependents(TestStep testStep) throws ServiceException {
		try {
			TestStepId stepId = testStep.getTestStepId();
			List<TestParamData> testParamDatas = stepId.getTestParamDatas();
			Iterator<TestParamData> paramDataItr = testParamDatas.iterator();
			while (paramDataItr.hasNext()) {
				TestParamData testParamData = (TestParamData) paramDataItr.next();
				Param parm = testParamData.getParam();
				Param param = insertParamData(parm, testStep);
				TestParamDataId paramDataId = testParamData.getTestParamDataId();
				String dataSet = paramDataId.getTestSet();
				paramDataId.setUpdatedBy(System.getenv(DataConstants.DEFAULT_USER));
				paramDataId.setUpdatedDateTime(new Date());
				paramDataId.setTestStepId(testStep.getTestStepId().getTestStepId());
				paramDataId.setTestCaseId(testStep.getTestCase().getTestCaseId());
				paramDataId.setParamId(param.getParamId());
				paramDataId.setAppId(new BigDecimal(testStep.getTestCase().getTestSuite().getTestSuiteId().getAppId().intValue()));
				logger.info("TestParamData data set is : " + dataSet);
				int testParamDataId = 0;
				if (dataSet != null) {
					logger.info("TestParamData data set is inserting details based on data set");
					paramDataId.setCreatedBy(System.getenv(DataConstants.DEFAULT_USER));
					paramDataId.setCreatedDateTime(new Date());
					testParamData.setTestParamDataId(paramDataId);
					testParamDataId = this.testParamDataDao.insertTestParamDataGetKey(testParamData);
					logger.info("testParamData details were inserted with id : " + testParamDataId);
					insertTestscenarioParamdataMap(testStep, testParamDataId);
				}
			}
		} catch (DataAccessException e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		}
	}

	private void insertTestscenarioParamdataMap(TestStep testStep, int testParamDataId) throws ServiceException {
		try {
			TestscenarioParamdataMap scenarioParamDataMap = getTestscenarioParamdataMap();
			List<SuiteScenarioMapping> suiteScenarioMappings = testStep.getTestCase().getTestSuite().getTestSuiteId().getSuiteScenarioMappingList();
			Iterator<SuiteScenarioMapping> iterator = suiteScenarioMappings.iterator();
			while (iterator.hasNext()) {
				SuiteScenarioMapping suiteScenarioMapping = (SuiteScenarioMapping) iterator.next();
				scenarioParamDataMap.setTestParamDataId(new BigDecimal(testParamDataId));
				scenarioParamDataMap.setTestScenarioId(suiteScenarioMapping.getTestScenarioId());
				this.testScenarioParamDataDao.insertTestscenarioParamdataMapGetKey(scenarioParamDataMap);
			}
		} catch (DataAccessException e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		}
	}

	private TestscenarioParamdataMap getTestscenarioParamdataMap() {
		TestscenarioParamdataMap scenarioParamDataMap = new TestscenarioParamdataMap();
		scenarioParamDataMap.setUpdatedBy(System.getenv(DataConstants.DEFAULT_USER));
		scenarioParamDataMap.setUpdatedDateTime(new Date());
		scenarioParamDataMap.setCreatedBy(System.getenv(DataConstants.DEFAULT_USER));
		scenarioParamDataMap.setCreatedDateTime(new Date());
		return scenarioParamDataMap;
	}

	@Override
	public void insertObjectsData(String xlsPath) throws ServiceException {
		try {
			ReadObjectsData readObjectsData = new ReadObjectsData();
			List<Param> params = readObjectsData.getObjectsFromExcel(xlsPath);
			Iterator<Param> iterator = params.iterator();
			while (iterator.hasNext()) {
				Param parm = (Param) iterator.next();
				Param param = insertObjectsDataFromParam(parm);
				insertParamData(param, null);
			}
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		}
	}

	private Param insertParamGroupDataFromParam(Param param, TestStep testStep) throws ServiceException {
		try {
			ParamGroup paramGroup = param.getParamGroup();
			if (paramGroup != null) {
				ParamGroupId id = paramGroup.getParamGroupId();
				String paramGroupName = id.getParamGroupName();
				logger.info("paramGroupName is : " + paramGroupName);
				if (paramGroupName != null) {
					int paramGroupId = this.paramGroupDao.getParamGroupIdDetailsOnlyByName(paramGroupName);
					id.setUpdatedBy(System.getenv(DataConstants.DEFAULT_USER));
					id.setUpdatedDateTime(new Date());
					if (testStep != null) {
						id.setAppId(testStep.getTestCase().getTestSuite().getTestSuiteId().getAppId());
					} else {
						id.setAppId(param.getObjects().getObjectsId().getAppId());
					}
					if (paramGroupId == 0) {
						id.setCreatedBy(System.getenv(DataConstants.DEFAULT_USER));
						id.setCreatedDateTime(new Date());
						paramGroup.setParamGroupId(id);
						paramGroupId = this.paramGroupDao.insertParamGroupGetKey(paramGroup);
						logger.info("paramGroup details were inserted with id : " + paramGroupId);
					} else {
						id.setParamGroupId(new BigDecimal(paramGroupId));
						paramGroup.setParamGroupId(id);
						this.paramGroupDao.updateParamGroup(paramGroup);
						logger.info("paramGroup details were updated with id : " + paramGroupId);
					}
					id.setParamGroupId(new BigDecimal(paramGroupId));
					paramGroup.setParamGroupId(id);
					param.setParamGroup(paramGroup);
					insertTestStepParamGroupMapping(param, testStep);
				}
			}
		} catch (DataAccessException e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		}
		return param;
	}

	private void insertTestStepParamGroupMapping(Param param, TestStep testStep) throws ServiceException {
		try {
			if (testStep != null) {
				TestStepParamGroupMapping stepParamGroupMapping = getTestStepParamGroupMapping();
				stepParamGroupMapping.setParamGroupId(param.getParamGroup().getParamGroupId().getParamGroupId());
				TestCase testCase = testStep.getTestCase();
				stepParamGroupMapping.setTestCaseId(testCase.getTestCaseId());
				stepParamGroupMapping.setTestStepId(testStep.getTestStepId().getTestStepId());
				TestSuiteId suiteId = testCase.getTestSuite().getTestSuiteId();
				List<SuiteScenarioMapping> suiteScenarioMappings = suiteId.getSuiteScenarioMappingList();
				Iterator<SuiteScenarioMapping> iterator2 = suiteScenarioMappings.iterator();
				while (iterator2.hasNext()) {
					SuiteScenarioMapping suiteScenarioMapping = (SuiteScenarioMapping) iterator2.next();
					stepParamGroupMapping.setTestScenarioId(suiteScenarioMapping.getTestScenarioId());
					this.stepParamGroupMappingDao.insertTestStepParamGroupMappingDetails(stepParamGroupMapping);
				}
			}
		} catch (DataAccessException e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		}
	}

	private TestStepParamGroupMapping getTestStepParamGroupMapping() {
		TestStepParamGroupMapping testStepParamGroupMapping = new TestStepParamGroupMapping();
		testStepParamGroupMapping.setUpdatedBy(System.getenv(DataConstants.DEFAULT_USER));
		testStepParamGroupMapping.setUpdatedDateTime(new Date());
		testStepParamGroupMapping.setCreatedBy(System.getenv(DataConstants.DEFAULT_USER));
		testStepParamGroupMapping.setCreatedDateTime(new Date());
		return testStepParamGroupMapping;
	}

	private Objects insertObjectGroupDataFromParam(Param param) throws ServiceException {
		try {
			Objects objects = param.getObjects();
			ObjectGroup objectGroup = insertScreenFromObjectGroup(objects.getObjectGroup());
			if (objectGroup != null) {
				String objectGroupName = objectGroup.getObjectGroupName();
				logger.info("objectGroupName is : " + objectGroupName);
				if (objectGroupName != null) {
					int objectGroupId = this.objectGroupDao.getObjectGroupWithObjectsByName(objectGroupName);
					objectGroup.setUpdatedBy(System.getenv(DataConstants.DEFAULT_USER));
					objectGroup.setUpdatedDateTime(new Date());
					if (objectGroupId == 0) {
						objectGroup.setCreatedBy(System.getenv(DataConstants.DEFAULT_USER));
						objectGroup.setCreatedDateTime(new Date());
						objectGroupId = this.objectGroupDao.insertObjectGroupGetKey(objectGroup);
						logger.info("objectGroup details were inserted with id : " + objectGroupId);
					} else {
						objectGroup.setObjectGroupId(new BigDecimal(objectGroupId));
						this.objectGroupDao.updateObjectGroups(objectGroup);
						logger.info("objectGroup details were updated with id : " + objectGroupId);
					}
					objectGroup.setObjectGroupId(new BigDecimal(objectGroupId));
					objects.setObjectGroup(objectGroup);
				}
			}
			return objects;
		} catch (DataAccessException e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		}
	}

	private ObjectGroup insertScreenFromObjectGroup(ObjectGroup objectGroup) throws ServiceException {
		try {
			if (objectGroup != null) {
				Screen screen = objectGroup.getScreen();
				int appId = insertApplicationDetails(screen.getApplication());
				String screenName = screen.getScreenName();
				logger.info("screenName is : " + screenName);
				if (screenName != null) {
					int screenId = this.screenDao.getScreenIdByScreenName(screenName);
					screen.setUpdatedBy(System.getenv(DataConstants.DEFAULT_USER));
					screen.setUpdatedDateTime(new Date());
					screen.setAppId(new BigDecimal(appId));
					if (screenId == 0) {
						screen.setCreatedBy(System.getenv(DataConstants.DEFAULT_USER));
						screen.setCreatedDateTime(new Date());
						screenId = this.screenDao.insertScreenGetKey(screen);
						logger.info("objectGroup details were inserted with id : " + screenId);
					} else {
						screen.setScreenId(new BigDecimal(screenId));
						this.screenDao.updateScreen(screen);
						logger.info("objectGroup details were updated with id : " + screenId);
					}
					objectGroup.setScreenId(new BigDecimal(screenId));
					objectGroup.setAppId(screen.getAppId());
					objectGroup.setScreen(screen);
				}
			}
			return objectGroup;
		} catch (DataAccessException e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		}
	}

	private Objects insertIdentifierTypeFromObjects(Objects objects) throws ServiceException {
		try {
			IdentifierType idType = objects.getIdentifierType();
			if (idType != null) {
				String idTypeName = idType.getIndentifierTypeName();
				logger.info("identifierTypeName is : " + idTypeName);
				if (idTypeName != null) {
					int identifierTypeId = this.identifierTypeDao.getIdentifierTypeIdOnlyByName(idTypeName);
					idType.setUpdatedBy(System.getenv(DataConstants.DEFAULT_USER));
					idType.setUpdatedDateTime(new Date());
					idType.setAppId(objects.getObjectGroup().getAppId());
					if (identifierTypeId == 0) {
						idType.setCreatedBy(System.getenv(DataConstants.DEFAULT_USER));
						idType.setCreatedDateTime(new Date());
						identifierTypeId = this.identifierTypeDao.insertIdentifierTypeGetKey(idType);
						logger.info("objectGroup details were inserted with id : " + identifierTypeId);
					} else {
						idType.setIdentifierTypeId(new BigDecimal(identifierTypeId));
						this.identifierTypeDao.updateIdentifierType(idType);
						logger.info("objectGroup details were updated with id : " + identifierTypeId);
					}
					idType.setIdentifierTypeId(new BigDecimal(identifierTypeId));
					objects.setIdentifierType(idType);
				}
			}
			return objects;
		} catch (DataAccessException e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		}
	}

	private Param insertObjectsDataFromParam(Param param) throws ServiceException {
		try {
			Objects objs = insertObjectGroupDataFromParam(param);
			Objects objects = insertIdentifierTypeFromObjects(objs);
			ObjectsId id = objects.getObjectsId();
			String objName = id.getObjectName();
			logger.info("objName is : " + objName);
			if (objName != null) {
				int objectsId = this.objectsDao.getObjectsIdOnlyByName(objName);
				id.setUpdatedBy(System.getenv(DataConstants.DEFAULT_USER));
				id.setUpdatedDateTime(new Date());
				if (objects.getIdentifierType() != null) {
					id.setAppId(objects.getIdentifierType().getAppId());
					id.setIdentifierTypeId(objects.getIdentifierType().getIdentifierTypeId());
				} else {
					id.setAppId(new BigDecimal(0));
					id.setIdentifierTypeId(new BigDecimal(0));
				}
				if (objects.getObjectGroup() != null) {
					id.setObjectGroupId(objects.getObjectGroup().getObjectGroupId());
				} else {
					id.setObjectGroupId(new BigDecimal(0));
				}
				id.setObjectTypeId(new BigDecimal(0));
				if (objectsId == 0) {
					id.setCreatedBy(System.getenv(DataConstants.DEFAULT_USER));
					id.setCreatedDateTime(new Date());
					objects.setObjectsId(id);
					objectsId = this.objectsDao.insertObjectsGetKey(objects);
					logger.info("objects details were inserted with id : " + objectsId);
				} else {
					id.setObjectId(new BigDecimal(objectsId));
					objects.setObjectsId(id);
					this.objectsDao.updateObjects(objects);
					logger.info("objects details were updated with id : " + objectsId);
				}
				id.setObjectId(new BigDecimal(objectsId));
				objects.setObjectsId(id);
				param.setObjects(objects);
			}
			return param;
		} catch (DataAccessException e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		}
	}

	private Param insertParamData(Param param, TestStep testStep) throws ServiceException {
		try {
			Param parm = insertObjectsDataFromParam(param);
			Param param2 = insertParamGroupDataFromParam(parm, testStep);
			String paramName = param2.getParamName();
			logger.info("paramName is : " + paramName);
			if (paramName != null) {
				int paramId = this.paramDao.getParamIdDetailsOnlyByName(paramName);
				param2.setUpdatedBy(System.getenv(DataConstants.DEFAULT_USER));
				param2.setUpdatedDateTime(new Date());

				param2.setParamGroupId(new BigDecimal(0));
				if (param2.getObjects() != null) {
					BigDecimal objectId = param2.getObjects().getObjectsId().getObjectId();
					if (objectId != null)
						param2.setObjectId(objectId);
					else
						param2.setObjectId(new BigDecimal(0));
				}
				if (param2.getParamGroup() != null) {
					param2.setParamGroupId(param2.getParamGroup().getParamGroupId().getParamGroupId());
				}
				if (paramId == 0) {
					param2.setCreatedBy(System.getenv(DataConstants.DEFAULT_USER));
					param2.setCreatedDateTime(new Date());
					paramId = this.paramDao.insertParamGetKey(param2);
					logger.info("param details were inserted with id : " + paramId);
				} else {
					param2.setParamId(new BigDecimal(paramId));
					this.paramDao.updateParam(param2);
					logger.info("param details were updated with id : " + paramId);
				}
				param2.setParamId(new BigDecimal(paramId));
			}
			return param2;
		} catch (DataAccessException e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		}
	}

	private void insertTestSuiteDependents(TestSuite testSuite) throws ServiceException {
		try {
			TestSuiteId suiteId = testSuite.getTestSuiteId();
			List<TestCase> testCases = suiteId.getTestCases();
			Iterator<TestCase> testCaseItr = testCases.iterator();
			while (testCaseItr.hasNext()) {
				TestCase testCase = (TestCase) testCaseItr.next();
				Runner runner = testCase.getRunner();
				String runnerName = runner.getRunnerName();
				logger.info("runner name is : " + runnerName);
				if (runnerName != null) {
					int runnerId = this.runnerDao.getRunnerIdOnlyByName(runnerName);
					runner.setUpdatedBy(System.getenv(DataConstants.DEFAULT_USER));
					runner.setUpdatedDateTime(new Date());
					if (runnerId == 0) {
						runner.setCreatedBy(System.getenv(DataConstants.DEFAULT_USER));
						runner.setCreatedDateTime(new Date());
						runnerId = this.runnerDao.insertRunnerGetKey(runner);
						logger.info("runner details were inserted with id : " + runnerId);
					} else {
						runner.setRunnerId(new BigDecimal(runnerId));
						this.runnerDao.updateRunner(runner);
						logger.info("runner details were updated with id : " + runnerId);
					}
					testCase.setRunnerId(new BigDecimal(runnerId));
					testCase.setTestSuiteId(suiteId.getTestSuiteId());
					if (testCase.getOrderBy() == null) {
						logger.warn("since order by was not set in test case, setting to '0' ");
						testCase.setOrderBy(Long.valueOf(0L));
					}
					String testCaseName = testCase.getCaseName();
					logger.info("testCaseName is : " + testCaseName);
					if (testCaseName != null) {
						int testCaseId = this.testCaseDao.getTestCaseDetailsOnlyByName(testCaseName);
						testCase.setUpdatedBy(System.getenv(DataConstants.DEFAULT_USER));
						testCase.setUpdatedDateTime(new Date());
						if (testCaseId == 0) {
							testCase.setCreatedBy(System.getenv(DataConstants.DEFAULT_USER));
							testCase.setCreatedDateTime(new Date());
							testCaseId = this.testCaseDao.insertTestCaseGetKey(testCase);
							logger.info("testCase details were inserted with id : " + testCaseId);
						} else {
							testCase.setTestCaseId(new BigDecimal(testCaseId));
							this.testCaseDao.updateTestCase(testCase);
							logger.info("testCase details were updated with id : " + testCaseId);
						}
						testCase.setTestCaseId(new BigDecimal(testCaseId));
					}
					testCase.setTestSuite(testSuite);
					deleteTestStepDependents(testCase);
					insertTestCaseDependents(testCase);
				} else {
					logger.error("Please specify the runner name for testcase. Otherwise data insertion can not be proceeded!!!");
					throw new DataAccessException("Please specify the runner name for testcase. Otherwise data insertion can not be proceeded!!!");
				}
			}
		} catch (DataAccessException e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		}
	}

	private void insertTestSuiteData(Feature feature, SuiteScenarioMapping suiteScenarioMapping) throws ServiceException {
		try {
			List<TestSuite> testSuites = feature.getTestSuites();
			Iterator<TestSuite> testSuiteItr = testSuites.iterator();
			List<SuiteScenarioMapping> suiteScenarioMappings = new ArrayList<SuiteScenarioMapping>();
			while (testSuiteItr.hasNext()) {
				TestSuite testSuite = (TestSuite) testSuiteItr.next();
				TestSuiteId suiteId = testSuite.getTestSuiteId();
				suiteId.setAppId(feature.getFunctional().getAppId());
				suiteId.setFunctionalId(feature.getFunctionalId());
				suiteId.setFeatureId(feature.getFeatureId());
				suiteId.setUpdatedBy(System.getenv(DataConstants.DEFAULT_USER));
				suiteId.setUpdatedDateTime(new Date());
				if (suiteId.getOrderBy() == null) {
					logger.warn("since order by was not set in test suite, setting to '0' ");
					suiteId.setOrderBy(Long.valueOf(0L));
				}
				String testSuiteName = suiteId.getSuiteName();
				logger.info("testSuite name is : " + testSuiteName);
				if (testSuiteName != null) {
					int testSuiteId = this.testSuiteDao.getTestSuiteDetailsOnlyByName(testSuiteName);
					if (testSuiteId == 0) {
						suiteId.setCreatedBy(System.getenv(DataConstants.DEFAULT_USER));
						suiteId.setCreatedDateTime(new Date());
						testSuite.setTestSuiteId(suiteId);
						testSuiteId = this.testSuiteDao.insertTestSuiteGetKey(testSuite);
						logger.info("testSuite details were inserted with id : " + testSuiteId);
					} else {
						suiteId.setTestSuiteId(new BigDecimal(testSuiteId));
						testSuite.setTestSuiteId(suiteId);
						this.testSuiteDao.updateTestSuite(testSuite);
						logger.info("testSuite details were updated with id : " + testSuiteId);
					}
					suiteScenarioMapping.setTestSuiteId(new BigDecimal(testSuiteId));
					int suiteScenarioId = this.suiteScenarioMappingDao.insertSuiteScenarioMappingGetKey(suiteScenarioMapping);
					suiteScenarioMapping.setSuiteScenarioId(new BigDecimal(suiteScenarioId));
					suiteScenarioMappings.add(suiteScenarioMapping);
					suiteId.setSuiteScenarioMappingList(suiteScenarioMappings);
					suiteId.setTestSuiteId(new BigDecimal(testSuiteId));
					testSuite.setTestSuiteId(suiteId);
				}
				insertTestSuiteDependents(testSuite);
			}
		} catch (DataAccessException e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void insertReadPlanData(String xlsPath) throws ServiceException {
		try {
			ReadPlan readPlan = new ReadPlan();
			TestPlan testPlan = readPlan.readPlanObj(xlsPath, null);
			Functional functional = insertFunctionDetails(testPlan);
			List<Feature> features = functional.getFeatures();
			Iterator<Feature> iterator = features.iterator();
			SuiteScenarioMapping suiteScenarioMapping = null;
			while (iterator.hasNext()) {
				Feature feature = (Feature) iterator.next();
				suiteScenarioMapping = insertTestScenarioData(feature);
				insertTestSuiteData(feature, suiteScenarioMapping);
				String planRunName = testPlan.getTestPlanRunName();
				logger.info("planRunName is : " + planRunName);
				if (planRunName != null) {
					int testPlanId = this.testPlanDao.getTestPlanIdOnlyByName(planRunName);
					TestPlanId id = testPlan.getTestPlanId();
					id.setPlanName(planRunName);
					id.setUpdatedBy(System.getenv(DataConstants.DEFAULT_USER));
					id.setUpdatedDateTime(new Date());
					logger.warn("setting default values from pre/post conditions as '0'");
					id.setPreConditionGroupId(new BigDecimal(0));
					id.setPostConditionGroupId(new BigDecimal(0));
					if (testPlanId == 0) {
						id.setCreatedBy(System.getenv(DataConstants.DEFAULT_USER));
						id.setCreatedDateTime(new Date());
						testPlan.setTestPlanId(id);
						testPlanId = this.testPlanDao.insertTestPlanGetKey(testPlan);
						logger.info("testPlan details were inserted with id : " + testPlanId);
					} else {
						id.setTestPlanId(new BigDecimal(testPlanId));
						testPlan.setTestPlanId(id);
						this.testPlanDao.updateTestPlan(testPlan);
						logger.info("testPlan details were updated with id : " + testPlanId);
					}
					id.setTestPlanId(new BigDecimal(testPlanId));
					if (suiteScenarioMapping != null) {
						TestplanTestscenarioMap planSceMap = getTestplanTestscenarioMap();
						planSceMap.setTestPlanId(id.getTestPlanId());
						planSceMap.setTestScenarioId(suiteScenarioMapping.getTestScenarioId());
						this.testPlanScenarioMapDao.insertTestplanTestscenarioDetails(planSceMap);
					}
				} else {
					throw new DataAccessException("Please provide test plan run name");
				}
			}
		} catch (DataAccessException e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		}
	}

	private SuiteScenarioMapping insertTestScenarioData(Feature feature) throws ServiceException {
		try {
			List<TestScenario> testScenarios = feature.getTestScenarios();
			Iterator<TestScenario> testScenarioItr = testScenarios.iterator();
			while (testScenarioItr.hasNext()) {
				TestScenario testScenario = testScenarioItr.next();
				testScenario.setAppId(feature.getFunctional().getAppId());
				testScenario.setUpdatedBy(System.getenv(DataConstants.DEFAULT_USER));
				testScenario.setUpdatedDateTime(new Date());
				String testScenarioName = testScenario.getTestScenarioName();
				logger.info("testScenario name is : " + testScenarioName);
				if (testScenarioName != null) {
					int testScenarioId = this.testScenarioDao.getTestScenarioByName(testScenarioName);
					if (testScenarioId == 0) {
						testScenario.setCreatedBy(System.getenv(DataConstants.DEFAULT_USER));
						testScenario.setCreatedDateTime(new Date());
						testScenarioId = this.testScenarioDao.insertTestScenarioGetKey(testScenario);
						logger.info("testScenario details were inserted with id : " + testScenarioId);
					} else {
						testScenario.setTestScenarioId(new BigDecimal(testScenarioId));
						this.testScenarioDao.updateTestScenario(testScenario);
						logger.info("testScenario details were updated with id : " + testScenarioId);
					}
					SuiteScenarioMapping suiteScenarioMapping = getSuiteScenarioMapping();
					suiteScenarioMapping.setTestScenarioId(new BigDecimal(testScenarioId));
					return suiteScenarioMapping;
				}
			}
		} catch (DataAccessException e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		}
		return null;
	}

	private SuiteScenarioMapping getSuiteScenarioMapping() {
		SuiteScenarioMapping suiteScenarioMapping = new SuiteScenarioMapping();
		suiteScenarioMapping.setUpdatedBy(System.getenv(DataConstants.DEFAULT_USER));
		suiteScenarioMapping.setUpdatedDateTime(new Date());
		suiteScenarioMapping.setCreatedBy(System.getenv(DataConstants.DEFAULT_USER));
		suiteScenarioMapping.setCreatedDateTime(new Date());
		return suiteScenarioMapping;
	}

	private TestplanTestscenarioMap getTestplanTestscenarioMap() {
		TestplanTestscenarioMap testplanScenarioMap = new TestplanTestscenarioMap();
		testplanScenarioMap.setUpdatedBy(System.getenv(DataConstants.DEFAULT_USER));
		testplanScenarioMap.setUpdatedDateTime(new Date());
		testplanScenarioMap.setCreatedBy(System.getenv(DataConstants.DEFAULT_USER));
		testplanScenarioMap.setCreatedDateTime(new Date());
		return testplanScenarioMap;
	}

	private Functional insertFunctionDetails(TestPlan testPlan) throws ServiceException {
		try {
			List<Functional> functionals = testPlan.getTestPlanId().getFunctionals();
			Iterator<Functional> iterator = functionals.iterator();
			Functional functional = null;
			while (iterator.hasNext()) {
				functional = (Functional) iterator.next();
				String funName = functional.getFunctionalName();
				String functionName = funName;
				if (funName != null && funName.contains(":")) {
					functionName = StringUtils.substringAfter(funName, ":");
				}
				logger.info("functional name is : " + functionName);
				int functionalId = this.functionalDao.getFunctionalIdOnlyByName(functionName);
				int appId = insertApplicationDetails(functional.getApplication());
				functional.setAppId(new BigDecimal(appId));
				functional.setUpdatedDateTime(new Date());
				functional.setUpdatedBy(System.getenv(DataConstants.DEFAULT_USER));
				functional.setFunctionalName(functionName);
				if (functionalId == 0) {
					functional.setCreatedBy(System.getenv(DataConstants.DEFAULT_USER));
					functional.setCreatedDateTime(new Date());
					functionalId = this.functionalDao.insertFunctionalGetKey(functional);
					logger.info("functional details were inserted with id : " + functionalId);
				} else {
					functional.setFunctionalId(new BigDecimal(functionalId));
					this.functionalDao.updateFunctionalDetails(functional);
					logger.info("functional details were updated with id : " + functionalId);
				}
				functional.setFunctionalId(new BigDecimal(functionalId));
				functional = insertFunctionalDependents(functional);
			}
			return functional;
		} catch (DataAccessException e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		}
	}

	private int insertApplicationDetails(Application application) throws ServiceException {
		try {
			String appName = application.getAppName();
			String applicationName = appName;
			if (appName != null && appName.contains(":")) {
				applicationName = StringUtils.substringAfter(appName, ":");
			}
			logger.info("application name is : " + applicationName);
			int appId = this.applicationDao.getAppIdByAppName(applicationName);
			application.setAppName(applicationName);
			application.setUpdatedDateTime(new Date());
			application.setUpdatedBy(System.getenv(DataConstants.DEFAULT_USER));
			if (appId == 0) {
				application.setCreatedBy(System.getenv(DataConstants.DEFAULT_USER));
				application.setCreatedDateTime(new Date());
				appId = this.applicationDao.insertApplicationGetKey(application);
				logger.info("application details were inserted with id : " + appId);
			} else {
				application.setAppId(new BigDecimal(appId));
				this.applicationDao.updateApplication(application);
				logger.info("application details were updated with id : " + appId);
			}
			return appId;
		} catch (DataAccessException e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		}
	}

	private Functional insertFunctionalDependents(Functional functional) throws ServiceException {
		try {
			List<Feature> features = functional.getFeatures();
			List<Feature> featuresList = new ArrayList<Feature>();
			Iterator<Feature> iterator = features.iterator();
			while (iterator.hasNext()) {
				Feature feature = (Feature) iterator.next();
				String feaName = feature.getFeatureName();
				String featureName = feaName;
				if (feaName != null && feaName.contains(":")) {
					featureName = StringUtils.substringAfter(feaName, ":");
				}
				logger.info("feature name is : " + featureName);
				int featureId = this.featureDao.getFeatureIdOnlyByName(featureName);
				feature.setFeatureName(featureName);
				feature.setUpdatedBy(System.getenv(DataConstants.DEFAULT_USER));
				feature.setUpdatedDateTime(new Date());
				if (featureId == 0) {
					feature.setCreatedBy(System.getenv(DataConstants.DEFAULT_USER));
					feature.setCreatedDateTime(new Date());
					feature.setFunctionalId(functional.getFunctionalId());
					featureId = this.featureDao.insertFeatureGetKey(feature);
					logger.info("feature details were inserted with id : " + featureId);
				} else {
					feature.setFeatureId(new BigDecimal(featureId));
					this.featureDao.updateFeature(feature);
					logger.info("feature details were updated with id : " + featureId);
				}
				feature.setFeatureId(new BigDecimal(featureId));
				feature.setFunctional(functional);
				featuresList.add(feature);
			}
			functional.setFeatures(featuresList);
			return functional;
		} catch (DataAccessException e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new ServiceException(e.getMessage());
		}
	}

}
