package grxsnl;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;




/**
 * Name : SerialNumberFactory.java Desc :Factory method pattern implementation
 * that instantiates objects based on logic. Company : Exilant Technologies Pvt.
 * Ltd. Date : May 14, 2013
 *
 * @author : Shubha K
 *
 */
@Service

public class SerialNumberCheckerFactory {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SerialNumberCheckerFactory.class);

	private Map<Integer, SerialNumberCheckService> serialNumbermap = new HashMap<Integer, SerialNumberCheckService>();

	public SerialNumberCheckerFactory() throws Exception{
		initialize();
	}

	private void initialize() throws Exception {

		serialNumbermap.put(GRXMetadataConstants.SN_LENGTH_11,
				new SerialNumberLengthEleven());
		serialNumbermap.put(GRXMetadataConstants.SN_LENGTH_12,
				new SerialNumberLengthTwelve());
		serialNumbermap.put(GRXMetadataConstants.SN_LENGTH_13,
				new SerialNumberLengthThirteen());
		serialNumbermap.put(GRXMetadataConstants.SN_LENGTH_18,
				new SerialNumberLengthEighteen());
	}

	/**
	 * Method to return the class based on length.
	 *
	 * @param serialNumCharArray
	 * @return SerialNumberCheck.
	 *
	 * @throws GRXBusinessException
	 */
	public SerialNumberCheckService get(char[] serialNumCharArray) throws Exception {

		SerialNumberCheckService serialNumberCheck = null;
		int serialNumLength = serialNumCharArray.length;

		// based on logic factory instantiates an object
		switch (serialNumLength) {

		case GRXMetadataConstants.SN_LENGTH_11:
		case GRXMetadataConstants.SN_LENGTH_12:
		case GRXMetadataConstants.SN_LENGTH_13:
		case GRXMetadataConstants.SN_LENGTH_18:
			serialNumberCheck = serialNumbermap.get(Integer.valueOf(
					serialNumLength));
			break;
		default:
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(GRXBusinessConstants.SERIAL_NUMBER_FACTORY_EXCEPTION);
			}
			serialNumberCheck = null;
		}
		return serialNumberCheck;
	}

	public Map<Integer, SerialNumberCheckService> getSerialNumbermap() {
		return serialNumbermap;
	}
	
}
