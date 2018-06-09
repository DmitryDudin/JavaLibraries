package ua.com.javatraining;

import com.google.common.collect.Lists;
import common.Payment;
import examples.simple.SimpleReportModel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.junit.Test;
import org.subtlelib.poi.api.sheet.SheetContext;
import org.subtlelib.poi.api.style.Style;
import org.subtlelib.poi.api.style.StyleConfiguration;
import org.subtlelib.poi.api.totals.ColumnTotalsDataRange;
import org.subtlelib.poi.api.totals.Formula;
import org.subtlelib.poi.api.workbook.WorkbookContext;
import org.subtlelib.poi.impl.style.CompositeStyle;
import org.subtlelib.poi.impl.style.defaults.DataStyle;
import org.subtlelib.poi.impl.style.defaults.FontStyle;
import org.subtlelib.poi.impl.style.system.SystemCellWrapTextStyle;
import org.subtlelib.poi.impl.workbook.WorkbookContextFactory;
import ua.com.javatraining.myUtils.ExcelUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collection;
import java.util.stream.IntStream;

import static org.subtlelib.poi.impl.style.defaults.CellStyle.*;

public class SubtlelibExcelReportBuilderTest {

    @Test
    public void simple() throws IOException {
        SimpleReportView view = new SimpleReportView(WorkbookContextFactory.useXlsx());
        WorkbookContext workbook = view.render(new SimpleReportModel().getPayments());
        com.google.common.io.Files.write(workbook.toNativeBytes(), new File("Subtlelib_simple_example.xls"));
    }

    public class SimpleReportView {
        private final WorkbookContextFactory ctxFactory;
        private Collection<Payment> payments;

        public SimpleReportView(WorkbookContextFactory ctxFactory) {
            this.ctxFactory = ctxFactory;
        }

        public WorkbookContext render(Collection<Payment> payments) {
            this.payments = payments;

            WorkbookContext workbookCtx = ctxFactory.createWorkbook();
            SheetContext sheetCtx = workbookCtx.createSheet("Payments");

            // heading
            sheetCtx
                    .nextRow()
                    .skipCell()
                    .header("Amount")
                    .setHeaderStyle(new CompositeStyle(Lists.newArrayList(FontStyle.SECONDARY_HEADER, BORDERS_THIN_ALL, BORDERS_BOTTOM_DOUBLE)))
                    .header("Currency").setColumnWidth(25).setRowHeight(30)
                    .header("Beneficiary").setColumnWidth(25).setHeaderStyle(BORDERS_THIN_ALL)
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
