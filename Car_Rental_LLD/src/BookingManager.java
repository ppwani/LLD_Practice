import java.util.HashMap;

public class BookingManager {
    private HashMap<Integer, Booking> activeBookings;
    private HashMap<Integer, Booking> pastBookings;
    public Booking getBooking(Integer id){
        return activeBookings.get(id);
    }

    public boolean createBooking(BookingRequest request){
        Booking booking = new Booking();
        booking.from = request.from;
        booking.to = request.to;
        booking.userId = request.userId;
        booking.carId = request.carId;
        Integer newBookingId = 10; // put into db and get its id
        activeBookings.put(newBookingId, booking);
        return true;
    }
}
