import java.util.List;

// singleton Main Facade for all requests, as RestController in SpringBoot
public class HotelManagementSystem {
    private UserMgr userMgr;
    private RoomMgr roomMgr;
    private BookingMgr bookingMgr;

    private static HotelManagementSystem instance;
    private HotelManagementSystem(){
        userMgr = UserMgr.getInstance();
        roomMgr = RoomMgr.getInstance();
        bookingMgr = BookingMgr.getInstance();
    }
    // double-checked locking
    public static HotelManagementSystem getInstance(){
        if(instance == null){
            synchronized (HotelManagementSystem.class) {
                if(instance == null)instance = new HotelManagementSystem();
            }
        }
        return instance;
    }
    public Integer verifyOrAddUser(String aadharNo){
        return userMgr.verifyOrAddUser(aadharNo);
    }
    public List<Room> checkRooms(RoomType roomType){
        return roomMgr.getRoomsOfType(roomType);
    }
    public Integer bookRoom(Booking booking){
        // request for room booking with id=null and amountPaid=0 and bookingStatus=STARTED
        // here we are directly creating a booking,
        // but we can perform some checks before that, like if that room is available or not in that time
        Integer bookingId = bookingMgr.createBooking(booking);
        userMgr.getUser(booking.getUserId()).addBooking(bookingId);
        userMgr.getReceptionist(booking.getReceptionistId()).addBooking(bookingId);
        roomMgr.getRoom(booking.getRoomNo()).addBooking(bookingId);
        return bookingId;
    }
    public Integer makePayment(Integer bookingId, Integer amount){
        // delegate this request to a separate payment processor
        // which itself is a big system
        // after it returns, fill the amountPaid field in booking
        Booking booking = bookingMgr.getBooking(bookingId);
        Integer requiredAmount = booking.getFrom().compareTo(booking.getTo()) *
                roomMgr.getRoom(booking.getRoomNo()).getRentPerDay();
        booking.setAmountPaid(Math.min(requiredAmount, amount));
        if(amount >= requiredAmount) {
            booking.setBookingStatus(BookingStatus.PAID);
        }
        return amount - requiredAmount; // return the change to return, if negative, its not PAID
    }
    public boolean cancelBooking(Integer bookingId){
        Booking booking = bookingMgr.getBooking(bookingId);
        booking.setBookingStatus(BookingStatus.CANCELLED);
        roomMgr.getRoom(booking.getRoomNo()).removeBooking(bookingId);
        return true;
    }
    public boolean checkOut(Integer bookingId){
        Booking booking = bookingMgr.getBooking(bookingId);
        if(booking.getBookingStatus().equals(BookingStatus.PAID)) {
            booking.setBookingStatus(BookingStatus.CHECKED_OUT);
        }
        roomMgr.getRoom(booking.getRoomNo()).removeBooking(bookingId);
        return BookingStatus.CHECKED_OUT.equals(booking.getBookingStatus()); // returns true only if its paid and checked out successfully
    }
}
