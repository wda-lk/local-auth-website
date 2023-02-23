package lk.wda.localauthwebsite.controller;

import lk.wda.localauthwebsite.model.District;
import lk.wda.localauthwebsite.repository.DistrictRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/districts")
public class DistrictController {
    private final DistrictRepository districtRepository;

    public DistrictController(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<District> getAllDistrictsByProvinceId(
            @RequestParam(name = "province-id") long id) {
        return districtRepository.findAllByProvinceId(id);
    }
}
