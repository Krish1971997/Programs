package railwayTicketReservationSystem;

import java.util.ArrayList;
import java.util.List;

class RailwayTicketReservationSystem {
	private static final int MAX_CONFIRMED_TICKETS = 63;
	private static final int MAX_RAC_TICKETS = 18;
	private static final int MAX_WAITING_LIST_TICKETS = 10;

	private List<Ticket> tickets;
	private int confirmedTicketsCount;
	private int racTicketsCount;
	private int waitingListTicketsCount;

	private int lowerBerthCount;
	private int middleBerthCount;
	private int upperBerthCount;
	private int sideLowerBerthCount;
	private int sideUpperBerthCount;

	public RailwayTicketReservationSystem() {
		tickets = new ArrayList<>();
		confirmedTicketsCount = 0;
		racTicketsCount = 0;
		waitingListTicketsCount = 0;

		lowerBerthCount = 13;
		middleBerthCount = 13;
		upperBerthCount = 13;
		sideLowerBerthCount = 12;
		sideUpperBerthCount = 12;
	}

	public void bookTicket(Passenger passenger) {
		if (passenger.getAge() < 5) {
			System.out.println("Ticket cannot be booked for children below 5 years.");
			return;
		}

		if (waitingListTicketsCount > MAX_WAITING_LIST_TICKETS) {
			System.out.println("No tickets available. Waiting list is full.");
			return;
		}

		String status;
		BerthType berthType = determineBerthType(passenger);

		if (confirmedTicketsCount < MAX_CONFIRMED_TICKETS && hasAvailableBerth(berthType)) {
			status = "Confirmed";
			confirmedTicketsCount++;
			decrementBerthCount(berthType);
		} else if (racTicketsCount < MAX_RAC_TICKETS) {
			status = "RAC";
			racTicketsCount++;
		} else {
			status = "Waiting-List";
			waitingListTicketsCount++;
		}

		Ticket ticket = new Ticket(tickets.size() + 1, passenger, status);
		tickets.add(ticket);

		System.out.println("Ticket booked successfully. Ticket number: " + ticket.getTicketNumber() + " "
				+ ticket.getPassenger().getBerthPreference());
	}

	private BerthType determineBerthType(Passenger passenger) {
		if (passenger.getAge() > 60 || (passenger.getGender().equalsIgnoreCase("female") && passenger.getAge() > 5)) {
			return BerthType.LOWER;
		}
		if (passenger.getBerthPreference() != null) {
			return passenger.getBerthPreference();
		}

		return BerthType.SIDE_LOWER;
	}

	private boolean hasAvailableBerth(BerthType berthType) {
		switch (berthType) {
		case LOWER:
			return lowerBerthCount > 0;
		case MIDDLE:
			return middleBerthCount > 0;
		case UPPER:
			return upperBerthCount > 0;
		case SIDE_LOWER:
			return sideLowerBerthCount > 0;
		case SIDE_UPPER:
			return sideUpperBerthCount > 0;
		default:
			return false;
		}
	}

	private void decrementBerthCount(BerthType berthType) {
		switch (berthType) {
		case LOWER:
			lowerBerthCount--;
			break;
		case MIDDLE:
			middleBerthCount--;
			break;
		case UPPER:
			upperBerthCount--;
			break;
		case SIDE_LOWER:
			sideLowerBerthCount--;
			break;
		case SIDE_UPPER:
			sideUpperBerthCount--;
			break;
		}
	}

	public void cancelTicket(int ticketNumber) {
		Ticket ticket = findTicket(ticketNumber);
		if (ticket == null) {
			System.out.println("Invalid ticket number.");
			return;
		}

		if (ticket.getStatus().equalsIgnoreCase("Confirmed")) {
			confirmedTicketsCount--;
			incrementBerthCount(ticket.getPassenger().getBerthPreference());
		} else if (ticket.getStatus().equalsIgnoreCase("RAC")) {
			racTicketsCount--;
		} else if (ticket.getStatus().equalsIgnoreCase("Waiting-List")) {
			waitingListTicketsCount--;
		}

		ticket.setStatus("Cancelled");

		if (racTicketsCount < MAX_RAC_TICKETS && waitingListTicketsCount > 0) {
			Ticket waitingListTicket = findFirstWaitingList();
			waitingListTicket.setStatus("RAC");
			racTicketsCount++;
			waitingListTicketsCount--;
		}

		System.out.println("Ticket cancelled successfully.");
	}

	private void incrementBerthCount(BerthType berthType) {
		switch (berthType) {
		case LOWER:
			lowerBerthCount++;
			break;
		case MIDDLE:
			middleBerthCount++;
			break;
		case UPPER:
			upperBerthCount++;
			break;
		case SIDE_LOWER:
			sideLowerBerthCount++;
			break;
		case SIDE_UPPER:
			sideUpperBerthCount++;
			break;
		}
	}

	public void printBookedTickets() {
		System.out.println("Booked Tickets:");
		for (Ticket ticket : tickets) {
			if (!ticket.getStatus().equalsIgnoreCase("Cancelled")) {
				System.out.println("Ticket Number: " + ticket.getTicketNumber());
				System.out.println("Passenger Name: " + ticket.getPassenger().getName());
				System.out.println("Passenger Age: " + ticket.getPassenger().getAge());
				System.out.println("Passenger Gender: " + ticket.getPassenger().getGender());
				System.out.println("Berth Preference: " + ticket.getPassenger().getBerthPreference());
				System.out.println("Status: " + ticket.getStatus());
				System.out.println("-----------------------------");
			}
		}
		System.out.println("Total Booked Tickets: " + confirmedTicketsCount);
	}

	public void printAvailableTickets() {
		System.out.println("Available Tickets:");
		System.out.println("Lower Berth Count: " + lowerBerthCount);
		System.out.println("Middle Berth Count: " + middleBerthCount);
		System.out.println("Upper Berth Count: " + upperBerthCount);
		System.out.println("Side Lower Berth Count: " + sideLowerBerthCount);
		System.out.println("Side Upper Berth Count: " + sideUpperBerthCount);
		System.out.println("Total Available Tickets: "
				+ (lowerBerthCount + middleBerthCount + upperBerthCount + sideLowerBerthCount + sideUpperBerthCount));
	}

	private Ticket findTicket(int ticketNumber) {
		for (Ticket ticket : tickets) {
			if (ticket.getTicketNumber() == ticketNumber) {
				return ticket;
			}
		}
		return null;
	}

	private Ticket findFirstWaitingList() {
		for (Ticket ticket : tickets) {
			if (ticket.getStatus().equalsIgnoreCase("Waiting-List")) {
				return ticket;
			}
		}
		return null;
	}
}