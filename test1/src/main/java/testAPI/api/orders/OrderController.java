package testAPI.api.orders;

import testAPI.api.drug.Pharmacy;
import testAPI.api.drug.PharmacyRepo;
import testAPI.api.user.User;
import testAPI.api.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class OrderController {

    private final OrderRepo orderRepo;  //constructor injection is used instead of field injection
    private final OrderLineRepo orderLineRepo;
    private final UserRepo userRepo;
    private final PharmacyRepo pharmacyRepo;


    @Autowired
    public OrderController(OrderRepo orderRepo, OrderLineRepo orderLineRepo, UserRepo userRepo, PharmacyRepo pharmacyRepo) {
        this.orderRepo = orderRepo;
        this.orderLineRepo = orderLineRepo;
        this.userRepo = userRepo;
        this.pharmacyRepo = pharmacyRepo;
    }

    @GetMapping("/orders")
    public List<Order> getOrders() {
        return orderRepo.findAll();
    }

    @PostMapping("neworder/{userID}/")
    public ResponseEntity<Object> addOrder(@RequestBody Order order, @PathVariable long userID) {
        if (userID > 0) {
            Optional<User> byId = userRepo.findById(userID);
            if (byId.isPresent()) {
                order.setUser(byId.get());
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        Order save = orderRepo.save(order);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("addproduct/{userID}/")
    public ResponseEntity<Object> addOrderLines(@RequestBody OrderLineDTO orderLineDTO, @PathVariable long userID) {
        if (userID > 0) {
            Optional<Order> existingOrderById = orderRepo.findById(orderLineDTO.getOrderID());
            if (existingOrderById.isEmpty())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // todo exception oder not found

            Optional<Pharmacy> product = pharmacyRepo.findById(orderLineDTO.getProductID());
            if (product.isEmpty())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // todo exception product not found


            OrderLine orderLine = new OrderLine(existingOrderById.get(),product.get());
            orderLine.setQuantity(orderLineDTO.getQuantity());
            orderLine.setPrice(orderLineDTO.getPrice());

            OrderLine save = orderLineRepo.save(orderLine);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // todo execption oder not found
    }

    @PostMapping("addorderwithproducts/{userID}/")
    public ResponseEntity<Object> addOrderWithProducts( @PathVariable long userID) {
        Order order =  new Order();
        if (userID > 0) {
            Optional<User> byId = userRepo.findById(userID);
            if (byId.isPresent()) {
                order.setUser(byId.get());
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            order.setDate(new Date());

            Optional<Pharmacy> product1 = pharmacyRepo.findById(8L);
            Optional<Pharmacy> product2 = pharmacyRepo.findById(11L);

            OrderLine line1 = new OrderLine(order,product1.get());
            line1.setOrder(order);
            line1.setPrice(100);
            line1.setQuantity(1);

            OrderLine line2 = new OrderLine(order,product2.get());
            line1.setOrder(order);
            line2.setPrice(200);
            line2.setQuantity(5);
            order.setOrderLines(Arrays.asList(line1,line2));

            Order save = orderRepo.save(order);
            return new ResponseEntity<>(HttpStatus.CREATED);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // todo exception oder not found
    }

}
