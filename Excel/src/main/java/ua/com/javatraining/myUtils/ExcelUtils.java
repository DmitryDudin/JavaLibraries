package ua.com.javatraining.myUtils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.HashMap;
import java.util.Map;

public class ExcelUtils {
    public enum CellFormat {
        HEADER_YELLOW_CELL_FORMAT_LEFT, HEADER_YELLOW_CELL_FORMAT_CENTRE, HEADER_WHITE_CELL_FORMAT_LEFT, HEADER_WHITE_CELL_FORMAT_CENTRE,
        STANDARD_CELL_FORMAT_LEFT, STANDARD_CELL_FORMAT_RIGHT, STANDARD_CELL_FORMAT_CENTRE, STANDARD_CELL_FORMAT_LEFT_GRAY, STANDARD_CELL_FORMAT_RIGHT_GRAY, STANDARD_CELL_FORMAT_CENTRE_GRAY,
        DATE_CELL_FORMAT_LEFT, DATE_CELL_FORMAT_LEFT_GRAY,
        INTEGER_NUMBER_FORMAT_LEFT, INTEGER_NUMBER_FORMAT_CENTER, INTEGER_NUMBER_FORMAT_LEFT_GRAY, INTEGER_NUMBER_FORMAT_CENTER_GRAY,
        BOLD_CELL_FORMAT_LEFT, BOLD_CELL_FORMAT_CENTRE,
    }

    public enum Alignment {
        LEFT, CENTRE, RIGHT
    }

    public enum CellType {
        HEADER_YELLOW, HEADER_WHITE, STANDARD, DATE, INTEGER, BOLD
    }

    public static CellStyle getCellStyle(CellType cellType, Alignment alignment, boolean zebra, Integer index, Map<CellFormat, CellStyle> cellStyleMap) {
        StringBuilder cellFormatName = new StringBuilder();
        switch (cellType) {
            case HEADER_YELLOW:
                cellFormatName.append("HEADER_YELLOW_");
                break;
            case HEADER_WHITE:
                cellFormatName.append("HEADER_WHITE_");
                break;
            case STANDARD:
                cellFormatName.append("STANDARD_");
                break;
            case DATE:
                cellFormatName.append("DATE_");
                break;
            case INTEGER:
                cellFormatName.append("INTEGER_");
                break;
            case BOLD:
                cellFormatName.append("BOLD_");
                break;
            default:
                throw new IllegalArgumentException();
        }

        cellFormatName.append("CELL_FORMAT_");

        switch (alignment) {
            case LEFT:
                cellFormatName.append("LEFT");
                break;
            case RIGHT:
                cellFormatName.append("RIGHT");
                break;
            case CENTRE:
                cellFormatName.append("CENTRE");
                break;
            default:
                throw new IllegalArgumentException();
        }

        if (zebra && index % 2 == 1) {
            cellFormatName.append("_GRAY");
        }

        CellFormat cellFormat = CellFormat.valueOf(cellFormatName.toString());
        return cellStyleMap.get(cellFormat);
    }

