package ua.com.javatraining.myUtils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class ExcelUtils {
    public enum CellFormat {
        HEADER_CELL_FORMAT, HEADER_CELL_FORMAT_CENTRE,
        STANDARD_CELL_FORMAT, STANDARD_CELL_FORMAT_CENTRE, STANDARD_CELL_FORMAT_GRAY, STANDARD_CELL_FORMAT_CENTRE_GRAY,
        DATE_CELL_FORMAT, DATE_CELL_FORMAT_GRAY,
        INTEGER_NUMBER_FORMAT, INTEGER_NUMBER_FORMAT_GRAY,
        BOLD_CELL_FORMAT, BOLD_CELL_FORMAT_CENTRE,


    }

    public static File generateExcelXlsxFile(String sheetName, String[] headers) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        Map<CellFormat, CellStyle> cellStyleMap = generateCellFormatMapXlsx(workbook);
        Sheet sheet = workbook.createSheet(sheetName);

        Row rowHeader = sheet.createRow(0);
        IntStream.range(0, headers.length)
                .mapToObj(rowHeader::createCell)
                .forEachOrdered(cellHeader -> {
                            cellHeader.setCellStyle(cellStyleMap.get(CellFormat.HEADER_CELL_FORMAT));
                            cellHeader.setCellValue("Unit number");
                        }
                );


        return new File("");
    }

    private static Map<CellFormat, CellStyle> generateCellFormatMapXlsx(XSSFWorkbook wb) {
        Map<CellFormat, CellStyle> cellFormatMap = new HashMap<>();

        //generate fonts
        XSSFFont arial10BoldFont = wb.createFont();
        arial10BoldFont.setFontName("ARIAL");
        arial10BoldFont.setBold(true);
        arial10BoldFont.setFontHeightInPoints((short) 10);

        Font arial10Font = wb.createFont();
        arial10Font.setFontName("ARIAL");
        arial10Font.setBold(false);
        arial10Font.setFontHeightInPoints((short) 10);

        //generate cell styles
        DataFormat df = wb.createDataFormat();//todo ???

        CellStyle bgYellowArial10BoldCellStyle = wb.createCellStyle();
        bgYellowArial10BoldCellStyle.setFont(arial10BoldFont);
        bgYellowArial10BoldCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());//todo or background color?????
//        bgYellowArial10BoldCellStyle.setFillForegroundColor(HSSFColorPredefined.YELLOW.getIndex());//todo or background color?????
//        bgYellowArial10BoldCellStyle.setDataFormat(df.getFormat("@"));//todo ???????
//        bgYellowArial10BoldCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellFormatMap.put(CellFormat.HEADER_CELL_FORMAT, bgYellowArial10BoldCellStyle);

        CellStyle bgYellowArial10BoldCenterCellStyle = wb.createCellStyle();
        bgYellowArial10BoldCenterCellStyle.cloneStyleFrom(bgYellowArial10BoldCellStyle);
        bgYellowArial10BoldCenterCellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellFormatMap.put(CellFormat.HEADER_CELL_FORMAT_CENTRE, bgYellowArial10BoldCellStyle);

        CellStyle bgWhiteArial10CellStyle = wb.createCellStyle();
        bgWhiteArial10CellStyle.setFont(arial10Font);
//        bgWhiteArial10CellStyle.setDataFormat(df.getFormat("@"));
        cellFormatMap.put(CellFormat.STANDARD_CELL_FORMAT, bgWhiteArial10CellStyle);

        cellFormatMap.put(CellFormat.STANDARD_CELL_FORMAT_GRAY,
                CellStyleBuilder.newBuilder()
                        .workBook(wb)
                        .font(arial10Font)
//                        .dataFormat(df.getFormat("@"))
                        .fillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex())
//                        .fillPattern(FillPatternType.SOLID_FOREGROUND)//todo check???????
                        .build());

