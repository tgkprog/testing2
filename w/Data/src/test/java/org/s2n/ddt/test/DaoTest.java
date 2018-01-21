package org.s2n.ddt.test;

import java.math.BigDecimal;
import java.sql.Date;

import org.apache.log4j.Logger;

import org.s2n.ddt.dao.impl.ApplicationDaoImpl;
import org.s2n.ddt.dao.impl.FunctionalDaoImpl;
import org.s2n.ddt.pojo.Application;
import org.s2n.ddt.pojo.Feature;
import org.s2n.ddt.pojo.Functional;
import org.s2n.ddt.pojo.Screen;
import org.s2n.ddt.pojo.input.ObjectGroup;
import org.s2n.ddt.utils.DBUtils;

public class DaoTest {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DaoTest.class);

	public static void main(String[] args) throws Exception {
		ApplicationDaoImpl impl12 = new ApplicationDaoImpl();
		impl12.setDataSource(DBUtils.getDataSource());
		Application application = impl12.getApplicationDetailsTillFunctional(1);
		if (application != null)
			logger.info(application.toString());
		else
			logger.info("empty");
		FunctionalDaoImpl impl2 = new FunctionalDaoImpl();
		impl2.setDataSource(DBUtils.getDataSource());
		Functional functional = impl2.getFunctionalDetailsById(1);
		System.out.println(functional);
	}

	private static BigDecimal getRandom() {
		return new BigDecimal(1 + (int) (Math.random() * 10000));
	}

	private static String getUser() {
		return System.getenv("USER");
	}

	private static Date getDate() {
		return new Date(3213246546456L);
	}

	private static Application getApplication() {
		Application application = new Application();
		// application.setAppId(getRandom());
		application.setAppName("SampleSpringData");
		application.setDescription("SampleSpringData-Description");
		application.setCreatedBy(getUser());
		application.setCreatedDateTime(getDate());
		application.setUpdatedBy(getUser());
		application.setUpdatedDateTime(getDate());
		return application;
	}

	protected static Application getApplication1() {
		Application application = new Application();
		application.setAppName("SampleSpringData");
		application.setDescription("SampleSpringData-Description");
		application.setCreatedBy("Anand");
		application.setCreatedDateTime(getDate());
		application.setUpdatedBy("Anand");
		application.setUpdatedDateTime(getDate());
		return application;
	}

	private static Functional getFunctional() {
		Functional functional = new Functional();
		functional.setApplication(getApplication());
		functional.setFunctionalId(new BigDecimal(7895));
		functional.setFunctionalName("SampleSpringData-functional");
		functional.setDescription("SampleSpringData-Description-functional");
		functional.setCreatedBy(getUser());
		functional.setCreatedDateTime(getDate());
		functional.setUpdatedBy(getUser());
		functional.setUpdatedDateTime(getDate());
		return functional;
	}

	public static Feature getfeature() {
		Feature feature = new Feature();
		feature.setFunctional(getFunctional());
		feature.setFeatureId(getRandom());
		feature.setFeatureName("SampleSpringData-Feature");
		feature.setDescription("SampleSpringData-Description-Feature");
		feature.setCreatedBy(getUser());
		feature.setCreatedDateTime(getDate());
		feature.setUpdatedBy(getUser());
		feature.setUpdatedDateTime(getDate());
		return feature;
	}

	public static Screen getScreen() {
		Screen screen = new Screen();
		screen.setApplication(getApplication());
		screen.setScreenId(getRandom());
		screen.setScreenName("SampleSpringData-Screen");
		screen.setDescription("SampleSpringData-Description-Object Group");
		screen.setCreatedBy(getUser());
		screen.setCreatedDateTime(getDate());
		screen.setUpdatedBy(getUser());
		screen.setUpdatedDateTime(getDate());
		return screen;
	}

	public static ObjectGroup getObjectGroup() {
		ObjectGroup objGroup = new ObjectGroup();
		objGroup.setApplication(getApplication());
		objGroup.setScreen(getScreen());
		objGroup.setObjectGroupId(getRandom());
		objGroup.setObjectGroupName("SampleSpringData-Object group");
		objGroup.setDescription("SampleSpringData-Description-Object Group");
		objGroup.setCreatedBy(getUser());
		objGroup.setCreatedDateTime(getDate());
		objGroup.setUpdatedBy(getUser());
		objGroup.setUpdatedDateTime(getDate());
		return objGroup;
	}
}
