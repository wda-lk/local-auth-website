package lk.wda.localauthwebsite.controller;

import lk.wda.localauthwebsite.model.Application;
import lk.wda.localauthwebsite.repository.ApplicationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {
    private final ApplicationRepository applicationRepository;

    public ApplicationController(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Application> getAllApplicationsByLocalAuthorityId(
            @RequestParam(name = "local-auth-id") long id,
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit) {
        return applicationRepository.findAllByLocalAuthorityId(id, PageRequest.of(offset, limit));
    }
}
