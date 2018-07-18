package ua.com.javatraining;

import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.extractor.XSSFExcelExtractor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class ApachPoiFeatures {

    @Test
    public void test() throws IOException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("fileForParser.xls");
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        ExcelExtractor extractor = new ExcelExtractor(workbook);
        String text = extractor.getText();
        System.out.println(text);
    }

    @Test
    public void test2() throws IOException, InvalidFormatException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("fileForParser.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFExcelExtractor xssfExcelExtractor = new XSSFExcelExtractor(workbook);
        String text = xssfExcelExtractor.getText();
        System.out.println(text);
    }

    @Test
    public void test3() throws IOException, InvalidFormatException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("fileForParser.xls");
        POIFSFileSystem poifsFileSystem = null;
        poifsFileSystem = new POIFSFileSystem(inputStream);
        ExcelExtractor excelExtractor = new ExcelExtractor(poifsFileSystem);
        System.out.println(excelExtractor.getText());
    }

}
