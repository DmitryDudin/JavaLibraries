package ua.com.javatraining.myUtils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ua.com.javatraining.Employee;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class ExcelReportGenerator {

    //    public static File generateExcelXlsxFile(String sheetName, String[] headers, List<Employee> employees) {
    public static ByteArrayOutputStream generateExcelXlsxFile(List<Employee> employees) throws IOException {
        String[] headers = new String[]{"Name", "Birthday", "Payment", "BONUS"};

        XSSFWorkbook workbook = new XSSFWorkbook();
        Map<ExcelUtils.CellFormat, CellStyle> cellStyleMap = ExcelUtils.generateCellFormatMapXlsx(workbook);
//        String sheetName = "sourceDest";
//        Sheet sheet = workbook.createSheet(sheetName);
        Sheet sheet = workbook.createSheet();
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);

        Row rowHeader = sheet.createRow(0);
        IntStream.range(0, headers.length)
                .forEach(column -> {
                    Cell cellHeader = rowHeader.createCell(column);
                    cellHeader.setCellValue(headers[column]);
//                    cellHeader.setCellStyle(cellStyleMap.get(ExcelUtils.CellFormat.HEADER_YELLOW_CELL_FORMAT_CENTRE));
                    cellHeader.setCellStyle(ExcelUtils
                            .getCellStyle(ExcelUtils.CellType.HEADER_YELLOW, ExcelUtils.Alignment.CENTRE, false, null, cellStyleMap));
                });

        IntStream.range(0, employees.size())
                .forEach(i -> {
                    int column = 0;
                    Row row = sheet.createRow(i + 1);
                    Employee employee = employees.get(i);

                    Cell cell = row.createCell(column++, CellType.STRING);
                    cell.setCellValue(employee.getName());
                    cell.setCellStyle(ExcelUtils
                            .getCellStyle(ExcelUtils.CellType.STANDARD, ExcelUtils.Alignment.LEFT, true, i, cellStyleMap));

                    cell = row.createCell(column++, CellType.STRING);
                    cell.setCellValue(employee.getBirthDate());
                    cell.setCellStyle(ExcelUtils
                            .getCellStyle(ExcelUtils.CellType.DATE, ExcelUtils.Alignment.LEFT, true, i, cellStyleMap));

                    cell = row.createCell(column++, CellType.STRING);
                    cell.setCellValue(employee.getPayment().toPlainString());
                    cell.setCellStyle(ExcelUtils
                            .getCellStyle(ExcelUtils.CellType.STANDARD, ExcelUtils.Alignment.LEFT, true, i, cellStyleMap));

                    cell = row.createCell(column++, CellType.STRING);
                    cell.setCellValue(employee.getBonus().toPlainString());
                    cell.setCellStyle(ExcelUtils
                            .getCellStyle(ExcelUtils.CellType.STANDARD, ExcelUtils.Alignment.LEFT, true, i, cellStyleMap));

                });

//        sheet.autoSizeColumn(0);//todo
//        sheet.autoSizeColumn(1);
//        sheet.setColumnWidth(0, 10000);
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        /*
        * if autoSizeColumn is to long
        *where 1.14388 is a max character width of the "Serif" font and 256 font units.
        * Performance of autosizing improved from 10 minutes to 6 seconds.
        * maxNumCharacters - simply calculate number of characters in each cell of column and find the largest, than apply it in formula
        *
        int width = ((int)(maxNumCharacters * 1.14388)) * 256;
        sheet.setColumnWidth(i, width);
        */

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        FileOutputStream fileOutputStream = new FileOutputStream();
//            new File();
//        ByteBuffer wrap = ByteBuffer.wrap(outputStream.toByteArray());
//        wrap.as
        workbook.write(outputStream);
        return outputStream;
    }

}
