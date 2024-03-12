import java.util.HashMap;

public class BookingMgr {
    private HashMap<Integer, Booking> bookings;

    private static BookingMgr instance;

    private BookingMgr() {
        bookings = new HashMap<>();
    }

    public static BookingMgr getInstance(){
        if(instance == null){
            synchronized (HotelManagementSystem.class) {
                if(instance == null)instance = new BookingMgr();
            }
        }
        return instance;
    }

    public Booking getBooking(Integer bookingId){
        return bookings.get(bookingId);
    }

    public Integer createBooking(Booking booking) {
        // create new booking using this parameter
        // put this booking in DB, get the new ID from DB
        booking.setBookingId(101);
        booking.setBookingStatus(BookingStatus.DONE);
        return booking.getBookingId();
    }
}