//        CellStyle bgWhiteArial10CenterCellStyle = wb.createCellStyle();
//        bgWhiteArial10CenterCellStyle.cloneStyleFrom(bgWhiteArial10CellStyle);
//        bgWhiteArial10CenterCellStyle.setAlignment(HorizontalAlignment.CENTER);
//        cellFormatMap.put(CellFormat.STANDARD_CELL_FORMAT_CENTRE, bgWhiteArial10CenterCellStyle);
        cellFormatMap.put(CellFormat.STANDARD_CELL_FORMAT_CENTRE,
                CellStyleBuilder.newBuilder()
                        .workBook(wb)
                        .font(arial10Font)
                        //.dataFormat(df.getFormat("@"))
                        .alignment(HorizontalAlignment.CENTER)
                        .fillPattern(FillPatternType.SOLID_FOREGROUND)
                        .build());

        cellFormatMap.put(CellFormat.STANDARD_CELL_FORMAT_CENTRE_GRAY,
                CellStyleBuilder.newBuilder()
                        .workBook(wb)
                        .font(arial10Font)
                        //.dataFormat(df.getFormat("@"))
                        .alignment(HorizontalAlignment.CENTER)
                        .fillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex())
                        .fillPattern(FillPatternType.SOLID_FOREGROUND)
                        .build());

        cellFormatMap.put(CellFormat.DATE_CELL_FORMAT,
                CellStyleBuilder.newBuilder()
                        .workBook(wb)
                        .font(arial10Font)
                        .dataFormat(df.getFormat("dd/MM/yy"))//todo date format??????????????
                        .fillPattern(FillPatternType.SOLID_FOREGROUND)
                        .build());

        cellFormatMap.put(CellFormat.DATE_CELL_FORMAT_GRAY,
                CellStyleBuilder.newBuilder()
                        .workBook(wb)
                        .font(arial10Font)
                        .dataFormat(df.getFormat("dd/MM/yy"))
                        .fillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex())
                        .fillPattern(FillPatternType.SOLID_FOREGROUND)
                        .build());

        cellFormatMap.put(CellFormat.INTEGER_NUMBER_FORMAT,
                CellStyleBuilder.newBuilder()
                        .workBook(wb)
                        .font(arial10Font)
                        //.dataFormat(df.getFormat("@"))
                        .fillPattern(FillPatternType.SOLID_FOREGROUND)
                        .build());

        cellFormatMap.put(CellFormat.INTEGER_NUMBER_FORMAT_GRAY,
                CellStyleBuilder.newBuilder()
                        .workBook(wb)
                        .font(arial10Font)
                        //.dataFormat(df.getFormat("@"))
                        .fillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex())
                        .fillPattern(FillPatternType.SOLID_FOREGROUND)
                        .build());

        cellFormatMap.put(CellFormat.BOLD_CELL_FORMAT,
                CellStyleBuilder.newBuilder()
                        .workBook(wb)
                        .font(arial10BoldFont)
                        //.dataFormat(df.getFormat("@"))
                        .fillPattern(FillPatternType.SOLID_FOREGROUND)
                        .build());

        cellFormatMap.put(CellFormat.BOLD_CELL_FORMAT_CENTRE,
                CellStyleBuilder.newBuilder()
                        .workBook(wb)
                        .font(arial10BoldFont)
                        //.dataFormat(df.getFormat("@"))
                        .alignment(HorizontalAlignment.CENTER)
                        .fillPattern(FillPatternType.SOLID_FOREGROUND)
                        .build());

        return cellFormatMap;
    }

    private static class CellStyleBuilder {
        private Workbook workbook;
        private Font font;
        private Short fillForegroundColor;
        private FillPatternType fillPattern;
        private HorizontalAlignment alignment;
        private VerticalAlignment verticalAlignment;
        private Short dataFormat;

        public static CellStyleBuilder newBuilder() {
            return new CellStyleBuilder();
        }

        //must set
        public CellStyleBuilder workBook(Workbook workbook) {
            this.workbook = workbook;
            return this;
        }

        public CellStyleBuilder font(Font font) {
            this.font = font;
            return this;
        }

        public CellStyleBuilder fillForegroundColor(short fillForegroundColor) {
            this.fillForegroundColor = fillForegroundColor;
            return this;
        }

        public CellStyleBuilder fillPattern(FillPatternType fillPattern) {
            this.fillPattern = fillPattern;
            return this;
        }

        public CellStyleBuilder alignment(HorizontalAlignment alignment) {
            this.alignment = alignment;
            return this;
        }

        public CellStyleBuilder verticalAlignment(VerticalAlignment verticalAlignment) {
            this.verticalAlignment = verticalAlignment;
            return this;
        }

        public CellStyleBuilder dataFormat(short dataFormat) {
            this.dataFormat = dataFormat;
            return this;
        }

        public CellStyle build() {
            if (null == workbook) {
                throw new IllegalArgumentException();
            }
            CellStyle cellStyle = workbook.createCellStyle();
            if (font != null) {
                cellStyle.setFont(font);
            }
            if (fillForegroundColor != null) {
                cellStyle.setFillForegroundColor(fillForegroundColor);
            }
            if (fillPattern != null) {
                cellStyle.setFillPattern(fillPattern);
            }
            if (alignment != null) {
                cellStyle.setAlignment(alignment);
            }
            if (dataFormat != null) {
                cellStyle.setDataFormat(dataFormat);
            }
            if (verticalAlignment != null) {
                cellStyle.setVerticalAlignment(verticalAlignment);
            }
            return cellStyle;
        }

    }

    public static void main(String[] args) {//todo delete ????
        XSSFWorkbook wb = new XSSFWorkbook();
        System.out.println("nubmer of fonts= " + wb.getNumberOfFonts());
        System.out.println("font0= " + wb.getFontAt((short) 0));
        System.out.println("findFont= " + wb.findFont(false,
                IndexedColors.BLACK.getIndex(),
                (short) 2,
                "Calibri",
                false,
                false,
                (short) 0,
                (byte) 0));

        System.out.println("getNumCellStyles= " + wb.getNumCellStyles());
        System.out.println("getCellStyleAt= " + wb.getCellStyleAt(0).getFillForegroundColor());

    }
}
