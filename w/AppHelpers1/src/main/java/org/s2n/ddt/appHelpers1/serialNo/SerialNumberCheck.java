package org.s2n.ddt.appHelpers1.serialNo;

import java.util.Map;



public interface SerialNumberCheck {

	boolean checkSerialNumber(char[] serialNumber, Map<Integer,SerialNumberCheckService> SerialNumberCheckerFactory, SNLReqVO snlRequest) throws Exception;
}
