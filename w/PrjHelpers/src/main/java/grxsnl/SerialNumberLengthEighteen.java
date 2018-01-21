package grxsnl;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


//import com.apple.ist.grx.validator.cancel.CancelOrderEligibilityValidator;

/**
 * Name : SerialNumberLengthEighteen.java Desc : Class to hold logic for serial
 * number check when the length is 18. Company : Exilant Technologies Pvt. Ltd.
 * Date : May 14, 2013
 * 
 * @author : Shubha K
 * 
 */
public class SerialNumberLengthEighteen extends SerialNumberCheckService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SerialNumberLengthEighteen.class);

	/**
	 * Method to validate Serial number - year week validation.
	 * 
	 * @param serialNumber
	 * @return true if validation is successful.
	 * 
	 * @throws Exception
	 */
	//@Override
	public boolean checkSerialNumber1(char[] serialNumber,  Map<Integer, SerialNumberCheckService> SerialNumberCheckerFactory, SNLReqVO snlRequest) throws Exception {

		boolean validSerialNumber = false;
		
		return validSerialNumber;
	}

	/**
	 * Method to validate check sum.
	 * 
	 * @param serialNumber
	 * @return true if validation is successful.
	 * 
	 * @throws Exception
	 */
	private boolean validCheckSum(char[] serialNumber) throws Exception {
		Pattern patternCheck;
		int radix = 10;
		int total = 0;		
		String serialNumberCS = new String(serialNumber);
				
		patternCheck = Pattern.compile(GRXMetadataConstants.CHECKSUM_PATTERN);
		CharSequence serialNo = serialNumberCS;
		
		Boolean snoStatus = validatePatternMatcher(patternCheck, serialNo);
		
		if(snoStatus){
			for(int iterateSerialNo = 0 ; iterateSerialNo <= 17 ; iterateSerialNo++){
				if(!Character.isDigit(serialNumber[iterateSerialNo])){
					radix = 34;
				} else if (Character.getNumericValue(serialNumber[iterateSerialNo]) == 1) {
					total += 3;
				} else {
					total += Character.getNumericValue(serialNumber[iterateSerialNo]);
				}
			}			
		} else {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(GRXBusinessConstants.INVALID_NUMBER);
			}
			return false;
		}		
		return checkSumResponse(total, radix);	
	}

	private boolean checkSumResponse(int total, int radix)
			throws Exception {
			if (((total % radix) == 0)) {
				return true;
			}		
		return false;
	}
	
	private boolean validatePatternMatcher(Pattern pattern,
			CharSequence strValidation) {
		Matcher matcher;
		matcher = pattern.matcher(strValidation);
		LOGGER.debug("Status of matcher : "+ matcher.matches());
		return matcher.matches();
	}

	private boolean validateYearWeekDay(char[] serialNumber)
			throws Exception {
		boolean validYrWeekDay = false;

			// Concatenate the 3,4,5,6 characters and check if all are
			// numbers

			for (int i = 3; i < 7; i++) {
				if (Character.isDigit(serialNumber[i])) {
					validYrWeekDay = true;
				}
			}

			if (validYrWeekDay) {
				int day = Character.getNumericValue(serialNumber[6]);
				if (!(day >= 1 && day <= 7)) {
					validYrWeekDay = false;
				}
			}

		return validYrWeekDay;
	}

	public boolean checkSerialNumber(char[] serialNumber, Map<Integer, SerialNumberCheckService> SerialNumberCheckerFactory, SNLReqVO snlRequest)
			throws Exception {
		return true;
	}
}
