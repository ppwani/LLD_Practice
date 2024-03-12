import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // creating room
        Room room = new BedDecorator(new ACDecorator(new DeluxeRoom(100)));
        System.out.println(room.getRentPerDay());
        RoomMgr roomMgr = RoomMgr.getInstance();
        roomMgr.addRoom(room);

        // creating receptionist
        UserMgr userMgr = UserMgr.getInstance();
        User receptionist = new Receptionist();
        receptionist.setId(123);
        userMgr.addReceptionist(receptionist);

        // mocking customer flow for checking roo availability, verification and booking
        HotelManagementSystem system = HotelManagementSystem.getInstance();
        List<Room> roomsAvailableForType = system.checkRooms(RoomType.DELUXE);
        System.out.println("checked rooms:" + roomsAvailableForType);
        System.out.println("User is proceeding with the room " + roomsAvailableForType.get(0));
        System.out.println("Verifying User");
        Integer userId = system.verifyOrAddUser("1234567890");
        System.out.println("User has the ID: "+ userId);
        Integer bookingId = system.bookRoom(new Booking(null,
                userId,
                receptionist.getId(),
                roomsAvailableForType.get(0).getRoomNo(),
                0,
                new Date(System.currentTimeMillis()),
                new Date(System.currentTimeMillis() + 10000),
                BookingStatus.STARTED));
        System.out.println("Room Booked with booking number: " + bookingId);
    }
}