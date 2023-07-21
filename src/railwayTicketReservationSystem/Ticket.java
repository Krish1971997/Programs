package railwayTicketReservationSystem;

class Ticket {
    private int ticketNumber;
    private Passenger passenger;
    private String status;

    public Ticket(int ticketNumber, Passenger passenger, String status) {
        this.ticketNumber = ticketNumber;
        this.passenger = passenger;
        this.status = status;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}