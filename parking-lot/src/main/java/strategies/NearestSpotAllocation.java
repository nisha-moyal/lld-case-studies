package strategies;

import models.*;

public class NearestSpotAllocation implements SpotAllocationStrategy {
    @Override
    public ParkingSpot findSpot(Vehicle vehicle, ParkingLot parkingLot) {
        ParkingSpotType spotType = getSpotTypeForVehicle(vehicle.getType());

        for(ParkingSpot spot: parkingLot.getSpots()) {
            if(spotType.equals(spot.getType()) && spot.isFree()) {
                return spot;
            }
        }

        return null;
    }

    private ParkingSpotType getSpotTypeForVehicle(VehicleType type) {
        return switch (type) {
            case BIKE -> ParkingSpotType.BIKE;
            case CAR -> ParkingSpotType.COMPACT;
            case TRUCK -> ParkingSpotType.LARGE;
            default -> throw new IllegalArgumentException("Unknown vehicle type: " + type);
        };
    }
}
