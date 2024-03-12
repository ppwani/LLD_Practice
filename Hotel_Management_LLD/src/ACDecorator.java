public class ACDecorator extends RoomDecorator{
    public ACDecorator(Room baseRoom) {
        super(baseRoom);
    }

    @Override
    public Integer getRentPerDay(){
        return baseRoom.getRentPerDay() + 300;
    }
}
