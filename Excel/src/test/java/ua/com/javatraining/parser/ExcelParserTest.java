package ua.com.javatraining.parser;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ExcelParserTest {

    @Test
    public void shouldParseFile() throws IOException, InvalidFormatException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("fileForParser.xlsx");
        ExcelParser parser = new ExcelParser();
        List<CustomProfile> customProfiles = parser.parse(inputStream);
        System.out.println(customProfiles);
    }

    @Test
    public void shouldParseFile2() throws IOException, InvalidFormatException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("fileForParser.xlsx");
        ExcelParser parser = new ExcelParser();
        List<CustomProfile> customProfiles = parser.parseJava8(inputStream);
        System.out.println(customProfiles);
    }

}
