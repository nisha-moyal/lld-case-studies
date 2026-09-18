package strategies;

import models.ParkingLot;
import models.ParkingSpot;
import models.Vehicle;

public interface SpotAllocationStrategy {
    public ParkingSpot findSpot(Vehicle vehicle, ParkingLot parkingLot);
}
