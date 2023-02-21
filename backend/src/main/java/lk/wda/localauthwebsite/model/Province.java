package lk.wda.localauthwebsite.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "province")
@JsonIgnoreProperties({"createdAt", "updatedAt", "districts"})
public class Province extends BaseModel {
    @Column(name = "name_en", nullable = false)
    private String nameEN;

    @Column(name = "name_si", nullable = false)
    private String nameSI;

    @Column(name = "name_ta", nullable = false)
    private String nameTA;

    @OneToMany(mappedBy = "province", cascade = CascadeType.REMOVE)
    private List<District> districts;

    public Province() {
    }

    public Province(String nameSI, String nameEN, String nameTA) {
        this.nameSI = nameSI;
        this.nameEN = nameEN;
        this.nameTA = nameTA;
    }

    public String getNameEN() {
        return nameEN;
    }

    public void setNameEN(String nameEN) {
        this.nameEN = nameEN;
    }

    public String getNameSI() {
        return nameSI;
    }

    public void setNameSI(String nameSI) {
        this.nameSI = nameSI;
    }

    public String getNameTA() {
        return nameTA;
    }

    public void setNameTA(String nameTA) {
        this.nameTA = nameTA;
    }

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }
}