    public static Map<CellFormat, CellStyle> generateCellFormatMapXlsx(XSSFWorkbook wb) {
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
        DataFormat df = wb.createDataFormat();

        cellFormatMap.put(CellFormat.HEADER_YELLOW_CELL_FORMAT_LEFT,
                CellStyleBuilder.newBuilder()
                        .workBook(wb)
                        .font(arial10BoldFont)
                        .fillForegroundColor(IndexedColors.YELLOW.getIndex())
                        .dataFormat(df.getFormat("@"))
                        .fillPattern(FillPatternType.SOLID_FOREGROUND)
                        .border(BorderStyle.MEDIUM, BorderStyle.MEDIUM, BorderStyle.MEDIUM, BorderStyle.MEDIUM)
                        .verticalAlignment(VerticalAlignment.CENTER)
                        .build());

        cellFormatMap.put(CellFormat.HEADER_YELLOW_CELL_FORMAT_CENTRE,
                CellStyleBuilder.newBuilder()
                        .workBook(wb)
                        .font(arial10BoldFont)
                        .fillForegroundColor(IndexedColors.YELLOW.getIndex())
                        .dataFormat(df.getFormat("@"))
                        .fillPattern(FillPatternType.SOLID_FOREGROUND)
//                        .border(BorderStyle.MEDIUM, BorderStyle.MEDIUM, BorderStyle.MEDIUM, BorderStyle.MEDIUM)
                        .border(BorderStyle.THIN, BorderStyle.THIN, BorderStyle.THIN, BorderStyle.THIN)
                        .alignment(HorizontalAlignment.CENTER)
                        .verticalAlignment(VerticalAlignment.CENTER)
                        .build());

        cellFormatMap.put(CellFormat.HEADER_WHITE_CELL_FORMAT_LEFT,
                CellStyleBuilder.newBuilder()
                        .workBook(wb)
                        .font(arial10BoldFont)
                        .fillForegroundColor(IndexedColors.WHITE.getIndex())
                        .dataFormat(df.getFormat("@"))
                        .fillPattern(FillPatternType.SOLID_FOREGROUND)
                        .border(BorderStyle.MEDIUM, BorderStyle.MEDIUM, BorderStyle.MEDIUM, BorderStyle.MEDIUM)
                        .verticalAlignment(VerticalAlignment.CENTER)
                        .build());

        cellFormatMap.put(CellFormat.HEADER_WHITE_CELL_FORMAT_CENTRE,
                CellStyleBuilder.newBuilder()
                        .workBook(wb)
                        .font(arial10BoldFont)
                        .fillForegroundColor(IndexedColors.WHITE.getIndex())
                        .dataFormat(df.getFormat("@"))
                        .fillPattern(FillPatternType.SOLID_FOREGROUND)
//                        .border(BorderStyle.MEDIUM, BorderStyle.MEDIUM, BorderStyle.MEDIUM, BorderStyle.MEDIUM)
                        .border(BorderStyle.THIN, BorderStyle.THIN, BorderStyle.THIN, BorderStyle.THIN)
                        .alignment(HorizontalAlignment.CENTER)
                        .verticalAlignment(VerticalAlignment.CENTER)
                        .build());

        cellFormatMap.put(CellFormat.STANDARD_CELL_FORMAT_LEFT,
                CellStyleBuilder.newBuilder()
                        .workBook(wb)
                        .font(arial10Font)
                        .dataFormat(df.getFormat("@"))
                        .border(BorderStyle.THIN, BorderStyle.THIN, BorderStyle.THIN, BorderStyle.THIN)
                        .alignment(HorizontalAlignment.LEFT)
                        .fillPattern(FillPatternType.NO_FILL)
                        .build());

        cellFormatMap.put(CellFormat.STANDARD_CELL_FORMAT_LEFT_GRAY,
                CellStyleBuilder.newBuilder()
                        .workBook(wb)
                        .font(arial10Font)
                        .dataFormat(df.getFormat("@"))
                        .fillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex())
                        .fillPattern(FillPatternType.SOLID_FOREGROUND)
                        .border(BorderStyle.THIN, BorderStyle.THIN, BorderStyle.THIN, BorderStyle.THIN)
                        .alignment(HorizontalAlignment.LEFT)
                        .build());

        cellFormatMap.put(CellFormat.STANDARD_CELL_FORMAT_RIGHT,
                CellStyleBuilder.newBuilder()
                        .workBook(wb)
                        .font(arial10Font)
                        .dataFormat(df.getFormat("@"))
                        .border(BorderStyle.THIN, BorderStyle.THIN, BorderStyle.THIN, BorderStyle.THIN)
                        .alignment(HorizontalAlignment.RIGHT)
                        .build());

