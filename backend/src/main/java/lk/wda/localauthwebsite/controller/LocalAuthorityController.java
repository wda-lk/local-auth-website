package lk.wda.localauthwebsite.controller;

import lk.wda.localauthwebsite.exception.GoogleSheetConfigException;
import lk.wda.localauthwebsite.model.LocalAuthority;
import lk.wda.localauthwebsite.repository.LocalAuthorityRepository;
import lk.wda.localauthwebsite.service.LocalAuthorityService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/local-auths")
public class LocalAuthorityController {
    private final LocalAuthorityService localAuthorityService;
    private final LocalAuthorityRepository localAuthorityRepository;

    public LocalAuthorityController(LocalAuthorityService localAuthorityService,
                                    LocalAuthorityRepository localAuthorityRepository) {
        this.localAuthorityService = localAuthorityService;
        this.localAuthorityRepository = localAuthorityRepository;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createAuthorities() throws GoogleSheetConfigException {
        localAuthorityService.createAuthorities();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LocalAuthority> getAllLocalAuthoritiesByDistrictId(
            @RequestParam(name = "district-id") long id) {
        return localAuthorityRepository.findAllByDistrictId(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<LocalAuthority> getLocalAuthorityById(
            @PathVariable long id) {
        return localAuthorityRepository.findById(id);
    }
}
