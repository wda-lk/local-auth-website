package lk.wda.localauthwebsite.controller;

import lk.wda.localauthwebsite.model.Contact;
import lk.wda.localauthwebsite.repository.ContactRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {
    private final ContactRepository contactRepository;

    public ContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Contact> getAllContactsByLocalAuthorityId(
            @RequestParam(name = "local-auth-id") long id,
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit) {
        return contactRepository.findAllByLocalAuthorityId(id, PageRequest.of(offset, limit));
    }
}

