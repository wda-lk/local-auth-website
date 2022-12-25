package lk.wda.localauthwebsite.repository;

import lk.wda.localauthwebsite.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    List<Application> findAllByLocalAuthorityId(Long localAuthorityId);

}
