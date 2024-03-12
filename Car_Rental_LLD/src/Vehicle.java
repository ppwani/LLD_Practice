import java.util.Date;
import java.util.List;

public class Vehicle {
    public boolean isBooked;
    public User renter;
    public User bookedBy;
    public String location;
    public CarCondition carCondition;
    public SeaterType seaterType;
    public CarType carType;
    public List<Integer>bookingIds;
}
