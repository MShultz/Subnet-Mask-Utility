package shultz.logic.util;

import java.util.Scanner;

public class NetworkValidator {
	private SubnetMask subnetMask;
	private IPAddress firstIP, secondIP;
	private Scanner scan = new Scanner(System.in);
	
	public boolean isContainedInNetwork() {
		String[] subnetParts = subnetMask.getSubnetParts();
		String[] ipOne = firstIP.getAddressParts();
		String[] ipTwo = secondIP.getAddressParts();
		for (int i = 0; i < subnetParts.length; ++i) {
			for (int j = 0; j < 8; ++j) {
				if (subnetParts[i].charAt(j) == '1') {
					if (ipOne[i].charAt(j) != ipTwo[i].charAt(j))
						return false;
				}
			}
		}
		return true;
	}

	public void validate() {
		

		while (true) {
			setFirstIP();
			setSubnetMask();
			setSecondIP();
			System.out.println("Your second IP Address" + (isContainedInNetwork() ? " is a part of the network." : " is not a part of the network.\n"));
			resetValues();
		}
	}
	
	private void setFirstIP(){
		do {
			System.out.println((firstIP == null? "Welcome!" : "That is an invalid address, please try again."));
			System.out.print("Please input your first IP Address: ");
			firstIP = new IPAddress(scan.nextLine());
		} while (firstIP == null || !firstIP.isValidAddress());
	}
	
	private void setSubnetMask(){
		do{
			if(subnetMask != null)
			System.out.println("That is an invalid subnet, please try again.");
			System.out.print("Please input your subnet mask: ");
			subnetMask = new SubnetMask(scan.nextLine());
		}while(subnetMask == null || !subnetMask.isValidSubnet());
	}
	
	private void setSecondIP(){
		do {
			if(secondIP != null)
			System.out.println("That is an invalid address, please try again.");
			System.out.print("Please input your second IP Address: ");
			secondIP = new IPAddress(scan.nextLine());
		} while (secondIP == null || !secondIP.isValidAddress());
	}
	
	private void resetValues(){
		firstIP = secondIP = null;
		subnetMask = null;
	}
}
