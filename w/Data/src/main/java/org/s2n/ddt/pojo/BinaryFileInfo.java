package org.s2n.ddt.pojo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class BinaryFileInfo {

	private byte[] binartData;//use this
	private String suggestedName;//ore fix to file name
	private String suggestedExtn;//use this, ending part of file name
	private String mimeType;
	
	
	
	/**
	 * @param args
	 */
	public static void main (String[] args) {
		try {
			BinaryFileInfo bImg = new BinaryFileInfo();
			bImg.setMimeType("image/jpeg");
			bImg.setSuggestedExtn("jpg");
			bImg.setSuggestedName("error_sceen_shot");
			File folderForTaskResult = null;//frpm task result
			java.io.File fFile = File.createTempFile(bImg.suggestedName, bImg.suggestedExtn, folderForTaskResult);
			
			ArrayList <Byte> imgInfo = new ArrayList <Byte> ();
			byte byteReadFromFile = 33;
			//use inpout stream - read bytes and add 
			imgInfo.add(byteReadFromFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	public final byte[] getBinartData() {
		return binartData;
	}



	public final void setBinartData(byte[] binartData) {
		this.binartData = binartData;
	}



	public final String getSuggestedName() {
		return suggestedName;
	}



	public final void setSuggestedName(String suggestedName) {
		this.suggestedName = suggestedName;
	}



	public final String getSuggestedExtn() {
		return suggestedExtn;
	}



	public final void setSuggestedExtn(String suggestedExtn) {
		this.suggestedExtn = suggestedExtn;
	}



	public final String getMimeType() {
		return mimeType;
	}



	public final void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

}
