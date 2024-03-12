public abstract class RoomDecorator extends Room{
    protected Room baseRoom;
    public RoomDecorator(Room baseRoom){
        super(baseRoom.getRentPerDay());
        this.baseRoom = baseRoom;
    }
}
