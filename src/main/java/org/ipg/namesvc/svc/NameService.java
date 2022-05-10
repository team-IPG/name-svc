package org.ipg.namesvc.svc;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class NameService {

    public void processNamesFile(MultipartFile file) {
        // parse contents
        // split into records
        // insert/update db
        // publish events
    }
}
