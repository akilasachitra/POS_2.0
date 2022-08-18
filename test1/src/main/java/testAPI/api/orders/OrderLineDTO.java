package testAPI.api.orders;

import lombok.Data;

@Data
public class OrderLineDTO {

    private long orderID;
    private long productID;
    private long quantity;
    private double price;

    public OrderLineDTO() {
    }

}
