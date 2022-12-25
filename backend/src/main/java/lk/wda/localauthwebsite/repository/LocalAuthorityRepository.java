package lk.wda.localauthwebsite.repository;

import lk.wda.localauthwebsite.model.LocalAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalAuthorityRepository extends JpaRepository<LocalAuthority, Long> {
}
