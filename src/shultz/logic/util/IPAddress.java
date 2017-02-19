package shultz.logic.util;

import java.text.DecimalFormat;

public class IPAddress {
	private String address;
	private boolean isValidAddress;
	private String[] addressParts;

	public IPAddress(String address) {
		this.setAddress(address);
		this.setValidAddress();
	}

	public boolean isValidAddress() {
		return isValidAddress;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	private void setValidAddress() {
		isValidAddress = (!address.isEmpty() && !address.endsWith(".") && digitsAreCorrect());
	}

	private boolean digitsAreCorrect() {
		try {		
			this.addressParts = address.split("\\.");
			if (addressParts.length != 4)
				return false;
			for (String part : addressParts) {
				int addressNum = Integer.parseInt(part);
				if (addressNum > 255 || addressNum < 0)
					return false;
			}
			setAddressParts();
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public String[] getAddressParts() {
		return addressParts;
	}

	public void setAddressParts() {

		DecimalFormat format = new DecimalFormat("00000000");
		for (int i = 0; i < addressParts.length; i++) {
			Integer inBinary = Integer.parseInt(Integer.toBinaryString(Integer.parseInt(addressParts[i])));
			addressParts[i] = format.format(inBinary).toString();
		}
	}

}
