package lk.wda.localauthwebsite.controller;

import lk.wda.localauthwebsite.model.Province;
import lk.wda.localauthwebsite.repository.ProvinceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/provinces")
public class ProvinceController {
    private final ProvinceRepository provinceRepository;

    public ProvinceController(ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Province> getAllProvince() {
        return provinceRepository.findAll();
    }
}
