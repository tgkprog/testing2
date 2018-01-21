package org.s2n.ddt.test;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.s2n.ddt.bean.UtlConf;
import org.s2n.ddt.service.InputService;

public class DaoInsertionTest {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DaoInsertionTest.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml", "dataAccessContext-local.xml" });

		InputService inputService = (InputService) context.getBean("inputService");
		inputService.insertReadPlanData(UtlConf.getProperty("xlsPath.path"));
		inputService.insertObjectsData(UtlConf.getProperty("xlsPath.path"));
		logger.info("insertion completed");
	}

}
