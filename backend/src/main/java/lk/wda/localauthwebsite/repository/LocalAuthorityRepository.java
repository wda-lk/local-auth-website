package lk.wda.localauthwebsite.repository;

import java.util.List;
import java.util.Optional;

import lk.wda.localauthwebsite.model.LocalAuthority;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalAuthorityRepository extends JpaRepository<LocalAuthority, Long> {
    Optional<LocalAuthority> findByNameEN(String name);

    List<LocalAuthority> findAllByDistrictId(Long districtId);
}
