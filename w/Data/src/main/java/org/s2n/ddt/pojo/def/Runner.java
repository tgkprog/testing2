package org.s2n.ddt.pojo.def;

import java.util.Map;

public interface Runner extends DdtRemoteInterface {
	
	//public void runnersInfo(Map<String, Runner> runners );
	//public void paramSet(String name, String value);
	public Runner clone2() throws CloneNotSupportedException;
}
