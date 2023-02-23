package lk.wda.localauthwebsite.controller;

import java.util.List;

import lk.wda.localauthwebsite.model.LocalAuthority;
import lk.wda.localauthwebsite.repository.LocalAuthorityRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/districts")
public class DistrictController {
    private final LocalAuthorityRepository localAuthorityRepository;

    public DistrictController(LocalAuthorityRepository localAuthorityRepository) {
        this.localAuthorityRepository = localAuthorityRepository;
    }

    @GetMapping("/{id}/local-auths")
    @ResponseStatus(HttpStatus.OK)
    public List<LocalAuthority> getAllLocalAuthorities(@PathVariable long id) {
        return localAuthorityRepository.findAllByDistrictId(id);
    }
}
