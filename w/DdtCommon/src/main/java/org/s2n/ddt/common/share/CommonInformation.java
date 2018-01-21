package org.s2n.ddt.common.share;

import java.io.Serializable;
import java.util.Set;

public interface CommonInformation {
	public String setParamValue(String name, String value);
	public String getParamValue(String name);
	public Set<String> paramsKeySet();
	
	public Serializable setBinaryParamValue(String name, Serializable value);
	public Serializable getBinaryParamValue(String name);
	public Set<String> paramsBinaryKeySet();
	
}
