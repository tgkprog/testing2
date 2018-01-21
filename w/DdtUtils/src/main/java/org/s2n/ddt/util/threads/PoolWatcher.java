package org.s2n.ddt.util.threads;

import org.apache.log4j.Logger;

import org.s2n.ddt.bean.UtlConf;

public class PoolWatcher implements Runnable{
	private static final Logger logger = Logger.getLogger(PoolWatcher.class);
	private static PoolWatcher poolW = new PoolWatcher();
	
	
	
	 static {
		 reinitExtrasPool();
	 }

	private static void reinitExtrasPool() {
		final String poolName = "background-extras";
		logger.trace("pool :");
		String smax = UtlConf.getProperty("utl.background-extras.poolMax", "1");
		int max = 1;
		try {
			max = Integer.parseInt(smax);
		} catch (NumberFormatException e) {
			logger.warn("reinitPool smax parse " + e + " for pool :" + poolName + ", mx :" + max);
		}
		PoolOptions options = new PoolOptions();
		options.setMaxThreads(max);
		options.setCoreThreads((int) ((1 + max) / 2)); 
		DdtPools.initPool(poolName, options);
		
	}

	
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
