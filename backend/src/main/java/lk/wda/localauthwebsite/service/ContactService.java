package lk.wda.localauthwebsite.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Map;

import lk.wda.localauthwebsite.exception.GoogleSheetConfigException;
import lk.wda.localauthwebsite.exception.NoResourceFoundException;
import lk.wda.localauthwebsite.model.Contact;
import lk.wda.localauthwebsite.model.LocalAuthority;
import lk.wda.localauthwebsite.repository.ContactRepository;
import lk.wda.localauthwebsite.utility.GoogleSheetsUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
    private static final Logger log = LoggerFactory.getLogger(ContactService.class);
    private final ContactRepository contactRepository;
    private final LocalAuthorityService localAuthorityService;
    @Value("${google.sheet.id}")
    private String spreadsheet_id;

    public ContactService(ContactRepository contactRepository, LocalAuthorityService localAuthorityService) {
        this.contactRepository = contactRepository;
        this.localAuthorityService = localAuthorityService;
    }

    public List<Contact> createContacts() throws GoogleSheetConfigException, NoResourceFoundException {
        String range = "contact!A:K";
        try {
            // Clear table
            contactRepository.deleteAll();
            // Extract sheet data
            List<Map<String, String>> data = GoogleSheetsUtil.extractRawData(spreadsheet_id, range);
            for (Map<String, String> row : data) {
                // Validate the relevant local authority
                LocalAuthority localAuthority =
                        localAuthorityService.validateLocalAuthority(row.get("local_authority"));
                // Create contact
                Contact contact =
                        new Contact(row.get("name_si"), row.get("name_en"), row.get("name_ta"), row.get("tel_number"),
                                    row.get("position_si"), row.get("position_en"), row.get("position_ta"),
                                    row.get("unit_si"), row.get("unit_en"), row.get("unit_ta"));
                contact.setLocalAuthority(localAuthority);
                contactRepository.save(contact);
            }
            return contactRepository.findAll();
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
