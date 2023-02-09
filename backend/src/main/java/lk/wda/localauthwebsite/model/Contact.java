package lk.wda.localauthwebsite.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "contact")
public class Contact extends BaseModel {
    @Column(nullable = false)
    private String nameEN;

    @Column(nullable = false)
    private String nameSI;

    @Column(nullable = false)
    private String nameTA;

    @Column
    private String positionEN;

    @Column
    private String positionSI;

    @Column
    private String positionTA;

    @Column
    private String unitEN;

    @Column
    private String unitSI;

    @Column
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
