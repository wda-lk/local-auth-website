package lk.wda.localauthwebsite.utility;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.java6.auth.oauth2.VerificationCodeReceiver;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoogleSheetsUtil {
    private static final String APPLICATION_NAME = "Local Authority Website";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "backend/src/main/resources/tokens";
    private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS);
    private static final String CREDENTIALS_FILE_PATH = "/google-sheets-credentials.json";

    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        InputStream in = GoogleSheetsUtil.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        AuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow
                .Builder(HTTP_TRANSPORT,
                         JSON_FACTORY,
                         GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in)),
                         SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        VerificationCodeReceiver receiver = new LocalServerReceiver
                .Builder()
                .setPort(8888)
                .build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    private static Sheets getService() throws IOException, GeneralSecurityException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        return new Sheets
                .Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    private static List<Map<String, String>> enrichData(List<List<Object>> rawData) {
        List<Map<String, String>> mappedData = new ArrayList<>();
        List<Object> rawHeaderRow = rawData.get(0);
        for (int i = 1; i < rawData.size(); i++) {
            List<Object> rawBodyRow = rawData.get(i);
            Map<String, String> mappedRow = new HashMap<>();
            for (int j = 0; j < rawBodyRow.size(); j++) {
                String rawBodyData = rawBodyRow.get(j).toString();
                // empty cells should be marked as null
                if (rawBodyData.isEmpty()) {
                    rawBodyData = null;
                }
                mappedRow.put(rawHeaderRow.get(j).toString(), rawBodyData);
            }
            // google sheets drop the empty values in last few columns of a row. Cells included in these dropped
            // columns should be marked as null
            if (rawBodyRow.size() < rawHeaderRow.size()) {
                for (int j = rawBodyRow.size(); j < rawHeaderRow.size(); j++) {
                    mappedRow.put(rawHeaderRow.get(j).toString(), null);
                }
            }
            mappedData.add(mappedRow);
        }
        return mappedData;
    }

    public static List<Map<String, String>> extractRawData(String spreadsheet_id, String range)
            throws GeneralSecurityException, IOException {
        Sheets sheetsService = getService();
        List<List<Object>> rawData = sheetsService.spreadsheets()
                                                  .values()
                                                  .get(spreadsheet_id, range)
                                                  .execute()
                                                  .getValues();
        return enrichData(rawData);
    }
}
