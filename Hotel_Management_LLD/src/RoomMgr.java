import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RoomMgr {
    private HashMap<RoomType, List<Room>> roomOfType;
    private HashMap<Integer, Room> rooms;

    private RoomMgr() {
        this.roomOfType = new HashMap<>();
        RoomType[] values = RoomType.values();
        for(int i=0;i<values.length;i++){
            roomOfType.put(values[i], new ArrayList<>());
        }
        this.rooms = new HashMap<>();
    }

    private static RoomMgr instance;
    public static RoomMgr getInstance(){
        if(instance == null){
            synchronized (HotelManagementSystem.class) {
                if(instance == null)instance = new RoomMgr();
            }
        }
        return instance;
    }

    public List<Room> getRoomsOfType(RoomType roomType) {
        return roomOfType.get(roomType);
    }
    public Room getRoom(Integer roomNo){
        return rooms.get(roomNo);
    }
    private RoomType getRoomType(Room room){
        // to get the baseclass of actual Room type, not the decorator type,
        // this can be used for deep copying in prototype pattern for creating clone of a room object
        while (room instanceof RoomDecorator){
            room = ((RoomDecorator) room).baseRoom;
        }
        // System.out.println(room.getClass().toString().substring(6));
        // .getClass() returns class name with "class " as a prefix, just removed that with substring
        switch (room.getClass().toString().substring(6)) {
            case "NormalRoom":
                return RoomType.NORMAL;
            case "DeluxeRoom":
                return RoomType.DELUXE;
            case "SuiteRoom":
                return RoomType.SUITE;
        }
        return RoomType.NORMAL;
    }
    public void addRoom(Room room){
        RoomType roomType = getRoomType(room);
        roomOfType.get(roomType).add(room);
        rooms.put(room.getRoomNo(), room);
    }
}