        cellFormatMap.put(CellFormat.STANDARD_CELL_FORMAT_RIGHT_GRAY,
                CellStyleBuilder.newBuilder()
                        .workBook(wb)
                        .font(arial10Font)
                        .dataFormat(df.getFormat("@"))
                        .fillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex())
                        .fillPattern(FillPatternType.SOLID_FOREGROUND)
                        .border(BorderStyle.THIN, BorderStyle.THIN, BorderStyle.THIN, BorderStyle.THIN)
                        .alignment(HorizontalAlignment.RIGHT)
                        .build());

        cellFormatMap.put(CellFormat.STANDARD_CELL_FORMAT_CENTRE,
                CellStyleBuilder.newBuilder()
                        .workBook(wb)
                        .font(arial10Font)
                        .dataFormat(df.getFormat("@"))
                        .fillForegroundColor(IndexedColors.WHITE.getIndex())
                        .fillPattern(FillPatternType.NO_FILL)
                        .border(BorderStyle.THIN, BorderStyle.THIN, BorderStyle.THIN, BorderStyle.THIN)
                        .alignment(HorizontalAlignment.CENTER)
                        .build());

        cellFormatMap.put(CellFormat.STANDARD_CELL_FORMAT_CENTRE_GRAY,
                CellStyleBuilder.newBuilder()
                        .workBook(wb)
                        .font(arial10Font)
                        .dataFormat(df.getFormat("@"))
                        .fillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex())
                        .fillPattern(FillPatternType.SOLID_FOREGROUND)
                        .border(BorderStyle.THIN, BorderStyle.THIN, BorderStyle.THIN, BorderStyle.THIN)
                        .alignment(HorizontalAlignment.CENTER)
                        .build());

        cellFormatMap.put(CellFormat.DATE_CELL_FORMAT_LEFT,
                CellStyleBuilder.newBuilder()
                        .workBook(wb)
                        .font(arial10Font)
                        .dataFormat(df.getFormat("dd-MM-yy HH-mm"))
//                        .dataFormat(df.getFormat("d-mmm-yy"))
                        .border(BorderStyle.THIN, BorderStyle.THIN, BorderStyle.THIN, BorderStyle.THIN)
                        .alignment(HorizontalAlignment.LEFT)
                        .build());

        cellFormatMap.put(CellFormat.DATE_CELL_FORMAT_LEFT_GRAY,
                CellStyleBuilder.newBuilder()
                        .workBook(wb)
                        .font(arial10Font)
                        .dataFormat(df.getFormat("dd-MM-yy HH-mm"))
//                        .dataFormat(df.getFormat("dd/MM/yy"))
                        .fillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex())
                        .fillPattern(FillPatternType.SOLID_FOREGROUND)
                        .border(BorderStyle.THIN, BorderStyle.THIN, BorderStyle.THIN, BorderStyle.THIN)
                        .alignment(HorizontalAlignment.LEFT)
                        .build());

        cellFormatMap.put(CellFormat.INTEGER_NUMBER_FORMAT_LEFT,
                CellStyleBuilder.newBuilder()
                        .workBook(wb)
                        .font(arial10Font)
                        .border(BorderStyle.THIN, BorderStyle.THIN, BorderStyle.THIN, BorderStyle.THIN)
                        .alignment(HorizontalAlignment.LEFT)
                        .build());

        cellFormatMap.put(CellFormat.INTEGER_NUMBER_FORMAT_LEFT_GRAY,
                CellStyleBuilder.newBuilder()
                        .workBook(wb)
                        .font(arial10Font)
                        .fillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex())
                        .fillPattern(FillPatternType.SOLID_FOREGROUND)
                        .border(BorderStyle.THIN, BorderStyle.THIN, BorderStyle.THIN, BorderStyle.THIN)
                        .alignment(HorizontalAlignment.LEFT)
                        .build());

        cellFormatMap.put(CellFormat.INTEGER_NUMBER_FORMAT_CENTER,
                CellStyleBuilder.newBuilder()
                        .workBook(wb)
                        .font(arial10Font)
                        .border(BorderStyle.THIN, BorderStyle.THIN, BorderStyle.THIN, BorderStyle.THIN)
                        .alignment(HorizontalAlignment.CENTER)
                        .build());

        cellFormatMap.put(CellFormat.INTEGER_NUMBER_FORMAT_CENTER_GRAY,
                CellStyleBuilder.newBuilder()
                        .workBook(wb)
                        .font(arial10Font)
                        .fillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex())
                        .fillPattern(FillPatternType.SOLID_FOREGROUND)
                        .border(BorderStyle.THIN, BorderStyle.THIN, BorderStyle.THIN, BorderStyle.THIN)
                        .alignment(HorizontalAlignment.CENTER)
                        .build());

        cellFormatMap.put(CellFormat.BOLD_CELL_FORMAT_LEFT,
                CellStyleBuilder.newBuilder()
                        .workBook(wb)
                        .font(arial10BoldFont)
                        .dataFormat(df.getFormat("@"))
                        .border(BorderStyle.THIN, BorderStyle.THIN, BorderStyle.THIN, BorderStyle.THIN)
                        .alignment(HorizontalAlignment.LEFT)
                        .build());

        cellFormatMap.put(CellFormat.BOLD_CELL_FORMAT_CENTRE,
                CellStyleBuilder.newBuilder()
                        .workBook(wb)
                        .font(arial10BoldFont)
                        .dataFormat(df.getFormat("@"))
                        .border(BorderStyle.THIN, BorderStyle.THIN, BorderStyle.THIN, BorderStyle.THIN)
                        .alignment(HorizontalAlignment.CENTER)
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
        private BorderStyle borderTop;
        private BorderStyle borderBottom;
        private BorderStyle borderLeft;
        private BorderStyle borderRight;
        private Boolean wrapText;

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

        public CellStyleBuilder borderTop(BorderStyle borderStyle) {
            this.borderTop = borderStyle;
            return this;
        }

        public CellStyleBuilder borderBottom(BorderStyle borderStyle) {
            this.borderBottom = borderStyle;
            return this;
        }

        public CellStyleBuilder borderLeft(BorderStyle borderStyle) {
            this.borderLeft = borderStyle;
            return this;
        }

        public CellStyleBuilder borderRight(BorderStyle borderStyle) {
            this.borderRight = borderStyle;
            return this;
        }

        public CellStyleBuilder border(BorderStyle top, BorderStyle botton, BorderStyle left, BorderStyle right) {
            if (top != null) {
                this.borderTop = top;
            }
            if (botton != null) {
                this.borderBottom = botton;
            }
            if (left != null) {
                this.borderLeft = left;
            }
            if (right != null) {
                this.borderRight = right;
            }
            return this;
        }

        public CellStyleBuilder wrapText(Boolean wrapText) {
            this.wrapText = wrapText;
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
            if (borderTop != null) {
                cellStyle.setBorderTop(borderTop);
            }
            if (borderBottom != null) {
                cellStyle.setBorderBottom(borderBottom);
            }
            if (borderLeft != null) {
                cellStyle.setBorderLeft(borderLeft);
            }
            if (borderRight != null) {
                cellStyle.setBorderRight(borderRight);
            }
            if (wrapText != null) {
                cellStyle.setWrapText(wrapText);
            }
            return cellStyle;
        }
    }

}
