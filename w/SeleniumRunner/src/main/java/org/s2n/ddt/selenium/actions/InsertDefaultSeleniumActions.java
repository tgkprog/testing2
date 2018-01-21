package org.s2n.ddt.selenium.actions;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.pojo.Actions;
import org.s2n.ddt.service.MainService;

public class InsertDefaultSeleniumActions {
	public static final String ACTION_SCRIPT_CLASS = "org.s2n.ddt.runner.selenium.ActionScript";

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(InsertDefaultSeleniumActions.class);

	private MainService mainService;

	public void setMainService(MainService mainService) {
		this.mainService = mainService;
	}

	@SuppressWarnings("rawtypes")
	private List<String> getDefaultSeleniumActions() throws ClassNotFoundException {
		Class actionClass = Class.forName(InsertDefaultSeleniumActions.ACTION_SCRIPT_CLASS);
		List<String> actionNames = new ArrayList<String>();
		Method[] methods = actionClass.getMethods();
		for (Method method : methods) {
			actionNames.add(method.getName());
		}
		logger.info("selenium action names : " + actionNames);
		return actionNames;
	}

	public void setActionDetailsInDB() throws DataAccessException, ClassNotFoundException {
		List<String> dbActionNames = mainService.getAllActionNames();
		logger.info("database action names : " + dbActionNames);
		List<String> selActionNames = getDefaultSeleniumActions();
		Iterator<String> iterator = selActionNames.iterator();
		while (iterator.hasNext()) {
			String selActionName = (String) iterator.next();
			if (!dbActionNames.contains(selActionName)) {
				insertSeleniumActions(selActionName);
			}
		}
	}

	private void insertSeleniumActions(String actionName) throws DataAccessException {
		Actions actions3 = new Actions();
		actions3.setActionName(actionName);
		actions3.setDescription(actionName);
		actions3.setCreatedBy(System.getenv("USER"));
		actions3.setCreatedDateTime(new Date());
		actions3.setUpdatedBy(System.getenv("USER"));
		actions3.setUpdatedDateTime(new Date());
		mainService.insertActionsDetails(actions3);
	}

}
