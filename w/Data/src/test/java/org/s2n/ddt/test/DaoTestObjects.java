package org.s2n.ddt.test;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.w3c.dom.Document;

import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.pojo.input.Objects;
import org.s2n.ddt.pojo.input.ObjectsId;
import org.s2n.ddt.service.InputService;

public class DaoTestObjects {

	/**
	 * Logger for this class
	 */
	static Map<String, String> map = new HashMap<String, String>();
	static String key, value;
	static ArrayList<String> objTypeArr, identifierTypeArr, objectGrpArr = new ArrayList<String>();
	static ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml", "dataAccessContext-local.xml" });
	static String user = "user.name";
	static int appID = 1;

	private static final Logger logger = Logger.getLogger(DaoTestObjects.class);

	/**
	 * @param args
	 * @throws DataAccessException
	 */
	public static void main(String[] args) throws DataAccessException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			logger.info("!!!!!!START!!!!!!");
			String home = System.getenv("HOME");
			File fname = new File(home + "/ObjectDump_Screenwise");
			if (fname.isDirectory()) {
				File[] fileNames;
				fileNames = fname.listFiles();
				for (int i = 0; i < fileNames.length; i++) {
					String filename = fileNames[i].getName().toString();
					if (!filename.contains(".DS")) {
						filename = fname + "/" + filename;
						System.out.println(filename);
						DocumentBuilder db = dbf.newDocumentBuilder();
						Document doc = db.parse(filename);
						ManipulateObjects mObj = new ManipulateObjects();
						map = mObj.parseDocument(doc);
						InputService inputService = (InputService) context.getBean("inputService");
						ObjectsId object = new ObjectsId();
						object.setObjectId(new BigDecimal(970));
						object.setObjectName("test");
						object.setObjectGroupId(new BigDecimal(8367489));
						object.setObjectTypeId(new BigDecimal(6734));
						object.setDescription("New Description");
						object.setIdentifierTypeId(new BigDecimal(345366));
						object.setIndentifier("hjdgfsdfgfhhg");
						object.setAppId(new BigDecimal(7898));
						object.setCreatedBy(System.getProperty(user));
						object.setCreatedDateTime(new Date());
						object.setUpdatedBy(System.getProperty(user));
						object.setUpdatedDateTime(new Date());
						Objects objects = new Objects();
						objects.setObjectsId(object);
						logger.info(inputService.insertObjectsDetails(objects));
						break;
					}
				}
			}

			logger.info("!!!!!!!END!!!!!!!");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
