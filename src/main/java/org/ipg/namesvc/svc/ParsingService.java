package org.ipg.namesvc.svc;

import org.ipg.namesvc.dto.EmployeeDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParsingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParsingService.class);

    public List<EmployeeDTO> parse(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            return new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                    .lines()
                    .map(this::parseLine)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.warn("Failed to read file", e);
        }
        return Collections.emptyList();
    }

    protected EmployeeDTO parseLine(String line) {
        String[] parts = line.split(",");
        return new EmployeeDTO(parts[0].trim(), parts[1].trim(), parts[2].trim());
    }

}
