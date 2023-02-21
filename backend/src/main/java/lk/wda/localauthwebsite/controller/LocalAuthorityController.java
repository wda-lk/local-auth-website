package lk.wda.localauthwebsite.controller;

import lk.wda.localauthwebsite.model.District;
import lk.wda.localauthwebsite.model.LocalAuthority;
import lk.wda.localauthwebsite.model.Province;
import lk.wda.localauthwebsite.repository.DistrictRepository;
import lk.wda.localauthwebsite.repository.LocalAuthorityRepository;
import lk.wda.localauthwebsite.repository.ProvinceRepository;
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
    private final ProvinceRepository provinceRepository;
    private final DistrictRepository districtRepository;

    public LocalAuthorityController(LocalAuthorityService localAuthorityService,
                                    LocalAuthorityRepository localAuthorityRepository,
                                    ProvinceRepository provinceRepository,
                                    DistrictRepository districtRepository) {
        this.localAuthorityService = localAuthorityService;
        this.localAuthorityRepository = localAuthorityRepository;
        this.provinceRepository = provinceRepository;
        this.districtRepository = districtRepository;
    }

    @GetMapping("/provinces")
    @ResponseStatus(HttpStatus.OK)
    public List<Province> getAllProvinces() {
        return provinceRepository.findAll();
    }

    @GetMapping("/provinces/{id}/districts")
    @ResponseStatus(HttpStatus.OK)
    public List<District> getAllDistrictsByProvinceId(
            @PathVariable long id) {
        return districtRepository.findAllByProvinceId(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LocalAuthority> getAllLocalAuthoritiesByDistrictId(
            @RequestParam(name = "district-id") long id) {
        return localAuthorityRepository.findAllByDistrictId(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void initialiseLocalAuthorities() {
        localAuthorityService.initialiseLocalAuthorities();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<LocalAuthority> getLocalAuthorityById(
            @PathVariable long id) {
        return localAuthorityRepository.findById(id);
    }
}
