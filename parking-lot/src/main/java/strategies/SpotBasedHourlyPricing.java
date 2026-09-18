package strategies;

import models.ParkingSpotType;
import models.Ticket;

import java.time.Duration;
import java.time.Instant;

public class SpotBasedHourlyPricing implements PricingStrategy {
    @Override
    public int calculateAmount(Ticket ticket, Instant exitTime) {
        long duration = Duration.between(ticket.getEntryTime(), exitTime).toMinutes();

        // Calculate the number of hours, rounding up to the next hour
        long hours = (duration + 59) / 60; // Add 59 to round up to the next hour

        // Determine the rate based on the parking spot type
        int pricePerHour = getPricePerHour(ticket.getSpotType());
        return (int) (hours * pricePerHour);
    }

    private int getPricePerHour(ParkingSpotType spotType) {
        return switch (spotType) {
            case BIKE -> 20;
            case COMPACT -> 50;
            case LARGE -> 100;
            default -> throw new IllegalArgumentException("Unknown parking spot type: " + spotType);
        };
    }
}
