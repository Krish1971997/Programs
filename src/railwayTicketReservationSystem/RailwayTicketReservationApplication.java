package railwayTicketReservationSystem;
import java.util.Scanner;

public class RailwayTicketReservationApplication {
    public static void main(String[] args) {
        RailwayTicketReservationSystem reservationSystem = new RailwayTicketReservationSystem();
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Railway Ticket Reservation System");
            System.out.println("1. Book Ticket");
            System.out.println("2. Cancel Ticket");
            System.out.println("3. Print Booked Tickets");
            System.out.println("4. Print Available Tickets");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Passenger Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Passenger Age: ");
                    int age = scanner.nextInt();
                    System.out.print("Enter Passenger Gender: ");
                    String gender = scanner.nextLine();
                    System.out.print("Enter Berth Preference (LOWER, MIDDLE, UPPER, SIDE_LOWER, SIDE_UPPER): ");
                    BerthType berthPreference = BerthType.valueOf(scanner.nextLine().toUpperCase());
                    Passenger passenger = new Passenger(name, age, gender, berthPreference);
                    reservationSystem.bookTicket(passenger);
                    break;
                case 2:
                    System.out.print("Enter Ticket Number to Cancel: ");
                    int ticketNumber = scanner.nextInt();
                    reservationSystem.cancelTicket(ticketNumber);
                    break;
                case 3:
                    reservationSystem.printBookedTickets();
                    break;
                case 4:
                    reservationSystem.printAvailableTickets();
                    break;
                case 5:
                    System.out.println("Thank you for using the Railway Ticket Reservation System.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 5);

        scanner.close();
    }
}
