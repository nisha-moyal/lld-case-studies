import models.*;
import strategies.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        SpotAllocationStrategy allocationStrategy = new NearestSpotAllocation();
        PricingStrategy strategy = new SpotBasedHourlyPricing();
        PricingStrategy strategy1 = new SpotBasedSecondlyPricing();

        ParkingLot parkingLot = new ParkingLot(allocationStrategy, strategy);

        parkingLot.addSpot(new ParkingSpot("A1", ParkingSpotType.BIKE));
        parkingLot.addSpot(new ParkingSpot("A2", ParkingSpotType.COMPACT));
        parkingLot.addSpot(new ParkingSpot("A3", ParkingSpotType.LARGE));

        System.out.println("Parking Lot initialized with 3 spots.");

        Vehicle vehicle = new Vehicle("KA-01-HH-1234", VehicleType.CAR);

        System.out.println("Let's park a vehicle with plate number: " + vehicle.getPalateNumber());
        Ticket ticket = parkingLot.parkVehicle(vehicle);
        System.out.println("Vehicle parked. Ticket generated: " + ticket.getId());

        Thread.sleep(7000); // Simulate some time passing (5 seconds)

        System.out.println("Unparking the vehicle and calculating payment.");
        PaymentReceipt receipt = parkingLot.unparkAndPay(ticket);
        System.out.println("Payment receipt generated. Amount: " + receipt.getAmount() + ", Paid at: " + receipt.getMode());
    }
}