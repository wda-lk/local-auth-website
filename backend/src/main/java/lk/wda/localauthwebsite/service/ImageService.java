package lk.wda.localauthwebsite.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lk.wda.localauthwebsite.exception.GoogleSheetConfigException;
import lk.wda.localauthwebsite.exception.MalformedImageURL;
import lk.wda.localauthwebsite.exception.NoResourceFoundException;
import lk.wda.localauthwebsite.model.Image;
import lk.wda.localauthwebsite.model.LocalAuthority;
import lk.wda.localauthwebsite.repository.ImageRepository;
import lk.wda.localauthwebsite.utility.GoogleSheetsUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    private static final Logger log = LoggerFactory.getLogger(ContactService.class);
    private final ImageRepository imageRepository;
    private final LocalAuthorityService localAuthorityService;
    @Value("${google.sheet.id}")
    private String spreadsheet_id;

    public ImageService(ImageRepository imageRepository, LocalAuthorityService localAuthorityService) {
        this.imageRepository = imageRepository;
        this.localAuthorityService = localAuthorityService;
    }

    public List<Image> createImages() throws GoogleSheetConfigException,
                                             NoResourceFoundException,
                                             MalformedImageURL {
        String range = "image!A:B";
        try {
            // Clear table
            imageRepository.deleteAll();
            // Extract sheet data
            List<Map<String, String>> data = GoogleSheetsUtil.extractRawData(spreadsheet_id, range);
            for (Map<String, String> row : data) {
                // Validate the relevant local authority
                LocalAuthority localAuthority =
                        localAuthorityService.validateLocalAuthority(row.get("local_authority"));
                // Extract ID that each item in google drive has been assigned.
                String imageURL = row.get("image");
                Pattern pattern = Pattern.compile("d/(.*?)/view", Pattern.MULTILINE);
                Matcher matcher = pattern.matcher(imageURL);
                if (!matcher.find()) {
                    throw new MalformedImageURL("Invalid image url: " + imageURL);
                }
                String imageID = matcher.group(1);
                // Create image
                Image image = new Image(imageID, imageURL);
                image.setLocalAuthority(localAuthority);
                imageRepository.save(image);
            }
            return imageRepository.findAll();
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
