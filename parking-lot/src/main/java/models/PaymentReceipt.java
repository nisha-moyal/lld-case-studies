package models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentReceipt {
    private String id;
    private PaymentStatus status;
    private int amount;
    private PaymentMode mode;
}
