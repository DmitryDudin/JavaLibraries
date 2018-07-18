package ua.com.javatraining.parser;

import javafx.scene.Node;
import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ua.com.javatraining.myUtils.ExcelUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelParser {

    public List<CustomProfile> parse(InputStream inputStream) throws IOException, InvalidFormatException {
        List<CustomProfile> result = new ArrayList<>();

//        InputStream inputStream = new FileInputStream(file);
//        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
//        HSSFSheet sheet = workbook.getSheetAt(0);
//        XSSFWorkbook workbook = new XSSFWorkbook();
//        XSSFSheet sheet = workbook.getSheetAt(0);

        // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();
//        dataFormatter.formatCellValue();

        Workbook workbook = WorkbookFactory.create(inputStream);//universal workbook, detect excel file version automatically
        Sheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next();//skip header
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.iterator();
            CustomProfile profile = new CustomProfile();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                /*CellType cellTypeEnum = cell.getCellTypeEnum();
                switch (cellTypeEnum) {
                    case STRING:
                        cell.getStringCellValue();
                        break;
                    case NUMERIC:
                        cell.getNumericCellValue();
                        break;
                    case BOOLEAN:
                        cell.getBooleanCellValue();
                        break;
                    case FORMULA:
                        break;
                    default:
                        throw new ExcelParserException("Cell type=" + cellTypeEnum + " not supported.");
                }*/

                /*int columnIndex = cell.getColumnIndex();
                if (columnIndex == 0) {
                    profile.setId(cell.getStringCellValue());
                } else if (columnIndex == 1) {
                    profile.setName(cell.getStringCellValue());
                } else if (columnIndex == 2) {
                    profile.setPassword(cell.getStringCellValue());
                }*/

                int columnIndex = cell.getColumnIndex();
                String cellValue = dataFormatter.formatCellValue(cell);
                if (columnIndex == 0) {
                    profile.setId(cellValue);
                } else if (columnIndex == 1) {
                    profile.setName(cellValue);
                } else if (columnIndex == 2) {
                    profile.setPassword(cellValue);
                }
            }
            result.add(profile);
        }

        workbook.close();
        return result;
    }

    public List<CustomProfile> parseJava8(InputStream inputStream) throws IOException, InvalidFormatException {

        List<CustomProfile> result = new ArrayList<>();
        DataFormatter dataFormatter = new DataFormatter();
        Workbook workbook = WorkbookFactory.create(inputStream);//universal workbook, detect excel file version automatically
        Sheet sheet = workbook.getSheetAt(0);

        sheet.forEach(row -> {
            if (row.getRowNum() == 0) return;//skip header
            CustomProfile profile = new CustomProfile();
            row.forEach(cell -> {
                int columnIndex = cell.getColumnIndex();
                String cellValue = dataFormatter.formatCellValue(cell);
                if (columnIndex == 0) {
                    profile.setId(cellValue);
                } else if (columnIndex == 1) {
                    profile.setName(cellValue);
                } else if (columnIndex == 2) {
                    profile.setPassword(cellValue);
                }
            });
            result.add(profile);
        });

        workbook.close();
        return result;
    }

}
