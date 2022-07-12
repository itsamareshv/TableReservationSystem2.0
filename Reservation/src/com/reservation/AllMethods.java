package com.reservation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;

interface ReservationSystem {
	public class AllMethods {
		private static final String dataFilePath = "C:/Users/Amaresh/Desktop/data.txt";
		private static final String cleardataFilePath ="C://Users//Amaresh//Documents//Reports//20220711213119Reservation _Report_.txt" ;
		private static String generatePAth = null;

		static DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy:HH:mm:ssa");
		static DateFormat formatter1 = new SimpleDateFormat("yyyy");
		private static LocalDateTime Date = LocalDateTime.now();
		static Date currentDate = new Date();
		private static ArrayList<Reservation> data = new ArrayList();
		static Scanner scan = new Scanner(System.in);
		static Scanner scanString = new Scanner(System.in);
		static Scanner scanString1 = new Scanner(System.in);
		static DecimalFormat decimal = new DecimalFormat("#.##");
		static String adult, children;
		static String customerName, userInput, eightDigitId, reservationDescription;
		static File file = new File(dataFilePath);

		static Iterator<Reservation> itr = data.iterator();

		public static void addReservation() throws IOException, ClassNotFoundException {

			int flage = 1;
			dolabel: while (flage == 1) {

				try {
					// ArrayList<Reservation> data = new ArrayList();
					scan.nextLine();
					System.out.println(
							"----------------------------------------------------------------------------------------------------------------");
					System.out.println("Working Hour = " + currentDate.getHours());
					if (currentDate.getHours() < 22 && currentDate.getHours() >= 0) {
						System.out.println(
								"----------------------------------------------------------------------------------------------------------------");
						System.out.println("->>>>>>>>>>>>>>>>>>>>>>WE ARE BACK -> " + " Happy To Serve You");
						System.out.println(
								"----------------------------------------------------------------------------------------------------------------");
					} else {
						System.out.println("We are Sorry We Unable to serve you");
						System.exit(0);
					}
					System.out.println(
							"----------------------------------------------------------------------------------------------------------------");
					System.out.println("Please Enter Reservation Details");
					System.out.println(
							"----------------------------------------------------------------------------------------------------------------");

					int getYear = Date.getYear();

					System.out.println(getYear);
					int flag = 1;
					loop1: while (flag == 1) {
						System.out.println("-> Enter Reservation ID");
						userInput = scan.next();

						if (userInput.length() > 4) {
							System.out.println("Please Enter Four Digits");
							continue loop1;
						} else if (!userInput.matches("[0-9]+")) {
							System.out.println("Enter A Numeric Digit");
							continue loop1;
						} else {
							String currentYear = Integer.toString(getYear);
							eightDigitId = currentYear.concat(userInput);
							System.out.println("ID Length = " + eightDigitId.length());
							// flag =0;
						}
						ArrayList<Reservation> data = readWithoutPrinting();
						for (Reservation reservationList : data) {

							if (reservationList.getReservationId().equals(eightDigitId)) {
								// System.out.println(r.getReservationId());
								System.out.println(
										"-------------------------------------------------------------------------------------------------------------------------------------");
								System.out.println("Duplicate Order Id. Please enter unique order id");
								continue loop1;
							}

						}
						flag = 0;
					}

					String reservationId = "";
					if (eightDigitId.length() == 8) {
						reservationId = eightDigitId;
					} else {
						System.out.println("ID is to long:please enter 4 digits only");
						// addReservation();
						continue dolabel;
					}

					outer: while (true) {
						scan.nextLine();
						System.out.println("-> Enter Your Name");
						customerName = scan.nextLine();

						if (!customerName.matches("[a-zA-Z ]+")) {
							System.out.println("Invalid Name");
							continue outer;
						} else {
							break;
						}
					}

					notok: while (true) {
						scanString1.nextLine();
						System.out.println("-> Enter Reservation_Description");

						reservationDescription = scanString1.nextLine();
						if (!reservationDescription.matches("[a-zA-Z0-9 ]+")) {

							System.out.println("Special Characaters are Not allowed");
							continue notok;
						} else

							break;
					}

					LocalDateTime reservationDate = LocalDateTime.now();

					notok1: while (true) {
						System.out.println("-> Enter No of Adults ");

						adult = scan.next();
						if (!adult.matches("[{2}0-9]+")) {

							System.out.println("Please Enter Numeric Digit");
							continue notok1;
						} else

							break;
					}

					notok2: while (true) {
						System.out.println("-> Enter No of Childrens");
						children = scan.next();

						if (!children.matches("[{2}0-9]+")) {

							System.out.println("Please Enter Numeric Digit");
							continue notok2;
						} else

							break;
					}

					double subTotalAmount = (Integer.parseInt(adult) * 500) + (Integer.parseInt(children) * 300);
					double discountAmount;
					if (DayOfWeek.WEDNESDAY != null) {
						discountAmount = .2;
					} else {
						discountAmount = .25;
					}

					double taxAmount = (((subTotalAmount - discountAmount) / 100) * 5);
					System.out.println("Toatl Tax = " + "Rs	" + taxAmount);

					double totalAmount = ((subTotalAmount - discountAmount) + taxAmount);

					String status = "Booked";

					System.out.println(
							"-------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("           Reservation Created Successfully");
					System.out.println(
							"---------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("-> Reservation ID = " + reservationId);
					System.out.println("-> Reservation Sub Total Amount = " + subTotalAmount);
					System.out.println("-> Reservation Sub Discount Amount = " + discountAmount);
					System.out.println("-> Reservation Sub Tax Amount = " + taxAmount);
					System.out.println("-> Reservation Total Amount = " + totalAmount);
					System.out.println("");
					System.out.println(
							"---------------------------------------------------------------------------------------------------------------------------------------");

					Reservation tableReservation = new Reservation(reservationId, customerName, reservationDescription,
							reservationDate, adult, children, subTotalAmount, discountAmount, taxAmount, totalAmount,
							status);
					;
					data.add(tableReservation);
					;
					System.out.println(tableReservation);

					System.out.println(
							"-------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("            Order Added Successfully         ");
					writeReservationData(data);
					data.clear();

				} catch (Exception e) {
					System.out.println("We love's you bookings closed please come back tommorow");
				}
				System.out.println(
						"---------------------------------------------------------------------------------------------------------------------------------");
				System.out.println("                   Do you want to continue Yes/No                      ");

				String qes = scan.next();
				if (qes.equalsIgnoreCase("yes")) {

					scan.nextLine();
					continue dolabel;
				} else if (qes.equalsIgnoreCase("no")) {
					return;
				}
			}
		}

		static public ArrayList<Reservation> readReservationFile() {
			ArrayList<Reservation> data = new ArrayList<Reservation>();
			try {

				File file = new File("C:\\Users\\Amaresh\\Desktop\\data.txt"); // file to be read
				Scanner readfile = new Scanner(file);
				StringTokenizer token = null;
				String reservationId = "";
				String customerName = "";
				String reservationDescription = "";
				LocalDateTime reservationDate;
				String adult = "";
				String children = "";
				String status = "";
				double subTotalAmount = 0.0;
				double discountAmount = 0.0;
				double taxAmount = 0.0;
				double totalAmount = 0.0;

				System.out.println("Reservation_Id|" + " Name     |" + "   Discription      |"
						+ " Reservation_Date           |" + "Adult|" + " Children|" + " Sub_T_Amnt   |"
						+ " Tax_Amnt   |" + "Total_Ant   2|" + "    Status ");
				System.out.println(
						"______________________________________________________________________________________________________________________________________________________");

				while (readfile.hasNextLine()) {
					token = new StringTokenizer(readfile.nextLine(), "\t");
					reservationId = token.nextToken();
					customerName = token.nextToken();
					reservationDescription = token.nextToken();
					reservationDate = LocalDateTime.parse(token.nextToken(), DateTimeFormatter.ISO_DATE_TIME);
					adult = token.nextToken();
					children = token.nextToken();
					subTotalAmount = Double.parseDouble(token.nextToken());
					discountAmount = Double.parseDouble(token.nextToken());
					taxAmount = Double.parseDouble(token.nextToken());
					totalAmount = Double.parseDouble(token.nextToken());
					status = token.nextToken();
					Reservation tableReservation = new Reservation(reservationId, customerName, reservationDescription,
							reservationDate, adult, children, subTotalAmount, discountAmount, taxAmount, totalAmount,
							status);

					data.add(tableReservation);
					System.out.println(tableReservation);
					System.out.println(
							"--------------------------------------------------------------------------------------------------------------------------------------------------------");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return data;

		}

		public static void writeReservationData(ArrayList<Reservation> data) throws IOException {
			FileWriter writer = new FileWriter(dataFilePath, true);
			try {
				for (int i = 0; i < data.size(); i++) {
					writer.write(data.get(i).toString() + "\n");
				}
				writer.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public static void viewOrderByID() {
			scan.nextLine();
			ArrayList<Reservation> data = readWithoutPrinting();
			int flag = 1;
			outer: while (flag == 1) {
				System.out.println("Enter ID to Search");
				String id = scan.nextLine();
				ArrayList tempId = new ArrayList();
				for (Reservation reservationList : data) {
					tempId.add(reservationList.getReservationId());
				}
				if (!(tempId.contains(id))) {
					System.out.println("Not Found");
					continue outer;
				}
				for (Reservation reservationList : data) {
					if (reservationList.getReservationId().equals((id))) {
						System.out.println("              Found                   ");
						System.out.println("Reservation ID ->" + reservationList.getReservationId());
						System.out.println("Customer Name  ->" + reservationList.getCustomerName());
						System.out.println("Reservation Date ->" + reservationList.getReservationDate());
						System.out.println("Reservation Discription ->" + reservationList.getReservationDescription());
						System.out.println("No of Adults  ->" + reservationList.getAdult());
						System.out.println("No of Childrens  ->" + reservationList.getChildren());
						System.out.println("Sub Total Amount  ->" + reservationList.getSubTotalAmount());
						System.out.println("Discount Amount ->" + reservationList.getDiscountAmount());
						System.out.println("Tax Amount ->" + reservationList.getTaxAmount());
						System.out.println("Total Amount ->" + reservationList.getTotalAmount());
						System.out.println("Reservation Status ->" + reservationList.getStatus());
						System.out.println(
								"---------------------------------------------------------------------------------------------------------------------------------");
						flag = 0;
					}
				}

			}
			System.out.println("Do you Want to search more Y/N");
			String qes = scan.next();
			if (qes.equalsIgnoreCase("y")) {
				viewOrderByID();
			} else if (qes.equalsIgnoreCase("n")) {
				return;
			}
		}

		public static void deleteOrderByID() throws IOException {
			int flag = 1;
			outer: while (flag == 1) {
				ArrayList<Reservation> data = readWithoutPrinting();

				scan.nextLine();

				System.out.println("Enter ID to Delete");

				String ID = scan.nextLine();

				ArrayList tempId = new ArrayList();

				for (Reservation reservationList : data) {
					tempId.add(reservationList.getReservationId());
				}

				if (!(tempId.contains(ID))) {
					System.out.println("Not Found");
					continue outer;
				}

				for (Reservation reservationList : data) {
					System.out.println(reservationList);
					if (reservationList.getReservationId().equals((ID))) {

						data.remove(reservationList);
						System.out.println(
								"---------------------------------------------------------------------------------------------------------------------------------");

						System.out.println("              Found                   ");
						System.out.println(
								"---------------------------------------------------------------------------------------------------------------------------------");
						System.out.println("Reservation ID ->" + reservationList.getReservationId());
						System.out.println("Customer Name  ->" + reservationList.getCustomerName());
						System.out.println("Reservation Date ->" + reservationList.getReservationDate());
						System.out.println("Reservation Discription ->" + reservationList.getReservationDescription());
						System.out.println("No of Adults  ->" + reservationList.getAdult());
						System.out.println("No of Childrens  ->" + reservationList.getChildren());
						System.out.println("Sub Total Amount  ->" + reservationList.getSubTotalAmount());
						System.out.println("Discount Amount ->" + reservationList.getDiscountAmount());
						System.out.println("Tax Amount ->" + reservationList.getTaxAmount());
						System.out.println("Total Amount ->" + reservationList.getTotalAmount());
						System.out.println("Reservation Status ->" + reservationList.getStatus());

						System.out.println("      Deleted Sucessfully            ");
						System.out.println(
								"---------------------------------------------------------------------------------------------------------------------------------");
						break;
					}

				}
				clearFile();

				writeReservationData(data);
				System.out.println(
						"---------------------------------------------------------------------------------------------------------------------------------");
				tempId.clear();
				flag = 0;
			}

			System.out.println("Do you Want to Delete more Y/N");
			String qes = scan.next();
			if (qes.equalsIgnoreCase("Y")) {

				deleteOrderByID();

			} else if (qes.equalsIgnoreCase("N")) {
				return;
			}

		}

		public static void confirmByRiD() throws IOException {
			ArrayList<Reservation> data = readWithoutPrinting();
			// boolean found = false;
			int flag = 1;
			outer: while (flag == 1) {
				scan.nextLine();
				System.out.println("Enter ID to Confirm");
				String confirmID = scan.nextLine();
				ArrayList tempId = new ArrayList();
				for (Reservation reservationList : data) {
					tempId.add(reservationList.getReservationId());
				}
				if (!(tempId.contains(confirmID))) {
					System.out.println("Not Found");
					continue outer;
				}
				for (Reservation reservationList : data) {
					// System.out.println(l);
					if (reservationList.getStatus().contentEquals("Confirmed")
							&& reservationList.getReservationId().equals(confirmID)) {
						System.out.println("Already Confirmed");
						break;
					} else if (reservationList.getStatus().contentEquals("Canceled")
							&& reservationList.getReservationId().equals(confirmID)) {
						System.out.println("Already Cancelled Cant be mark as confirmed");
						break;
					}

					if (reservationList.getReservationId().equals((confirmID))) {

						reservationList.setStatus("Confirmed");
						System.out.println(
								"---------------------------------------------------------------------------------------------------------------------------------");

						System.out.println("              Found                   ");
						System.out.println(
								"---------------------------------------------------------------------------------------------------------------------------------");
						System.out.println("Reservation ID ->" + reservationList.getReservationId());
						System.out.println("Customer Name  ->" + reservationList.getCustomerName());
						System.out.println("Reservation Date ->" + reservationList.getReservationDate());
						System.out.println("Reservation Discription ->" + reservationList.getReservationDescription());
						System.out.println("No of Adults  ->" + reservationList.getAdult());
						System.out.println("No of Childrens  ->" + reservationList.getChildren());
						System.out.println("Sub Total Amount  ->" + reservationList.getSubTotalAmount());
						System.out.println("Discount Amount ->" + reservationList.getDiscountAmount());
						System.out.println("Tax Amount ->" + reservationList.getTaxAmount());
						System.out.println("Total Amount ->" + reservationList.getTotalAmount());
						System.out.println("Reservation Status ->" + reservationList.getStatus());
						System.out.println(
								"---------------------------------------------------------------------------------------------------------------------------------");
						System.out.println("      Confirmed Sucessfully            ");
						System.out.println(
								"---------------------------------------------------------------------------------------------------------------------------------");
						break;
					}

				}
				clearFile();

				writeReservationData(data);
				System.out.println(
						"---------------------------------------------------------------------------------------------------------------------------------");

				tempId.clear();
				flag = 0;
			}
			System.out.println("Do you Want to Confirm more Y/N");
			String qes = scan.next();
			if (qes.equalsIgnoreCase("y")) {

				confirmByRiD();

			} else if (qes.equalsIgnoreCase("n")) {
				return;
			}

		}

		public static void cancelByRiD() throws IOException {

			ArrayList<Reservation> data = readWithoutPrinting();
			// System.out.println(data);
			int flag = 1;
			outer: while (flag == 1) {

				System.out.println("Enter ID to Cancel");
				String cancelID = scan.nextLine();

				ArrayList tempIdList = new ArrayList();

				for (Reservation reservationList : data) {
					tempIdList.add(reservationList.getReservationId());
				}

				if (!(tempIdList.contains(cancelID))) {
					System.out.println("Not Found");
					continue outer;
				}

				for (Reservation reservationList : data) {
					// System.out.println(l);
					if (reservationList.getStatus().contentEquals("Canceled")
							&& (reservationList.getReservationId().contentEquals(cancelID))) {
						System.out.println("Already Canceled Cant be Changed");
						continue outer;
					} else

					if (reservationList.getReservationId().equals((cancelID))) {
						reservationList.setStatus("Canceled");
						System.out.println(
								"---------------------------------------------------------------------------------------------------------------------------------");
						System.out.println("              Found                   ");
						System.out.println(
								"---------------------------------------------------------------------------------------------------------------------------------");
						System.out.println("Reservation ID ->" + reservationList.getReservationId());
						System.out.println("Customer Name  ->" + reservationList.getCustomerName());
						System.out.println("Reservation Date ->" + reservationList.getReservationDate());
						System.out.println("Reservation Discription ->" + reservationList.getReservationDescription());
						System.out.println("No of Adults  ->" + reservationList.getAdult());
						System.out.println("No of Childrens  ->" + reservationList.getChildren());
						System.out.println("Sub Total Amount  ->" + reservationList.getSubTotalAmount());
						System.out.println("Discount Amount ->" + reservationList.getDiscountAmount());
						System.out.println("Tax Amount ->" + reservationList.getTaxAmount());
						System.out.println("Total Amount ->" + reservationList.getTotalAmount());
						System.out.println("Reservation Status ->" + reservationList.getStatus());
						System.out.println(
								"---------------------------------------------------------------------------------------------------------------------------------");
						System.out.println("      Canceled Sucessfully            ");
						System.out.println(
								"---------------------------------------------------------------------------------------------------------------------------------");
				System.out.println("Reservation  ID = "+reservationList.getReservationId()+" cancelled.");
				System.out.println("Amount  = "+reservationList.getTotalAmount()+"  will refund within 24 hours");
						break;
					}

				}
				clearFile();
				writeReservationData(data);

				// ar1.clear();
				flag = 0;
			}
			System.out.println("Do you Want to Cancel more Y/N");
			String qes = scan.next();
			if (qes.equalsIgnoreCase("y")) {
				scan.nextLine();
				cancelByRiD();

			} else if (qes.equalsIgnoreCase("n")) {
				return;
			}

		}

		public static void clearFile() throws FileNotFoundException {
			PrintWriter writeempty = new PrintWriter(dataFilePath);
			writeempty.print("");
			writeempty.close();
		}

		public static void clearThreadFile() throws FileNotFoundException {
			PrintWriter writeempty = new PrintWriter(cleardataFilePath);
			writeempty.print("");
			writeempty.close();
		}
		public static void sortReserveationById() throws IOException {

			System.out.println("Enter Option");
			int idkey = scan.nextInt();
			// @SuppressWarnings("unused")
			class ascendingID implements Comparator<Reservation> {
				@Override
				public int compare(Reservation a, Reservation b) {
					return a.getReservationId().compareTo(b.getReservationId());
				}
			}
			class descendingID implements Comparator<Reservation> {
				@Override
				public int compare(Reservation a, Reservation b) {
					return b.getReservationId().compareTo(a.getReservationId());
				}
			}

			switch (idkey) {
			case 1:

				ArrayList<Reservation> data = AllMethods.readReservationFile();
				System.out.println("                                 After Sorting                          ");

				System.out.println(
						"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				Collections.sort(data, new ascendingID());

				for (int i = 0; i < data.size(); i++) {
					System.out.println(data.get(i));
				}
				System.out.println(
						"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println(
						"                            RESERVATION ID -> Sorted Sucessfully -> In ASCENDING order");

				break;
			case 2:

				ArrayList<Reservation> dataD = AllMethods.readReservationFile();
				System.out.println("                                 After Sorting                          ");
				System.out.println(
						"---------------------------------------------------------------------------------------------------------------------------------");

				Collections.sort(dataD, new descendingID());
				System.out.println(dataD.size());
				for (int i = 0; i < dataD.size(); i++) {
					System.out.println(dataD.get(i));
				}
				System.out.println(
						"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println(
						"                            RESERVATION ID -> Sorted Sucessfully -> In DESCENDING order");

				break;
			default:
				break;
			}
		}

		public static void sortReserveByDiscription() {
			System.out.println("Enter Option");
			int idkey = scan.nextInt();
			// @SuppressWarnings("unused")
			class ascendingA implements Comparator<Reservation> {
				@Override
				public int compare(Reservation a, Reservation b) {
					return a.getReservationDescription().compareTo(b.getReservationDescription());
				}
			}
			class descendingD implements Comparator<Reservation> {
				@Override
				public int compare(Reservation a, Reservation b) {
					return b.getReservationDescription().compareTo(a.getReservationDescription());
				}
			}

			switch (idkey) {
			case 1:

				ArrayList<Reservation> data = AllMethods.readReservationFile();
				System.out.println("                                 After Sorting                          ");

				System.out.println(
						"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

				Collections.sort(data, new ascendingA());
				for (int i = 0; i < data.size(); i++) {
					System.out.println(data.get(i) + "\t");
				}
				System.out.println(
						"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println(
						"                            RESERVATION DISCRIPTION -> Sorted Sucessfully -> In ASCENDING order");
				break;
			case 2:

				ArrayList<Reservation> dataD = AllMethods.readReservationFile();
				System.out.println("                                 After Sorting                          ");
				System.out.println(
						"---------------------------------------------------------------------------------------------------------------------------------");

				Collections.sort(dataD, new descendingD());
				System.out.println(dataD.size());
				for (int i = 0; i < dataD.size(); i++) {
					System.out.println(dataD.get(i) + "\t");
				}
				System.out.println(
						"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println(
						"                            RESERVATION Discription -> Sorted Sucessfully -> In DESCENDING order");

				break;
			default:
				break;
			}

		}

		public static void sortReserveByDate() {
			System.out.println("Enter Option");
			int idkey = scan.nextInt();
			// @SuppressWarnings("unused")
			class ascendingA implements Comparator<Reservation> {
				@Override
				public int compare(Reservation a, Reservation b) {
					return a.getReservationDate().compareTo(b.getReservationDate());
				}
			}
			class descendingD implements Comparator<Reservation> {
				@Override
				public int compare(Reservation a, Reservation b) {
					return b.getReservationDate().compareTo(a.getReservationDate());
				}
			}

			switch (idkey) {
			case 1:

				ArrayList<Reservation> data = AllMethods.readReservationFile();
				System.out.println("                                 After Sorting                          ");

				System.out.println(
						"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				Collections.sort(data, new ascendingA());
				for (int i = 0; i < data.size(); i++) {
					System.out.println(data.get(i) + "\t");
				}
				System.out.println(
						"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println(
						"                            RESERVATION DATE -> Sorted Sucessfully -> In ASCENDING order");
				break;
			case 2:

				ArrayList<Reservation> dataD = AllMethods.readReservationFile();
				System.out.println("                                 After Sorting                          ");
				System.out.println(
						"---------------------------------------------------------------------------------------------------------------------------------");

				Collections.sort(dataD, new descendingD());
				System.out.println(dataD.size());
				for (int i = 0; i < dataD.size(); i++) {
					System.out.println(dataD.get(i) + "\t");
				}
				System.out.println(
						"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println(
						"                            RESERVATION DATE -> Sorted Sucessfully -> In DESCENDING order");
				break;
			default:
				break;
			}

		}

		public static void sortReserveByTotalAmount() {
			System.out.println("Enter Option");
			int idkey = scan.nextInt();
			// @SuppressWarnings("unused")
			class ascendingA implements Comparator<Reservation> {
				@Override
				public int compare(Reservation a, Reservation b) {
					return (int) (a.getTotalAmount() - b.getTotalAmount());
				}
			}
			class descendingD implements Comparator<Reservation> {
				@Override
				public int compare(Reservation a, Reservation b) {
					if ((b.getTotalAmount() < a.getTotalAmount()))
						return -1;
					if ((b.getTotalAmount() > a.getTotalAmount()))
						return 1;
					return 0;
				}
			}
			switch (idkey) {
			case 1:

				ArrayList<Reservation> data = AllMethods.readReservationFile();
				System.out.println("                                 After Sorting                          ");

				System.out.println(
						"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				Collections.sort(data, new ascendingA());
				for (int i = 0; i < data.size(); i++) {
					System.out.println(data.get(i) + "\t");
				}
				System.out.println(
						"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println(
						"                            RESERVATION TOTAL AMOUNT -> Sorted Sucessfully -> In ASCENDING order");

				break;
			case 2:

				ArrayList<Reservation> dataD = AllMethods.readReservationFile();
				System.out.println("                                 After Sorting                          ");
				System.out.println(
						"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

				Collections.sort(dataD, new descendingD());
				System.out.println(dataD.size());
				for (int i = 0; i < dataD.size(); i++) {
					System.out.println(dataD.get(i) + "\t");
				}
				System.out.println(
						"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println(
						"                            RESERVATION TOTAL AMOUNT -> Sorted Sucessfully -> In DESCENDING order");
				break;
			default:
				break;
			}

		}

		public static void generateReportAll() throws IOException {
			ArrayList<Reservation> data = readWithoutPrinting();
			createFileStamp();

			FileWriter writer = new FileWriter(generatePAth, true);
			Writer write = new BufferedWriter(writer);

			try {
				for (int i = 0; i < data.size(); i++) {
					writer.write(data.get(i).toString() + "\n");
				}
				writer.close();

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		
		public static void generateReportAllByThread() throws IOException {
			ArrayList<Reservation> data = readWithoutPrinting();
			createFileStamp();

			FileWriter writer = new FileWriter(generatePAth, true);
			Writer write = new BufferedWriter(writer);

			try {
				for (int i = 0; i < data.size(); i++) {
					writer.write(data.get(i).toString() + "\n");
				}
				writer.close();

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		private static String createFileStamp() {

//		
			try {
				String filename = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "Reservation _Report_"
						+ ".txt";

				File file = new File("C://Users//Amaresh//Documents//Reports//" + filename);
				System.out.println(file.getAbsolutePath());
				generatePAth = file.getAbsolutePath();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return generatePAth;
		}

		public static void byStatus(String opt1) throws IOException {
			ArrayList<Reservation> data = readWithoutPrinting();
			
		
				
			createFileStamp();
			System.out.println("_______________________________________________________________________________________________________________________________________________________________________");
			FileWriter writer = new FileWriter(generatePAth,true);
			Writer write = new BufferedWriter(writer);

			
			try {
				for (Reservation reservationList : data) {
					if ((reservationList.getStatus().equals(opt1))) {
						//System.out.println(reservationList);
						writer.write(reservationList + "\n");
					}
				}
				writer.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}

		static public ArrayList<Reservation> readWithoutPrinting() {
			ArrayList<Reservation> data = new ArrayList<Reservation>();
			try {

				File file = new File("C:\\Users\\Amaresh\\Desktop\\data.txt");
				Scanner readfile = new Scanner(file);
				StringTokenizer token = null;
				String reservationId = "";
				String customerName = "";
				String reservationDescription = "";
				LocalDateTime reservationDate;
				String adult = "";
				String children = "";
				String status = "";
				double subTotalAmount = 0.0;
				double discountAmount = 0.0;
				double taxAmount = 0.0;
				double totalAmount = 0.0;

				while (readfile.hasNextLine()) {
					token = new StringTokenizer(readfile.nextLine(), "\t");
					reservationId = token.nextToken();
					customerName = token.nextToken();
					reservationDescription = token.nextToken();
					reservationDate = LocalDateTime.parse(token.nextToken(), DateTimeFormatter.ISO_DATE_TIME);
					adult = token.nextToken();
					children = token.nextToken();
					subTotalAmount = Double.parseDouble(token.nextToken());
					discountAmount = Double.parseDouble(token.nextToken());
					taxAmount = Double.parseDouble(token.nextToken());
					totalAmount = Double.parseDouble(token.nextToken());
					status = token.nextToken();
					Reservation tableReservation = new Reservation(reservationId, customerName, reservationDescription,
							reservationDate, adult, children, subTotalAmount, discountAmount, taxAmount, totalAmount,
							status);

					data.add(tableReservation);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return data;

		}

	}
}