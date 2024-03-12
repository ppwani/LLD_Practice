import java.util.ArrayList;
import java.util.List;

public abstract class User {
    protected Integer id;
    private List<Integer> bookings;

    public User() {
        bookings = new ArrayList<>();
    }

    public void addBooking(Integer bookingId) {
        bookings.add(bookingId);
    }
    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id = id;
    }

}
