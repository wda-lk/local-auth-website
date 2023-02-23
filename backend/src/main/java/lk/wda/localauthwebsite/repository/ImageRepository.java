package lk.wda.localauthwebsite.repository;

import java.util.List;

import lk.wda.localauthwebsite.model.Image;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findAllByLocalAuthorityId(Long localAuthorityId);
}
