package testAPI.api.drug;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class PharmacyDTO {
    private long id;
    private String code;
    private String name;
    private String description;
    private double price;

    public PharmacyDTO() {
    }

    public PharmacyDTO(String code, String name, String description, double price) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    @JsonIgnore
    public PharmacyDTO(Pharmacy pharmacy) {
        this.id = pharmacy.getId();
        this.code = pharmacy.getCode();
        this.name = pharmacy.getName();
        this.description = pharmacy.getDescription();
        this.price = pharmacy.getPrice();
    }

    @JsonIgnore
    public Pharmacy getProduct() {
        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setId(id);
        pharmacy.setCode(code);
        pharmacy.setName(name);
        pharmacy.setDescription(description);
        pharmacy.setPrice(price);
        return pharmacy;
    }

}
