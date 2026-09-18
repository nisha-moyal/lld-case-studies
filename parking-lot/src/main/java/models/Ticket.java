package models;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class Ticket {
    private String id;
    private Instant entryTime;
    private TicketStatus status;
    private String plateNumber;
    private String spotId;
    private VehicleType vehicleType;
    private ParkingSpotType spotType;
}
