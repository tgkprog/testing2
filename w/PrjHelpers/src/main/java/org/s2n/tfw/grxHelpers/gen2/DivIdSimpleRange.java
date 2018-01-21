package org.s2n.ddt.grxHelpers.gen2;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import org.s2n.ddt.bean.UtlProps;
import org.s2n.ddt.util.LangUtils;

public class DivIdSimpleRange {

	// import org.s2n.ddt.util.LangUtils;

	/**
	 * Generate random device ids for GRX. Since at times we can have server set to accept certain device ids (when talking to SAP) this class can be
	 * configured to give from a fixed set or generate a random one
	 * */

	static {
		// System.setProperty("log4j.debug", "true");
	}
	private static final Logger logger = Logger.getLogger(DivIdSimpleRange.class);
	// public static final String POST_DEVICE_IDS[] = { "DTTP", "F8GH", "DJ8V", "DV33", "DHJP", "66JL", "F9GH" };
	// public static String POST_DEVICE_IDS[] = { "DTTP", "F8GH"};
	private ArrayList<String> postSuffixes = new ArrayList<String>();
	private ArrayList<String> infixes = new ArrayList<String>();
	private static ThreadLocal<SecureRandom> srtl = new ThreadLocal<SecureRandom>();
	private static ThreadLocal<Integer> nxtFixed = new ThreadLocal<Integer>();
	private static List<String> fixedIds = new ArrayList<String>();

	private static String defaultProps = "/data/ddt/config/grx/generate/deviceId_gen.pro";
	// private static DivIdSimpleRange dig = new DivIdSimpleRange();
	private UtlProps props = new UtlProps();
	long min;
	long max;
	long current = -1;
	private int infixAt;

	public DivIdSimpleRange() {
		init();

	}

	public DivIdSimpleRange(UtlProps propsp) {
		props = propsp;
		init();
	}

	public DivIdSimpleRange(String propsp) {
		props.initialize(new File(propsp));
		init();
	}

	public DivIdSimpleRange(Properties propsp) {
		props.getProps().putAll(propsp);
		init();
	}

	public void setProps(Properties propsp) {
		props.getProps().putAll(propsp);
		init();
	}

	public void testGenerate() {
		int count = 100000;
		// Set<String> set = generate(count);
		// assertEquals(count, set.size());
	}

	public void initPostDeviceIds() {
		postSuffixes.clear();
		String pDIds = props.getProperty("postDeviceIds");
		if (pDIds == null) {
			postSuffixes.add(new String("DTTP"));
			postSuffixes.add(new String("F8GH"));
		} else {
			for (String str : pDIds.split(",")) {
				postSuffixes.add(str);
			}
		}

		infixes.clear();
		pDIds = props.getProperty("infixes", "KY");

		for (String str : pDIds.split(",")) {
			infixes.add(str);
		}
		String s = props.getProperty("insertIndexesAt", "3");
		infixAt = LangUtils.getInt(s, 3, "infix at loc (from 0");
	}

	public String generate() {
		StringBuilder s1 = new StringBuilder();

		// int index = 0;
		// int oldSize = -1, infiniteLoopDetector = 10;
		// String timeSeed = props.getProperty("timeSeed");
		// String preFix = props.getProperty("preFix", "");
		// String numbLenS = props.getProperty("numLen", "7");
		// String xAtS = props.getProperty("xAt", "3");
		// int xAt = LangUtils.getInt(xAtS, 4, "xAt");
		// int numbLen = LangUtils.getInt(numbLenS, 4, "randRemoveFrom");

		s1.append(current + "");
		current++;
		if (current > max) {
			current = min;
			logger.warn("exhausted device ids, recycling from next");
		}
		int index = getRand(0, infixes.size());
		String infix = infixes.get(index++);
		s1.insert(infixAt, infix);
		index = getRand(0, postSuffixes.size());
		s1.append(postSuffixes.get(index));

		return s1.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		log4Default();
		if (args != null && args.length == 0) {
			final String s = defaultProps;
			args = new String[1];
			args[0] = s;
		}

		// Properties pro = new Properties();

		// loadProps(args[0], pro, false);
		DivIdSimpleRange dig = new DivIdSimpleRange(args[0]);
		for (int i = 1; i <= 20; i++) {
			logger.trace(i + " " + dig.getOne());
		}

	}

