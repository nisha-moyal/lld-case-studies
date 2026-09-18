package models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParkingSpot {
    private String id;
    private ParkingSpotType type;
    private Vehicle parkedVehicle;

    public ParkingSpot(String id, ParkingSpotType type) {
        this.id = id;
        this.type = type;
        this.parkedVehicle = null;
    }

    public boolean isFree() {
        return parkedVehicle == null;
    }

    public void parkVehicle(Vehicle vehicle) {
        this.parkedVehicle = vehicle;
    }

    public Vehicle unparkVehicle() {
        if (this.parkedVehicle != null) {
            Vehicle vehicle = this.parkedVehicle;
            this.parkedVehicle = null;
            return vehicle;
        }
        return null;
    }
}
