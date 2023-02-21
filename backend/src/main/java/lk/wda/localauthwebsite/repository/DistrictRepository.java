package lk.wda.localauthwebsite.repository;

import lk.wda.localauthwebsite.model.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {
    List<District> findAllByProvinceId(Long provinceId);

    boolean existsByNameEN(String name);

    Optional<District> findByNameEN(String name);
}
