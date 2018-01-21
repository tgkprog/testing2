package com.exilant.grx;

import java.util.Properties;

import javax.xml.ws.Endpoint;

public class PublishService {
	private static Endpoint ep;
	private static String ip = "0.0.0.0";
	//private static Properties mainProps = new Properties();

	public static void main(String[] a) {
		//org.s2n.ddt.bean.UtlProps.setMainProps(mainProps);
		//org.s2n.ddt.bean.UtlProps.initialize("/data/ddt/config/testApp.properties");
		
		System.out.println("1 starting");
		ep = Endpoint.publish("http://" + ip + ":9896/aeorderservice/", new OrderProcess());

		System.out.println("2started");
	}
	
	/**
	 * 
	 * @param name
	 *            - name of property non null.
	 * @param defalt
	 *            - can be null
	 * @return
	 */
	public static String getProperty(String name, String defalt) {
		return mainProps.getProperty(name, defalt);
	}
}
