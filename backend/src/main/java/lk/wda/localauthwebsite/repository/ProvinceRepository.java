package lk.wda.localauthwebsite.repository;

import lk.wda.localauthwebsite.model.Province;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {
    boolean existsByNameEN(String name);

    Optional<Province> findByNameEN(String name);
}
