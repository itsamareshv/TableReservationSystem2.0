package com.reservation;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;




public abstract class TableReservation implements ReservationSystem,Runnable, Serializable {
	static ObjectOutputStream oos = null;
	static ObjectInputStream ois = null;

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		Scanner scan = new Scanner(System.in);

		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out.println(
					"________________________________________________________________________________________________________________________________________");
			System.out.println("                       MENU                                            ");
			System.out.println(
					"_________________________________________________________________________________________________________________________________________");

			System.out.println("                   TABLE RESERVATION SYSTEM                          ");
			System.out.println(
					"_________________________________________________________________________________________________________________________________________");
			System.out.println("            1.Add Reservation");
			System.out.println("            2.View Reservation List");
			System.out.println("            3.View By Reservation Id");
			System.out.println("            4.Delete Reservation by Id");
			System.out.println("            5.Confirm By Reservation Id");
			System.out.println("            6.Cancel By Reservation Id");
			System.out.println("            7.Sort Reservation");
			System.out.println("            8.Generate Report");
			System.out.println("            9.Exit");
			System.out.println(
					"____________________________________________________________________________________________________________________________________________");

			try {
			int val = sc.nextInt();
			 if (val > 9 && val < 0) {
			 System.out.println("Enter Valid Number");
			 }

			switch (val) {
			case 1:
				AllMethods.addReservation();
				break;
			case 2:
				AllMethods.readReservationFile();
				break;
			case 3:
				AllMethods.viewOrderByID();
				break;
			case 4:
				AllMethods.deleteOrderByID();
				break;

			case 5:
				
				AllMethods.confirmByRiD();
				break;
			case 6:
				
				AllMethods.cancelByRiD();
				break;
			case 7:
				System.out.println("Sort Reservation");
				System.out.println("____________________________________________________________________");

				System.out.println("              Choose Sort Reservation Property                   ");
				System.out.println("____________________________________________________________________");
				System.out.println("              1:->Reservation Id                     ");
				System.out.println("              2:->Reservation Description             ");
				System.out.println("              3:->Reservation Date                    ");
				System.out.println("              4:->Total Amount                     ");
				System.out.println("____________________________________________________________________");
				System.out.println("                 Enter Your Option                          ");
				System.out.println("____________________________________________________________________");
				int key = sc.nextInt();
				switch (key) {
				case 1:
					System.out.println("____________________________________________________________________");
					System.out.println("             ->Welcome To Sort By Reservation ID");
					System.out.println("____________________________________________________________________");
					System.out.println("                  Choose Sort Order                                   ");
					System.out.println("                   1: Ascending            ");
					System.out.println("                   2:Descending           ");
					System.out.println("____________________________________________________________________");

					AllMethods.sortReserveationById();
					System.out.println("____________________________________________________________________");
					break;

				case 2:
					System.out.println("____________________________________________________________________");
					System.out.println("           -> Welcome To Sort By Reservation Description");
					System.out.println("____________________________________________________________________");
					System.out.println("               Choose Sort Order                                   ");
					System.out.println("                1: Ascending            ");
					System.out.println("                2:Descending           ");
					System.out.println("____________________________________________________________________");
					System.out.println("                Enter Your Option        ");
					System.out.println("____________________________________________________________________");

					AllMethods.sortReserveByDiscription();
					System.out.println("____________________________________________________________________");
					break;

				case 3:
					System.out.println("____________________________________________________________________");
					System.out.println("              -> Welcome To Sort By Reservation Date");
					System.out.println("____________________________________________________________________");
					System.out.println("                Choose Sort Order                                   ");
					System.out.println("                1: Ascending            ");
					System.out.println("                2:Descending           ");
					System.out.println("____________________________________________________________________");
					System.out.println("                Enter Your Option        ");
					System.out.println("____________________________________________________________________");
					AllMethods.sortReserveByDate();
					System.out.println("____________________________________________________________________");
					break;

				case 4:
					System.out.println("____________________________________________________________________");
					System.out.println("               -> Welcome To Sort By Reservation Total Amount");
					System.out.println("____________________________________________________________________");
					System.out.println(
							"                        Choose Sort Order                                         ");
					System.out.println("                         1: Ascending            ");
					System.out.println("                         2:Descending           ");
					System.out.println("                         Enter Your Option        ");
					System.out.println("____________________________________________________________________");

					AllMethods.sortReserveByTotalAmount();
					System.out.println("____________________________________________________________________");
					break;

				default:
					break;
				}
				break;
			case 8:
				ReportGenaration thread = new ReportGenaration();
				thread.run();
				break;

			case 9:
				System.out.println("___________________________________________________________________________________________________________________________________________");
				System.out.println("                                                      THANK YOU");
				System.out.println("___________________________________________________________________________________________________________________________________________");
				System.exit(0);
				
				break;
			}

		}
			catch (Exception e) {
				System.out.println("Choose correct Option");
			}
			
	}
	}
	
	
	
		
	
	
	
}
