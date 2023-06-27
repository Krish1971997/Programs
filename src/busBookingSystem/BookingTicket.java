package busBookingSystem;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

enum BusType {
	AC, NONAC
};

enum BookingType {
	SEATER, SLEEPER
};

public class BookingTicket {
	static Scanner sc = new Scanner(System.in);
	static Map<String, Customers> sleeperAC = new LinkedHashMap<>();
	static Map<String, Customers> sleeperNonAC = new LinkedHashMap<>();
	static Map<String, Customers> seaterAC = new LinkedHashMap<>();
	static Map<String, Customers> seaterNonAC = new LinkedHashMap<>();

	static final int SLEEPERACTICKETS = 12;
	static final int SEATERACTICKETS = 12;
	static final int SLEEPERNONACTICKETS = 12;
	static final int SEATERNONACTICKETS = 12;

	static final double SLEEPERACPRICE = 700.00;
	static final double SEATERACPRICE = 550.00;
	static final double SLEEPERNONACPRICE = 600.00;
	static final double SEATERNONACPRICE = 450.00;

	public BookingTicket() {
		seaterTicketsAssign(seaterAC);
		seaterTicketsAssign(seaterNonAC);
		sleeperTicketsAssign(sleeperAC);
		sleeperTicketsAssign(sleeperNonAC);
	}

	public void seaterTicketsAssign(Map<String, Customers> sleeper) {
		sleeper.put("1A", null);
		sleeper.put("2A", null);
		sleeper.put("1B", null);
		sleeper.put("2B", null);
		sleeper.put("1C", null);
		sleeper.put("2C", null);
		sleeper.put("3A", null);
		sleeper.put("4A", null);
		sleeper.put("3B", null);
		sleeper.put("4B", null);
		sleeper.put("3C", null);
		sleeper.put("4C", null);
	}

	public void sleeperTicketsAssign(Map<String, Customers> sleeper) {
		sleeper.put("1A", null);
		sleeper.put("1B", null);
		sleeper.put("2A", null);
		sleeper.put("2B", null);
		sleeper.put("3A", null);
		sleeper.put("3B", null);
		sleeper.put("4A", null);
		sleeper.put("4B", null);
		sleeper.put("5A", null);
		sleeper.put("5B", null);
		sleeper.put("6A", null);
		sleeper.put("6B", null);
	}

	public void availableTickets() {
		int seaterACcount = 0;
		int seaterNonACcount = 0;
		int sleeperACcount = 0;
		int sleeperNonACcount = 0;

		for (Entry<String, Customers> map : seaterAC.entrySet()) { // SeaterAC count
			if (map.getValue() == null)
				seaterACcount++;
		}
		for (Entry<String, Customers> map : seaterNonAC.entrySet()) { // SeaterAC count
			if (map.getValue() == null)
				seaterNonACcount++;
		}
		for (Entry<String, Customers> map : sleeperAC.entrySet()) { // SeaterAC count
			if (map.getValue() == null)
				sleeperACcount++;
		}
		for (Entry<String, Customers> map : sleeperNonAC.entrySet()) { // SeaterAC count
			if (map.getValue() == null)
				sleeperNonACcount++;
		}
		System.out.println("AC Sleeper - " + sleeperACcount);
		System.out.println("AC Seater - " + seaterACcount);
		System.out.println("Non AC Sleeper - " + sleeperNonACcount);
		System.out.println("Non AC Seater -" + seaterNonACcount);
	}

	public boolean bookTicket(List<Customers> list, BusType busType, BookingType bookingType, int person, double sum) {
		Map<String, Customers> booking = bookingTypeCheck(busType, bookingType);
		showAvailableTickets(booking);
		System.out.println("Please confirm the Total fare is (Y/N) : " + sum);
		String ch = sc.next().toUpperCase();
		if (ch.equalsIgnoreCase("Y")) {
			for (int i = 0; i < person; i++) {
				System.out.println("Enter the ticket number");
				String ticketNumber = sc.next().toUpperCase();
				for (Entry<String, Customers> map : booking.entrySet()) {
					if (map.getKey().equalsIgnoreCase(ticketNumber))
						booking.put(ticketNumber, list.get(i));
				}
			}
			return true;
		}
		return false;
	}

	private void showAvailableTickets(Map<String, Customers> booking) {
		for (Entry<String, Customers> map : booking.entrySet()) {
			if (map.getValue() == null)
				System.out.println(map.getKey());
		}
	}

	public static Map<String, Customers> bookingTypeCheck(BusType busType, BookingType bookingType) {
		switch (busType) {
		case AC: {
			if (bookingType == BookingType.SEATER)
				return seaterAC;
			else
				return sleeperAC;
		}
		case NONAC: {
			if (bookingType == BookingType.SEATER)
				return seaterNonAC;
			else
				return sleeperNonAC;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + busType);
		}
	}

