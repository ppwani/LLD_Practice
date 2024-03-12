public class BedDecorator extends RoomDecorator{
    public BedDecorator(Room baseRoom) {
        super(baseRoom);
    }
    @Override
    public Integer getRentPerDay(){
        return baseRoom.getRentPerDay() + 200;
    }
}
