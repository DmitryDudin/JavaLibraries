package ua.com.javatraining.nvenkyExcelParser;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.javafunk.excelparser.SheetParser;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class NvenkyExcelParser {

//https://github.com/nvenky/excel-parser

    @Test
    public void shouldParse() throws IOException, InvalidFormatException {
        SheetParser parser = new SheetParser();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("fileForParser.xlsx");
        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        List entityList = parser.createEntity(sheet, sheet.getSheetName(), NvenkyCustomProfile.class);
        System.out.println(entityList);

//        List<NvenkyCustomProfile> newList = parser.createEntity(sheet, NvenkyCustomProfile.class, System.out::println);
        List<NvenkyCustomProfile> newList = parser.createEntity(sheet, NvenkyCustomProfile.class, null);
        System.out.println(newList);

        workbook.close();
    }

}
