package railwayTicketReservationSystem;

enum BerthType {
    LOWER,
    MIDDLE,
    UPPER,
    SIDE_LOWER,
    SIDE_UPPER
}

class Passenger {
    private String name;
    private int age;
    private String gender;
    private BerthType berthPreference;

    public Passenger(String name, int age, String gender, BerthType berthPreference) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.berthPreference = berthPreference;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public BerthType getBerthPreference() {
        return berthPreference;
    }
}
