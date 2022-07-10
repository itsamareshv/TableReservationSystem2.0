package com.reservation;

import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;

interface ReservationSystem {

	public class Reservation implements Serializable {
	  private String reservationId;
		private String customerName;
		private String reservationDescription;
		private LocalDateTime reservationDate;
		private String adult;
		private String children;
		private String status;
		private double subTotalAmount;
		private double discountAmount;
		private double taxAmount;
		private double totalAmount;
		private static final String dataFilePath = "C:/Users/Amaresh/git/trs/Table_Reservation_System/reports/table_reservation_data.txt";
		static File file = new File(dataFilePath);

		static ObjectOutputStream oos = null;
		static ObjectInputStream ois = null;
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy:HH:mm:ssa");

		Date now = new Date();
		Scanner scan = new Scanner(System.in);

		public Reservation(String reservationId, String customerName, String reservationDescription,
				LocalDateTime reservationDate, String adult, String children, double subTotalAmount,
				double discountAmount, double taxAmount, double totalAmount, String status) {
			super();
			this.reservationId = reservationId;
			this.customerName = customerName;
			this.reservationDescription = reservationDescription;
			this.reservationDate = reservationDate;
			this.adult = adult;
			this.children = children;
			this.status = status;
			this.subTotalAmount = subTotalAmount;
			this.discountAmount = discountAmount;
			this.taxAmount = taxAmount;
			this.totalAmount = totalAmount;
		}

		@Override
		public String toString() {
			return reservationId + "\t" + customerName + "\t" + reservationDescription + "\t\t\t" + reservationDate + "\t"
					+ adult + "\t" + children + "\t" + subTotalAmount + "\t" + discountAmount + "\t" + taxAmount + "\t"
					+ totalAmount + "\t" + status;

		}

		public  String getReservationId() {
			return reservationId;
		}

		public void setReservationId(String reservationId) {
		this.reservationId = reservationId;
		}

		public String getCustomerName() {
			return customerName;
		}

		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}

		public String getReservationDescription() {
			return reservationDescription;
		}

		public void setReservationDescription(String reservationDescription) {
			this.reservationDescription = reservationDescription;
		}

		public LocalDateTime getReservationDate() {
			return reservationDate;
		}

		public void setReservationDate(LocalDateTime reservationDate) {
			this.reservationDate = reservationDate;
		}

		public String getAdult() {
			return adult;
		}

		public void setAdult(String adult) {
			this.adult = adult;
		}

		public String getChildren() {
			return children;
		}

		public void setChildren(String children) {
			this.children = children;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public double getSubTotalAmount() {
			return subTotalAmount;
		}

		public void setSubTotalAmount(double subTotalAmount) {
			this.subTotalAmount = subTotalAmount;
		}

		public double getDiscountAmount() {
			return discountAmount;
		}

		public void setDiscountAmount(double discountAmount) {
			this.discountAmount = discountAmount;
		}

		public double getTaxAmount() {
			return taxAmount;
		}

		public void setTaxAmount(double taxAmount) {
			this.taxAmount = taxAmount;
		}

		public double getTotalAmount() {
			return totalAmount;
		}

		public void setTotalAmount(double totalAmount) {
			this.totalAmount = totalAmount;
		}

	}
}
