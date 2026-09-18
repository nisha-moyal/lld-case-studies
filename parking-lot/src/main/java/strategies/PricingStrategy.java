package strategies;

import models.Ticket;

import java.time.Instant;

public interface PricingStrategy {
    public int calculateAmount(Ticket ticket, Instant exitTime);
}
