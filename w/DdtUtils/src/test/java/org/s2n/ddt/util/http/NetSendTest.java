package org.s2n.ddt.util.http;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.s2n.ddt.util.LangUtils;

import junit.framework.Assert;

//import javax.net.ssl.*;

/**
 * Test Send data to other JVM .
 * 
 * @author Tushar Kapila
 * 
 */
public class NetSendTest {
	private static final Logger logger = Logger.getLogger(NetSendTest.class);
	java.util.Date dt = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS ZZZ");
	static {
		LangUtils.log4Default(false);
	}

	private String makeTesNameValuesToSend() {
		final StringBuilder sb = new StringBuilder();
		try {
			sb.append("ok=s&app=trade");
			final String tm = java.net.URLEncoder.encode(sdf.format(dt));
			sb.append("sub_app=sell&when=").append(tm);
		} catch (Exception e) {
			System.out.println("Encode ex :" + e);
			e.printStackTrace();
		}
		return sb.toString();
	}

	@Test
	public void sendSSl() {
		final String dat = makeTesNameValuesToSend();
		System.out.println("Final data to server");
		Map<String, String> headers = new HashMap<String, String>();
		String exp = "connect to data base OK";
		// exp ="pickerElementDisplay.style.display";
		HttpData hDat = new HttpData();
		hDat.setTimeoutMilli(12000);
		// hDat.setUrl("https://exvis2.sel2in.com/SSO/index.htm");
		hDat.setUrl("https://sel2in.com/up6.php");
		try {
			boolean send1 = send(hDat, dat, headers, exp);
			Assert.assertEquals(true, send1);
			System.out.println("https port 443 ssl worked");
		} catch (Exception e) {
			logger.warn("ssl send error " + e, e);
		}
	}

	@Test
	public void sendHttp() {
		final String dat = makeTesNameValuesToSend();
		Map<String, String> headers = new HashMap<String, String>();
		String exp = "connect to data base OK";
		// exp ="pickerElementDisplay.style.display";
		HttpData hDat = new HttpData();
		hDat.setTimeoutMilli(12000);
		// hDat.setUrl("https://exvis.sel2in.com/SSO/index.htm");
		hDat.setUrl("http://sel2in.com/up6.php");
		try {
			boolean send1 = send(hDat, dat, headers, exp);
			Assert.assertEquals(true, send1);
			System.out.println("http port 80 no ssl worked");
		} catch (Exception e) {
			logger.warn("http err :" + e, e);
		}
	}

	public static boolean send(HttpData hDat, String dat, Map<String, String> headers, String expext) throws Exception {

		boolean b = false;
		String rtn = NetSend.send(hDat, dat, headers);
		b = rtn != null && rtn.indexOf(expext) > -1;
		logger.info("RTN :" + rtn);
		return b;
	}

}
