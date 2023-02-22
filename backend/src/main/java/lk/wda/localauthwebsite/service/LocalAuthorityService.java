package lk.wda.localauthwebsite.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import lk.wda.localauthwebsite.exception.GoogleSheetConfigException;
import lk.wda.localauthwebsite.model.District;
import lk.wda.localauthwebsite.model.LocalAuthority;
import lk.wda.localauthwebsite.model.Province;
import lk.wda.localauthwebsite.repository.DistrictRepository;
import lk.wda.localauthwebsite.repository.LocalAuthorityRepository;
import lk.wda.localauthwebsite.repository.ProvinceRepository;
import lk.wda.localauthwebsite.utility.GoogleSheetsUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LocalAuthorityService {
    private static final Logger log = LoggerFactory.getLogger(LocalAuthorityService.class);
    private final ProvinceRepository provinceRepository;
    private final DistrictRepository districtRepository;
    private final LocalAuthorityRepository localAuthorityRepository;
    @Value("${google.sheet.id}")
    private String spreadsheet_id;

    public LocalAuthorityService(ProvinceRepository provinceRepository,
                                 DistrictRepository districtRepository,
                                 LocalAuthorityRepository localAuthorityRepository) {
        this.provinceRepository = provinceRepository;
        this.districtRepository = districtRepository;
        this.localAuthorityRepository = localAuthorityRepository;
    }

    private void saveProvince(Province province) {
        String provinceName = province.getNameEN();
        if (!provinceRepository.existsByNameEN(provinceName)) {
            provinceRepository.save(province);
            log.info(MessageFormat.format("Created Province with name: {0}.", provinceName));
        } else {
            log.warn(MessageFormat.format("Province already exists for name: {0}.", provinceName));
        }
    }

    private void saveDistrict(String provinceName, District district) {
        Optional<Province> optionalProvince = provinceRepository.findByNameEN(provinceName);
        Province province = optionalProvince.get();
        String districtName = district.getNameEN();
        if (!districtRepository.existsByNameEN(districtName)) {
            district.setProvince(province);
            districtRepository.save(district);
            log.info(MessageFormat.format("Created District with name: {0}.", districtName));
        } else {
            log.warn(MessageFormat.format("District already exists for name: {0}.", districtName));
        }
    }

    private void saveLocalAuthority(String districtName, LocalAuthority localAuthority) {
        Optional<District> optionalDistrict = districtRepository.findByNameEN(districtName);
        District district = optionalDistrict.get();
        localAuthority.setDistrict(district);
        localAuthorityRepository.save(localAuthority);
    }

    public void createAuthorities() throws GoogleSheetConfigException {
        String range = "local_authority!A:P";
        try {
            // clean table
            provinceRepository.deleteAll();
            // extract sheet data
            List<Map<String, String>> data = GoogleSheetsUtil.extractRawData(spreadsheet_id, range);
            for (Map<String, String> row : data) {
                // create province
                Province province = new Province(row.get("province_si"),
                                                 row.get("province_en"),
                                                 row.get("province_ta"));
                saveProvince(province);
                // create district
                District district = new District(row.get("district_si"),
                                                 row.get("district_en"),
                                                 row.get("district_ta"));
                saveDistrict(province.getNameEN(), district);
                // create local authority
                LocalAuthority localAuthority =
                        new LocalAuthority(row.get("name_si"), row.get("name_en"), row.get("name_ta"),
                                           row.get("view_statement_si"), row.get("view_statement_en"),
                                           row.get("view_statement_ta"), row.get("mission_statement_si"),
                                           row.get("mission_statement_en"), row.get("mission_statement_ta"),
                                           row.get("logo"), row.get("logo"));
                saveLocalAuthority(district.getNameEN(), localAuthority);
            }
        } catch (IOException e) {
            String message = "Failed to create local authorities, due to non existing credential files.";
            log.error(message);
            throw new GoogleSheetConfigException(message, e);
        } catch (GeneralSecurityException e) {
            String message = "Failed to create local authorities, due to a critical error.";
            log.error(message);
            throw new GoogleSheetConfigException(message, e);
        }
    }
}
