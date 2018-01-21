package org.s2n.ddt.test;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.s2n.ddt.pojo.output.TestPlan;
import org.s2n.ddt.service.InputService;

public class DaoFetchDataTest {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DaoFetchDataTest.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml", "dataAccessContext-local.xml" });

		InputService inputService = (InputService) context.getBean("inputService");
		TestPlan testPlan = inputService.getDeepTestPlan(19);
		logger.info(testPlan);
		logger.info("done");
	}

}
