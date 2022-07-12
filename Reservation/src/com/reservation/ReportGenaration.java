package com.reservation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ReportGenaration implements  ReservationSystem , Runnable{
	

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
					System.out.println("");
					System.out.println("Please Wait Report By All Is Currently Running............");
					Thread.sleep(5000);
					try {
						AllMethods.generateReportAllByThread();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				//t.run();
			try {
				AllMethods.clearThreadFile();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
				

				try {
					
					System.out.println("");
				System.out.println("Please Wait Report By Status Booked Is Currently Running............");
				System.out.println("_______________________________________________________________________________________________________________________________________________________________________");
					try {
						Thread.sleep(5000);
						String opt1 = "Booked";
						AllMethods.byStatus(opt1);
						System.out.println("____________________________________________________________________");
						System.out.println("Report Generated SucessFully");
						System.out.println("____________________________________________________________________");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					AllMethods.clearThreadFile();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;

			case 2:
				try {
					
				System.out.println("");
				System.out.println("Please Wait Report By Status Canceled Is Currently Running............");
				System.out.println("_______________________________________________________________________________________________________________________________________________________________________");
				try {
					Thread.sleep(1000);
					String opt2 = "Canceled";
				
					AllMethods.byStatus(opt2);
					System.out.println("____________________________________________________________________");
					System.out.println("Report Generated SucessFully");
					System.out.println("____________________________________________________________________");
					//AllMethods.clearThreadFile();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
			case 3:
				
				try {
					
					System.out.println("");
					System.out.println("Please Wait Report By Status Confirmed Is Currently Running............");
					System.out.println("_______________________________________________________________________________________________________________________________________________________________________");
					try {
						Thread.sleep(6000);
						String opt3 = "Confirmed";
					
						AllMethods.byStatus(opt3);
						System.out.println("____________________________________________________________________");
						System.out.println("Report Generated SucessFully");
						System.out.println("____________________________________________________________________");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					} catch (IOException e) {
						e.printStackTrace();
					}
				
				break;
			default:
				break;
			}
		}
	
	}

	
	public static void main(String[] args) {
		ReportGenaration threadReport=new ReportGenaration();
		
	
		
		threadReport.run();
		
	}
}
