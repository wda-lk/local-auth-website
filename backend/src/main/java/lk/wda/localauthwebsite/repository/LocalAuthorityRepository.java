package lk.wda.localauthwebsite.repository;

import lk.wda.localauthwebsite.model.LocalAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocalAuthorityRepository extends JpaRepository<LocalAuthority, Long> {
    List<LocalAuthority> findAllByDistrictId(Long districtId);
}
