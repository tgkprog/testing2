package org.s2n.ddt.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.s2n.ddt.constants.Constants;
import org.s2n.ddt.bean.ActionScriptException;

/**
 * ActionUtil classes
 */

public class ActionUtils {

	private static Logger logger = Logger.getLogger(ActionUtils.class);

	/**
	 * Snap shot. *
	 * 
	 * @param inputParams
	 *            the input params * @return the list
	 * @throws ActionScriptException
	 */
	public static List<String> snapShots(String inputParams, WebDriver oBrowser)
			throws ActionScriptException {
		List<String> list = ActionUtils.getList();
		try {
			Date date = new Date();
			String name = String.format("%s.%s", Constants.SNAPSHOT, date);
			String snaps = "./snaps/" + name + ".png";
			File scrFile = ((TakesScreenshot) oBrowser)
					.getScreenshotAs(OutputType.FILE);
			if (scrFile != null){
				FileUtils.copyFile(scrFile, new File(snaps));
			}
			list.add(Constants.SNAP_SHOT_CAPTURED);
			logger.info(Constants.SNAP_SHOT_CAPTURED);
		} catch (IOException ex) {
			logger.error("snapShot is not captured for failed testcase", ex);
		}
		return list;
	}

	/**
	 * Snap shot.
	 * 
	 * @return the string
	 * @throws ActionScriptException
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static String snapShot(WebDriver oBrowser)
			throws ActionScriptException {
		String snaps = null;
		try {
			Date date = new Date();
			String name = String.format("%s.%s", Constants.SNAPSHOT, date);
			snaps = "src/Failshots/snaps/"
					+ name.replace(" ", "").replace(":", "_") + ".jpg";
			File scrFile = ((TakesScreenshot) oBrowser)
					.getScreenshotAs(OutputType.FILE);
			if (scrFile != null){
				FileUtils.copyFile(scrFile, new File(snaps));
			}
			logger.info("snapShot is captured for failed testcase");
		} catch (IOException ex) {
			logger.error("snapShot is not captured for failed testcase", ex);

		}
		return snaps;
	}

	/**
	 * This method defines the waiting period to load a page
	 * 
	 * @param timesecs
	 * @param oBrowser
	 */
	public static void waitForPageToLoad(int timesecs, WebDriver oBrowser) {
		logger.info("Calling wait For Page To Load Action");
		oBrowser.manage().timeouts().implicitlyWait(timesecs, TimeUnit.SECONDS);
	}

	/**
	 * This will return an empty list for the caller
	 * 
	 * @return List<String>
	 */
	public static List<String> getList() {
		List<String> list = new ArrayList<String>();
		return list;
	}

	public static int compareLocalAndRemoteResources(File localImg,
			URL remoteUrl) {
		InputStream isLocal = null;
		InputStream remoteis = null;
		int ret = 1;
		boolean same = false;
		try {
			// take buffer data from botm image files //

			isLocal = new BufferedInputStream(new FileInputStream(localImg));

			remoteis = new BufferedInputStream(remoteUrl.openStream());
			int remoteInt = -1;
			int localInt = -1;
			same = true;
			while (remoteis.available() > 0 && isLocal.available() > 0) {
				localInt = isLocal.read();
				remoteInt = remoteis.read();
				if (localInt != remoteInt) {
					same = false;
					break;
				}
			}
		} catch (FileNotFoundException e) {
			logger.error("Failed to compare image files ..."+e);
		} catch (IOException e) {
			logger.error("Failed to compare image files ..."+e);
		} finally {
			try {
				isLocal.close();
			} catch (Exception e) {
				logger.error("Failed to compare image files ...");
			}
			try {
				remoteis.close();
			} catch (Exception e) {
				logger.error("Failed to compare image files ...");
			}
		}
		logger.info("same ..." + same);
		return ret;
	}

//	public static void tabOut() {
//		com.exilant.selenium.util.RobotHelper.sendKeys("\t", 100);
//	}

}
