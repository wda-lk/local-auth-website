package lk.wda.localauthwebsite.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "district")
@JsonIgnoreProperties({"localAuthorities"})
public class District extends BaseModel {
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "district")
    private List<LocalAuthority> localAuthorities;

    public District() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LocalAuthority> getLocalAuthorities() {
        return localAuthorities;
    }

    public void setLocalAuthorities(
            List<LocalAuthority> localAuthorities) {
        this.localAuthorities = localAuthorities;
    }
}
