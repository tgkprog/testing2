package grxsnl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




/**
 * Name : SerialNumberCheck.java
 *
 * Desc : Company : Exilant Technologies Pvt. Ltd. 
 * Date : May 14, 2013
 * 
 * @author : Shubha K
 * 
 */
public abstract class SerialNumberCheckService implements SerialNumberCheck {

	/** The Constant logger. */
	private static final Logger LOG = LoggerFactory
			.getLogger(SerialNumberCheckService.class);
	
	/**
	 * Method to validate the device ID.
	 * 
	 * @param serialNumber
	 * @return true if validation is successful.
	 * @throws Exception
	 */

	//TODO - private boolean validate(char[] serialNumber)
	
	
	boolean validate(char[] serialNumber)
			throws Exception {
		boolean valid = true;
		try {
			int length = serialNumber.length - 1;

			int seqChars = 0, repeatChar = 0, firstChar = 0, secChar = 0;

			for (int i = 0; i < length; i++) {
				firstChar = serialNumber[i];
				secChar = serialNumber[i + 1];
				if ((secChar - firstChar == 1)
						|| (secChar - firstChar + 32 == 1)
						|| (secChar - firstChar - 32 == 1)) {
					seqChars++;
				}

				if ((secChar == firstChar) || (secChar == firstChar - 32)
						|| (secChar == firstChar + 32)) {
					repeatChar++;
				}
			}

			if (seqChars == length) {
				LOG.debug("Serial number has sequential characters.");
				valid = false;
			}

			if (repeatChar == length) {
				LOG.debug("Serial number has same alpha/numeric characters.");
				valid = false;
			}
			
		} catch (Exception exception) {
			throw new Exception(exception);
		}

		return valid;
	}
	
}
