/**
t * The  Class Action Script is driven by the agent
 * Performs the defined actions over the application.
 * It will Invoke different types of browsers.
 * Validates the UI input fields.
 * Appends the result after validation to the driver.
 **/

package org.s2n.ddt.runner.selenium;

import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.s2n.ddt.bean.ActionScriptException;
import org.s2n.ddt.bean.UtlConf;
import org.s2n.ddt.common.DdtCoreUtls;
import org.s2n.ddt.common.share.CommonInformation;
import org.s2n.ddt.pojo.TestSuite;
import org.s2n.ddt.pojo.input.TestCase;
import org.s2n.ddt.pojo.input.TestStep;
import org.s2n.ddt.pojo.output.TestCaseResult;
import org.s2n.ddt.pojo.output.TestPlan;
import org.s2n.ddt.pojo.output.TestStepResult;
import org.s2n.ddt.pojo.output.TestSuiteResultSummary;
import org.s2n.ddt.util.ActionUtils;
import org.s2n.ddt.util.RobotHelper;

/**
 * The Class ActionScript.
 */
public class ActionScript {
	/** The logger. */
	private static final Logger logger = Logger.getLogger(ActionScript.class);
	private org.s2n.ddt.common.ParamFromTstGenClz paramFromExt = null;
	private Map<String, String> replaceStrs = new HashMap<String, String>();

	/** The o browser. */
	private WebDriver oBrowser;
	private String winHandleBefore = null;
	private String browserCheck;

	private Map<Integer, String> objectPathMap = new HashMap<Integer, String>();
	/** The capture. */
	String capture;

	RobotHelper robot = new RobotHelper();
	private CommonInformation ci;

