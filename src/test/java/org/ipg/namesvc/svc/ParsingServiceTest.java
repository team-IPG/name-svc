package org.ipg.namesvc.svc;

import org.ipg.common.EmployeeDTO;
import org.ipg.common.VoicePreset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ParsingServiceTest {

    private final ParsingService parsingService = new ParsingService();

    private EmployeeDTO expectedName1;
    private EmployeeDTO expectedName2;
    private EmployeeDTO expectedName3;

    @BeforeEach
    void setup() {
        VoicePreset defaultPreset = VoicePreset.valueOf(VoicePreset.DEFAULT_PRESET);
        Double defaultRate = Double.valueOf(VoicePreset.DEFAULT_RATE);
        expectedName1 = new EmployeeDTO("u01236","Abdullah", "Khan", "Aby", defaultPreset, defaultRate, true);
        expectedName2 = new EmployeeDTO("u01234", "Sanja", "Kon", "Sanja", defaultPreset, defaultRate, true);
        expectedName3 = new EmployeeDTO("u04567", "Jose", "Aldo", "Jose", defaultPreset, defaultRate, true);
    }

    @Test
    void parse() {
        String line = "u01236, Abdullah, Khan, Aby";
        EmployeeDTO testEmployeeDTO = parsingService.parseLine(line);
        assertEquals(expectedName1, testEmployeeDTO);
    }

    @Test
    void parseLine() throws IOException {
        InputStream is = this.getClass().getResourceAsStream("/simple_names.csv");
        MultipartFile file = new MockMultipartFile("simple_names.csv", is);
        List<EmployeeDTO> names = parsingService.parse(file);
        assertEquals(3, names.size());
        assertThat(names, hasItem(expectedName1));
        assertThat(names, hasItem(expectedName2));
        assertThat(names, hasItem(expectedName3));
    }
}