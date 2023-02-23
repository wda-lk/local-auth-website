package lk.wda.localauthwebsite.repository;

import java.util.Optional;

import lk.wda.localauthwebsite.model.Province;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {
    boolean existsByNameEN(String name);

    Optional<Province> findByNameEN(String name);
}
