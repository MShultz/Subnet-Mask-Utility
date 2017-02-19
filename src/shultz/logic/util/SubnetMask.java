package shultz.logic.util;

import java.text.DecimalFormat;

public class SubnetMask {
	private String subnet;
	private String[] subnetParts;
	private boolean isValidSubnet;

	public SubnetMask(String subnet) {
		this.setSubnet(subnet);
		this.splitSubnet();
		this.setValidSubnet();
	}

	public String getSubnet() {
		return subnet;
	}

	public void setSubnet(String subnet) {
		this.subnet = subnet;
	}

	public String[] getSubnetParts() {
		return subnetParts;
	}

	public void setSubnetParts() {
		DecimalFormat format = new DecimalFormat("00000000");
		for (int i = 0; i < subnetParts.length; i++) {
			Integer inBinary = Integer.parseInt(Integer.toBinaryString(Integer.parseInt(subnetParts[i])));
			subnetParts[i] = format.format(inBinary).toString();
		}
	}

	public boolean isValidSubnet() {
		return isValidSubnet;
	}

	public void setValidSubnet() {
		isValidSubnet = (!subnet.isEmpty() && !subnet.endsWith(".") && subnetIsProper());
	}

	private boolean subnetIsProper() {
		try {
			if (subnetParts.length != 4)
				return false;
			for (String part : subnetParts) {
				int addressNum = Integer.parseInt(part);
				if (addressNum > 255 || addressNum < 0)
					return false;
			}
			setSubnetParts();
			if (!hasCorrectBittage())
				return false;
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private boolean hasCorrectBittage() {
		boolean zerosHaveBegun = false;
		for (String part : subnetParts) {
			for (int i = 0; i < 8; ++i) {
				if(part.charAt(i) == '1' && zerosHaveBegun)
					return false;
				else if(part.charAt(i) == '0' && !zerosHaveBegun)
						zerosHaveBegun = true;
			}
		}
		return true;
	}

	private void splitSubnet() {
		subnetParts = this.getSubnet().split("\\.");

	}

}
