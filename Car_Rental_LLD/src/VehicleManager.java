import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VehicleManager {
    private Map<String, List<Vehicle>> locationVehicles;
    private Map<String, Vehicle> vehicles;

    public VehicleManager(){
        locationVehicles = new HashMap<>();
    }

    public List<Vehicle> getVehiclesForLocation(String locationPin) {
        return locationVehicles.get(locationPin);
    }

    public Vehicle getVehicle(String vehicleId) {
        return vehicles.get(vehicleId);
    }
}
