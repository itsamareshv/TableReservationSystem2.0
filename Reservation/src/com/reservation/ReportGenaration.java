package com.reservation;

import java.io.IOException;
import java.util.Scanner;

public class ReportGenaration implements Runnable{

	@Override
	public void run() {
		Scanner scan = new Scanner(System.in);
		System.out.println("____________________________________________________________________");
		System.out.println("******** Choose Report Type*********");
		System.out.println("____________________________________________________________________");
		System.out.println("1.Export All");
		System.out.println("2.Export By Status");
		System.out.println("Enter Your Option");
		System.out.println("____________________________________________________________________");
		int opt = scan.nextInt();
		System.out.println("____________________________________________________________________");
		switch (opt) {
		case 1:
			
			try {
				AllMethods.generateReportAll();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case 2:
			System.out.println("____________________________________________________________________");
			System.out.println("******** Choose Status*********");
			System.out.println("____________________________________________________________________");
			System.out.println("1.Booked");
			System.out.println("2.Cancelled");
			System.out.println("3.Confirmed");
			System.out.println("____________________________________________________________________");
			int key1 = scan.nextInt();
			switch (key1) {
			case 1:
				String opt1 = "Booked";

				try {
					AllMethods.byStatus(opt1);
					System.out.println("____________________________________________________________________");
					System.out.println("Report Generated SucessFully");
					System.out.println("____________________________________________________________________");
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;

			case 2:
				String opt2 = "Canceled";
				try {
					AllMethods.byStatus(opt2);
					System.out.println("____________________________________________________________________");
					System.out.println("Report Generated SucessFully");
					System.out.println("____________________________________________________________________");
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case 3:
				String opt3 = "Confirmed";
				try {
					AllMethods.byStatus(opt3);
					System.out.println("____________________________________________________________________");
					System.out.println("Report Generated SucessFully");
					System.out.println("____________________________________________________________________");
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			default:
				break;
			}
		}
	
	}

}
