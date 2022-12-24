package lk.wda.localauthwebsite.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "local_authority")
public class LocalAuthority extends BaseModel {
    @Column(nullable = false)
    private String district;

    @Column(nullable = false)
    private String name;

    @Column
    private String favicon;

    @Column
    private String viewStatement;

    @Column
    private String missionStatement;

    @OneToMany(mappedBy = "localAuthority")
    private List<Image> images;

    @OneToMany(mappedBy = "localAuthority")
    private List<Application> applications;

    @OneToMany(mappedBy = "localAuthority")
    private List<Contact> contacts;

    public LocalAuthority() {
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFavicon() {
        return favicon;
    }

    public void setFavicon(String favicon) {
        this.favicon = favicon;
    }

    public String getViewStatement() {
        return viewStatement;
    }

    public void setViewStatement(String viewStatement) {
        this.viewStatement = viewStatement;
    }

    public String getMissionStatement() {
        return missionStatement;
    }

    public void setMissionStatement(String missionStatement) {
        this.missionStatement = missionStatement;
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
