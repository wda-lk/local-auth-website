package lk.wda.localauthwebsite.controller;

import lk.wda.localauthwebsite.model.District;
import lk.wda.localauthwebsite.service.DistrictService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/districts")
public class DistrictController {
    private final DistrictService districtService;

    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<District> getAllDistricts() {
        return districtService.getAllDistricts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public District createDistrict(@Valid @RequestBody District district) {
        return districtService.addDistrict(district);
    }
}
