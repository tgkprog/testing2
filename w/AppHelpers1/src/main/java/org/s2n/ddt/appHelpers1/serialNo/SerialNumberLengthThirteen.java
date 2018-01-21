package org.s2n.ddt.appHelpers1.serialNo;
import java.util.Map;



public class SerialNumberLengthThirteen extends SerialNumberCheckService {

	/**
	 * Method to validate Serial number - Digit check.
	 * 
	 * @param serialNumber
	 * @return true if validation is successful.
	 * 
	 * @throws Exception
	 */

	public boolean checkSerialNumber(char[] serialNumber, Map<Integer, SerialNumberCheckService> serialNumberFactory, SNLReqVO snlRequest) throws Exception {
		boolean validSerialNumber = false;
		if (super.validate(serialNumber)) {
			validSerialNumber = validateForLeadingSChar(serialNumber,
					serialNumberFactory, snlRequest);
		}
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
	private boolean validateForLeadingSChar(char[] serialNumber,
			Map<Integer, SerialNumberCheckService> serialNumberFactory,
			SNLReqVO snlRequest)
			throws Exception {
		boolean validSerialNumber=false;
		char forthChar = serialNumber[4];
		if (Character.isLetter(forthChar)) {
			char[] newSerialNumber = new char[(serialNumber.length)-1];
			//Remove S and perform 12 length validation
			for(int i =1;i<serialNumber.length;i++)
			{
				newSerialNumber[i-1] = serialNumber[i];
			}
			
			//Call 12 length validation class.
			SerialNumberCheck serialNumberCheck = serialNumberFactory.get(GRXMetadataConstants.SN_LENGTH_12);
			validSerialNumber = serialNumberCheck.checkSerialNumber(newSerialNumber, serialNumberFactory,snlRequest);
			//Send the new formated 12 char length serial number for SAP.
			snlRequest.setSerialNumber(new String(newSerialNumber));
		}
		return validSerialNumber;
	}
}