package lk.wda.localauthwebsite.repository;

import lk.wda.localauthwebsite.model.Application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Page<Application> findAllByLocalAuthorityId(Long localAuthorityId, Pageable pageable);
}
