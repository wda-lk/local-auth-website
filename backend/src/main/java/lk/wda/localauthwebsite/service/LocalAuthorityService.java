package lk.wda.localauthwebsite.service;

import com.google.api.services.sheets.v4.Sheets;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.MessageFormat;
import java.util.List;
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
    @Value("${google.sheet.id}")
    private String spreadsheet_id;
    private final ProvinceRepository provinceRepository;
    private final DistrictRepository districtRepository;
    private final LocalAuthorityRepository localAuthorityRepository;

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

    public void initialise() throws GoogleSheetConfigException {
        String range = "local_authority!A:P";
        try {
            Sheets sheetsService = GoogleSheetsUtil.getService();
            List<List<Object>> rawData = sheetsService.spreadsheets()
                                                      .values()
                                                      .get(spreadsheet_id, range)
                                                      .execute()
                                                      .getValues();
            // Clean database
            provinceRepository.deleteAll();
            for (int i = 1; i < rawData.size(); i++) {
                List<Object> rawRow = rawData.get(i);
                Province province = new Province(rawRow.get(0).toString(),
                                                 rawRow.get(1).toString(),
                                                 rawRow.get(2).toString());
                saveProvince(province);
                District district = new District(rawRow.get(3).toString(),
                                                 rawRow.get(4).toString(),
                                                 rawRow.get(5).toString());
                saveDistrict(province.getNameEN(), district);
                LocalAuthority localAuthority =
                        new LocalAuthority(rawRow.get(6).toString(), rawRow.get(7).toString(), rawRow.get(8).toString(),
                                           rawRow.get(9).toString(), rawRow.get(10).toString(),
                                           rawRow.get(11).toString(), rawRow.get(12).toString(),
                                           rawRow.get(13).toString(), rawRow.get(14).toString(),
                                           rawRow.get(15).toString(), rawRow.get(15).toString());
                saveLocalAuthority(district.getNameEN(), localAuthority);
            }
        } catch (IOException e) {
            String message = "Failed to locate credential files.";
            log.error(message);
            throw new GoogleSheetConfigException(message, e);
        } catch (GeneralSecurityException e) {
            String message = "Failed due to a critical error.";
            log.error(message);
            throw new GoogleSheetConfigException(message, e);
        }
    }
}
