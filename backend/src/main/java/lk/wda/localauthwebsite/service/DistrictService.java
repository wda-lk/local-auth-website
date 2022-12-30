package lk.wda.localauthwebsite.service;

import lk.wda.localauthwebsite.model.District;
import lk.wda.localauthwebsite.repository.DistrictRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictService {
    private final DistrictRepository districtRepository;

    public DistrictService(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    public List<District> getAllDistricts() {
        return districtRepository.findAll();
    }

    public District addDistrict(District district) {
        return districtRepository.save(district);
    }
}
