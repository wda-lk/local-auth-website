package lk.wda.localauthwebsite.repository;

import lk.wda.localauthwebsite.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findAllByLocalAuthorityId(Long localAuthorityId);

}
