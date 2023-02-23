package lk.wda.localauthwebsite.repository;

import java.util.List;
import java.util.Optional;

import lk.wda.localauthwebsite.model.District;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {
    List<District> findAllByProvinceId(Long provinceId);

    boolean existsByNameEN(String name);

    Optional<District> findByNameEN(String name);
}
