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
@Table(name = "local_authority")
@JsonIgnoreProperties({"images", "applications", "contacts"})
public class LocalAuthority extends BaseModel {
    @Column(name = "name_si", nullable = false)
    private String nameSI;

    @Column(name = "name_en", nullable = false)
    private String nameEN;

    @Column(name = "name_ta", nullable = false)
    private String nameTA;

    @Column(name = "viewStatement_si", columnDefinition = "TEXT")
    private String viewStatementSI;

    @Column(name = "viewStatement_en", columnDefinition = "TEXT")
    private String viewStatementEN;

    @Column(name = "viewStatement_ta", columnDefinition = "TEXT")
    private String viewStatementTA;

    @Column(name = "missionStatement_si", columnDefinition = "TEXT")
    private String missionStatementSI;

    @Column(name = "missionStatement_en", columnDefinition = "TEXT")
    private String missionStatementEN;

    @Column(name = "missionStatement_ta", columnDefinition = "TEXT")
    private String missionStatementTA;

    @Column(columnDefinition = "TEXT")
    private String favicon;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String logo;

    @ManyToOne
    @JoinColumn(name = "district_id")
    private District district;

    @OneToMany(mappedBy = "localAuthority")
    private List<Image> images;

    @OneToMany(mappedBy = "localAuthority")
    private List<Application> applications;

    @OneToMany(mappedBy = "localAuthority")
    private List<Contact> contacts;

    public LocalAuthority() {
    }

    public LocalAuthority(String nameSI, String nameEN, String nameTA,
                          String viewStatementSI, String viewStatementEN, String viewStatementTA,
                          String missionStatementSI, String missionStatementEN, String missionStatementTA,
                          String favicon, String logo) {
        this.nameSI = nameSI;
        this.nameEN = nameEN;
        this.nameTA = nameTA;
        this.viewStatementSI = viewStatementSI;
        this.viewStatementEN = viewStatementEN;
        this.viewStatementTA = viewStatementTA;
        this.missionStatementSI = missionStatementSI;
        this.missionStatementEN = missionStatementEN;
        this.missionStatementTA = missionStatementTA;
        this.favicon = favicon;
        this.logo = logo;
    }

    public String getNameSI() {
        return nameSI;
    }

    public void setNameSI(String nameSI) {
        this.nameSI = nameSI;
    }

    public String getNameEN() {
        return nameEN;
    }

    public void setNameEN(String nameEN) {
        this.nameEN = nameEN;
    }

    public String getNameTA() {
        return nameTA;
    }

    public void setNameTA(String nameTA) {
        this.nameTA = nameTA;
    }

    public String getViewStatementSI() {
        return viewStatementSI;
    }

    public void setViewStatementSI(String viewStatementSI) {
        this.viewStatementSI = viewStatementSI;
    }

    public String getViewStatementEN() {
        return viewStatementEN;
    }

    public void setViewStatementEN(String viewStatementEN) {
        this.viewStatementEN = viewStatementEN;
    }

    public String getViewStatementTA() {
        return viewStatementTA;
    }

    public void setViewStatementTA(String viewStatementTA) {
        this.viewStatementTA = viewStatementTA;
    }

    public String getMissionStatementSI() {
        return missionStatementSI;
    }

    public void setMissionStatementSI(String missionStatementSI) {
        this.missionStatementSI = missionStatementSI;
    }

    public String getMissionStatementEN() {
        return missionStatementEN;
    }

    public void setMissionStatementEN(String missionStatementEN) {
        this.missionStatementEN = missionStatementEN;
    }

    public String getMissionStatementTA() {
        return missionStatementTA;
    }

    public void setMissionStatementTA(String missionStatementTA) {
        this.missionStatementTA = missionStatementTA;
    }

    public String getFavicon() {
        return favicon;
    }

    public void setFavicon(String favicon) {
        this.favicon = favicon;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
