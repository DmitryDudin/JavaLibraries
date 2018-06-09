package ua.com.javatraining;

import common.Payment;
import examples.simple.SimpleReportModel;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.junit.Test;
import org.subtlelib.poi.api.sheet.SheetContext;
import org.subtlelib.poi.api.style.Style;
import org.subtlelib.poi.api.totals.ColumnTotalsDataRange;
import org.subtlelib.poi.api.totals.Formula;
import org.subtlelib.poi.api.workbook.WorkbookContext;
import org.subtlelib.poi.impl.workbook.WorkbookContextFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collection;

public class SubtlelibExcelReportBuilderTest {

    @Test
    public void simple() throws IOException {
        SimpleReportView view = new SimpleReportView(WorkbookContextFactory.useXlsx());
        WorkbookContext workbook = view.render(new SimpleReportModel().getPayments());
        com.google.common.io.Files.write(workbook.toNativeBytes(), new File("Subtlelib_simple_example.xls"));
    }

    public class SimpleReportView {
        private final WorkbookContextFactory ctxFactory;

        public SimpleReportView(WorkbookContextFactory ctxFactory) {
            this.ctxFactory = ctxFactory;
        }

        public WorkbookContext render(Collection<Payment> payments) {

            WorkbookContext workbookCtx = ctxFactory.createWorkbook();
            SheetContext sheetCtx = workbookCtx.createSheet("Payments");

            // heading
            sheetCtx
                    .nextRow()
                    .skipCell()
                    .header("Amount")
                    .header("Currency").setHeaderStyle(org.subtlelib.poi.impl.style.defaults.CellStyle.BORDERS_THIN_ALL)
                    .header("Beneficiary").setColumnWidth(25).setHeaderStyle(org.subtlelib.poi.impl.style.defaults.CellStyle.BORDERS_THIN_ALL)
                    .header("Payee bank").setColumnWidth(35);

            ColumnTotalsDataRange totalsData = sheetCtx.startColumnTotalsDataRangeFromNextRow();

            // data
            for (Payment payment : payments) {
                sheetCtx
                        .nextRow()
                        .skipCell()
                        .number(payment.getAmount())
                        .text(payment.getCurrency())
                        .text(payment.getBeneficiary())
                        .text(payment.getPayeeBank());
            }

            sheetCtx
                    .nextRow().setTotalsDataRange(totalsData)
                    .header("Total:")
                    .total(Formula.SUM);

            return workbookCtx;
        }
    }


}
