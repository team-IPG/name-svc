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

    private NameRecord expectedNameRecord1;
    private NameRecord expectedNameRecord2;

    @BeforeEach
    void setup() {
         expectedNameRecord1 = new NameRecord("joe", "doe", "1234");
         expectedNameRecord2 = new NameRecord("jane", "doe", "5678");
    }

    @Test
    void parse() {
        String line = "joe, doe, 1234";
        NameRecord testNameRecord = parsingService.parseLine(line);
        assertEquals(expectedNameRecord1, testNameRecord);
    }

    @Test
    void parseLine() throws IOException {
        InputStream is = this.getClass().getResourceAsStream("/simple_names.csv");
        MultipartFile file = new MockMultipartFile("simple_names.csv", is);
        List<NameRecord> names = parsingService.parse(file);
        assertEquals(2, names.size());
        assertThat(names, hasItem(expectedNameRecord1));
        assertThat(names, hasItem(expectedNameRecord2));
    }
}