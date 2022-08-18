package testAPI.api.drug;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PharmacyRepo extends JpaRepository<Pharmacy, Long> {

    Page<Pharmacy> findAll(Pageable pageable);

    List<Pharmacy> findByNameContains(String name, Pageable pageable);

}
