package org.s2n.ddt.util;

import java.io.File;

import org.apache.log4j.Logger;


public class DdtIoUtls {
	//private static final Logger logger = Logger.getLogger(DdtIoUtls.class);
	
	//keep this before logger as its a helper to see where the logs are going - relative to app path.
	public static void printCurrentFolderName() {
		try {
			java.io.File f = new File("./");
			System.err.println("Current Folder (-) :" + f.getAbsolutePath());
			f = f.getCanonicalFile();
			System.err.println("Canonical Folder :" + f);
		} catch (Exception e) {
			System.err.println("Error getting current folder ./ :" + e);
			e.printStackTrace(System.err);
		}
	}
	
	
	
	public static void main(String[] args) {
		 printCurrentFolderName();
	}

}
