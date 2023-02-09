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
    @Column(nullable = false)
    private String nameEN;

    @Column(nullable = false)
    private String nameSI;

    @Column(nullable = false)
    private String nameTA;

    @Column(columnDefinition = "TEXT")
    private String favicon;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String logo;

    @Column
    private String viewStatementEN;

    @Column
    private String viewStatementSI;

    @Column
    private String viewStatementTA;

    @Column
    private String missionStatementEN;

    @Column
    private String missionStatementSI;

    @Column
    private String missionStatementTA;

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

    public String getViewStatementEN() {
        return viewStatementEN;
    }

    public void setViewStatementEN(String viewStatementEN) {
        this.viewStatementEN = viewStatementEN;
    }

    public String getViewStatementSI() {
        return viewStatementSI;
    }

    public void setViewStatementSI(String viewStatementSI) {
        this.viewStatementSI = viewStatementSI;
    }

    public String getViewStatementTA() {
        return viewStatementTA;
    }

    public void setViewStatementTA(String viewStatementTA) {
        this.viewStatementTA = viewStatementTA;
    }

    public String getMissionStatementEN() {
        return missionStatementEN;
    }

    public void setMissionStatementEN(String missionStatementEN) {
        this.missionStatementEN = missionStatementEN;
    }

    public String getMissionStatementSI() {
        return missionStatementSI;
    }

    public void setMissionStatementSI(String missionStatementSI) {
        this.missionStatementSI = missionStatementSI;
    }

    public String getMissionStatementTA() {
        return missionStatementTA;
    }

    public void setMissionStatementTA(String missionStatementTA) {
        this.missionStatementTA = missionStatementTA;
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
