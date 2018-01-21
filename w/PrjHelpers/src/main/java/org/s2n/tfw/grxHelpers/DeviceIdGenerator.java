package org.s2n.ddt.grxHelpers;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import org.s2n.ddt.service.impl.TestDao;

import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

/**
 * Generate random device ids for GRX. Since at times we can have server set to accept certain device ids (when talking to SAP) this class can be
 * configured to give from a fixed set or generate a random one
 * */

public class DeviceIdGenerator {
	static {
		System.setProperty("log4j.debug", "true");
	}
	private static final Logger logger = Logger.getLogger(DeviceIdGenerator.class);
	//public static final String POST_DEVICE_IDS[] = { "DTTP", "F8GH", "DJ8V", "DV33", "DHJP", "66JL", "F9GH" };
	//public static String POST_DEVICE_IDS[] = { "DTTP", "F8GH"};
	public static ArrayList<String> POST_DEVICE_IDS;
	private static ThreadLocal<SecureRandom> srtl = new ThreadLocal<SecureRandom>();
	private static ThreadLocal<Integer> nxtFixed = new ThreadLocal<Integer>();
	private static List<String> fixedIds = new ArrayList<String>();
	// private static DeviceIdGenerator dig = new DeviceIdGenerator();
	private Properties props;
	private java.sql.Connection connection;
	private java.sql.Statement stm;
	private ResultSet rs;
	private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private Date date = new Date();


	public DeviceIdGenerator() {
		props = new Properties();

	}

	public DeviceIdGenerator(Properties propsp) {
		props = propsp;
		initFixed();
	}
	
	public void setProps(Properties propsp) {
		props = propsp;
		initFixed();	
	}
	
	public void testGenerate() {
		int count = 100000;
		// Set<String> set = generate(count);
		// assertEquals(count, set.size());
	}
	
	public void initPostDeviceIds(){
		POST_DEVICE_IDS = new ArrayList<String>();
		String pDIds = props.getProperty("postDeviceIds");
		if (pDIds == null) {
			POST_DEVICE_IDS.add(new String("DTTP"));
			POST_DEVICE_IDS.add(new String("F8GH"));
		}else {
			for(String str : pDIds.split(",")) {
				POST_DEVICE_IDS.add(str);
			}
		}
	}
	
