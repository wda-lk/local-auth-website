package lk.wda.localauthwebsite.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "province")
public class Province extends BaseModel {
    @Column(nullable = false)
    private String nameEN;

    @Column(nullable = false)
    private String nameSI;

    @Column(nullable = false)
    private String nameTA;

    @OneToMany(mappedBy = "province")
    private List<District> districts;

    public Province() {
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
