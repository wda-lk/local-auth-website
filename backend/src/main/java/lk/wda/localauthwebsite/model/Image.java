package lk.wda.localauthwebsite.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "image")
public class Image extends BaseModel {
    @Column(columnDefinition = "TEXT", nullable = false)
    private String url;

    @Column
    private String altText;

    @ManyToOne
    @JoinColumn(name = "local_authority_id")
    private LocalAuthority localAuthority;

    public Image() {
    }

    public Image(String url, String altText) {
        this.url = url;
        this.altText = altText;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAltText() {
        return altText;
    }

    public void setAltText(String altText) {
        this.altText = altText;
    }

    public LocalAuthority getLocalAuthority() {
        return localAuthority;
    }

    public void setLocalAuthority(LocalAuthority localAuthority) {
        this.localAuthority = localAuthority;
    }
}
