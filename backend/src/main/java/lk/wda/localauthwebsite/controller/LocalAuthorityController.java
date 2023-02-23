package lk.wda.localauthwebsite.controller;

import java.util.List;
import java.util.Optional;

import lk.wda.localauthwebsite.exception.GoogleSheetConfigException;
import lk.wda.localauthwebsite.model.Application;
import lk.wda.localauthwebsite.model.Contact;
import lk.wda.localauthwebsite.model.Image;
import lk.wda.localauthwebsite.model.LocalAuthority;
import lk.wda.localauthwebsite.repository.ApplicationRepository;
import lk.wda.localauthwebsite.repository.ContactRepository;
import lk.wda.localauthwebsite.repository.ImageRepository;
import lk.wda.localauthwebsite.repository.LocalAuthorityRepository;
import lk.wda.localauthwebsite.service.LocalAuthorityService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/local-auths")
public class LocalAuthorityController {
    private final LocalAuthorityService localAuthorityService;
    private final LocalAuthorityRepository localAuthorityRepository;
    private final ImageRepository imageRepository;
    private final ContactRepository contactRepository;
    private final ApplicationRepository applicationRepository;

    public LocalAuthorityController(LocalAuthorityService localAuthorityService,
                                    LocalAuthorityRepository localAuthorityRepository,
                                    ImageRepository imageRepository, ContactRepository contactRepository,
                                    ApplicationRepository applicationRepository) {
        this.localAuthorityService = localAuthorityService;
        this.localAuthorityRepository = localAuthorityRepository;
        this.imageRepository = imageRepository;
        this.contactRepository = contactRepository;
        this.applicationRepository = applicationRepository;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<LocalAuthority> getLocalAuthorityById(@PathVariable long id) {
        return localAuthorityRepository.findById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createLocalAuthorities() throws GoogleSheetConfigException {
        localAuthorityService.createAuthorities();
    }

    @GetMapping("/{id}/images")
    @ResponseStatus(HttpStatus.OK)
    public List<Image> getAllImages(@PathVariable long id) {
        return imageRepository.findAllByLocalAuthorityId(id);
    }

    @GetMapping("/{id}/applications")
    @ResponseStatus(HttpStatus.OK)
    public Page<Application> getAllApplications(
            @PathVariable long id,
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit) {
        return applicationRepository.findAllByLocalAuthorityId(id, PageRequest.of(offset, limit));
    }

    @GetMapping("/{id}/contacts")
    @ResponseStatus(HttpStatus.OK)
    public Page<Contact> getAllContacts(
            @PathVariable long id,
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit) {
        return contactRepository.findAllByLocalAuthorityId(id, PageRequest.of(offset, limit));
    }
}
