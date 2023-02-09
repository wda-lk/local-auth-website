package lk.wda.localauthwebsite.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "contact")
public class Contact extends BaseModel {
    @Column(name = "name_en", nullable = false)
    private String nameEN;

    @Column(name = "name_si", nullable = false)
    private String nameSI;

    @Column(name = "name_ta", nullable = false)
    private String nameTA;

    @Column(name = "position_en")
    private String positionEN;

    @Column(name = "position_si")
    private String positionSI;

    @Column(name = "position_ta")
    private String positionTA;

    @Column(name = "unit_en")
    private String unitEN;

    @Column(name = "unit_si")
    private String unitSI;

    @Column(name = "unit_ta")
    private String unitTA;

    @Column(nullable = false)
    private String telNumber;

    @ManyToOne
    @JoinColumn(name = "local_authority_id")
    private LocalAuthority localAuthority;

    public Contact() {
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

    public String getPositionEN() {
        return positionEN;
    }

    public void setPositionEN(String positionEN) {
        this.positionEN = positionEN;
    }

    public String getPositionSI() {
        return positionSI;
    }

    public void setPositionSI(String positionSI) {
        this.positionSI = positionSI;
    }

    public String getPositionTA() {
        return positionTA;
    }

    public void setPositionTA(String positionTA) {
        this.positionTA = positionTA;
    }

    public String getUnitEN() {
        return unitEN;
    }

    public void setUnitEN(String unitEN) {
        this.unitEN = unitEN;
    }

    public String getUnitSI() {
        return unitSI;
    }

    public void setUnitSI(String unitSI) {
        this.unitSI = unitSI;
    }

    public String getUnitTA() {
        return unitTA;
    }

    public void setUnitTA(String unitTA) {
        this.unitTA = unitTA;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public LocalAuthority getLocalAuthority() {
        return localAuthority;
    }

    public void setLocalAuthority(LocalAuthority localAuthority) {
        this.localAuthority = localAuthority;
    }
}
