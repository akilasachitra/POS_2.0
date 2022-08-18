package testAPI.api.orders;

import com.fasterxml.jackson.annotation.JsonBackReference;
import testAPI.api.drug.Pharmacy;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class OrderLinePK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Order order;

    @ManyToOne
    @JoinColumn(name = "pharmacy_id")
    @JsonBackReference
    private Pharmacy pharmacy;

    public OrderLinePK() {
    }

    public OrderLinePK(Order order, Pharmacy pharmacy) {
        this.order = order;
        this.pharmacy = pharmacy;
    }
}
