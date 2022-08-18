package testAPI.api.orders;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import testAPI.api.drug.Pharmacy;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class OrderLine implements Serializable {


    @EmbeddedId
    @JsonIgnore
    private OrderLinePK orderLinePK;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "order_id")
    @MapsId("order")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "pharmacy_id")
    @MapsId("pharmacy")
    @JsonIdentityInfo(property = "id", generator = ObjectIdGenerators.PropertyGenerator.class)
    private Pharmacy pharmacy;


    private long quantity;

    private double price;


    public OrderLine() {
    }

    public OrderLine(Order order, Pharmacy pharmacy) {
        orderLinePK = new OrderLinePK(order, pharmacy);
    }

    @Transient
    public Pharmacy getPharmacy() {
        return this.orderLinePK.getPharmacy();
    }

    @Transient
    public Double getTotalPrice() {
        return quantity * price;
    }

    @Transactional
    @JsonIgnore
    public Order getOrder() {
        return order;
    }
}
