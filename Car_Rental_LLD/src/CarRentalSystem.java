import java.util.List;

public class CarRentalSystem {

    UserRequestProcessor userRequestProcessor;
    public List<Vehicle> searchVehiclesForLocation(SearchRequest searchRequest){
        return userRequestProcessor.searchVehicles(searchRequest);
    }

    public boolean bookVehicle(BookingRequest request){
        return userRequestProcessor.bookVehicle(request);
    }
}
