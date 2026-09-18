package strategies;

import models.ParkingSpotType;
import models.Ticket;

import java.time.Instant;

// For Testing purpose, we can implement a pricing strategy that calculates the amount based on the number of seconds parked.
// This is not a realistic pricing model but can be used for testing and demonstration purposes.
public class SpotBasedSecondlyPricing implements PricingStrategy {
    @Override
    public int calculateAmount(Ticket ticket, Instant exitTime) {
        long duration = java.time.Duration.between(ticket.getEntryTime(), exitTime).getSeconds();
        int pricePerSecond = getPricePerSecond(ticket.getSpotType());
        return (int) duration * pricePerSecond;
    }

    private int getPricePerSecond(ParkingSpotType spotType) {
        return switch (spotType) {
            case BIKE -> 1; // 20 per hour = 20/3600 per second
            case COMPACT -> 2; // 50 per hour = 50/3600
            case LARGE -> 3; // 100 per hour = 100/3600
            default -> throw new IllegalArgumentException("Unknown parking spot type: " + spotType);
        };
    }
}
