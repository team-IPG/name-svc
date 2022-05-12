package org.ipg.namesvc.svc;

import org.ipg.namesvc.dto.NameRecord;
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

    private NameRecord expectedName1;
    private NameRecord expectedName2;
    private NameRecord expectedName3;

    @BeforeEach
    void setup() {
        expectedName1 = new NameRecord("Abdullah", "Khan", "Aby");
        expectedName2 = new NameRecord("Sanja", "Kon", "Sanja");
        expectedName3 = new NameRecord("Jose", "Aldo", "Jose");
    }

    @Test
    void parse() {
        String line = "Abdullah, Khan, Aby";
        NameRecord testNameRecord = parsingService.parseLine(line);
        assertEquals(expectedName1, testNameRecord);
    }

    @Test
    void parseLine() throws IOException {
        InputStream is = this.getClass().getResourceAsStream("/simple_names.csv");
        MultipartFile file = new MockMultipartFile("simple_names.csv", is);
        List<NameRecord> names = parsingService.parse(file);
        assertEquals(3, names.size());
        assertThat(names, hasItem(expectedName1));
        assertThat(names, hasItem(expectedName2));
        assertThat(names, hasItem(expectedName3));
    }
}