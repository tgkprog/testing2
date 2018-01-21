package grxsnl;
public class SNLReqVO {
	private String serialNumber;
	private String subDt;
	private String comptia;
	private String countryofpurchase;
	private boolean isSAP;
	private String salesOrgID;
	private String soldToCode;

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getSubDt() {
		return subDt;
	}

	public void setSubDt(String subDt) {
		this.subDt = subDt;
	}

	public String getComptia() {
		return comptia;
	}

	public void setComptia(String comptia) {
		this.comptia = comptia;
	}

	public String getCountryofpurchase() {
		return countryofpurchase;
	}

	public void setCountryofpurchase(String countryofpurchase) {
		this.countryofpurchase = countryofpurchase;
	}

	/**
	 * @return the isSAP
	 */
	public boolean isSAP() {
		return isSAP;
	}

	/**
	 * @param isSAP the isSAP to set
	 */
	public void setSAP(boolean isSAP) {
		this.isSAP = isSAP;
	}


	public String getSalesOrgID() {
		return salesOrgID;
	}

	public void setSalesOrgID(String salesOrgID) {
		this.salesOrgID = salesOrgID;
	}

	public String getSoldToCode() {
		return soldToCode;
	}

	public void setSoldToCode(String soldToCode) {
		this.soldToCode = soldToCode;
	}

	@Override
	public String toString() {
		StringBuilder deviceString = new StringBuilder();

			deviceString.append(" serialNumber=\"")
				.append(getSerialNumber())
				.append("\"");

			deviceString.append(" salesOrgId=\"")
			.append(getSalesOrgID())
			.append("\"");

			deviceString.append(" isSAP=\"")
			.append(isSAP())
			.append("\"");




		return new StringBuilder()
			.append("svcAcctNo=\"")
			.append(getSoldToCode())
			.append("\" ")
			.append(deviceString)
			.toString();
	}


}
