package org.s2n.ddt.common.share;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class CommonInformationDefault implements CommonInformation {
	private Map <String, String > data = new HashMap<String, String>();
	private Map <String, Serializable > dataBnry = new HashMap<String, Serializable>();
	
	
	public String setParamValue(String name, String value) {
		return data.put(name, value);
		
	}

	
	public String getParamValue(String name) {
		return data.get(name);
	}


	public Serializable setBinaryParamValue(String name, Serializable value) {
		return dataBnry.put(name, value);		
	}

	
	public Serializable getBinaryParamValue(String name) {
		return dataBnry.get(name);
	}


	public Set<String> paramsKeySet() {
		return data.keySet();
	}


	public Set<String> paramsBinaryKeySet() {
		return dataBnry.keySet();
	}

}
