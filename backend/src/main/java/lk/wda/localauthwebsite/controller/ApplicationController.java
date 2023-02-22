package lk.wda.localauthwebsite.controller;

import java.util.List;

import lk.wda.localauthwebsite.exception.GoogleSheetConfigException;
import lk.wda.localauthwebsite.exception.NoResourceFoundException;
import lk.wda.localauthwebsite.model.Application;
import lk.wda.localauthwebsite.repository.ApplicationRepository;
import lk.wda.localauthwebsite.service.ApplicationService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {
    private final ApplicationService applicationService;
    private final ApplicationRepository applicationRepository;

    public ApplicationController(ApplicationService applicationService,
                                 ApplicationRepository applicationRepository) {
        this.applicationService = applicationService;
        this.applicationRepository = applicationRepository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Application> getAllApplicationsByLocalAuthorityId(@RequestParam(name = "local-auth-id") long id,
                                                                  @RequestParam(defaultValue = "0") int offset,
                                                                  @RequestParam(defaultValue = "10") int limit) {
        return applicationRepository.findAllByLocalAuthorityId(id, PageRequest.of(offset, limit));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<Application> createApplications() throws GoogleSheetConfigException, NoResourceFoundException {
        return applicationService.createApplications();
    }
}
