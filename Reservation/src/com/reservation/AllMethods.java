package com.reservation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.reservation.ReservationSystem.Reservation;

public class AllMethods {
	private static final String dataFilePath = "C:/Users/Amaresh/Desktop/data.txt";

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

	static FileOutputStream fos = null;
	static ObjectOutputStream oos = null;
	static ObjectInputStream ois = null;
	static FileInputStream fis = null;
	static Iterator<Reservation> itr = data.iterator();

	public static void addReservation() throws IOException, ClassNotFoundException {
		do {

			try {
				System.out.println(
						"----------------------------------------------------------------------------------------------------------------");
				System.out.println("Working Hour = " + currentDate.getHours());
				if (currentDate.getHours() < 24 && currentDate.getHours() >= 0) {
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

				System.out.println("-> Enter Reservation ID");

				int getYear = Date.getYear();

				System.out.println(getYear);

				loop1: while (true) {
					userInput = scan.next();
					if (!userInput.matches("[0-9]+")) {
						System.out.println("Enter A Numeric Digit");
						continue loop1;
					} else {
						String currentYear = Integer.toString(getYear);
						eightDigitId = currentYear.concat(userInput);
						System.out.println("ID Length = " + eightDigitId.length());
						break;
					}
				}

				try {
					Scanner scan = new Scanner(new File(dataFilePath));
					while (scan.hasNext()) {
						String line = scan.next();
						if (line.equals(eightDigitId)) {
							System.out.println("Duplicate Order Id. Please enter unique order id");
							addReservation();
						} else {
							break;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				String reservationId = "";
				if (eightDigitId.length() == 8) {
					reservationId = eightDigitId;
				} else {
					System.out.println("ID is to long:please enter 4 digits only");
					addReservation();
				}

				outer: while (true) {
					System.out.println("-> Enter Your Name");
					customerName = scan.next();

					if (!customerName.matches("[a-zA-Z_ ]+")) {
						System.out.println("Invalid Name");
						continue outer;
					} else {
						break;
					}
				}

				notok: while (true) {
					System.out.println("-> Enter Reservation_Description");
					reservationDescription = scanString1.nextLine();
					if (!reservationDescription.matches("[a-zA-Z0-9 ]+")) {

						System.out.println("Special Characaters are Not allowed");
						continue notok;
					} else

						break;
				}

				// System.out.println("-> Enter Reservation Date");
				LocalDateTime reservationDate = LocalDateTime.now();

				System.out.println("-> Enter No of Adults ");
				adult = scan.next();

				System.out.println("-> Enter No of Childrens");
				children = scan.next();

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
				//  totalAmount = decimal.format(amount);

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

			} catch (Exception e) {
				System.out.println("We love's you bookings closed please come back tommorow");
			}
			System.out.println(
					"---------------------------------------------------------------------------------------------------------------------------------");

			writeReservationData(data);

			System.out.println("                   Do you want to continue Yes/No                      ");

			String qes = scan.next();
			if (qes.equalsIgnoreCase("yes")) {
				addReservation();
			} else if (qes.equalsIgnoreCase("no")) {
				// TableReservation.main(null);
				break;

			}
			// scan.close();
		} while (true);

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

			while (readfile.hasNextLine()) {
				// System.out.println("Hii");
				token = new StringTokenizer(readfile.nextLine(), "\t");
				reservationId = token.nextToken();
				// System.out.println(reservationId);
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
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;

	}

	public static ArrayList<Reservation> getData() {
		return AllMethods.data;
	}

	public static void setData(ArrayList<Reservation> data) {
		AllMethods.data = data;
	}

	public static void writeReservationData(ArrayList<Reservation> data) throws IOException {
		FileWriter writer = new FileWriter(dataFilePath, true);
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

	public static void viewOrderByID() {
		ArrayList<Reservation> data = readReservationFile();
		boolean found = false;
		System.out.println("Enter ID to Search");
		String ID = scan.nextLine();
		ArrayList ar = new ArrayList();
		for (Reservation l : data) {
			ar.add(l.getReservationId());
		}
		if (!(ar.contains(ID))) {
			System.out.println("Not Found");
			viewOrderByID();
		}
		for (Reservation l : data) {
			if (l.getReservationId().equals((ID))) {
				System.out.println("              Found                   ");
				System.out.println("Reservation ID ->" + l.getReservationId());
				System.out.println("Customer Name  ->" + l.getCustomerName());
				System.out.println("Reservation Date ->" + l.getReservationDate());
				System.out.println("Reservation Discription ->" + l.getReservationDescription());
				System.out.println("No of Adults  ->" + l.getAdult());
				System.out.println("No of Childrens  ->" + l.getChildren());
				System.out.println("Sub Total Amount  ->" + l.getSubTotalAmount());
				System.out.println("Discount Amount ->" + l.getDiscountAmount());
				System.out.println("Tax Amount ->" + l.getTaxAmount());
				System.out.println("Total Amount ->" + l.getTotalAmount());
				System.out.println("Reservation Status ->" + l.getStatus());

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
		ArrayList<Reservation> data = readReservationFile();
		// boolean found = false;

		System.out.println("Enter ID to Delete");

		String ID = scan.nextLine();

		ArrayList ar = new ArrayList();

		for (Reservation l : data) {
			ar.add(l.getReservationId());
		}

		if (!(ar.contains(ID))) {
			System.out.println("Not Found");
			deleteOrderByID();
		}

		for (Reservation l : data) {
			System.out.println(l);
			if (l.getReservationId().equals((ID))) {

				data.remove(l);

				System.out.println("              Found                   ");
				System.out.println("Reservation ID ->" + l.getReservationId());
				System.out.println("Customer Name  ->" + l.getCustomerName());
				System.out.println("Reservation Date ->" + l.getReservationDate());
				System.out.println("Reservation Discription ->" + l.getReservationDescription());
				System.out.println("No of Adults  ->" + l.getAdult());
				System.out.println("No of Childrens  ->" + l.getChildren());
				System.out.println("Sub Total Amount  ->" + l.getSubTotalAmount());
				System.out.println("Discount Amount ->" + l.getDiscountAmount());
				System.out.println("Tax Amount ->" + l.getTaxAmount());
				System.out.println("Total Amount ->" + l.getTotalAmount());
				System.out.println("Reservation Status ->" + l.getStatus());
				System.out.println("      Deleted Sucessfully            ");
				break;
			}

		}
		System.out.println(data);

		clearFile();

		writeReservationData(data);

		ar.clear();

		System.out.println("Do you Want to search more Y/N");
		String qes = scan.next();
		if (qes.equalsIgnoreCase("y")) {

			deleteOrderByID();

		} else if (qes.equalsIgnoreCase("n")) {
			return;
		}

	}

	public static void confirmByRiD(String cID) throws IOException {
		ArrayList<Reservation> data = readReservationFile();
		// boolean found = false;

		ArrayList ar = new ArrayList();

		for (Reservation l : data) {
			ar.add(l.getReservationId());
		}

		if (!(ar.contains(cID))) {
			System.out.println("Not Found");
			return;
		}

		for (Reservation l : data) {
			// System.out.println(l);
			if (l.getStatus().contentEquals("Confirmed")) {
				System.out.println("Already Confirmed");
				break;
			} else

			if (l.getReservationId().equals((cID))) {

				l.setStatus("Confirmed");

				System.out.println("              Found                   ");
				System.out.println("Reservation ID ->" + l.getReservationId());
				System.out.println("Customer Name  ->" + l.getCustomerName());
				System.out.println("Reservation Date ->" + l.getReservationDate());
				System.out.println("Reservation Discription ->" + l.getReservationDescription());
				System.out.println("No of Adults  ->" + l.getAdult());
				System.out.println("No of Childrens  ->" + l.getChildren());
				System.out.println("Sub Total Amount  ->" + l.getSubTotalAmount());
				System.out.println("Discount Amount ->" + l.getDiscountAmount());
				System.out.println("Tax Amount ->" + l.getTaxAmount());
				System.out.println("Total Amount ->" + l.getTotalAmount());
				System.out.println("Reservation Status ->" + l.getStatus());
				System.out.println("      Confirmed Sucessfully            ");
				break;
			}

		}
		clearFile();

		writeReservationData(data);

		ar.clear();

		System.out.println("Do you Want to search more Y/N");
		String qes = scan.next();
		if (qes.equalsIgnoreCase("y")) {

			return;

		} else if (qes.equalsIgnoreCase("n")) {
			return;
		}

	}

	public static void cancelByRiD(String cID) throws IOException {
		ArrayList<Reservation> data = readReservationFile();
		// boolean found = false;

		ArrayList ar = new ArrayList();

		for (Reservation l : data) {
			ar.add(l.getReservationId());
		}

		if (!(ar.contains(cID))) {
			System.out.println("Not Found");
			return;
		}

		for (Reservation l : data) {
			// System.out.println(l);
			if (l.getStatus().contentEquals("Canceled")) {
				System.out.println("Already Canceled Cant be Changed");
				break;
			} else

			if (l.getReservationId().equals((cID))) {
				l.setStatus("Canceled");
				System.out.println("              Found                   ");
				System.out.println("Reservation ID ->" + l.getReservationId());
				System.out.println("Customer Name  ->" + l.getCustomerName());
				System.out.println("Reservation Date ->" + l.getReservationDate());
				System.out.println("Reservation Discription ->" + l.getReservationDescription());
				System.out.println("No of Adults  ->" + l.getAdult());
				System.out.println("No of Childrens  ->" + l.getChildren());
				System.out.println("Sub Total Amount  ->" + l.getSubTotalAmount());
				System.out.println("Discount Amount ->" + l.getDiscountAmount());
				System.out.println("Tax Amount ->" + l.getTaxAmount());
				System.out.println("Total Amount ->" + l.getTotalAmount());
				System.out.println("Reservation Status ->" + l.getStatus());
				System.out.println("      Confirmed Sucessfully            ");
				break;
			}

		}
		clearFile();
		writeReservationData(data);

		ar.clear();

		System.out.println("Do you Want to search more Y/N");
		String qes = scan.next();
		if (qes.equalsIgnoreCase("y")) {

			return;

		} else if (qes.equalsIgnoreCase("n")) {
			return;
		}

	}

	public static void clearFile() throws FileNotFoundException {
		PrintWriter writeempty = new PrintWriter(dataFilePath);
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

			System.out.println(
					"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			Collections.sort(data, new ascendingID());
			for (int i = 0; i < data.size(); i++) {
				System.out.println(data.get(i));
			}

			
			break;
		case 2:

			ArrayList<Reservation> dataD = AllMethods.readReservationFile();

			Collections.sort(dataD, new descendingID());
			System.out.println(dataD.size());
			for (int i = 0; i < dataD.size(); i++) {
				System.out.println(dataD.get(i));
			}

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

			System.out.println(
					"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			Collections.sort(data, new ascendingA());
			for (int i = 0; i < data.size(); i++) {
				System.out.println(data.get(i)+"\t");
			}

			
			break;
		case 2:

			ArrayList<Reservation> dataD = AllMethods.readReservationFile();

			Collections.sort(dataD, new descendingD());
			System.out.println(dataD.size());
			for (int i = 0; i < dataD.size(); i++) {
				System.out.println(dataD.get(i)+"\t");
			}

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

			System.out.println(
					"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			Collections.sort(data, new ascendingA());
			for (int i = 0; i < data.size(); i++) {
				System.out.println(data.get(i)+"\t");
			}

			
			break;
		case 2:

			ArrayList<Reservation> dataD = AllMethods.readReservationFile();

			Collections.sort(dataD, new descendingD());
			System.out.println(dataD.size());
			for (int i = 0; i < dataD.size(); i++) {
				System.out.println(dataD.get(i)+"\t");
			}

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
				return (int) (a.getTotalAmount()-b.getTotalAmount());
			}
		}
		 class descendingD implements Comparator<Reservation> {
				@Override
				public int compare(Reservation a, Reservation b) {
					return (int) (b.getTotalAmount()-a.getTaxAmount());
				}
			}

		switch (idkey) {
		case 1:
			
			ArrayList<Reservation> data = AllMethods.readReservationFile();

			System.out.println(
					"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			Collections.sort(data, new ascendingA());
			for (int i = 0; i < data.size(); i++) {
				System.out.println(data.get(i)+"\t");
			}

			
			break;
		case 2:

			ArrayList<Reservation> dataD = AllMethods.readReservationFile();

			Collections.sort(dataD, new descendingD());
			System.out.println(dataD.size());
			for (int i = 0; i < dataD.size(); i++) {
				System.out.println(dataD.get(i)+"\t");
			}

			break;
		default:
			break;
		}
		
	}



}