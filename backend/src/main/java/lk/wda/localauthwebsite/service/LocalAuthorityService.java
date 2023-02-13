package lk.wda.localauthwebsite.service;

import com.google.api.services.sheets.v4.Sheets;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.stream.Collectors;

import lk.wda.localauthwebsite.exception.ResourceNotFoundException;
import lk.wda.localauthwebsite.model.Province;
import lk.wda.localauthwebsite.repository.ProvinceRepository;
import lk.wda.localauthwebsite.utility.GoogleSheetsUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LocalAuthorityService {
    private final static Logger log = LoggerFactory.getLogger(LocalAuthorityService.class);
    private final ProvinceRepository provinceRepository;

    public LocalAuthorityService(ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }

    private List<Province> fetchProvinces() {
        String SPREADSHEET_ID = "1fL1TH2TIy8A2umyOdI85x0zWAnesARtMBXz0UQBcvGY";
        String range = "local_authority!A:C";
        try {
            Sheets sheetsService = GoogleSheetsUtil.getService();
            List<List<Object>> data = sheetsService.spreadsheets()
                                                   .values()
                                                   .get(SPREADSHEET_ID, range)
                                                   .execute()
                                                   .getValues();
            return data.stream()
                       .skip(1)
                       .map(row -> row.stream().limit(3).collect(Collectors.toList()))
                       .distinct()
                       .map(row -> new Province(row.get(0).toString(),
                                                row.get(1).toString(),
                                                row.get(2).toString()))
                       .collect(Collectors.toList());
        } catch (IOException e) {
            log.error("Failed to locate credential files,", e);
        } catch (GeneralSecurityException e) {
            log.error("Failed with critical error,", e);
        }
        return null;
    }

    public List<Province> createProvinces() throws ResourceNotFoundException {
        List<Province> provinces = fetchProvinces();
        if (provinces != null) {
            provinceRepository.deleteAll();
            return provinceRepository.saveAll(provinces);
        }
        throw new ResourceNotFoundException("No Province data found in the Data Sheet.");
    }
}
