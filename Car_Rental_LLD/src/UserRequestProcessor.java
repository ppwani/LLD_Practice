import java.util.*;
import java.util.stream.Collectors;

public class UserRequestProcessor {

    private VehicleManager vehicleManager;
    private UserManager userManager;
    private BookingManager bookingManager;

    public UserRequestProcessor(VehicleManager vehicleManager){
        this.vehicleManager = vehicleManager;
    }

    public List<Vehicle> searchVehicles(SearchRequest searchRequest) {
        // for location
        List<Vehicle> vehicles = vehicleManager.getVehiclesForLocation(searchRequest.locationPin);

        // filter for all search parameters
        vehicles = vehicles.stream()
                .filter(vehicle -> this.isVehicleAvailable(vehicle, searchRequest.from, searchRequest.to))
                .filter(vehicle -> vehicle.carType == searchRequest.carType)
                .filter(vehicle -> vehicle.carCondition == searchRequest.carCondition)
                .filter(vehicle -> vehicle.seaterType == searchRequest.seaterType)
                .collect(Collectors.toList());

        return vehicles;
    }

    private boolean isVehicleAvailable(Vehicle vehicle, Date from, Date to) {
        if(from == null || to == null)return true;
        List<Booking> bookings = new ArrayList<>();
        vehicle.bookingIds.forEach(bookingId -> bookings.add(bookingManager.getBooking(bookingId)));
        for(int i=0;i<bookings.size();i++){
            Booking booking = bookings.get(i);
            if(from.after(booking.from) && to.before(booking.to)){
                return false;
            }
        }
        return true;
    }

    public boolean bookVehicle(BookingRequest request) {
        User user = userManager.getUser(request.userId);
        Vehicle vehicle = vehicleManager.getVehicle(request.carId);
        List<Booking> bookings = new ArrayList<>();
        vehicle.bookingIds.forEach(bookingId -> bookings.add(bookingManager.getBooking(bookingId)));
        Date requestFrom = request.from;
        Date requestTo = request.to;
        for(int i=0;i<bookings.size();i++){
            Booking booking = bookings.get(i);
            if(requestFrom.after(booking.from) && requestTo.before(booking.to)){
                return false;
            }
        }
        return bookingManager.createBooking(request);
    }

    public boolean makePayment(){
        // assign this request to payment processor or strategy
        return true;
    }
}
