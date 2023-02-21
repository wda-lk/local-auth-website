package lk.wda.localauthwebsite.service;

import com.google.api.services.sheets.v4.Sheets;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.MessageFormat;
import java.util.List;

import lk.wda.localauthwebsite.model.Province;
import lk.wda.localauthwebsite.repository.DistrictRepository;
import lk.wda.localauthwebsite.repository.ProvinceRepository;
import lk.wda.localauthwebsite.utility.GoogleSheetsUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LocalAuthorityService {
    private static final Logger log = LoggerFactory.getLogger(LocalAuthorityService.class);
    private static final String SPREADSHEET_ID = "1fL1TH2TIy8A2umyOdI85x0zWAnesARtMBXz0UQBcvGY";
    private final ProvinceRepository provinceRepository;
    private final DistrictRepository districtRepository;

    public LocalAuthorityService(ProvinceRepository provinceRepository,
                                 DistrictRepository districtRepository) {
        this.provinceRepository = provinceRepository;
        this.districtRepository = districtRepository;
    }

    public void initialiseLocalAuthorities() {
        String range = "local_authority!A:P";
        try {
            Sheets sheetsService = GoogleSheetsUtil.getService();
            List<List<Object>> rawData = sheetsService.spreadsheets()
                                                      .values()
                                                      .get(SPREADSHEET_ID, range)
                                                      .execute()
                                                      .getValues();
            List<Object> rawRow;
            String provinceNameEN;
            for (int i = 1; i < rawData.size(); i++) {
                rawRow = rawData.get(i);
                provinceNameEN = rawRow.get(1).toString();
                Province province = new Province(rawRow.get(0).toString(), provinceNameEN, rawRow.get(2).toString());
                if (!provinceRepository.existsByNameEN(provinceNameEN)) {
                    // Create province
                    provinceRepository.save(province);
                    log.info(MessageFormat.format("Created Province with name: {0}.", provinceNameEN));
                } else {
                    log.warn(MessageFormat.format("Province already exists for name: {0}.", provinceNameEN));
                }
            }
        } catch (IOException e) {
            log.error("Failed to locate credential files,", e);
        } catch (GeneralSecurityException e) {
            log.error("Failed with critical error,", e);
        }
    }
}
