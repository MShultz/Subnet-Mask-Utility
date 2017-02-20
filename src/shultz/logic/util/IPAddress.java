package shultz.logic.util;

public class IPAddress {
	private String address;
	private boolean isValidAddress;
	private String[] addressParts;

	public IPAddress(String address) {
		this.setAddress(address);
		this.setAddressParts();
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

			if (addressParts.length != 4)
				return false;
			for (String part : addressParts) {
				int addressNum = Integer.parseInt(part);
				if (addressNum > 255 || addressNum < 0)
					return false;
			}
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public String[] getAddressParts() {
		return addressParts;
	}

	public void setAddressParts() {
		this.addressParts = address.split("\\.");
	}

}
