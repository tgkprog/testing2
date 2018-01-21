package org.s2n.ddt.common;

import java.util.Map;

import org.s2n.ddt.common.share.CommonInformation;
import org.s2n.ddt.pojo.def.Runner;

/**
 * Does nothing runner. Good to extend and over ride only functions you need
 * @author tusharkapila
 *
 */
//TODO move def.Runner and other classes from Data to Common
public class RunnerAdapter implements  Runner2 {

	
	public void setRunnersInformation(Map<String, Runner> runners) {
	
		
	}

	
	public void setCommonInformationProvider(CommonInformation ci) {
		
	}

}
