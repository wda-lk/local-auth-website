package lk.wda.localauthwebsite.repository;

import lk.wda.localauthwebsite.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    Page<Contact> findAllByLocalAuthorityId(Long localAuthorityId, Pageable pageable);

}
