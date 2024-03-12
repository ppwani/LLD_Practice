import java.util.Date;

public class Booking {
    private Integer bookingId;

    public Booking(Integer bookingId, Integer userId, Integer receptionistId, Integer roomNo, Integer amountPaid, Date from, Date to, BookingStatus bookingStatus) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.receptionistId = receptionistId;
        this.roomNo = roomNo;
        this.amountPaid = amountPaid;
        this.from = from;
        this.to = to;
        this.bookingStatus = bookingStatus;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    private Integer userId;
    private Integer receptionistId;
    private Integer roomNo;
    private Integer amountPaid;
    private Date from;
    private Date to;
    private BookingStatus bookingStatus;

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getReceptionistId() {
        return receptionistId;
    }

    public Integer getRoomNo() {
        return roomNo;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public Date getFrom() {
        return from;
    }
    public Date getTo() {
        return to;
    }

    public void setAmountPaid(Integer amount) {
        this.amountPaid = amount;
    }

    public Integer getAmountPaid() {
        return amountPaid;
    }
}
