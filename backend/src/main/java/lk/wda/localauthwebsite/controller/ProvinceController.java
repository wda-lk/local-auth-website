package lk.wda.localauthwebsite.controller;

import lk.wda.localauthwebsite.model.District;
import lk.wda.localauthwebsite.model.Province;
import lk.wda.localauthwebsite.repository.DistrictRepository;
import lk.wda.localauthwebsite.repository.ProvinceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/provinces")
public class ProvinceController {
    private final ProvinceRepository provinceRepository;
    private final DistrictRepository districtRepository;

    public ProvinceController(ProvinceRepository provinceRepository,
                              DistrictRepository districtRepository) {
        this.provinceRepository = provinceRepository;
        this.districtRepository = districtRepository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Province> getAllProvinces() {
        return provinceRepository.findAll();
    }

    @GetMapping("/{id}/districts")
    @ResponseStatus(HttpStatus.OK)
    public List<District> getAllDistricts(@PathVariable long id) {
        return districtRepository.findAllByProvinceId(id);
    }
}
