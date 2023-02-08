package lk.wda.localauthwebsite.service;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.BatchGetValuesResponse;
import lk.wda.localauthwebsite.utility.GoogleSheetsUtil;
import lk.wda.localauthwebsite.repository.LocalAuthorityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class LocalAuthorityService {
    private final static Logger log = LoggerFactory.getLogger(LocalAuthorityService.class);
    private final LocalAuthorityRepository localAuthorityRepository;

    public LocalAuthorityService(LocalAuthorityRepository localAuthorityRepository) {
        this.localAuthorityRepository = localAuthorityRepository;
    }

    public Object[] initDBData() {
        String SPREADSHEET_ID = "1fL1TH2TIy8A2umyOdI85x0zWAnesARtMBXz0UQBcvGY";
        List<String> ranges = Arrays.asList("local_authority", "contact", "application", "image");
        // Truncate database
        try {
            Sheets sheetsService = GoogleSheetsUtil.getService();
            BatchGetValuesResponse request = sheetsService.spreadsheets()
                                                          .values()
                                                          .batchGet(SPREADSHEET_ID)
                                                          .setRanges(ranges)
                                                          .execute();
            request.getValueRanges()
                   .forEach(System.out::println);
        } catch (IOException e) {
            log.error("Failed to locate credential files.", e);
        } catch (GeneralSecurityException e) {
            log.error("Failed critical error occurred.", e);
        }
        return null;
    }
}
