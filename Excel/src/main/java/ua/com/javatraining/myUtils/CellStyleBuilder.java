package ua.com.javatraining.myUtils;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;

public class CellStyleBuilder {

    private Workbook workbook;
    private Font font;

    public static CellStyleBuilder newBuilder(Workbook workbook) {
//        this.wb = workbook;
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

    private CellStyle build() {
//        return new CellStyle(border, format, alignment, fontSize, fontColour, fill);
        return workbook.createCellStyle();
    }

}
