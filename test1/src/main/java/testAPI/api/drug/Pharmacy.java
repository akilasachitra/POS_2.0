package testAPI.api.drug;

import com.fasterxml.jackson.annotation.JsonIgnore;
import testAPI.api.orders.OrderLine;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Pharmacy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String code;
    private String name;
    private String description;
    private double price;

    @OneToMany(mappedBy = "pharmacy")
    @JsonIgnore
    private List<OrderLine> orderLines;

    public Pharmacy() {
    }
}
