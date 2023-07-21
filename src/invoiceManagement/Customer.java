package invoiceManagement;

import java.util.Scanner;

public class Customer {
	String cust_name, mobileNo, address;
	int[] invoice = new int[10];
	int length;
	Scanner s = new Scanner(System.in);

	public void addCustomer() {
		System.out.println("Enter customer name:");
		cust_name = s.nextLine();
		System.out.println("Enter address:");
		address = s.nextLine();
		System.out.println("Enter Mobile no:");
		mobileNo = s.nextLine();
	}

	public void addInvoiceId(int a) {
		invoice[length] = a;
		length++;
	}

	public int[] getArray() {
		return invoice;
	}
}
