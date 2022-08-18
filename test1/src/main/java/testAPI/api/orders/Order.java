package testAPI.api.orders;

import lombok.Getter;
import lombok.Setter;
import testAPI.api.user.User;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.Past;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Prescription")
@Transactional
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Past(message = "Past order date is not allowed ")
    private Date date;

    @OneToOne()
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderLine> orderLines;

    public Order() {
    }
// user
    // OrderedProducts (order line include order id and product id) is many to many table ?? (

//  add product   public class AddToCartDto {
//        private Integer id;
//        private @NotNull Integer userId;
//        private @NotNull Long productId;
//        private @NotNull Integer quantity;

    // todo check and return order summery object (items list , total)


}
