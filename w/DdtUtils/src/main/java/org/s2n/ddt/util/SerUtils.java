package org.s2n.ddt.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;

/**
 * @author Tushar Kapila
 */

public class SerUtils {
	private static final Logger logger = Logger.getLogger(SerUtils.class);

	public static void main(String[] args) {

	}

	public static byte[] getObjectSerialized(Object object) {
		try {
			ObjectOutputStream out;
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			out = new ObjectOutputStream(bos);
			out.writeObject(object);
			out.close();
			return bos.toByteArray();
		} catch (Exception e) {
			logger.fatal("Error: " + e, e);
		}
		return null;
	}

	public static Object retriveObject(byte bytes[]) {
		try {
			Object object;
			ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bytes));
			object = in.readObject();
			return object;
		} catch (Exception e) {
			logger.fatal("Error: " + e, e);
		}
		return null;
	}

}
