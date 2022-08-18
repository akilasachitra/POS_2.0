package testAPI.api.drug;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PharmacyController {

    private final PharmacyService pharmacyService; //constructor injection is used instead of field injection

    @Autowired
    public PharmacyController(PharmacyService pharmacyService) {
        this.pharmacyService = pharmacyService;
    }

    @GetMapping("/pharmacies")
    public List<PharmacyDTO> getProducts(@RequestParam int page) {
        return pharmacyService.getProducts(page);
    }

    @GetMapping("/pharmacies/{id}")
    public PharmacyDTO getProduct(@PathVariable Long id) {
        return pharmacyService.getProduct(id);
    }

    @DeleteMapping("/pharmacies/{id}")
    public void deleteProduct(@PathVariable Long id) {
        pharmacyService.deleteProduct(id);
    }

    @GetMapping("/findpharmacies")
    public List<PharmacyDTO> searchPharmacy(@RequestParam String name, @RequestParam int page) {
        return pharmacyService.getProducts(name, page);
    }


    @PostMapping("/savePharmacy")
    public ResponseEntity<Object> saveProduct(@RequestBody PharmacyDTO pharmacyDTO) {
        long productID = pharmacyService.saveProduct(pharmacyDTO);
        return ResponseEntity
                .created(ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(productID)
                        .toUri())
                .build();
    }


}
