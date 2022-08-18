package testAPI.api.drug;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 *
 */
@RunWith(SpringRunner.class)
public class PharmacyTest2 {


    @TestConfiguration
    static class PharmacyServiceTestContextConfiguration {
        @Bean
        public PharmacyService getPharmacyService() {
            return new PharmacyService();
        }
    }

    @Autowired
    private PharmacyService pharmacyService;

    @MockBean
    private PharmacyRepo pharmacyRepository;

    @Before
    public void setUp() {
        Pharmacy item1 = new Pharmacy();
        item1.setId(1);
        item1.setCode("100");
        item1.setName("item 100");
        item1.setDescription("item 100 description");
        item1.setPrice(500);
        Mockito.when(pharmacyRepository.findByNameContains(item1.getName(), PageRequest.of(1, pharmacyService.pageSize))).thenReturn(List.of(item1));
        Page<Pharmacy> page = new PageImpl<>(List.of(item1));
        Mockito.when(pharmacyRepository.findAll(PageRequest.of(1, pharmacyService.pageSizeExtraLong))).thenReturn(page);
    }

    @Test
    public void searchPharmacyTest() {
        String itemName = "item 100";
        List<PharmacyDTO> products = pharmacyService.getProducts(itemName, 1);
        Assertions.assertNotNull(products);
        Assertions.assertSame(products.get(0).getName(), itemName);
    }

    @Test
    public void getAllPharmacyTest() {
        String itemName = "item 100";
        List<PharmacyDTO> products1 = pharmacyService.getProducts(1);
        Assertions.assertNotNull(products1);
        Assertions.assertSame(products1.get(0).getName(), itemName);
    }


}
