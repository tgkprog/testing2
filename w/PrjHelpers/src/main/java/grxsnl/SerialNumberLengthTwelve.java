package grxsnl;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Name : SerialNumberLengthTwelve.java Desc : Class to hold logic for serial
 * number check when the length is 12. Company : Exilant Technologies Pvt. Ltd.
 * Date : May 14, 2013
 * 
 * @author : Aburva M
 * 
 */
public class SerialNumberLengthTwelve extends SerialNumberCheckService {
	private Pattern patternYear;
	private Pattern patternWeek;
	
	public SerialNumberLengthTwelve() {
		
		patternYear = Pattern
				.compile(GRXMetadataConstants.SERIAL_NUMBER_YEARPATTERN);
		patternWeek = Pattern
				.compile(GRXMetadataConstants.SERIAL_NUMBER_WEEKPATTERN);
		
	}
	/**
	 * Method to validate Serial number - Pattern check and digit check.
	 * 
	 * @param serialNumber
	 * @return true if validation is successful.
	 * 
	 * @throws Exception
	 */
	
	public boolean checkSerialNumber(char[] serialNumber, Map<Integer, SerialNumberCheckService> serialNumberFactory,SNLReqVO snlRequest) throws Exception {

		boolean validSerialNumber = false;

		// Perform basic validation for the device ID.
		if (super.validate(serialNumber)) {

			//Remove the first character "S"
			char strYearChar = serialNumber[3];
			
			// If character 4 is numeric then it is a 11 character serial # with
			// leading S (SPPYWWSSSCCC). Remove the leading "S" and validate for
			// 11 character serial number format and send the 11 character
			// serial number to SAP.
			if (Character.isDigit(strYearChar)) {
				validSerialNumber = validateLeadingSChar(serialNumber,
						serialNumberFactory, snlRequest);

			}
			// If character 4 is alpha numeric then it is a 12 character serial
			// # without leading S (PPPYWSSSCCCC). Validate for 12 character
			// serial number format and send the 12 character serial number to
			// SAP.
			else {
				validSerialNumber = lengthTwelveValidation(serialNumber,
						snlRequest);
			}
		}
		return validSerialNumber;
	}
	
	/**
	 * If character 4 is alpha numeric then it is a 12 character serial #
	 * without leading S (PPPYWSSSCCCC). Validate for 12 character serial number
	 * format and send the 12 character serial number to SAP.
	 * 
	 * @param serialNumber
	 * @param serialNumberFactory
	 * @param snlRequest
	 * 
	 * @return true if validation is successful.
	 * 
	 */
	private boolean lengthTwelveValidation(char[] serialNumber,
			SNLReqVO snlRequest) {
		boolean validSerialNumber = false;
		CharSequence strYear = Character.toString(serialNumber[4]);
		CharSequence strWeek = Character.toString(serialNumber[5]);

		Boolean strYearStatus = validatePatternMatcher(patternYear,
				strYear);
		Boolean strWeekStatus = validatePatternMatcher(patternWeek,
				strWeek);
		Boolean yearStatus = validatePatternMatcher(patternYear,
				Character.toString(serialNumber[3]));
		Boolean weekStatus = validatePatternMatcher(patternWeek,
				Character.toString(serialNumber[4]));

		boolean isDigit = Character.isDigit(serialNumber[3]);

		if (isDigit && strYearStatus && strWeekStatus) {
			validSerialNumber = true;
		} else if (!isDigit && yearStatus && weekStatus) {
			validSerialNumber = true;
		}
		//Send the old validated 12 char length serial number for SAP.
		snlRequest.setSerialNumber(new String(serialNumber));
		return validSerialNumber;
	}
	
	/**
	 * If 5th character is alpha numeric then the serial number has a leading "S"
	 * (SPPPYWSSSCCCC). Remove the leading "S" and validate for 12 character
	 * serial number format and send the 12 character serial number to SAP.
	 * 
	 * @param serialNumber
	 * @param serialNumberFactory
	 * @param snlRequest
	 * 
	 * @return true if validation is successful.
	 * 
	 * @throws Exception
	 */
	private boolean validateLeadingSChar(char[] serialNumber,
			Map<Integer, SerialNumberCheckService> serialNumberFactory,
			SNLReqVO snlRequest) throws Exception {
		boolean validSerialNumber;
		char[] newSerialNumber = new char[(serialNumber.length)-1];

		//Remove S and form a new serial number
		for(int i =1;i<serialNumber.length;i++)
		{
			newSerialNumber[i-1] = serialNumber[i];
		}
		//Call 11 length validation class.
		SerialNumberCheck serialNumberCheck = serialNumberFactory.get(GRXMetadataConstants.SN_LENGTH_11);
		validSerialNumber = serialNumberCheck.checkSerialNumber(newSerialNumber, serialNumberFactory,snlRequest);
		//Send the new formated 11 char length serial number for SAP.
		snlRequest.setSerialNumber(new String(newSerialNumber));
		return validSerialNumber;
	}

	/**
	 * Method to validate Serial number - Pattern check and digit check.
	 * 
	 * @param serialNumber
	 * @return true if validation is successful.
	 * 
	 * @throws Exception
	 */
	private boolean validatePatternMatcher(Pattern pattern,
			CharSequence strValidation) {
		
		Matcher matcher = pattern.matcher(strValidation);
		return matcher.matches();

	}
	

}
