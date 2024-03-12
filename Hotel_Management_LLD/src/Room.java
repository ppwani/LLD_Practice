import java.util.ArrayList;
import java.util.List;

public abstract class Room {
    protected Integer roomNo;
    private List<Integer> bookings;
    private Integer rentPerDay;

    public Room(Integer rentPerDay){
        bookings = new ArrayList<>();
        this.rentPerDay = rentPerDay;
    }

    // can be used to create a deep copy of this room, as there are many rooms with only varying field roomNo.
    // so we can just clone a prototype object using this method and change its roomNo only.
    public Room clone(){
        Room room = this;
        while (room instanceof RoomDecorator){
            room = ((RoomDecorator) room).baseRoom;
            // here push the types of decorators in a stack and pop them after this loop to create a new copy
        }
        Room clone = null;
        // this if else creates a base room object if any, present in those decorator nestings
        if(room.getClass().toString().equals("class NormalRoom")){
            clone = new NormalRoom(room.getRentPerDay());
        }
        else if(room.getClass().toString().equals("class DeluxeRoom")){
            clone = new DeluxeRoom(room.getRentPerDay());
        }
        else if(room.getClass().toString().equals("class SuiteRoom")){
            clone = new SuiteRoom(room.getRentPerDay());
        }

        return this;
    }

    public Integer getRoomNo(){
        return roomNo;
    }

    public Integer getRentPerDay(){
        return rentPerDay;
    }

    public void addBooking(Integer bookingId) {
        bookings.add(bookingId);
    }

    public void removeBooking(Integer bookingId) {
        bookings.remove(bookingId);
    }
}
