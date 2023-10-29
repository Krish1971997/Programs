package busBookingSystem;
enum status {
    CONFIRMED, CANCELLED
}

public class Customers {
    String name;
    int age;
    String gender;
    BookingType type;
    BusType busType;
    double fare;
    status status;
    String key;


    public void setKey(String key) {
        this.key = key;
    }

    public Customers() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    BookingType getType() {
        return type;
    }

    void setType(BookingType type) {
        this.type = type;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public void setAge(int i) {
        this.age = i;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    BusType getBusType() {
        return busType;
    }

	public String getKey() {
		return key;
	}

    void setBusType(BusType busType) {
        this.busType = busType;
    }

    void setStatus(status confirmed) {
        this.status = confirmed;
    }

    @Override
    public String toString() {
        return "Customers{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", type=" + type +
                ", busType=" + busType +
                ", fare=" + fare +
                ", status=" + status +
                ", key='" + key + '\'' +
                '}';
    }
}
