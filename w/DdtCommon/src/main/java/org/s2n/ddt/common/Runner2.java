package org.s2n.ddt.common;

import java.util.Map;

import org.s2n.ddt.common.share.CommonInformation;
import org.s2n.ddt.pojo.def.Runner;

public interface Runner2 {
	public void setRunnersInformation(Map<String, Runner> runners );
	public void setCommonInformationProvider(CommonInformation ci);
	
}
