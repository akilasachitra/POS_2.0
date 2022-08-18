package testAPI.api.drug;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import testAPI.api.general.ServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PharmacyService extends ServiceImpl {

    @Autowired
    private PharmacyRepo pharmacyRepo;
//    @Autowired
//    public PharmacyService(PharmacyRepo pharmacyRepo) {
//        this.pharmacyRepo = pharmacyRepo;
//    }

    public PharmacyService() {
    }

    public List<PharmacyDTO> getProducts(int page) {
        Page<Pharmacy> all = pharmacyRepo.findAll(PageRequest.of(page, pageSizeExtraLong));
        return all.stream().map(PharmacyDTO::new).collect(Collectors.toList());
    }

    public PharmacyDTO getProduct(Long id) {
        Optional<Pharmacy> byId = pharmacyRepo.findById(id);
        if (byId.isPresent())
            return new PharmacyDTO(byId.get());

        throw new PharmacyNotFoundException("Pharmacy not found for id " + id);
    }

    public void deleteProduct(Long id) {
        Optional<Pharmacy> byId = pharmacyRepo.findById(id);
        if (byId.isPresent()) {
            pharmacyRepo.deleteById(id);
            return;
        }

        throw new PharmacyNotFoundException("Pharmacy not found for id " + id);
    }

    public List<PharmacyDTO> getProducts(String name, int page) {
        List<Pharmacy> all = pharmacyRepo.findByNameContains(name, PageRequest.of(page, pageSize));//, Sort.by(Sort.Direction.DESC,"name"
        return all.stream().map(PharmacyDTO::new).collect(Collectors.toList());
    }


    public long saveProduct(PharmacyDTO pharmacyDTO) {
        Pharmacy pharmacy = pharmacyRepo.save(pharmacyDTO.getProduct());
        return pharmacy.getId();
    }

}
