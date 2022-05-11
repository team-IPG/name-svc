package org.ipg.namesvc.svc;

import org.ipg.namesvc.dto.NameRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class NameService {

    private final ParsingService parsingService;

    public NameService(ParsingService parsingService) {
        this.parsingService = parsingService;
    }

    public void processNamesFile(MultipartFile file) {
        // parse contents
        List<NameRecord> nameRecords = parsingService.parse(file);
        // split into records
        // insert/update db
        // publish events
    }
}