	/**
	 * Invokes the type of browser, specified by the user.
	 * 
	 * @param ac
	 *            the ac
	 * @throws ActionScriptException
	 *             the action script exception
	 * @throws MalformedURLException
	 */
	public void launchBrowser(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep, TestStepResult stepResult,
			TestSuiteResultSummary suiteSummaryResult) throws ActionScriptException, MalformedURLException {
		logger.info("Calling Launch Browser Action");
		String browserName = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getParamName();
		browserCheck = browserName;
		logger.info("browserName  " + browserName);
		File profileDirectory = new File(testStep.getStepParam());
		boolean foundBrowser = false;
		boolean chromeBrowser = false;
		if (browserName.equalsIgnoreCase("FireFox")) {
			FirefoxDriver drv = null;
			if(profileDirectory == null || !profileDirectory.exists()){
				drv = new FirefoxDriver();
			}else{
				FirefoxProfile profile = new FirefoxProfile(profileDirectory);
				drv = new FirefoxDriver(profile);
			}
			setoBrowser(drv);	
			foundBrowser = true;
		} else if (browserName.equalsIgnoreCase("Safari")) {
			setoBrowser(new SafariDriver());
			foundBrowser = true;

		} else if (browserName.equalsIgnoreCase("chrome")) {

			if (profileDirectory.exists()) {

				System.setProperty("webdriver.chrome.driver", testStep.getStepParam());
				setoBrowser(new ChromeDriver());
				foundBrowser = true;
			} else {
				logger.error("Chrome Browser could not launch.Please verify whether you a chrome browser supporting selenium ");
				chromeBrowser = true;
			}
		}
		if (chromeBrowser) {
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false,
					"Chrome Browser could not launch.Please verify whether you a chrome browser supporting selenium ", null, null);
			logger.error("Chrome Browser could not launch.Please verify whether you a chrome browser supporting selenium ");
		} else {
			if (foundBrowser == false) {

				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, browserName + "   not supported provide a valid browser", null, null);
				logger.error(browserName + "   not supported provide a valid browser");
			} else if (getoBrowser().getWindowHandle().isEmpty()) {
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Browser not found", null, null);
				logger.error("Browser not supported.Provide a valid browser");

			} else {
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, browserName + "  launched successfully ", null, null);
				logger.info(browserName + " browser  launched successfully");
			}
		}
	}

	/**
	 * Passes the url to url tab, specified by the user.
	 * 
	 * @param ac
	 *            the ac
	 * @throws ActionScriptException
	 *             the action script exception
	 */
	public void launchApplication(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep,
			TestStepResult stepResult, TestSuiteResultSummary suiteSummaryResult) throws ActionScriptException {

		logger.info("Calling loadUrl Action");
		String screen = null;
		String url = null;
		try {
			url = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getParamName();
			getoBrowser().get(url);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Navigated to the " + "  " + url + " " + "  URL", null, null);
			logger.info("Browser navigated to URL");
			waitForPageToLoad(20);

		} catch (org.openqa.selenium.WebDriverException e) {
			screen = snapShot(plan);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "URL not found", null, screen);

			logger.info("The url mentioned is not found.Please pass a valid url");
		} catch (NullPointerException e) {
			screen = snapShot(plan);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "verify url/verify the browser launched", null, screen);
			logger.info("verify url/verify the browser launched");
		}

	}

	public void highLightById(String id) throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) getoBrowser();

		WebElement element = getoBrowser().findElement(By.id(id));
		js.executeScript("arguments[0].style.border='2px solid red'", element);
		Thread.sleep(100);
		js.executeScript("arguments[0].style.border=''", element);
	}

	public void highLightByXpath(String xpath) throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) getoBrowser();
		WebElement element = getoBrowser().findElement(By.xpath(xpath));
		js.executeScript("arguments[0].style.border='2px solid red'", element);
		Thread.sleep(2000);
		js.executeScript("arguments[0].style.border=''", element);
	}

	public void verifyButtonEnabled(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep,
			TestStepResult stepResult, TestSuiteResultSummary suiteSummaryResult) throws InterruptedException, ActionScriptException {
		String objectPath = null;
		String screen = null;
		try {
			objectPath = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier();
			org.openqa.selenium.WebElement ele = findElement(objectPath);
			if (ele.isEnabled()) {
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Button Enabled", null, null);
				logger.info("Button enabled");
			} else {
				screen = snapShot(plan);
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Button Disabled", null, screen);
				logger.error("Button is not enabled");
			}

		} catch (org.openqa.selenium.WebDriverException ex) {
			screen = snapShot(plan);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Invalid object or Object not found ", null, screen);
			logger.error("Button not found");
		}
	}

	public void verifyTextDropDown(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep,
			TestStepResult stepResult, TestSuiteResultSummary suiteSummaryResult) throws InterruptedException, ActionScriptException {
		waitForPageToLoad(2000);
		String objectPath = null;
		String textValue = null;
		String screen = null;
		try {
			objectPath = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier();
			;

			textValue = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getParamName();
			org.openqa.selenium.WebElement ele = findElement(objectPath);
			Select select = new Select(ele);
			select.getFirstSelectedOption();
			List<WebElement> options = select.getOptions();
			for (WebElement we : options) {
				if (textValue.equals(we.getText())) {
					org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Drop down text verified", null, null);
					logger.info("Drop down text verified");
					break;
				} else {
					screen = snapShot(plan);
					org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Drop down text not verified", null, screen);
					logger.error("Drop down text not verified");
				}
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			screen = snapShot(plan);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Invalid object or Object not found ", null, screen);
			logger.error("Drop down text not verified");
		}
	}

	public void uploadFiles(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep, TestStepResult stepResult,
			TestSuiteResultSummary suiteSummaryResult) throws ActionScriptException, InterruptedException {

		logger.info("Calling Upload Action");
		String objectPath = null;
		String value = null;
		String screen = null;

		try {
			objectPath = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier();
			value = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getParamName();
			boolean bool = RobotHelper.sendKeys(value, 100, false, false, true, false, 'G', false);
			logger.info("  result from bool " + bool);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "File has been uploaded", null, null);
			logger.info("File has been uploaded");

		} catch (org.openqa.selenium.NoSuchElementException e) {
			screen = snapShot(plan);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Invalid object or Object not found ", null, screen);
			logger.error("Enter button is not clciked");
		}

	}

	public void selectTextDropDown(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep,
			TestStepResult stepResult, TestSuiteResultSummary suiteSummaryResult) throws InterruptedException, ActionScriptException {
		waitForPageToLoad(2000);
		String objectPath = null;
		String textValue = null;
		String screen = null;

		boolean foundElement = false;

		try {
			objectPath = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier();
			textValue = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getParamName();
			org.openqa.selenium.WebElement ele = findElement(objectPath);
			Select select = new Select(ele);
			List<WebElement> options = select.getOptions();
			for (WebElement wElement : options) {
				logger.info(" textValue " + textValue + "   wElement.getText()  " + wElement.getText());
				if (textValue.equals(wElement.getText().trim())) {
					select.selectByVisibleText(textValue);
					org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Drop down text selected", null, null);
					logger.info("Drop down text selected");
					foundElement = true;
					break;
				}
			}
			if (!foundElement) {
				screen = snapShot(plan);
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Text not present in the dropdown ", null, screen);
				logger.error("text not present in the dropdown ");
			}

		} catch (org.openqa.selenium.NoSuchElementException e) {
			screen = snapShot(plan);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Invalid object or Object not found ", null, screen);
			logger.error("text not present in the dropdown ");
		}
	}

	public void verifyButtonDisabled(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep,
			TestStepResult stepResult, TestSuiteResultSummary suiteSummaryResult) throws InterruptedException, ActionScriptException {
		waitForPageToLoad(2000);
		String objectPath = null;
		String screen = null;
		try {
			objectPath = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier();
			;
			org.openqa.selenium.WebElement ele = findElement(objectPath);
			if (!(ele.isEnabled())) {
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Button Disabled", null, null);
				logger.info("Button Disabled");
			} else {
				screen = snapShot(plan);
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Button is not Disabled", null, screen);
				logger.error("Button is not Disabled");
			}
		} catch (org.openqa.selenium.WebDriverException ex) {
			screen = snapShot(plan);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Invalid object or Object not found ", null, screen);
			logger.error("Button not found");
		}
	}

	public void clearFieldValue(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep,
			TestStepResult stepResult, TestSuiteResultSummary suiteSummaryResult) throws InterruptedException, ActionScriptException {
		waitForPageToLoad(5000);
		String objectPath = null;
		String screen = null;
		String placevalue1 = null;
		try {
			objectPath = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier();
			;
			highLightById(objectPath);
			JavascriptExecutor js = (JavascriptExecutor) getoBrowser();
			Thread.sleep(1000);
			js.executeScript("document.getElementById('" + objectPath + "').value='';");
			Thread.sleep(500);
			placevalue1 = (String) js.executeScript("return document.getElementById('" + objectPath + "').value;");
			if (placevalue1.equals("")) {
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Field Cleared", null, null);
				logger.error("Field Cleared");
			} else {
				screen = snapShot(plan);
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Field not Cleared", null, screen);
				logger.error("Field not Cleared");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			screen = snapShot(plan);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Invalid object or Object not found ", null, screen);
			logger.error("Invalid object or Object not found ");
		}
	}

	public void moveBack(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep, TestStepResult stepResult,
			TestSuiteResultSummary suiteSummaryResult) throws ActionScriptException {
		logger.info("Calling moveBack action");
		String screen = null;
		try {
			getoBrowser().navigate().back();
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Moved back a single item.", null, null);
			logger.info("Moved back a single item.");
		} catch (Exception e) {
			screen = snapShot(plan);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Browser not navigated Back", null, screen);
			logger.info("Browser not navigated Back");
		}
	}

	public void moveForward(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep, TestStepResult stepResult,
			TestSuiteResultSummary suiteSummaryResult) throws ActionScriptException {
		String screen = null;
		try {
			getoBrowser().navigate().forward();
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Moved forward a single item.", null, null);
			logger.info("Moved forward a single item.");
		} catch (Exception e) {
			screen = snapShot(plan);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Browser not navigated forward", null, screen);
			logger.info("Browser not navigated forward");
		}

	}

	/*
	 * public void uploadFiles(TestStep testStep, TestStepResult
	 * stepResult,TestPlan plan) throws ActionScriptException {
	 * 
	 * logger.info("Calling Upload Action"); ArrayList<String> getVal =
	 * ac.getValues(); String value =
	 * testStep.getTestStepId().getTestParamDatas(
	 * ).get(0).getParam().getParamName(); try { RobotHelper.presskeys(value,
	 * 100, true, false, true, false, 'G', true);
	 * org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult,
	 * true,"Navigated to the " + "  " + url + " " + "  URL", null,null);
	 * stepResult.setResult(true);
	 * stepResult.setComment("File has been uploaded");
	 * logger.info("File has been uploaded");
	 * 
	 * } catch (org.openqa.selenium.NoSuchElementException e) {
	 * 
	 * screen = snapShot(plan);
	 * org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult,
	 * false,"Browser not found", null,screen); stepResult.setResult(false);
	 * stepResult.setComment("Invalid object or Object not found "); screen =
	 * snapShot(plan); stepResult.setSnapShot(screen);
	 * logger.error("Enter button is not clciked"); }
	 * 
	 * }
	 */
	@Deprecated
	public void snooze(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep, TestStepResult stepResult,
			TestSuiteResultSummary suiteSummaryResult) throws ActionScriptException {

		logger.fatal(" snooze function deprecated in selenium runner use in Core but put delay in testStep param and blank in object param, will remove in next version from selenium");
		String screen = null;
		int delay = Integer.parseInt(testStep.getTestStepId().getTestParamDatas().get(0).getParam().getParamName());
		try {
			Thread.sleep(delay*1000);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Page snoozed", null, null);
			logger.info("Page snoozed");

		} catch (InterruptedException interupt) {
			screen = snapShot(plan);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Interrupt occurred while snooze", null, screen);
			logger.error("Interrupt occurred while snooze");
		}
	}

	/**
	 * If this works sometimes and not at other times : need to make sure
	 * control already has focus and currently browser window with your page has
	 * focus, this is a OS level key event so which ever window has focus will
	 * get the tab key
	 * 
	 * @throws ActionScriptException
	 * */

	public void tabOutAction(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep, TestStepResult stepResult,
			TestSuiteResultSummary suiteSummaryResult) throws AWTException, InterruptedException, ActionScriptException {
		logger.info("calling tab out action 1 second sleep and then 10 seconds wait for object load");
		if (browserCheck != null && browserCheck.length() != 0 && browserCheck.equalsIgnoreCase("safari")) {
			tabOutWithJs(plan, suite, testCase, caseResult, testStep, stepResult, suiteSummaryResult);
		} else {
			tabOutNative(plan, suite, testCase, caseResult, testStep, stepResult, suiteSummaryResult);
		}
	}

	public void tabOutNative(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep, TestStepResult stepResult,
			TestSuiteResultSummary suiteSummaryResult) throws AWTException, InterruptedException {
		logger.info("calling tab out action 1 second sleep and then 10 seconds wait for object load");
		if (RobotHelper.tabOut()) {
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "tabed out", null, null);
			logger.info("Tabbed out in tabOutAction");
		} else {
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Failure to tabout", null, null);
			logger.info("Failure to tabout");
		}
	}

	public void tabOutWithJs(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep, TestStepResult stepResult,
			TestSuiteResultSummary suiteSummaryResult) throws AWTException, InterruptedException, ActionScriptException {
		String objectPath = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier();
		List<TestStep> testSteps = testCase.getTestSteps();
		Collections.unmodifiableList(testSteps);
		ListIterator<TestStep> iterator = testSteps.listIterator();
		while (iterator.hasNext()) {
			if((TestStep) iterator.next()==testStep){
				TestStep testStep2 = (TestStep) iterator.previous();
				while(testStep2.getActions().getActionName()==testStep.getActions().getActionName()){
					testStep2 = (TestStep) iterator.previous();
				}
				objectPath = testStep2.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier();
				break;
			}
		}
		try {
			org.openqa.selenium.WebElement ele = findElement(objectPath);
			JavascriptExecutor js = (JavascriptExecutor)oBrowser;
			js.executeScript("$(arguments[0]).change(); return true;",ele);
			DdtCoreUtls.stepRslt(stepResult, true, "TabOut performed", null, null);
			logger.info("Javascript able to perform TabOut action");
		} catch (Exception e) {
			logger.error("Javascript unable to perform a TabOut action :" + e, e);
			String screen = null;
			String screenEx = "";
			try {
				screen = snapShot(plan);
			} catch (ActionScriptException e1) {
				screenEx = " And Screen shot exception " + e1 + ". ";
				logger.error("Javascript falied and screenshot not taken :" + e1, e1);
			}
			DdtCoreUtls.stepRslt(stepResult, false, "TabOut Javascript  not Executed", e.getMessage() + screenEx, screen, e);
		}
	}

	public void selectSaturday(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep, TestStepResult stepResult,
			TestSuiteResultSummary suiteSummaryResult) throws AWTException, InterruptedException, ActionScriptException {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-M");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		WebElement selectDiv = oBrowser.findElement(By.id("calendar-" + dateFormat.format(cal.getTime())));
		WebElement dateTable = selectDiv.findElement(By.tagName("tbody"));
		WebElement daysTable = selectDiv.findElement(By.tagName("thead"));
		List<WebElement> dateRows = dateTable.findElements(By.tagName("tr"));
		List<WebElement> daysRow = daysTable.findElements(By.tagName("th"));
		ArrayList<WebElement> saturdays = new ArrayList<WebElement>();
		for (int i = 0; i < dateRows.size(); i++) {
			if (!dateRows.get(i).getText().equals("   ")) {
				List<WebElement> colCollection = dateRows.get(i).findElements(By.tagName("td"));
				for (int j = 0; j < colCollection.size(); j++) {
					if ((!colCollection.get(j).getAttribute("class").equalsIgnoreCase("disabled"))
							&& (!colCollection.get(j).getAttribute("class").equalsIgnoreCase("blank"))) {
						if (daysRow.get(j).getAttribute("title").equalsIgnoreCase("Saturday")) {
							saturdays.add(colCollection.get(j));
						}
					}
				}
			}
		}
		Random randomSaturday = new Random();
		if (saturdays.size() == 0) {
			logger.error("No more saturdays in this month");
			String screen = snapShot(plan);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "No more saturdays in this month", null, screen);
		} else if (saturdays.size() > 1) {
			WebElement sat = saturdays.get(randomSaturday.nextInt(saturdays.size()));
			sat.click();
			DdtCoreUtls.stepRslt(stepResult, true, "Random saturday selected", null, null);
			logger.info("Selected a saturday with date: "+sat.toString());
		}else{
			WebElement sat = saturdays.get(saturdays.size() - 1);
			sat.click();
			DdtCoreUtls.stepRslt(stepResult, true, "Random saturday selected", null, null);
			logger.info("Selected a saturday with date: "+sat.toString());
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Random saturday selected", null, null);
			logger.info("Selected a saturday with date: " + sat.toString());
		}
	}

	public void textButtonValue(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep,
			TestStepResult stepResult, TestSuiteResultSummary suiteSummaryResult) throws InterruptedException, ActionScriptException {
		waitForPageToLoad(50000);
		String objectPath = null;
		String buttonValue = null;
		String screen = null;
		String buttonVal = null;
		try {
			objectPath = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier();

			buttonValue = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getParamName();
			buttonVal = getObjectValue(objectPath);
			if (buttonVal.equals(buttonValue)) {
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Value in button  matched", null, null);
				logger.info("Value in button  matched");
			} else {
				screen = snapShot(plan);
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Value in button not matched", null, screen);
				logger.error("Value in button not matched");
			}
		} catch (Exception e) {
			screen = snapShot(plan);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Invalid Object or Object not found", null, screen);
			logger.error("Invalid Object or Object not found");

		}
	}

	/**
	 * Specifies the amount of time the driver should wait when searching for an
	 * element if it is not immediately present.
	 * 
	 * @param timesecs
	 *            the timesecs
	 */

	public void waitForPageToLoad(int timesecs) {
		logger.info("Calling wait For Page To Load Action");

		getoBrowser().manage().timeouts().implicitlyWait(timesecs, TimeUnit.SECONDS);
	}

	/**
	 * Passes value to text box
	 * 
	 * @param ac the ac
	 * @throws ActionScriptException  the action script exception
	 */
	public void textBox(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep, TestStepResult stepResult,
			TestSuiteResultSummary suiteSummaryResult) throws ActionScriptException, InterruptedException {
		logger.info("Calling textBox Action");
		// waitForPageToLoad(30);
		String objectPath = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier();
		String prm = testStep.getStepParam();
		String value = null;
		String pname = null;
		String from = null;
		final StringBuilder sb = new StringBuilder().append("Value passed to Textbox");
		if(prm != null){
			StringTokenizer st = new StringTokenizer(prm, "|");
			if(st.hasMoreElements()){
				from = st.nextToken();
			}
			if(st.hasMoreElements()){
				pname = st.nextToken();
			}
		}
		if("c".equals(from)){
			value = ci.getParamValue(pname);
			logger.debug("CI param name :" + pname + ", val :" + value);
			sb.append(", CI param name :" + pname + ", val :" + value + ".");
		}else{
			value = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getParamName();
		}

		String screen = null;
		try {
			objectPath = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier();
			value = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getParamName();
			if (value.length() != 0) {
				org.openqa.selenium.WebElement ele = findElement(objectPath);
				ele.clear();
				ele.sendKeys(value);
				// getoBrowser().findElement(By.id(objectPath)).clear();
				// getoBrowser().findElement(By.id(objectPath)).sendKeys((value));
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, sb.toString(), null, null);
				logger.info("Value passed to Textbox");
			} else {
				screen = snapShot(plan);
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Value is null", null, screen);
				logger.info("Value is missing");
			}
		} catch (Exception ex) {
			String screenEx = "";
			try {
				screen = snapShot(plan);
			} catch (ActionScriptException e1) {
				screenEx = " And Screen shot exception " + e1 + ". ";
				logger.error("textbox failed and screenshot not taken :" + e1, e1);
			}
			DdtCoreUtls.stepRslt(stepResult, false, "Invalid object or Object not found ", ex.getMessage() + screenEx, screen, ex);
			logger.error("textbox: not is present in the specified location " + ex, ex);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Invalid object or Object not found", ex.getMessage() + screenEx + ", Path :" + objectPath + ". Value :" + value + ".", screen);
		}
	}
	
	public void textBox2(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep, TestStepResult stepResult,
			TestSuiteResultSummary suiteSummaryResult) throws ActionScriptException, InterruptedException {
		String screen = null;
		String objectPath = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier();
		if (paramFromExt == null) {
			paramFromExt = new org.s2n.ddt.common.ParamFromTstGenClz();
		}
		paramFromExt.testStep(plan, null, null, null, testStep, stepResult, null, replaceStrs);
		String value = null;
		try {
			value = replaceStrs.get("deviceId");
			if (!(value.length() == 0)) {
				org.openqa.selenium.WebElement ele = findElement(objectPath);
				ele.clear();
				ele.sendKeys(value);
				// getoBrowser().findElement(By.id(objectPath)).clear();
				// getoBrowser().findElement(By.id(objectPath)).sendKeys((value));
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Value passed to Textbox", null, null);
				logger.info("Value passed to Textbox");
			} else {
				screen = snapShot(plan);
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Value is null", null, screen);
				logger.info("Value is missing");
			}
		} catch (org.openqa.selenium.WebDriverException ex) {
			String screenEx = "";
			try {
				screen = snapShot(plan);
			} catch (ActionScriptException e1) {
				screenEx = " And Screen shot exception " + e1 + ". ";
				logger.error("textBox2 failed and screenshot not taken :" + e1, e1);
			}
			DdtCoreUtls.stepRslt(stepResult, false, "Invalid object or Object not found ", ex.getMessage() + screenEx, screen, ex);
			logger.error("textBox2: not is present in the specified location " + ex, ex);
		}
	}

	/**
	 * For scrolling on the screen
	 * 
	 * @param ac
	 *            the ac
	 * @throws ActionScriptException
	 *             the action script exception
	 */
	public void scrollBar(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep, TestStepResult stepResult,
			TestSuiteResultSummary suiteSummaryResult) throws ActionScriptException {
		JavascriptExecutor jsx = (JavascriptExecutor) getoBrowser();
		jsx.executeScript("window.scrollBy(0,2000)", "");
		org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Scrolled", null, null);
		logger.info("Scrolling ");
	}

	/**
	 * Causes a new page to load. Checking the field element currently enabled
	 * or not and performs click operation.
	 * 
	 * @param ac
	 *            the ac
	 * @throws ActionScriptException
	 *             the action script exception
	 */
	public void click(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep, TestStepResult stepResult,
			TestSuiteResultSummary suiteSummaryResult) throws ActionScriptException, InterruptedException {
		logger.info("Calling click Action");
		String objectPath = null;
		String screen = null;
		String alertMessage = null;
		waitForPageToLoad(60);// TODO Config class - for each a ifferent cell in
		try {
			objectPath = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier();
			org.openqa.selenium.WebElement ele = findElement(objectPath);
			if (ele.isEnabled()) {
				ele.click();
				// alertMessage = isAlertPresent();
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Object Found,Button Enabled and Clicked on button", null, null);
				logger.info("Button enabled & clicked");
				// if (alertMessage == null) {
				// org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true,
				// "Object Found,Button Enabled and Clicked on button", null,
				// null);
				// logger.info("Button enabled & clicked");
				// } else {
				// screen = snapShot(plan);
				// org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult,
				// false, alertMessage +
				// " alert mesage is displayed", null, screen);
				// logger.info("Button enabled & clicked");
				// }
			} else {
				screen = snapShot(plan);
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Object Found ,Button Disabled and  not clicked on button ", null, screen);
				logger.error("Button is disabled");
			}

		} catch (org.openqa.selenium.WebDriverException ex) {
			screen = snapShot(plan);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Invalid object or Object not found ", null, screen);
			logger.error("Button to be clicked is not found");
		}
	}
	/**
	 * spanClick Checking the field element currently enabled or not and
	 * performs click operation.
	 * 
	 * @param ac
	 *            the ac
	 * @throws ActionScriptException
	 *             the action script exception
	 */
	public void clickOnSpanElement(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep,
			TestStepResult stepResult, TestSuiteResultSummary suiteSummaryResult) throws ActionScriptException, InterruptedException {
		logger.info("Calling spanclick Action");
		waitForPageToLoad(50);
		String screen = null;
		String objectPath = null;
		try {
			objectPath = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier();
			waitForPageToLoad(50);
			org.openqa.selenium.WebElement ele = findElement(objectPath);
			if (ele.isEnabled()) {
				ele.click();

				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Object Found,Button Enabled and Clicked on button", null, null);
				logger.info("Button enabled & clicked");

			} else {
				screen = snapShot(plan);
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Object Found ,Button Disabled and  not clicked on button ", null, screen);
				logger.error("Button is disabled");
			}

		} catch (org.openqa.selenium.WebDriverException ex) {
			screen = snapShot(plan);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Invalid object or Object not found ", null, screen);
			logger.error("Invalid object or Object not found ");
		}
	}

	public void setFocusForTabOut(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep,
			TestStepResult stepResult, TestSuiteResultSummary suiteSummaryResult) throws InterruptedException, ActionScriptException {

		String objectPath = null;
		String screen = null;
		try {
			objectPath = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier();
			WebElement element = findElement(objectPath);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Focused on element", null, null);
			logger.info("Cliked on element");
		} catch (org.openqa.selenium.WebDriverException ex) {
			screen = snapShot(plan);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Invalid object or Object not found ", null, screen);
			logger.error("Button to be clicked is not found");

		} catch (Exception e) {
			screen = snapShot(plan);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Unable to click on element", null, screen);
			logger.info("Unable to click on element");
		}

	}

	public void clickBasedOnSpanId(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep,
			TestStepResult stepResult, TestSuiteResultSummary suiteSummaryResult) throws InterruptedException, ActionScriptException {
		String screen = null;
		String objectPath = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier();
		;
		try {
			Thread.sleep(3000);
			JavascriptExecutor js = (JavascriptExecutor) getoBrowser();
			js.executeScript("return document.getElementById('" + objectPath + "').click();");
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Clicked on element", null, null);
			logger.info("Cliked on element");
		} catch (org.openqa.selenium.WebDriverException ex) {
			screen = snapShot(plan);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Invalid object or Object not found ", null, screen);
			logger.error("Button to be clicked is not found");

		} catch (Exception e) {
			screen = snapShot(plan);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Unable to click on element", null, screen);
			logger.info("Unable to click on element");
		}

	}

	/**
	 * Checking the field element currently enabled or not and selects the value
	 * in dropdown. And checks if the text is displayed
	 * 
	 * @param ac
	 *            the ac
	 * @throws ActionScriptException
	 *             the action script exception
	 */
	public void dropDown(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep, TestStepResult stepResult,
			TestSuiteResultSummary suiteSummaryResult) throws ActionScriptException {
		logger.info("Calling Drop down Action");
		String screen = null;
		String objectPath = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier();
		String value = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getParamName();
		try {
			if (!value.equals("")) {
				org.openqa.selenium.WebElement ele = findElement(objectPath);
				if (ele.isEnabled()) {
					WebElement dropdownElement = ele;
					Select select = new Select(dropdownElement);
					select.selectByVisibleText(value);
					org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Object Found,Button Enabled and Clicked on button", null, null);
					logger.info("button is enable and it is clicked");

				} else {

					screen = snapShot(plan);
					org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Object Found ,Disabled & not selected", null, screen);
					logger.error("Button is disabled");

				}
			} else {
				screen = snapShot(plan);
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Value is null", null, screen);
				logger.error("Value is null");
			}

		} catch (org.openqa.selenium.WebDriverException ex) {
			screen = snapShot(plan);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Invalid object or Object not found ", null, screen);
			logger.error("Button to be clicked is not found");

		} catch (Exception e) {
			logger.error("Err " + e);
		}

	}

	/**
	 * Check box. for checking action in the check box
	 * 
	 * @param ac
	 *            the ac
	 * @throws ActionScriptException
	 *             the action script exception
	 */
	public void checkBox(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep, TestStepResult stepResult,
			TestSuiteResultSummary suiteSummaryResult) throws ActionScriptException, InterruptedException {
		logger.info("Calling Check box  Action");
		String screen = null;
		String objectPath = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier();
		try {
			org.openqa.selenium.WebElement ele = findElement(objectPath);
			if (ele.isEnabled()) {
				ele.click();
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Object Found,Check Box value enabled and Selected the option ", null, null);
				logger.info("Check Box is enabled and checked");
			} else {
				screen = snapShot(plan);
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Object Found and CheckBox value Disabled and not Selected the option ",
						null, screen);
				logger.error("Check Box is disabled");

			}

		} catch (org.openqa.selenium.WebDriverException ex) {
			screen = snapShot(plan);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Invalid object or Object not found ", null, screen);
			logger.error("Checkbox object is not present");
		}
	}

	/**
	 * Handle alert box. Handles the alert IF you�ve triggered an action that
	 * opens a popup.
	 * 
	 * @param ac
	 *            the ac
	 * @throws ActionScriptException
	 *             the action script exception
	 */
	public void handleAlertBox(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep, TestStepResult stepResult,
			TestSuiteResultSummary suiteSummaryResult) throws ActionScriptException {

		logger.info("Calling Alert Action");
		String screen = null;
		String value = null;
		String alrtMsg = null;
		if (value.length() == 0) {

			screen = snapShot(plan);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "invalid/No data has been passed", null, screen);
			logger.error("Incorrect value is not mentioned for alert");
		}
		try {
			value = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getParamName();
			Alert alert = getoBrowser().switchTo().alert();
			alrtMsg = alert.getText();
			if (value.equalsIgnoreCase("ok")) {
				alert.accept();
			} else if (value.equalsIgnoreCase("cancel")) {
				alert.dismiss();
			}
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "'" + alrtMsg + "' alert message is displayed", null, null);
			logger.info("Alert is handled");
		} catch (org.openqa.selenium.UnhandledAlertException e) {
			screen = snapShot(plan);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Alert is not handled", null, screen);
			logger.error("Unhandled Alert");
		} catch (NoAlertPresentException ex) {
			screen = snapShot(plan);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "No popup messages", null, screen);
			logger.error("Popup is not present");
		}
	}

	/**
	 * Clicks the radio button based on user input.
	 * 
	 * @param ac
	 *            the ac
	 * @throws ActionScriptException
	 *             the action script exception
	 */
	public void radioButton(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep, TestStepResult stepResult,
			TestSuiteResultSummary suiteSummaryResult) throws ActionScriptException {
		logger.info("Calling Radio Button Action");
		String screen = null;
		String objectPath = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier();
		String value = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getParamName();
		Integer option = Integer.parseInt(value);
		List<WebElement> radioList = getoBrowser().findElements(By.name(objectPath));

		for (int i = 0; i < radioList.size(); i++) {
			if (radioList.get(i).isEnabled()) {
				radioList.get(option).click();
				if (radioList.get(option).isSelected()) {
					org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Value Clicked & selected", null, null);
					logger.info("Value Clicked & selected");
				} else {
					screen = snapShot(plan);
					org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Value Clicked & not selected", null, screen);
					logger.info("Value Clicked & not selected");
				}
			} else {
				screen = snapShot(plan);
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Radio Button Disabled", null, screen);
				logger.error("Radio Button Disabled");
			}
		}
	}

	/**
	 * Captures the values and label from the UI And it can be re-used.
	 * 
	 * @param ac
	 *            the ac
	 * @throws ActionScriptException
	 *             the action script exception
	 */
	public void captureUIObject(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep,
			TestStepResult stepResult, TestSuiteResultSummary suiteSummaryResult) throws ActionScriptException, InterruptedException {

		logger.info("Calling Capture UI Object Action");
		String objectPath = null;
		String screen = null;
		waitForPageToLoad(20);
		try {
			objectPath = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier();
			waitForPageToLoad(20);
			org.openqa.selenium.WebElement ele = findElement(objectPath);
			capture = ele.getAttribute("value");
			if (capture == null) {
				capture = ele.getText();
			}
			if (capture != null) {
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Captured Value :" + capture, null, null);
				logger.info("The value is captured");
			} else {

				screen = snapShot(plan);
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Value is not Captured", null, screen);
				logger.error("The value is not captured");
			}
		} catch (org.openqa.selenium.WebDriverException ex) {

			screen = snapShot(plan);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Invalid object or Object not found ", null, screen);
			logger.error("Object's value to be captured is not present");
		}

	}

	/**
	 * Gets the title of the current page, with leading and trailing whitespace
	 * stripped.
	 * 
	 * @param ac
	 *            the ac
	 * @throws ActionScriptException
	 *             the action script exception
	 */
	public void currentTitleOfPage(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep,
			TestStepResult stepResult, TestSuiteResultSummary suiteSummaryResult) throws ActionScriptException {
		String screen = null;
		waitForPageToLoad(120);
		logger.info("Calling Current Title Action");
		String value = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getParamName();
		String titleval = getoBrowser().getTitle();

		if (value.equals(titleval)) {
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Title matched", null, null);
			logger.info("Title matched");
		} else {
			screen = snapShot(plan);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Title differs", null, screen);
			logger.error("Title differs");

		}
	}

	/**
	 * Reuses the data whenever required.
	 * 
	 * @param ac
	 *            the ac
	 * @throws ActionScriptException
	 *             the action script exception
	 */
	public void reUse(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep, TestStepResult stepResult,
			TestSuiteResultSummary suiteSummaryResult) throws ActionScriptException, InterruptedException {

		logger.info("Calling Reuse  Action");
		String screen = null;
		String objectPath = null;
		try {
			objectPath = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier();
			if (capture != null) {
				org.openqa.selenium.WebElement ele = findElement(objectPath);
				ele.sendKeys(capture);
				// getoBrowser().findElement(By.id(objectPath)).sendKeys(capture);
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Captured value is reused", null, null);
				logger.info("The captured value is reused");
			} else {

				screen = snapShot(plan);
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "No value found", null, screen);
				logger.error("The captured value is not found");
			}
		} catch (org.openqa.selenium.WebDriverException ex) {

			screen = snapShot(plan);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Invalid object or Object not found ", null, screen);
			logger.error("Invalid object or Object not found ");
		}
	}

	/**
	 * To verify any particular label to be present in UI.
	 * 
	 * @param ac
	 *            the ac
	 * @throws ActionScriptException
	 *             the action script exception
	 */
	public void verification(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep, TestStepResult stepResult,
			TestSuiteResultSummary suiteSummaryResult) throws ActionScriptException, InterruptedException {

		logger.info("Calling Verification Action");
		// waitForPageToLoad(100);
		String objectPath = null;
		String screen = null;
		String idText = null;
		String value = null;
		try {
			objectPath = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier();
			org.openqa.selenium.WebElement ele = findElement(objectPath);
			value = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getParamName();
			idText = ele.getText();
			// String idText =
			// getoBrowser().findElement(By.xpath(objectPath)).getText();
		
			if (idText.contains(value)) {
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Verified", null, null);
				logger.info("Text verified");
			} else {
				screen = snapShot(plan);
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Text not matched & not verified", null, screen);
				logger.info("Text not matched & not verified");
			}

		} catch (org.openqa.selenium.WebDriverException ex) {
			screen = snapShot(plan);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Invalid object or Object not found", null, screen);
			logger.error("The object to be verified is not present");
		}
	}

	public void verifyFiledNotEmpty(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep,
			TestStepResult stepResult, TestSuiteResultSummary suiteSummaryResult) throws ActionScriptException, InterruptedException {
		logger.info("Calling Verification Action");
		// waitForPageToLoad(100);
		String objectPath = null;
		String screen = null;
		String idText = null;
		String value = null;
		try {
			objectPath = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier();
			org.openqa.selenium.WebElement ele = findElement(objectPath);
			value = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getParamName();
			idText = ele.getText();
			// String idText =
			// getoBrowser().findElement(By.xpath(objectPath)).getText();
			if (idText.contains(value)) {
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Verified", null, null);
				logger.info("Text verified");
			} else {
				screen = snapShot(plan);
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Text not matched & not verified", null, screen);
				logger.info("Text not matched & not verified");
			}

		} catch (org.openqa.selenium.WebDriverException ex) {
			screen = snapShot(plan);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Invalid object or Object not found", null, screen);
			logger.error("The object to be verified is not present");
		}
	}
	
	/**
	 * To verify any particular label to be present in UI.
	 * 
	 * @param ac
	 *            the ac
	 * @throws ActionScriptException
	 *             the action script exception
	 */
	public void verificationByIds(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep,
			TestStepResult stepResult, TestSuiteResultSummary suiteSummaryResult) throws ActionScriptException, InterruptedException {
		String objectPath = null;
		String value = null;
		logger.info("Calling Verification Action");
		waitForPageToLoad(100);
		String screen = null;
		String idText = null;
		try {
			objectPath = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier();
			value = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getParamName().trim();
			org.openqa.selenium.WebElement ele = findElement(objectPath);
			idText = ele.getText().trim();
			if (idText.equals(value)) {
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Verified", null, null);
				logger.info("Text verified");
			} else {

				screen = snapShot(plan);
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Text not matched & not verified", idText + " string appears in the ui",
						screen);
				logger.info("Text not matched & not verified");
			}

		} catch (org.openqa.selenium.WebDriverException ex) {
			screen = snapShot(plan);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Invalid object or Object not found ", null, screen);
			logger.error("Invalid object or Object not found ");
		}
	}

	private WebElement findElement(String objectPath) throws InterruptedException {
		if (objectPath == null) {
			return null;
		} else if (objectPath.contains("/")) {
			highLightByXpath(objectPath);
			WebElement ele = getoBrowser().findElement(By.xpath(objectPath));
			return ele;
		} else {
			highLightById(objectPath);
			WebElement ele = getoBrowser().findElement(By.id(objectPath));
			return ele;
		}
	}

	/**
	 * To verify shadow text,placeholder value in all textboxes.
	 * 
	 * @param ac
	 *            the ac
	 * @return the list
	 * @throws ActionScriptException
	 *             the action script exception
	 */
	public void textBoxShadowTextDivId(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep,
			TestStepResult stepResult, TestSuiteResultSummary suiteSummaryResult) throws ActionScriptException, InterruptedException {
		waitForPageToLoad(10);
		String placevalue = null;
		String screen = null;
		String value = null;
		String objectPath = null;
		try {
			value = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getParamName();
			objectPath = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier();
			JavascriptExecutor js = (JavascriptExecutor) getoBrowser();
			placevalue = (String) js.executeScript("return document.getElementById('" + objectPath + "').innerHTML;");
			if (placevalue.equals(value)) {
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Verified", null, null);
				logger.info("Text verified");
			} else {
				screen = snapShot(plan);
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Text not matched & not verified", null, screen);
				logger.info("Text not matched & not verified");
			}
		} catch (org.openqa.selenium.WebDriverException ex) {

			screen = snapShot(plan);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Invalid object or Object not found ", null, screen);
			logger.error("Invalid object or Object not found ");
		}
	}

	/**
	 * To verify shadow text,placeholder value in all textboxes.
	 * 
	 * @param ac
	 *            the ac
	 * @return the list
	 * @throws ActionScriptException
	 *             the action script exception
	 */
	public void textBoxShadowTextVerification(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep,
			TestStepResult stepResult, TestSuiteResultSummary suiteSummaryResult) throws ActionScriptException {
		waitForPageToLoad(10);
		String placevalue = null;
		String screen = null;
		String value = null;
		String objectPath = null;

		;
		try {
			value = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getParamName();
			objectPath = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier();
			getoBrowser().getCurrentUrl();
			JavascriptExecutor js = (JavascriptExecutor) getoBrowser();
			placevalue = (String) js.executeScript("return document.getElementById('" + objectPath + "').placeholder;");
			if (placevalue.equals(value)) {
				org.s2n.ddt.	common.DdtCoreUtls.stepRslt(stepResult, true, "Verified", null, null);
				logger.info("Text verified");
			} else {
				screen = snapShot(plan);
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Text not matched & not verified", null, screen);
				logger.info("Text not matched & not verified");
			}
		} catch (org.openqa.selenium.WebDriverException ex) {

			screen = snapShot(plan);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Invalid object or Object not found ", null, screen);
			logger.error("Invalid object or Object not found ");
		}
	}

	public void selectImageForCP(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep,
			TestStepResult stepResult, TestSuiteResultSummary suiteSummaryResult) throws InterruptedException, ActionScriptException {

		WebElement ele = null;
		String value = null;
		String objectPath = null;
		String screen = null;
		boolean flag = false;
		try {
			value = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getParamName();
			objectPath = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier();
			WebElement table_element = findElement(objectPath);
			List<WebElement> tr_collection = table_element.findElements(By.xpath("id('" + objectPath + "')/tbody/tr"));
			logger.info("Number of rows in this table = " + tr_collection.size());
			int row_num, col_num;
			row_num = 1;
			looprow: for (WebElement trElement : tr_collection) {
				List<WebElement> td_collection = trElement.findElements(By.tagName("td"));
				logger.info("Number of columns = " + td_collection.size());
				col_num = 1;
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, " Unable to click on the selected row ", null, null);
				for (WebElement tdElement : td_collection) {
					logger.info("row # " + row_num + ", col # " + col_num + "");
					ele = tdElement.findElement(By.tagName("img"));
					if (ele.isDisplayed() && ele.isEnabled()) {
						col_num++;
						break;
					} else {
						tdElement.click();
						flag = true;
					}
					if (flag) {
						org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, " Clicked on the selected row ", null, null);
						break looprow;

					}
				}
			}
		} catch (org.openqa.selenium.WebDriverException ex) {

			screen = snapShot(plan);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Invalid object or Object not found ", null, screen);
			logger.error("Invalid object or Object not found ");
		}
	}

	/**
	 * Quits this driver, closing every associated window. As creating a Driver
	 * instance starts a session, yet it has to be terminated explicitly with a
	 * call to quit().
	 * 
	 * @param ac
	 *            the ac
	 */
	public void closeBrowser(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep, TestStepResult stepResult,
			TestSuiteResultSummary suiteSummaryResult) {
		try {
			logger.info("Calling Close Browser Action");
			getoBrowser().close();
			getoBrowser().quit();
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Browser is closed", null, null);
			logger.info("Browser is closed");
		} catch (Exception ex) {
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Error in closing browser" + ex, null, null);
		}
	}

	/**
	 * Press enter key.
	 * 
	 * @param ac
	 *            the ac
	 * @throws ActionScriptException
	 *             the action script exception
	 */
	public void keyPressEnter(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep, TestStepResult stepResult,
			TestSuiteResultSummary suiteSummaryResult) throws ActionScriptException {
		logger.info("Calling press enter key Action");
		String objectPath = null;
		String screen = null;
		try {
			objectPath = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier();
			getoBrowser().findElement(By.id(objectPath)).sendKeys(Keys.RETURN);
			Actions builder = new Actions(getoBrowser());
			builder.keyDown(getoBrowser().findElement(By.id(objectPath)), Keys.ENTER).perform();
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Pressed Enter key", null, null);
			logger.info("pressed Enter key");
		} catch (Exception e) {
			screen = snapShot(plan);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Invalid object or Object not found ", null, screen);
			stepResult.setSnapShot(screen);
			logger.error("Enter button is not clciked");
		}
	}

	// public void keyPressEnter(TestStep testStep, TestStepResult stepResult,
	// TestPlan plan) throws ActionScriptException {
	// logger.info("Calling press enter key Action");
	// String objectPath =
	// testStep.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier();
	// ;
	// try {
	// RobotHelper.sendEnterKey();
	// org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true,
	// "Pressed Enter key", null, null);
	// logger.info("pressed Enter key");
	// } catch (Exception ex) {
	// org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false,
	// "Unable to press Enter key", null, null);
	// logger.error("Unable to press Enter key");
	// }
	// }

	/**
	 * Press down arrow key.
	 * 
	 * @param ac
	 *            the ac
	 * @throws ActionScriptException
	 *             the action script exception
	 */
	public void keyPressArrowDown(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep,
			TestStepResult stepResult, TestSuiteResultSummary suiteSummaryResult) throws ActionScriptException {

		logger.info("Calling press Down Arrow Action");
		String objectPath = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier();
		String screen = null;
		// driver.getKeyboard ().pressKey (Keys.DOWN)
		// driver.getKeyboard ().pressKey (Keys.DOWN)
		// driver.getKeyboard ().pressKey (Keys.DOWN)
		// driver.getKeyboard ().pressKey (Keys.UP)
		// driver.getKeyboard ().pressKey (Keys.UP)
		// driver.getKeyboard ().pressKey (Keys.UP)
		try {
			((RemoteWebDriver) getoBrowser()).getKeyboard().pressKey(Keys.DOWN);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Down arrow Key pressed", null, null);
			logger.info("Down arrow Key pressed");
		} catch (org.openqa.selenium.NoSuchElementException e) {
			screen = snapShot(plan);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Invalid object or Object not found ", null, screen);
			logger.error("Down arrow button is not pressed");
		}
	}

	/**
	 * Press up arrow key.
	 * 
	 * @param ac
	 *            the ac
	 * @throws ActionScriptException
	 *             the action script exception
	 */
	public void keyPressArrowUp(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep,
			TestStepResult stepResult, TestSuiteResultSummary suiteSummaryResult) throws ActionScriptException, InterruptedException {

		logger.info("Calling press Up Arrow Action");
		String objectPath = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier();
		String screen = null;
		try {
			org.openqa.selenium.WebElement ele = findElement(objectPath);
			ele.sendKeys(Keys.ARROW_UP);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Up arrow key pressed", null, null);
			logger.info("Pressed up arrow key");
		} catch (org.openqa.selenium.NoSuchElementException e) {
			screen = snapShot(plan);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Up arrow key not pressed", null, screen);
			logger.error("Up arrow key is not clciked");
		}

	}

	/**
	 * Press right arrow key.
	 * 
	 * @param ac
	 *            the ac
	 * @throws ActionScriptException
	 *             the action script exception
	 */
	public void keyPressArrowRight(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep,
			TestStepResult stepResult, TestSuiteResultSummary suiteSummaryResult) throws ActionScriptException, InterruptedException {

		logger.info("Calling press Right Arrow Action");
		String objectPath = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier();
		String screen = null;
		try {
			org.openqa.selenium.WebElement ele = findElement(objectPath);
			ele.sendKeys(Keys.ARROW_RIGHT);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Right arrow key pressed", null, null);
			logger.info("Right arrow key pressed");
		} catch (org.openqa.selenium.NoSuchElementException e) {
			screen = snapShot(plan);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Right arrow button not pressed", null, screen);
			logger.error("Right arrow key is not clciked");
		}
	}

	/**
	 * Press left arrow key.
	 * 
	 * @param ac
	 *            the ac
	 * @throws ActionScriptException
	 *             the action script exception
	 */
	public void keyPressArrowLeft(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep,
			TestStepResult stepResult, TestSuiteResultSummary suiteSummaryResult) throws ActionScriptException, InterruptedException {

		logger.info("Calling press Left Arrow Action");
		String screen = null;
		String objectPath = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier();
		;
		try {
			org.openqa.selenium.WebElement ele = findElement(objectPath);
			ele.sendKeys(Keys.ARROW_LEFT);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Left arrow key pressed", null, null);
			logger.info("Pressed left arrow key");
		} catch (org.openqa.selenium.NoSuchElementException e) {
			screen = snapShot(plan);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Left arrow key is not pressed", null, screen);
			logger.error("Left arrow key is not pressed");
		}
	}

	/*
	 * 
	 * To run a javascript
	 */

	public void javaScriptPut(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep, TestStepResult stepResult,
			TestSuiteResultSummary suiteSummaryResult) throws ActionScriptException {
		String screen = null;
		JavascriptExecutor js = null;
		try {
			
			js = (JavascriptExecutor) getoBrowser();
			String aa = testStep.getStepParam();
			js.executeScript(aa);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Javascript Executed", null, null);
			logger.info("Javascript able to perform a action");
		} catch (Exception e) {
			screen = snapShot(plan);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Javascript  not Executed", null, screen);
			logger.error("Javascript unable to perform a action");
			try {
				screen = snapShot(plan);
			} catch (ActionScriptException e1) {
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Javascript falied and screenshot not taken", null, screen);
				logger.error("Javascript falied and screenshot not taken");
			}
		}
	}

	/**
	 * Close browser when failed.
	 * 
	 * @param ac
	 *            the ac
	 */
	public void closeBrowserWhenFailed(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep,
			TestStepResult stepResult, TestSuiteResultSummary suiteSummaryResult) {

		logger.info("Calling Close Browser when test case gets failed");
		getoBrowser().quit();
	}

	/**
	 * Mouse over an element.
	 * 
	 * @param ac
	 *            the ac
	 * @throws ActionScriptException
	 *             the action script exception
	 */
	public void mouseOverAnElement(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep,
			TestStepResult stepResult, TestSuiteResultSummary suiteSummaryResult) throws ActionScriptException {

		logger.info("Calling mouse over an element Action");
		String screen = null;
		try {

			String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
			String objectPath = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier();
			;

			JavascriptExecutor js = (JavascriptExecutor) getoBrowser();
			WebElement element = getoBrowser().findElement(By.className(objectPath));
			js.executeScript(mouseOverScript, element);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Mouse over an element is viewed", null, null);
			logger.info("Mouse over an element is viewed");

		} catch (Exception ee) {
			screen = snapShot(plan);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Mouse over an element is not viewed", null, screen);
			logger.error("Mouse over an element is not viewd");
		}
	}

	/**
	 * Gets the current url.
	 * 
	 * @param ac
	 *            the ac
	 */
	public void refreshPage(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep, TestStepResult stepResult,
			TestSuiteResultSummary suiteSummaryResult) {

		logger.info("Calling refresh Action");
		waitForPageToLoad(20);
		String currentUrl = getoBrowser().getCurrentUrl();
		getoBrowser().get(currentUrl);
		org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Current url  " + currentUrl + "  is loaded", null, null);
		logger.info("Current url  " + currentUrl + "  is loaded");
	}

	public void customizedDatePicker(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep,
			TestStepResult stepResult, TestSuiteResultSummary suiteSummaryResult) throws ActionScriptException, InterruptedException {

		logger.info(" Calling customized date picker action");
		customizedDatePickerViaTxt(plan, suite, testCase, caseResult, testStep, stepResult, suiteSummaryResult);
	}

	public void customizedDatePickerViaTxt(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep,
			TestStepResult stepResult, TestSuiteResultSummary suiteSummaryResult) throws ActionScriptException, InterruptedException {
		String objectPath = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier();
		String screen = null;
		String dat = null;
		String value = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getParamName();
		if (value.charAt(0) == '$') {
			dat = value.substring(1, value.length() - 1);
		}
		try {
			org.openqa.selenium.WebElement ele = findElement(objectPath);
			ele.clear();
			ele.sendKeys((dat));
			org.openqa.selenium.WebElement ele1 = findElement("//*[contains(text(),'Done')]");
			ele1.click();
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, value + " is passed to datepicker", null, null);
			logger.info("Value passed to Textbox");
		} catch (org.openqa.selenium.WebDriverException ex) {
			screen = snapShot(plan);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Invalid object or Object not found ", null, screen);
			logger.error("Invalid object or Object not found");
		}
	}

	/**
	 * picks date from date picker
	 * 
	 * @param ac
	 *            the ac
	 * @throws ActionScriptException
	 *             the action script exception
	 */
	public void customizedDatePickerForGRX(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep,
			TestStepResult stepResult, TestSuiteResultSummary suiteSummaryResult) throws ActionScriptException, InterruptedException {
		String screen = null;

		try {

			logger.info(" Calling customized date picker action");
			// if (config.getDateChecker().equals("via-txt")) {
			// customizedDatePickerViaTxt(plan, suite, testCase, caseResult,
			// testStep, stepResult, suiteSummaryResult);
			// } else {
			//
			String objectPath = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier();
			;
			String dat = null;
			String value = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getParamName();
			if (value.charAt(0) == '$') {
				dat = value.substring(1, value.length() - 1);
			}
			org.openqa.selenium.WebElement ele = findElement(objectPath);
			ele.clear();
			ele.click();
			JavascriptExecutor js = (JavascriptExecutor) getoBrowser();
			js.executeScript("window.document.getElementById('" + objectPath + "').setAttribute('value', " + dat + ");");
			List<String> inputListdate = new ArrayList<String>();
			if (dat != null) {
				for (String retval : dat.split("/")) {
					inputListdate.add(retval);
				}
			}
			String dt = null;
			dt = formatDate(inputListdate, objectPath);

			if (dt != null) {
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Date is selected successfully", null, null);

			} else {
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Date selection was unsuccessful", null, null);
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			screen = snapShot(plan);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Element not found", null, screen);
			logger.error("Element not found");
		} catch (Exception e) {
			e.printStackTrace();
			screen = snapShot(plan);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Invalid object or Object not found ", null, screen);
			logger.error("Button to be clicked is not found");
		}
	}

	/**
	 * picks current date from date picker
	 * 
	 * @param ac
	 *            the ac
	 * @throws ActionScriptException
	 *             the action script exception
	 * @throws InterruptedException
	 */
	public void currentDatePickerForGRX(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep,
			TestStepResult stepResult, TestSuiteResultSummary suiteSummaryResult) throws ActionScriptException, InterruptedException {
		String objectPath = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier();
		String screen = null;
		String datePattern = "";
		SimpleDateFormat ft = null;
		List<String> inputListdate = new ArrayList<String>();

		Date date = new Date();
		ft = new SimpleDateFormat(datePattern);
		String strDateFormat = ft.format(date);
		for (String retval : strDateFormat.split("/")) {
			inputListdate.add(retval);
		}
		String dt = formatDate(inputListdate, objectPath);
		if (strDateFormat.equals(dt)) {
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Date is selected successfully", null, null);
			logger.info("Date is selected successfully");
		} else {
			screen = snapShot(plan);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Date selection was unsuccessful", null, screen);
			logger.error("Date selection was unsuccessful");
		}

	}

	/**
	 * Format date.
	 * 
	 * @param inputList
	 *            the input list
	 * @param objectPath
	 *            the object path
	 * @return true, if successful
	 * @throws ActionScriptException
	 *             the action script exception
	 * @throws InterruptedException
	 */
	public String formatDate(List<String> inputList, String objectPath) throws ActionScriptException, InterruptedException {
		String date = null;
		// Calendar Month and Year
		String appMonth = null;
		String appYear = null;
		int month;
		int year;
		boolean dateNotExist;
		List<String> monthList = Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
		org.openqa.selenium.WebElement ele = findElement(objectPath);
		getoBrowser().manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		ele.click();
		dateNotExist = true;
		month = Integer.parseInt(inputList.get(0));
		year = Integer.parseInt(inputList.get(2));
		date = inputList.get(1);
		while (dateNotExist) {
			org.openqa.selenium.WebElement eleMonth = getoBrowser().findElement(By.className("ui-datepicker-month"));
			Select select = new Select(eleMonth);
			org.openqa.selenium.WebElement eleYear = getoBrowser().findElement(By.className("ui-datepicker-year"));
			Select select2 = new Select(eleYear);
			// appMonth =
			// getoBrowser().findElement(By.className("ui-datepicker-month")).getText();
			appMonth = select.getFirstSelectedOption().getText();
			// appYear =
			// getoBrowser().findElement(By.className("ui-datepicker-year")).getText();
			appYear = select2.getFirstSelectedOption().getText();
			logger.log(Level.INFO, "appMonth  " + appMonth + " appYear " + appYear);
			if (monthList.indexOf(appMonth) + 1 == month && (year == Integer.parseInt(appYear))) {
				selectDate(date);
				dateNotExist = false;
			} else if (monthList.indexOf(appMonth) + 1 < month && (year == Integer.parseInt(appYear)) || year > Integer.parseInt(appYear)) {
				getoBrowser().findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
			} else if (monthList.indexOf(appMonth) + 1 > month && (year == Integer.parseInt(appYear)) || year < Integer.parseInt(appYear)) {
				getoBrowser().findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[1]/span")).click();
			}
		}
		JavascriptExecutor js = (JavascriptExecutor) getoBrowser();
		String placevalue = (String) js.executeScript("return document.getElementById('" + objectPath + "').value;");
		return placevalue;
	}

	/**
	 * Select date.
	 * 
	 * @param date
	 *            the date
	 * @throws InterruptedException
	 */
	public void selectDate(String date) throws InterruptedException {
		String selDate = null;
		WebElement dateSetter;
		List<WebElement> columns;
		dateSetter = getoBrowser().findElement(By.id("ui-datepicker-div"));
		List<WebElement> rows = dateSetter.findElements(By.tagName("tr"));
		columns = dateSetter.findElements(By.tagName("td"));
		if (date.startsWith("0")) {
			selDate = date.substring(1);
		} else {
			selDate = date;
		}

		for (WebElement cell : columns) {
			if (cell.getText().equals(selDate)) {
				cell.findElement(By.linkText(selDate)).click();

				break;
			}
		}
		try {
			getoBrowser().findElement(By.xpath("//*[contains(text(),'Done')]")).click();
		} catch (Exception e) {
			logger.info("No Button done");

		}
		// if(getoBrowser().findElement(By.xpath("//*[contains(text(),'Done')]"){
		// ele1.click();
	}

	/*
	 * public void selectDate(String date) {
	 * 
	 * @SuppressWarnings("unused") WebElement dateSetter; List<WebElement>
	 * columns; dateSetter =
	 * getoBrowser().findElement(By.id("ui-datepicker-div")); columns =
	 * dateSetter.findElements(By.tagName("td"));
	 * 
	 * for (WebElement cell : columns) { if (cell.getText().equals(date)) {
	 * cell.findElement(By.linkText(date)).click(); break; } } }
	 */

	/**
	 * An action that takes screen shot.
	 * 
	 * @param ac
	 *            the ac
	 * @throws ActionScriptException
	 *             the action script exception
	 */
	public void snapShot(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep, TestStepResult stepResult,
			TestSuiteResultSummary suiteSummaryResult) throws ActionScriptException {

		String snapName = "Snapshot";
		Date date = new Date();
		String name = String.format("%s.%s", snapName, date);
		String snaps = "./snaps/" + name + ".png";
		try {
			File scrFile = ((TakesScreenshot) getoBrowser()).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(snaps));
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Sanpshot captured", null, null);
			logger.info("snapShot captured");
		} catch (UnreachableBrowserException e) {
			getoBrowser().getCurrentUrl();
			File scrFile = ((TakesScreenshot) getoBrowser()).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(scrFile, new File(snaps));
			} catch (IOException e1) {

			}
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "snapShot is captured for failed testStep", null, null);
			logger.error("snapShot is captured for failed testcase");
		} catch (Exception ex) {
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Failed to capture snapshot", null, null);
			logger.error("screenShot not captured");
		}

	}

	/**
	 * Takes screenshot when a testcase fails
	 * 
	 * @return the string
	 * @throws ActionScriptException
	 *             the action script exception
	 */
	public String snapShot(TestPlan plan) throws ActionScriptException {
		String snaps = null;

		waitForPageToLoad(100);
		String snapName = "Snapshot";
		Date date = new Date();
		String name = String.format("%s.%s", snapName, date);
		snaps = name.replace(" ", "").replace(":", "_") + ".jpg";
		File fsnap = new File(plan.getTestDebugInfoFile() + "snapShots/", snaps);
		try {
			File scrFile = ((TakesScreenshot) getoBrowser()).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, fsnap);
			logger.info("snapShot is captured for failed testcase");
		} catch (UnreachableBrowserException e) {
			getoBrowser().getCurrentUrl();
			try {
				File scrFile = ((TakesScreenshot) getoBrowser()).getScreenshotAs(OutputType.FILE);
				logger.log(Level.INFO, "snap src [" + scrFile + "]");
				FileUtils.copyFile(scrFile, fsnap);// TODO delete srcFile ?
			} catch (Exception e1) {
				logger.error("snapShot is not captured for failed testcase :" + e1, e1);
			}
			logger.error("snapShot is captured for failed testcase");
		} catch (Exception ex) {
			logger.error("snapShot is not captured for failed testcase" + ex, ex);
		}
		logger.log(Level.INFO, "snap src [" + snaps + "]" + " file [" + fsnap + "] exists " + fsnap.exists());
		return snaps;
	}

	/**
	 * Slider action
	 * 
	 * @param ac
	 *            the ac
	 * @throws ActionScriptException
	 *             the action script exception
	 */
	public void sliderButton(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep, TestStepResult stepResult,
			TestSuiteResultSummary suiteSummaryResult) throws ActionScriptException, InterruptedException {
		String objectPath = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier();
		String screen = null;
		String value = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getParamName();

		getoBrowser().switchTo().frame(value);
		org.openqa.selenium.WebElement slider = findElement(objectPath);
		// WebElement slider = getoBrowser().findElement(By.xpath(objectPath));
		if (slider.isEnabled()) {
			Actions move = new Actions(getoBrowser());
			Action action = (Action) move.dragAndDropBy(slider, 85, 50).build();
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Slider is enabled and dragged", null, null);
			logger.info("Slider Dragged");
			action.perform();
		} else {
			screen = snapShot(plan);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Slider disabled unable to drag", null, screen);
			logger.info("Slider disabled cannot drag");
		}

	}

	/**
	 * Toggle action
	 * 
	 * @param ac
	 *            the ac
	 * @throws ActionScriptException
	 *             the action script exception
	 */
	public void toggleButton(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep, TestStepResult stepResult,
			TestSuiteResultSummary suiteSummaryResult) throws ActionScriptException, InterruptedException {

		logger.info("Calling Toggle action");
		String objectPath = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier();
		String screen = null;
		try {
			org.openqa.selenium.WebElement ele = findElement(objectPath);
			if (ele.isEnabled()) {
				ele.click();
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Displayed Element", null, null);
				logger.info("Displayed Element");
			} else {
				screen = snapShot(plan);
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Element not Displayed", null, screen);
				logger.error("Element not Displayed");
			}
		} catch (org.openqa.selenium.WebDriverException ex) {

			screen = snapShot(plan);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Invalid object or Object not found ", null, screen);
			logger.error("Invalid object or Object not found ");

		}

	}

	/**
	 * Drags an element and drops to specified location.
	 * 
	 * @param ac
	 *            the ac
	 * @throws ActionScriptException
	 *             the action script exception
	 */
	public void mouseDragAndDrop(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep,
			TestStepResult stepResult, TestSuiteResultSummary suiteSummaryResult) throws ActionScriptException {
		String screen = null;
		WebElement dragElement, dropElement;
		try {
			String dragID = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier();
			;
			String dropID = testStep.getTestStepId().getTestParamDatas().get(1).getParam().getObjects().getObjectsId().getIndentifier();

			dragElement = getoBrowser().findElement(By.id(dragID));
			dropElement = getoBrowser().findElement(By.id(dropID));

			Actions builder = new Actions(getoBrowser());
			Action dragAndDrop = builder.clickAndHold(dragElement).moveToElement(dropElement).release(dropElement).build();
			dragAndDrop.perform();
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Dragged and Dropped", null, null);
			logger.info("Dragged and Dropped");
		} catch (org.openqa.selenium.WebDriverException ex) {
			screen = snapShot(plan);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Invalid object or Object not found ", null, screen);
			logger.error("Invalid object or Object not found ");
		}
	}

	/**
	 * Compare local and remote resources.
	 * 
	 * @param ac
	 *            the ac
	 * @throws ActionScriptException
	 *             the action script exception
	 */
	/*
	 * public void compareLocalAndRemoteResources(TestPlan plan, TestSuite
	 * suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep,
	 * TestStepResult stepResult, TestSuiteResultSummary suiteSummaryResult)
	 * throws ActionScriptException { InputStream isLocal = null; InputStream
	 * remoteis = null; String localImg =
	 * testStep.getTestStepId().getTestParamDatas().get(0).getParam().getObjects
	 * ().getObjectsId().getIndentifier(); ; String remoteImg =
	 * testStep.getTestStepId
	 * ().getTestParamDatas().get(1).getParam().getObjects(
	 * ).getObjectsId().getIndentifier(); boolean same = false; try { // take
	 * buffer data from botm image files //
	 * 
	 * File file = new File(localImg); isLocal = new BufferedInputStream(new
	 * FileInputStream(file)); WebElement imgUI = findElement(remoteImg); String
	 * src = imgUI.getAttribute("src"); URL remoteUrl = new URL(src); remoteis =
	 * new BufferedInputStream(remoteUrl.openStream()); int remoteInt = -1; int
	 * localInt = -1; same = true; while (remoteis.available() > 0 &&
	 * isLocal.available() > 0) { localInt = isLocal.read(); remoteInt =
	 * remoteis.read(); if (localInt != remoteInt) { same = false; break; } }
	 * org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true,
	 * "Remote image and Local image are same", null, null);
	 * logger.info("Remote image and Local image are same"); } catch (Exception
	 * e) { screen = ActionUtils.snapShot(getoBrowser());
	 * org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false,
	 * "Failed to compare image files ...", null, screen); } finally { try {
	 * isLocal.close(); } catch (Exception e) { screen =
	 * ActionUtils.snapShot(getoBrowser());
	 * org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false,
	 * "Failed to compare image files ...", null, screen); } try {
	 * remoteis.close(); } catch (Exception e) { screen =
	 * ActionUtils.snapShot(getoBrowser());
	 * org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false,
	 * "Failed to compare image files ...", null, screen); } }
	 * 
	 * }
	 */

	/**
	 * Tabs out.
	 * 
	 * @param ac
	 *            the ac
	 * @return the list
	 * @throws ActionScriptException
	 *             the action script exception
	 */
	/*
	 * public void tabOut(TestStep testStep, TestStepResult stepResult,TestPlan
	 * plan) throws ActionScriptException { try { String object1 =
	 * testStep.getTestStepId
	 * ().getTestParamDatas().get(0).getParam().getObjects(
	 * ).getObjectsId().getIndentifier();;
	 * com.exilant.selenium.util.ActionUtils.tabOut();
	 * //com.exilant.selenium.util.getoBrowser().RobotHelper.sendKeys("\t",
	 * 100); FluentWait<By> fluentWait = new FluentWait<By>(By.id(object1));
	 * fluentWait.pollingEvery(100, TimeUnit.MILLISECONDS);
	 * fluentWait.withTimeout(1000, TimeUnit.MILLISECONDS); fluentWait.until(new
	 * Predicate<By>() { public boolean apply(By by) { try { return
	 * getoBrowser().findElement(by).isDisplayed(); } catch
	 * (NoSuchElementException ex) { return false; } } }); //WebElement element
	 * = wait.until(visibilityOfElementLocated(By.id(object1)));
	 * org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult,
	 * true,"Navigated to the " + "  " + url + " " + "  URL", null,null);
	 * stepResult.setResult(true); stepResult.setComment("tabed out"); } catch
	 * (org.openqa.selenium.TimeoutException e) { screen = snapShot(plan);
	 * org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult,
	 * false,"Browser not found", null,screen); stepResult.setResult(false);
	 * stepResult.setComment("Server failed to respond"); screen =
	 * ActionUtils.snapShot(getoBrowser()); stepResult.setSnapShot(screen); } }
	 */

	/*
	 * private static ExpectedCondition<WebElement>
	 * visibilityOfElementLocated(final By by) { return new
	 * ExpectedCondition<WebElement>() { public WebElement apply(WebDriver
	 * driver) { WebElement element = driver.findElement(by); return
	 * element.isDisplayed() ? element : null; } }; }
	 */

	public void checkDefaultSelectedValue(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep,
			TestStepResult stepResult, TestSuiteResultSummary suiteSummaryResult) throws ActionScriptException {
		String objectPath = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier();
		String screen = null;
		WebElement thisElement = getoBrowser().findElement(By.id(objectPath));
		if (!thisElement.isSelected() && thisElement.isEnabled()) {
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Value not selected by default", null, null);
			logger.info("Button is enabled and not selected");
		} else {
			screen = ActionUtils.snapShot(getoBrowser());
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Value selected by default", null, screen);
			logger.info("Value selected by default");
		}
	}

	public void doubleClick(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep, TestStepResult stepResult,
			TestSuiteResultSummary suiteSummaryResult) throws InterruptedException, ActionScriptException {
		logger.info("Calling double click Action");
		String objectPath = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier();
		String screen = null;
		waitForPageToLoad(30);// TODO Config class - for each a ifferent cell in
		// _config
		try {
			Actions builder = new Actions(getoBrowser());
			org.openqa.selenium.WebElement ele = findElement(objectPath);
			if (ele.isEnabled()) {
				org.openqa.selenium.interactions.Action doubleClick = builder.doubleClick(ele).build();
				doubleClick.perform();
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Button enabled & clicked", null, null);
				logger.info("Button enabled & clicked");
			} else {
				screen = ActionUtils.snapShot(getoBrowser());
				org.s2n.ddt.common.DdtCoreUtls
						.stepRslt(stepResult, false, "Object Found ,Element Disabled and  not clicked on element ", null, screen);
				logger.error("Button is disabled");
			}

		} catch (org.openqa.selenium.WebDriverException ex) {
			screen = ActionUtils.snapShot(getoBrowser());
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Invalid object or Object not found", null, screen);
			logger.error("Invalid object or Object not found" + ex);
		}
	}

	/**
	 * Verifies whether the image is loaded in UI
	 * 
	 * @param ac
	 *            the ac
	 * @throws ActionScriptException
	 *             the action script exception
	 */
	public void verifyImageLoad(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep,
			TestStepResult stepResult, TestSuiteResultSummary suiteSummaryResult) throws ActionScriptException, InterruptedException {
		String objectPath = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier();
		String screen = null;
		WebElement image = findElement(objectPath);
		Boolean imageLoaded1 = (Boolean) ((JavascriptExecutor) getoBrowser()).executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", image);
		if (!imageLoaded1) {
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "image is present", null, null);
		} else {
			screen = ActionUtils.snapShot(getoBrowser());
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "image is not present", null, screen);
		}

	}

	/***
	 * Method which verifies the UI field holding the current date in the
	 * specified format date format specified by the user Date value from the
	 * objects
	 * 
	 * @param ac
	 * @throws ActionScriptException
	 */
	public void verifyCurrentDateValue(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep,
			TestStepResult stepResult, TestSuiteResultSummary suiteSummaryResult) throws ActionScriptException {

		logger.info("Calling verify current date Action");
		String idValue = null;
		String screen = null;
		try {
			waitForPageToLoad(50000);
			String objectPath = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier();
			;
			String value = "";
			java.util.Date dNow = new Date();
			SimpleDateFormat ft = new SimpleDateFormat(value);
			String formattodayDate = ft.format(dNow);
			idValue = getObjectValue(objectPath);
			if (idValue.equals(formattodayDate)) {
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Current date is displayed", null, null);
				logger.info("Current date is displayed");
			} else {
				screen = snapShot(plan);
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Current date is not displayed", null, screen);
				logger.info("Current date is not displayed");
			}
		} catch (Exception e) {
			screen = snapShot(plan);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Date pattern not matching & value not verified", null, screen);
			logger.error("Date pattern not matching & value not verified");
		}
	}

	/**
	 * Method which returns the value of UI fields Accepts the parameter in form
	 * of strings (eg., xpath,id)
	 * 
	 * @param objectPath
	 * @return
	 * @throws InterruptedException
	 */
	public String getObjectValue(String objectPath) throws InterruptedException {
		highLightById(objectPath);
		JavascriptExecutor js = (JavascriptExecutor) getoBrowser();
		Thread.sleep(10000);
		String placevalue = (String) js.executeScript("return document.getElementById('" + objectPath + "').value;");
		return placevalue;
	}

	public WebDriver getoBrowser() {
		return oBrowser;
	}

	public void setoBrowser(WebDriver oBrowser) {
		this.oBrowser = oBrowser;
	}

	public void clearTextBoxValue(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep,
			TestStepResult stepResult, TestSuiteResultSummary suiteSummaryResult) throws InterruptedException, ActionScriptException {
		waitForPageToLoad(5000);
		String objectPath = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier();
		String screen = null;
		try {
			org.openqa.selenium.WebElement ele = findElement(objectPath);
			ele.clear();
			if (ele.getText().length() == 0) {
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Text Field Cleared", null, null);
				logger.error("Text Field Cleared");
			} else {
				screen = snapShot(plan);
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Text Field not Cleared", null, screen);
				logger.error("Text Field not Cleared");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			screen = snapShot(plan);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Object not found or Invalid object", null, screen);
			logger.error("Object not found or Invalid object");
		}
	}

	// public void doubleClick(TestStep testStep, TestStepResult stepResult,
	// TestPlan plan) throws InterruptedException, ActionScriptException {
	// logger.info("Calling double click Action");
	// String objectPath =
	// testStep.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier();
	// ;
	// waitForPageToLoad(30);// TODO Config class - for each a ifferent cell in
	// // _config
	// try {
	// Actions builder = new Actions(getoBrowser());
	// org.openqa.selenium.WebElement ele = findElement(objectPath);
	// if (ele.isEnabled()) {
	// org.openqa.selenium.interactions.Action dragAndDrop =
	// builder.doubleClick(ele).build();
	// System.out.println("***************************" + dragAndDrop);
	// dragAndDrop.perform();
	// org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true,
	// "Navigated to the " + "  " + url + " " +
	// "  URL", null, null);
	// stepResult.setResult(true);
	// stepResult.setComment("Object Found,Button Enabled and Clicked on button and redirected to  "
	// + getoBrowser().getCurrentUrl());
	// stepResult.setDetailMsgs("Double clicked");
	// logger.info("Button enabled & clicked");
	// } else {
	// screen = snapShot(plan);
	// org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false,
	// "Browser not found", null, screen);
	// stepResult.setResult(false);
	// stepResult.setComment("Object Found ,Button Disabled and  not clicked on button ");
	// stepResult.setDetailMsgs("Double click failed");
	// screen = snapShot(plan);
	// stepResult.setSnapShot(screen);
	// logger.error("Button is disabled");
	// }
	//
	// } catch (org.openqa.selenium.WebDriverException ex) {
	// screen = snapShot(plan);
	// org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false,
	// "Browser not found", null, screen);
	// stepResult.setResult(false);
	// stepResult.setComment("Invalid object or Object not found ");
	// stepResult.setDetailMsgs("Invalid object or Object not found ");
	// screen = snapShot(plan);
	// stepResult.setSnapShot(screen);
	// logger.error("Button to be clicked is not found");
	// }
	// }

	public static String osComm(String cmd) {
		try {

			Process Results = Runtime.getRuntime().exec(new String[] { "/bin/sh", "-c", cmd });
			// Results.waitFor(); //- Add this line if and only if you aren't
			// collecting any data but need the command line stuff to have
			// finished before continuing.
			// If you don't need either then you can remove 'Process Results='
			// from the line above.
			BufferedReader br = new BufferedReader(new InputStreamReader(Results.getInputStream()));
			String s = br.readLine();
			br.close();
			br = null;
			Results = null;
			return s;
		} catch (Exception e) {
			return "Error with UNIX Command";
		}
	}

	
	public void storeParentBrowserWindow(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep,
			TestStepResult stepResult, TestSuiteResultSummary suiteSummaryResult) throws ActionScriptException, InterruptedException {
		logger.info("Calling SwitchBrowser click Action");
		try {
			setWinHandleBefore(getoBrowser().getWindowHandle());
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Stored parent window", null, null);
		} catch (Exception ex) {
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Unable to store parent window", null, null);
		}
	}

	
	public void switchToChildWindow(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep,
			TestStepResult stepResult, TestSuiteResultSummary suiteSummaryResult) throws ActionScriptException, InterruptedException {
		logger.info("Calling Switch to parent browser  Action");
		int windowsCount = 0;
		try {
			String parentWindow=getoBrowser().getWindowHandle();
			setWinHandleBefore(parentWindow);
			logger.info("Title before switching " + getoBrowser().getTitle());
			Set <String> all=getoBrowser().getWindowHandles();
			Iterator<String> itr = all.iterator();
			logger.info("Windows count " + getoBrowser().getWindowHandles().size());
			windowsCount = getoBrowser().getWindowHandles().size();
			if (windowsCount > 1) {
				while (itr.hasNext()){
					String childWindow = itr.next();
					if(!childWindow.equals(parentWindow)){
						getoBrowser().switchTo().window(childWindow);
						logger.info("Title after switching " + getoBrowser().getTitle());
					}
				}
				logger.info("Title " + getoBrowser().getTitle());
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "switched to Child window", null, null);
				logger.info("switched to new window");
			} else {
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Child window is not popped up", null, null);
				logger.error("Child window is not popped up");
			}
		} catch (Exception ex) {
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Failed to switch between browser", null, null);
			logger.error("Failed to switch between browser");
		}
	}

	
	public void switchToParentBrowserWindow(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep,
			TestStepResult stepResult, TestSuiteResultSummary suiteSummaryResult) throws ActionScriptException, InterruptedException {
		logger.info("Calling SwitchBrowser click Action");
		try {
			if (getWinHandleBefore() != null) {

				getoBrowser().switchTo().window(getWinHandleBefore());
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Switched to parent window", null, null);
				logger.info("Switched to parent window");
			} else {
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "call storeParentBrowserWindow  action before calling parentBrowserwindow",
						null, null);
				logger.error("call storeParentBrowserWindow  action before calling parentBrowserwindow");
			}
		} catch (Exception ex) {
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Unable to switch to parent window", null, null);
		}
	}
	

	
	public void switchToFrame(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep,
			TestStepResult stepResult, TestSuiteResultSummary suiteSummaryResult) throws ActionScriptException, InterruptedException {
		logger.info("Calling Switch to frame  Action");
		String objectPath = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier();
		try {
			List<WebElement> iframeSet=getoBrowser().findElements(By.tagName("iframe"));
			if(iframeSet.size()<1){
				List<WebElement> frameSet=getoBrowser().findElements(By.tagName("frame"));
				if(frameSet.size()<1){
					logger.error("No frames found please call switchToMainContent before proceeding further");
					org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "No frames found please call switchToMainContent before this step", null, null);
				}}
			else if(objectPath.contains("/")){
				try{
					org.openqa.selenium.WebElement ele = findElement(objectPath);
					getoBrowser().switchTo().frame(ele);
					logger.info("switched to frame");
					org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "switched to frame", null, null);
				}catch(NoSuchElementException e){
					logger.info("Unable to locate frame on the basis of xpath try with id/name/index of the frame");
					org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "frame not found on the basis of xpath try with id/name/index of the frame", null, null);
				}}
			else {
				try{
					getoBrowser().switchTo().frame(objectPath);
					logger.info("switched to frame with ID "+objectPath);
					org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "switched to frame", null, null);
				}catch(NoSuchFrameException ex){
					try{
						getoBrowser().switchTo().frame(Integer.parseInt(objectPath));
					}catch(Exception e){
						logger.error("Unable to locate frame on the basis of id/name/index try with xapth of the frame");
						org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Unable to locate frame on the basis of id/name/index try with xapth of the frame", null, null);
					}}
			}
		} catch (NoSuchElementException e) {
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Failed to switch to the frame", null, null);
			logger.error("Failed to switch to the frame");
		}
	}


	public void switchToMainContent(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep,
			TestStepResult stepResult, TestSuiteResultSummary suiteSummaryResult) throws ActionScriptException, InterruptedException {
		logger.info("Calling switchToMainContent Action");
		try {
			getoBrowser().switchTo().defaultContent();
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "switched to main content", null, null);
			logger.info("switched to main content");
		} catch (Exception ex) {
			logger.error("Unable to switch to main content");
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Unable to switch to main content", null, null);
		}
	}


	public void maximizeBrowserWindow(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep,
			TestStepResult stepResult, TestSuiteResultSummary suiteSummaryResult) throws ActionScriptException, InterruptedException {
		logger.info("Calling maximizeBrowserWindow Action");
		try {
			getoBrowser().manage().window().maximize();
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Browser window maximized", null, null);
			logger.info("Browser window maximized");
		} catch (Exception ex) {
			logger.error("Failed to maximize browser window");
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Unable to maximize browser window", null, null);
		}
	}

	
	public void waitForElementToLoad(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep,
			TestStepResult stepResult, TestSuiteResultSummary suiteSummaryResult) throws InterruptedException, ActionScriptException {
		logger.info("Calling waitForElementToLoad Action");
		waitForPageToLoad(30);
		String objectPath = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier();
		String screen = null;
		try {
			org.openqa.selenium.WebElement ele = findElement(objectPath);
			WebDriverWait wait = new WebDriverWait(oBrowser, 30);
			wait.until(ExpectedConditions.visibilityOf(ele));
			if (ele.isDisplayed()) {
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Element found in the page", null, null);
				logger.info("Element found");
			} else {
				screen = snapShot(plan);
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Fail to find the element in the page", null, screen);
				logger.error("Fail to find the element in the page");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			screen = snapShot(plan);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Invalid object or Object not found ", null, screen);
			logger.error("Element to be found in not available in the page");
		}
	}
	
	
	public void verifyAlert(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep,
			TestStepResult stepResult, TestSuiteResultSummary suiteSummaryResult) throws InterruptedException, ActionScriptException {
		String action = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getParamName();
		logger.info(" Calling verifyAlert action");
		try {
			Alert alert = getoBrowser().switchTo().alert();
			String alertMessage = alert.getText();
			if(action.equalsIgnoreCase("accept")){
				alert.accept();
				logger.error("Alert found and clicked on OK button");
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Alert found and clicked on OK button", null, null);
			}else if(action.equalsIgnoreCase("dismiss")){
				alert.dismiss();
				logger.error("Alert found and dismissed");
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Alert found and dismissed", null, null);
			}else{
				if(alertMessage.equals(action)){
					logger.error("Alert found and alertText is verified");
					org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "Alert found and alertText is verified", null, null);	
				}else{
					logger.error("Alert found but alerttext does not match the expected text. Expected text on alert: "+ action +", Actual text found on alert: "+ alertMessage);
					org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Alert found but alerttext does not match the expected text. Expected text on alert is : "+ action +" Actual text found on alert: "+ alertMessage, null, null);	
				}
			}
		}catch (NoAlertPresentException ex) {
			logger.error("Alert not found");
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Alert not found ", null, null);
		}
	}
	
	
	public String isAlertPresent() {
		String isAlertExists = null;
		try {
			// Check the presence of alert
			Alert alert = getoBrowser().switchTo().alert();
			// Alert present; set the flag
			isAlertExists = alert.getText();
			// if present consume the alert
			alert.accept();
			return isAlertExists;
		} catch (NoAlertPresentException ex) {
			return null;
		}

	}

	
	public void downloadFile(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep, TestStepResult stepResult,
			TestSuiteResultSummary suiteSummaryResult) throws ActionScriptException, InterruptedException {
		String propPath = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getParamName();
		logger.info("property file path : " + propPath);
		File propFilePath = new File(propPath);
		String objectPath = null;
		String screen = null;
		String downloadDir = null;
		String filePrefix = null;
		String filePostFix = null;
		String waitFor1 = null;
		String waitFor2 = null;
		String minSizeInBytes = null;
		String maxIterate = null;
		List<File> allFilesAfterDownloading = new ArrayList<File>();
		double fileSizeInBytes = 0L;
		File[] listOfFilesAfterDownloading = null;
		int iteratorCounter = 1;
		boolean sizeNotMatched = false;
		int filesCountAfterDownloading = 0;
		org.s2n.ddt.bean.UtlProps prop = new org.s2n.ddt.bean.UtlProps(propFilePath);
		try {
			objectPath = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier();
			downloadDir = prop.getProperty("downloadDir");
			filePrefix = prop.getProperty("filePrefix");
			filePostFix = prop.getProperty("filePostFix");
			waitFor1 = prop.getProperty("waitFor1");
			waitFor2 = prop.getProperty("waitFor2");
			minSizeInBytes = prop.getProperty("minSizeInBytes");
			maxIterate = prop.getProperty("maxIterate");

			File folder = new File(downloadDir);
			File[] listOfFiles = folder.listFiles();
			List<File> allFiles = new ArrayList<File>();
			int filesCountBeforeDownloading = listOfFiles.length;

			for (int i = 0; i < filesCountBeforeDownloading; i++) {
				allFiles.add(listOfFiles[i]);
				logger.info("File " + listOfFiles[i].getName());
			}
			org.openqa.selenium.WebElement ele = findElement(objectPath);
			ele.click();
			Thread.sleep(Integer.parseInt(waitFor1) * 1000);
			listOfFilesAfterDownloading = folder.listFiles();
			filesCountAfterDownloading = listOfFilesAfterDownloading.length;

			if (filesCountAfterDownloading > filesCountBeforeDownloading) {

				if (filePrefix.length() == 0) {

					for (int i = 0; i < filesCountAfterDownloading; i++) {
						if (listOfFilesAfterDownloading[i].getName().endsWith(filePostFix)) {
							allFilesAfterDownloading.add(listOfFilesAfterDownloading[i]);
							logger.info("File " + listOfFiles[i].getName());
						}
					}
				} else {
					for (int i = 0; i < filesCountAfterDownloading; i++) {
						if (listOfFilesAfterDownloading[i].getName().startsWith(filePrefix)
								&& listOfFilesAfterDownloading[i].getName().endsWith(filePostFix)) {
							allFilesAfterDownloading.add(listOfFilesAfterDownloading[i]);
							logger.info("File " + listOfFilesAfterDownloading[i].getName());
						}
					}
				}

				// for (int i = 0; i < filesCountAfterDownloading; i++) {
				// if (listOfFiles[i].getName().startsWith(filePrefix) &&
				// listOfFiles[i].getName().endsWith(filePostFix)) {
				// allFiles.add(listOfFiles[i]);
				// logger.info("File " + listOfFiles[i].getName());
				// }
				// }
				if (allFilesAfterDownloading.size() > 1) {
					File lastModifiedFile = allFilesAfterDownloading.get(0);
					for (int fileCounter = 1; fileCounter < allFilesAfterDownloading.size(); fileCounter++) {
						if (lastModifiedFile.lastModified() < allFilesAfterDownloading.get(fileCounter).lastModified()) {
							lastModifiedFile = allFilesAfterDownloading.get(fileCounter);
						}
					}
					logger.info(" Last modified file is " + lastModifiedFile);
					fileSizeInBytes = lastModifiedFile.length();
					if (fileSizeInBytes > Integer.parseInt(minSizeInBytes)) {
						org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "File  " + lastModifiedFile
								+ " exists and size is also more than the expected size", null, null);
					} else {
						while (iteratorCounter != Integer.parseInt(maxIterate)) {
							Thread.sleep(Integer.parseInt(waitFor2) * 1000);
							if (fileSizeInBytes > Integer.parseInt(minSizeInBytes)) {
								org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "File  " + lastModifiedFile
										+ " exists in the specified folder", null, null);
								sizeNotMatched = true;
								break;
							}
							iteratorCounter++;
						}

						if (!sizeNotMatched) {
							org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, true, "File " + lastModifiedFile
									+ " downloaded and size is less than the expected file size ", null, null);
						}
					}
				} else {
					org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "File not found ", null, null);
				}

			} else {
				org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "File not found in the specified folder", null, null);
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			screen = snapShot(plan);
			org.s2n.ddt.common.DdtCoreUtls.stepRslt(stepResult, false, "Object not found or Invalid object", null, screen);
			logger.error("Object not found or Invalid object");
		}
	}

	public static String getVersion() {
		return "1.6.0";
	}

	public String getWinHandleBefore() {
		return winHandleBefore;
	}

	public void setWinHandleBefore(String winHandleBefore) {
		this.winHandleBefore = winHandleBefore;
	}

	public void setCommonInformationProvider(CommonInformation ci) {
		this.ci = ci;
	}

}