	public String getOne() {
		String type = props.getProperty("generateType", "range");
		initPostDeviceIds();
		String s = null;// DNPL1093DTTP
		if ("range".equals(type)) {

			s = generate();
		} else if ("dbFixed".equalsIgnoreCase(type)) {
			s = getDbFixed();
		} else {
			s = getNextFixed();
		}
		return s;
	}

	private String getDbFixed() {
		// String dId = TestDao.selectDeviceId();
		// if (dId != null) {
		// TestDao.updateDeviceIdStatus(dId);
		// return dId;
		// }else {
		// return new String("NoDeviceId");
		// }
		return "not done";
	}

	private int getRand(int mi, int mx) {
		SecureRandom sr = srtl.get();
		if (sr == null) {
			sr = new SecureRandom();
			srtl.set(sr);
		}
		int r = sr.nextInt(mx) + mi;
		return r;

	}

	private String getNextFixed() {
		String sRtnId = "NOT-SET";
		if (fixedIds.size() > 0) {
			Integer ii = nxtFixed.get();
			int i = 0;
			if (ii == null) {
				ii = 0;
			} else {
				ii = ii + 1;
			}

			i = ii;
			if (i >= fixedIds.size()) {
				// rotate
				i = 0;
				ii = i;
			}
			nxtFixed.set(ii);
			sRtnId = fixedIds.get(i);
		}
		return sRtnId;
	}

	private void init() {
		String ss = props.getProperty("min", "203000");
		min = LangUtils.getLong(ss, 20300, "min");

		ss = props.getProperty("max", "993000");
		max = LangUtils.getLong(ss, 993000, "max");
		if(current < min){
			current = min;
		}

		initPostDeviceIds();

		int i = 1;
		String key = "fixed." + i;
		String val = props.getProperty(key);
		while (val != null) {
			fixedIds.add(val);
			i++;
			key = "fixed." + i;
			val = props.getProperty(key);
		}
	}

	public void refreshTypeOnly(String propsp) {
		props.initialize(new File(propsp));
	}

	private static void log4Default() {
		boolean noLog = true;
		org.apache.log4j.Logger rootLogger = org.apache.log4j.Logger.getRootLogger();
		Enumeration appenders = rootLogger.getAllAppenders();
		if (!appenders.hasMoreElements()) {
			System.out.println("LOG4J config file is missing");
		} else {
			System.out.println("appender found " + ((Appender) appenders.nextElement()).getName());
			noLog = false;

		}

		if (noLog) {
			System.out.println("no log4j");
			Layout layout = new PatternLayout(" %-5p %t %d [%t][%F:%L] : %m%n");
			Appender ap = new ConsoleAppender(layout, ConsoleAppender.SYSTEM_OUT);
			Logger.getRootLogger().setLevel(Level.ALL);
			// Logger.getRootLogger().addAppender(new ConsoleAppender(layout, ConsoleAppender.SYSTEM_ERR));
			Logger.getRootLogger().addAppender(ap);
		}

	}

	/**
	 * if make new set your properties to what is returned. else can send an existng instance
	 * 
	 * @return can be null in case of error, or if null sent and makeNew is false
	 * */
	static Properties loadProps(String l, Properties pro, boolean makeNew) {
		File f = new File(l);
		InputStream is = null;
		if (makeNew) {
			pro = new Properties();
		}
		try {
			is = new BufferedInputStream(new FileInputStream(f));
			pro.load(is);
		} catch (Exception e) {
			logger.warn("Err " + e, e);

		} finally {
			try {
				is.close();
			} catch (IOException e) {
				logger.warn("Err close is " + e, e);
			}
		}
		return pro;

	}
}