	public double fareCalculation(BusType busType, BookingType bookingType) {
		switch (busType) {
		case AC: {
			if (bookingType == BookingType.SEATER)
				return SEATERACPRICE;
			else
				return SLEEPERACPRICE;
		}
		case NONAC: {
			if (bookingType == BookingType.SEATER)
				return SEATERNONACPRICE;
			else
				return SLEEPERNONACPRICE;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + busType);
		}
	}

	public int maxTicketsValidation(BusType busType, BookingType bookingType) {
		switch (busType) {
		case AC: {
			if (bookingType == BookingType.SEATER)
				return SEATERACTICKETS;
			else
				return SLEEPERACTICKETS;
		}
		case NONAC: {
			if (bookingType == BookingType.SEATER)
				return SEATERNONACTICKETS;
			else
				return SLEEPERNONACTICKETS;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + busType);
		}
	}

	public void history() {
		BookingHistory.bookingHistory(sleeperAC);
		BookingHistory.bookingHistory(seaterAC);
		BookingHistory.bookingHistory(sleeperNonAC);
		BookingHistory.bookingHistory(seaterNonAC);
	}

	public void cancellation() {
		history(); // 1
		System.out.println("\nDo you want to cancel which tickets\n \n1.cancel all tickets \n2.partial tickets");
		int choice = sc.nextInt();
		double refundAmount = 0.00;
		switch (choice) {
		case 1: {
			refundAmount += BookingHistory.cancelAllTickets(BusType.AC, BookingType.SLEEPER, sleeperAC);
			refundAmount += BookingHistory.cancelAllTickets(BusType.NONAC, BookingType.SLEEPER, sleeperNonAC);
			refundAmount += BookingHistory.cancelAllTickets(BusType.AC, BookingType.SEATER, seaterAC);
			refundAmount += BookingHistory.cancelAllTickets(BusType.NONAC, BookingType.SEATER, seaterNonAC);
			System.out.println("Cancelled the all tickets. Remaining fare amount : " + refundAmount);
			break;
		}
		case 2: {
			System.out.println("Enter the how many tickets you want to cancel:");
			int cancelChoice = sc.nextInt();

			System.out.println("Do you want to book AC or NONAC");
			BusType busType = BusType.valueOf(sc.next().toUpperCase());

			System.out.println("Do you want to book Seater or Sleeper");
			BookingType bookingType = BookingType.valueOf(sc.next().toUpperCase());

			List<String> list = new ArrayList<>();
			System.out.println("Enter the ticket numbers");
			for (int i = 0; i < cancelChoice; i++) {
				list.add(sc.next().toUpperCase());
			}
			BookingHistory.cancelPartialTickets(list, busType, bookingType);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + choice);
		}
	}

	public void adminLogin() {
		System.out.println("Enter the admin user id:");
		int id = sc.nextInt();
		System.out.println("Enter the admin password:");
		String password = sc.next();
		if (id == 1234 && password.equals("admin")) {
			busSummary(BusType.AC, BookingType.SLEEPER);
			busSummary(BusType.AC, BookingType.SEATER);
			busSummary(BusType.NONAC, BookingType.SLEEPER);
			busSummary(BusType.NONAC, BookingType.SEATER);
		} else
			System.out.println("Admin User Id or password is incorrect");
	}

	private void busSummary(BusType busType, BookingType bookingType) {
		Map<String, Customers> booking = BookingTicket.bookingTypeCheck(busType, bookingType);
		System.out.println("\n" + busType + " " + bookingType);
		int bookingCount = 0, cancellationCount = 0;
		double bookingFare = 0.00, cancellationFare = 0.00;

		for (Entry<String, Customers> map : booking.entrySet()) {
			if (map.getValue() != null) {
				bookingCount++;
				bookingFare += map.getValue().getFare();
			}
		}
		System.out.println("Number of seats filled : " + bookingCount + "");

		for (Entry<Integer, Customers> map : BookingHistory.CancelTickets.entrySet()) {
			if (map.getValue().busType == busType && map.getValue().getType() == bookingType) {
				cancellationCount++;
				cancellationFare += map.getValue().getFare();
			}
		}
		double fare = bookingFare + cancellationFare;
		System.out.println("Total Fare Collected : " + fare + " (" + bookingCount + " seats+ " + cancellationCount
				+ " cancellation )\n\nSeat Details : ");

		for (Entry<String, Customers> map : booking.entrySet()) {
			if (map.getValue() != null) {
				System.out.printf("Seat :%s\tName: %s\tGender: %s", map.getKey(), map.getValue().getName(),
						map.getValue().getGender());
				System.out.println();
			}
		}
	}
}
