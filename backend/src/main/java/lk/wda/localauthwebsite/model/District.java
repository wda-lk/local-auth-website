package lk.wda.localauthwebsite.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "district")
@JsonIgnoreProperties({"localAuthorities"})
public class District extends BaseModel {
    @Column(name = "name_en", nullable = false)
    private String nameEN;

    @Column(name = "name_si", nullable = false)
    private String nameSI;

    @Column(name = "name_ta", nullable = false)
    private String nameTA;

    @OneToMany(mappedBy = "district")
    private List<LocalAuthority> localAuthorities;

    @ManyToOne
    @JoinColumn(name = "province_id")
    private Province province;

    public District() {
    }

    public District(String nameEN, String nameSI, String nameTA) {
        this.nameEN = nameEN;
        this.nameSI = nameSI;
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

    public List<LocalAuthority> getLocalAuthorities() {
        return localAuthorities;
    }

    public void setLocalAuthorities(List<LocalAuthority> localAuthorities) {
        this.localAuthorities = localAuthorities;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }
}
