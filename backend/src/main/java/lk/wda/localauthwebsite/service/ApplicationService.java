package lk.wda.localauthwebsite.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Map;

import lk.wda.localauthwebsite.exception.GoogleSheetConfigException;
import lk.wda.localauthwebsite.exception.NoResourceFoundException;
import lk.wda.localauthwebsite.model.Application;
import lk.wda.localauthwebsite.model.LocalAuthority;
import lk.wda.localauthwebsite.repository.ApplicationRepository;
import lk.wda.localauthwebsite.utility.GoogleSheetsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {
    private static final Logger log = LoggerFactory.getLogger(ContactService.class);
    private final ApplicationRepository applicationRepository;
    private final LocalAuthorityService localAuthorityService;
    @Value("${google.sheet.id}")
    private String spreadsheet_id;

    public ApplicationService(ApplicationRepository applicationRepository,
                              LocalAuthorityService localAuthorityService) {
        this.applicationRepository = applicationRepository;
        this.localAuthorityService = localAuthorityService;
    }

    public List<Application> createApplications() throws GoogleSheetConfigException, NoResourceFoundException {
        String range = "application!A:C";
        try {
            // clean table
            applicationRepository.deleteAll();
            // extract sheet data
            List<Map<String, String>> data = GoogleSheetsUtil.extractRawData(spreadsheet_id, range);
            for (Map<String, String> row : data) {
                // validate the relevant local authority
                LocalAuthority localAuthority =
                        localAuthorityService.validateLocalAuthority(row.get("local_authority"));
                // create contact
                Application application = new Application(row.get("name"), row.get("file"));
                application.setLocalAuthority(localAuthority);
                applicationRepository.save(application);
            }
            return applicationRepository.findAll();
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