	public String generate(int index) {
		int count = 1;
		List<String> ids = new ArrayList<String>();
		if(POST_DEVICE_IDS.size() <= 0 ){
			initPostDeviceIds();
		}
		// int index = 0;
		int oldSize = -1, infiniteLoopDetector = 10;
		String timeSeed = props.getProperty("timeSeed");
		while (true) {
			String rand;
			if (timeSeed == null || timeSeed.equalsIgnoreCase("nano")) {
				rand = new Long(System.nanoTime()).toString();
			} else {
				rand = new Long(System.currentTimeMillis()).toString();
			}
			//rand = rand.substring(rand.length() - 8);
			rand = rand.substring(rand.length() - 7);

			String id = rand + POST_DEVICE_IDS.get(index++);
			ids.add(id);
			if (index >= POST_DEVICE_IDS.size()) {
				index = 0;
			}

			if (ids.size() == count) {
				break;
			}
			if (ids.size() == oldSize) {
				if (0 >= infiniteLoopDetector--) {
					throw new RuntimeException("can't generate " + count + " unique ids. Try a smaller number");
				}

			} else {
				oldSize = ids.size();
				infiniteLoopDetector = 10;
			}
		}
		 StringBuilder s1 = new StringBuilder(ids.get(0));
	     //s1.replace(4, 5, "X");
		 s1.insert(4, 'X');
	    // DevSaver(s1.toString());
		return s1.toString();
	}
	/*
	 * Simple File saver for Device ID and Date as req by QA team
	 */
	public static void DevSaver(String s) {
		Date dt = new Date();
		try {
		FileWriter fw = new FileWriter(new File("/data/ddt/DevIdStorage.txt"),true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("Date&Time :"+dt.toString()+"  Generated Device Id:"+s+"\n");
		bw.close();
		} catch (Exception ex) {
			System.out.println("Exception caught while writing data to DevFile");
		}
	}
	/**
	 * @param args
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException, IOException {

		log4Default();
		if (args != null && args.length == 0) {
			final String s = "deviceId_gen.properties";
			args = new String[1];
			args[0] = s;
		}
		Properties pro = new Properties();
		if (args != null && args.length > 0) {
			File f = new File(args[0]);
			InputStream is = null;

			try {
				is = new BufferedInputStream(new FileInputStream(f));
				pro.load(is);
			} catch (Exception e) {
				logger.warn("Err " + e, e);

			}finally {
				try {
					is.close();
				} catch (IOException e) {
					logger.warn("Err close is " + e, e);
				}
			}

		}
		DeviceIdGenerator dig = new DeviceIdGenerator(pro);
		// List<Integer> indxs = new ArrayList<Integer>();
		//int idx1 = 1;

		// for (int i = 0; i < 20; i++) {
		// if (i < args.length) {
		// try {
		// idx1 = Integer.parseInt(args[i]);
		// } catch (Exception e) {
		// idx1 = dig.getRand(0, POST_DEVICE_IDS.length);
		// }
		// } else {
		// idx1 = dig.getRand(0, POST_DEVICE_IDS.length);
		// }
		//
		// String s = dig.getOne();
		// logger.trace(i + ". " + POST_DEVICE_IDS[idx1] + " : " + s);
		// }

		for (int i = 1; i <= 20; i++) {
			logger.trace(i + " " + dig.getOneDeviceID());
		}

	}

	public String getOne() {
		String type = props.getProperty("generateType", "random");
		initPostDeviceIds();
		String s = null;// DNPL1093DTTP
		if ("random".equals(type)) {
			int i = getRand(0, POST_DEVICE_IDS.size());
			s = generate(i);
		} else if("dbFixed".equalsIgnoreCase(type)) {
			s = getDbFixed();
		} else {
			s = getNextFixed();
		}
		return s;
	}

	public java.sql.Connection connectToDB(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("MySQL JDBC Class Not found");
			e.printStackTrace();
		}
		java.sql.Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://10.1.0.25:3306/project","project", "project");
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
		}
		if (connection != null) {
			System.out.println("Connected to Database proceed...");
		} else {
			System.out.println("Failed to make connection!");
		}
		return connection;
	}

	
	public String getRandomSuffix() throws SQLException {
		String suffix = null;
		//connection=connectToDB();
		stm = connection.createStatement();
		rs = stm.executeQuery("select Suffix from GRX_DeviceID_Suffix WHERE Active ='1' ORDER BY RAND() LIMIT 1");
		while(rs.next()){
			suffix=rs.getString("Suffix");
		}
		return suffix;
	}
	
	public  String getOneDeviceID() throws SQLException, IOException {
		long range_size = 0;
		long absolute_min_value = 0;
		long absolute_max_value = 0;
		int x_position;
		
		long start_range;
		long end_range;
		long min = 0;
		long max = 0;
		String suffix;
		String sysDate=dateFormat.format(date);
		String randomPrefix=null;

		File file = new File("deviceId_gen.properties");
		FileInputStream fileInput = new FileInputStream(file);
		Properties properties = new Properties();
		properties.load(fileInput);
		fileInput.close();
		range_size = Long.parseLong(properties.getProperty("range_size"));
		absolute_min_value = Long.parseLong(properties.getProperty("absolute_min_value"));
		absolute_max_value = Long.parseLong(properties.getProperty("absolute_max_value"));
		x_position = Integer.parseInt(properties.getProperty("x_position"));
		start_range=absolute_min_value;
		end_range=start_range+range_size;
		
		connection=connectToDB();
		stm = connection.createStatement();
		do{
			suffix=getRandomSuffix();
			if(suffix==null){
				System.out.println("All suffixes are Inacive Please add suffixes before proceeding further");
				break;
			}else{
				rs = stm.executeQuery("select StartRange,EndRange from Runners_DeviceID_GEN where Type='DTTP' AND ExtraID = "+"'"+suffix+"'");
				if(rs.next()){
					min=rs.getLong("StartRange");
					max=rs.getLong("EndRange");
					if((range_size+max)<absolute_max_value){
						stm.executeUpdate("update Runners_DeviceID_GEN SET StartRange="+"'"+(min+range_size)+"'"+","+"EndRange="+"'"+(max+range_size)+"'"+"where ExtraID="+"'"+suffix+"'");
						randomPrefix=String.valueOf(getRand(min,max));
					}else{
						stm.executeUpdate("update GRX_DeviceID_Suffix SET Active='0' where Suffix="+"'"+suffix+"'");
					}}
				else{	
					stm.executeUpdate("INSERT INTO Runners_DeviceID_GEN (Type,StartRange,EndRange,DateAdded,ExtraID,Info) VALUES('DIDG',"+start_range+","+end_range+","+"'"+sysDate+"'"+","+"'"+suffix+"'"+",null"+")");
					min=start_range;
					max=end_range;
					randomPrefix=String.valueOf(getRand(min,max));
				}
			}
		}while(randomPrefix==null);
		StringBuilder DeviceID=new StringBuilder();
		DeviceID.append(randomPrefix);
		DeviceID.append(suffix);
		DeviceID.insert(x_position, "X");
	
		return DeviceID.toString();
	}
	
	private long  getRand(long min, long max) {
		SecureRandom sr = srtl.get();
		if (sr == null) {
			sr = new SecureRandom();
			srtl.set(sr);
		}
		long r = min+ (long)(sr.nextDouble()*(max-min));;
		return r;
	}
	
	
	private String getDbFixed() {
//		String dId = TestDao.selectDeviceId();
//		if (dId != null) { 
//			TestDao.updateDeviceIdStatus(dId);
//			return dId;
//		}else {
//			return new String("NoDeviceId");
//		}
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
			}else {
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

	private void initFixed() {
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
}
