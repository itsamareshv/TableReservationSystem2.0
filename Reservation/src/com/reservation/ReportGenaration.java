package com.reservation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ReportGenaration implements ReservationSystem, Runnable {

	@Override
	public void run() {
		Thread tread = Thread.currentThread();
		String name = tread.getName();
		if (name.equals("Booked") == true) {
			String str = "Booked";
			try {
				AllMethods.byStatus(str);

				System.out.println("Generated Sucessfully By Status Booked");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (name.equals("Canceled") == true) {
			String str = "Canceled";
			try {
				AllMethods.byStatus(str);
				System.out.println("Generated Sucessfully By Status Canceled");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (name.equals("Confirmed") == true) {
			String str = "Confirmed";
			try {
				AllMethods.byStatus(str);
				System.out.println("Generated Sucessfully By Status Confirmed");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				AllMethods.generateReportAll();
				System.out.println("Generated Sucessfully by ALL");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("Generated Sucessfully Thank You");
		System.out.println(
				"______________________________________________________________________________________________________________");
	}

	public static void main(String[] args) throws InterruptedException {

		
		ReportGenaration threadReport = new ReportGenaration();

		Thread t1 = new Thread(threadReport);
		Thread t2 = new Thread(threadReport);
		Thread t3 = new Thread(threadReport);
		t1.setName("Booked");
		t2.setName("Canceled");
		t3.setName("Confirmed");
		t3.setName("All");
		System.out.println("Generating Report By Status Booked Please wait ..............");
		t1.sleep(5000);
		t1.start();

		t2.sleep(10000);
		System.out.println("Generating Report By Status Canceled Please wait ..............");
		t2.start();

		t3.sleep(15000);
		System.out.println("Generating Report By Status Confirmed Please wait ..............");
		t3.start();
	}

}
