package org.s2n.ddt.appHelpers1.serialNo;
import java.util.Map;



public class SerialNumberLengthEleven extends SerialNumberCheckService {

	/**
	 * Method to validate Serial number - Digit check and week validation.
	 * 
	 * @param serialNumber
	 * @return true if validation is successful.
	 * 
	 * @throws Exception
	 */
	
	public boolean checkSerialNumber(char[] serialNumber, Map<Integer, SerialNumberCheckService> SerialNumberCheckerFactory, SNLReqVO snlRequest) throws Exception {

		if (super.validate(serialNumber)) {
			if ((Character.isDigit(serialNumber[2]))
					&& (Character.isDigit(serialNumber[3]))
					&& (Character.isDigit(serialNumber[4]))) {

				int buffer1 = Character.getNumericValue(serialNumber[3]);
				int buffer2 = Character.getNumericValue(serialNumber[4]);
				
				if(buffer2 == 0 && buffer1 != 0){
					buffer2 = 1;
				}
				
				int weekValidation = (int) Math.pow(10,
						Math.floor(Math.log10(buffer2)) + 1)
						* buffer1 + buffer2;

				if (weekValidation >= 1 && weekValidation <= 53) {
					return true;
				}
				throw new Exception(GRXBusinessConstants.GRX_SNV_002);
			}
		}

		return false;

	}

}
