package models;

import strategies.PricingStrategy;
import strategies.SpotAllocationStrategy;

import java.time.Instant;
import java.util.*;

public class ParkingLot {
    private List<ParkingSpot> spots = new ArrayList<>();
    private HashMap<String, Ticket> tickets = new HashMap<>();
    private SpotAllocationStrategy spotAllocationStrategy;
    private PricingStrategy pricingStrategy;

    public ParkingLot(SpotAllocationStrategy spotAllocationStrategy, PricingStrategy pricingStrategy) {
        this.spotAllocationStrategy = spotAllocationStrategy;
        this.pricingStrategy = pricingStrategy;
    }

    public List<ParkingSpot> getSpots() {
        if (this.spots.isEmpty()) {
            throw new RuntimeException("No spots available");
        }

        return Collections.unmodifiableList(spots);
    }

    public void addSpot(ParkingSpot spot) {
        if(spot == null) {
            throw new RuntimeException("Spot cannot be null");
        }
        this.spots.add(spot);
    }

    public Ticket parkVehicle(Vehicle vehicle) {
        ParkingSpot spot = spotAllocationStrategy.findSpot(vehicle, this);
        if(spot == null) {
            throw new RuntimeException("No spot available for vehicle type: " + vehicle.getType());
        }

        spot.parkVehicle(vehicle);
        Ticket ticket = new Ticket();
        ticket.setId(UUID.randomUUID().toString());
        ticket.setPlateNumber(vehicle.getPalateNumber());
        ticket.setSpotId(spot.getId());
        ticket.setVehicleType(vehicle.getType());
        ticket.setSpotType(spot.getType());
        ticket.setEntryTime(Instant.now());
        ticket.setStatus(TicketStatus.ACTIVE);

        tickets.put(ticket.getId(), ticket);
        return ticket;
    }

    public PaymentReceipt unparkAndPay(Ticket ticket) {
        Ticket existingTicket = tickets.get(ticket.getId());
        if(existingTicket == null) {
            throw new RuntimeException("Ticket not found");
        }

        if(existingTicket.getStatus() != TicketStatus.ACTIVE) {
            throw new RuntimeException("Ticket is not active");
        }

        int amount = pricingStrategy.calculateAmount(ticket, Instant.now());

        PaymentReceipt receipt = new PaymentReceipt();
        receipt.setId(UUID.randomUUID().toString());
        receipt.setAmount(amount);
        receipt.setMode(PaymentMode.CASH); // Assuming cash payment for simplicity
        receipt.setStatus(PaymentStatus.SUCCESS);

        return receipt;
    }
}
