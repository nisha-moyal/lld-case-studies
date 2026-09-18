package models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Vehicle {
    private String palateNumber;
    private VehicleType type;

    public Vehicle(String palateNumber, VehicleType type) {
        this.palateNumber = palateNumber;
        this.type = type;
    }
}
