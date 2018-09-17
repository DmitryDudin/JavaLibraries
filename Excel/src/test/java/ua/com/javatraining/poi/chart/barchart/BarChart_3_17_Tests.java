package ua.com.javatraining.poi.chart.barchart;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.openxmlformats.schemas.drawingml.x2006.chart.*;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;

import java.io.FileOutputStream;

public class BarChart_3_17_Tests {

    @Test
    public void main() throws Exception {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("Sheet1");

        Row row;
        Cell cell;

        row = sheet.createRow(0);
        row.createCell(0);
        row.createCell(1).setCellValue("HEADER 1");
        row.createCell(2).setCellValue("HEADER 2");
        row.createCell(3).setCellValue("HEADER 3");

        for (int r = 1; r < 5; r++) {
            row = sheet.createRow(r);
            cell = row.createCell(0);
            cell.setCellValue("Serie " + r);
            cell = row.createCell(1);
//            cell.setCellValue(new java.util.Random().nextDouble());
            cell.setCellValue(1 + r);
            cell = row.createCell(2);
//            cell.setCellValue(new java.util.Random().nextDouble());
            cell.setCellValue(2 + r);
            cell = row.createCell(3);
//            cell.setCellValue(new java.util.Random().nextDouble());
            cell.setCellValue(3 + r);
        }

        XSSFDrawing drawing = sheet.createDrawingPatriarch();
        ClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0, 5, 8, 20);

        XSSFChart chart = drawing.createChart(anchor);

        CTChart ctChart = ((XSSFChart) chart).getCTChart();
        CTPlotArea ctPlotArea = ctChart.getPlotArea();
        CTBarChart ctBarChart = ctPlotArea.addNewBarChart();
        CTBoolean ctBoolean = ctBarChart.addNewVaryColors();
        ctBoolean.setVal(true);
        ctBarChart.addNewBarDir().setVal(STBarDir.COL);


//        for (int r = 2; r < 6; r++) {
        // add series
        CTBarSer ctBarSer = ctBarChart.addNewSer();
        ctBarSer.addNewIdx().setVal(0);

        //title
        CTSerTx ctSerTx = ctBarSer.addNewTx();
        CTStrRef ctStrRef = ctSerTx.addNewStrRef();
//            ctStrRef.setF("Sheet1!$A$" + r);//Serie 1,  Serie 2,  Serie 3,  Serie 4
//            ctStrRef.setF("Sheet1!$B$1");//Serie 1,  Serie 2,  Serie 3,  Serie 4
//        ctStrRef.setF("Sheet1!$B$1");

//            ctBarSer.addNewIdx().setVal(r - 2);

        // Serie title
        CTAxDataSource cttAxDataSource = ctBarSer.addNewCat();
        ctStrRef = cttAxDataSource.addNewStrRef();
//            ctStrRef.setF("Sheet1!$B$1:$D$1");//HEADER 1	HEADER 2	HEADER 3
//            ctStrRef.setF("Sheet1!$B$1");
        String formatRange = chart.formatRange(new CellRangeAddress(1, 4, 0, 0));
        ctStrRef.setF("Sheet1!$A$2:$A$5");
//            ctStrRef.setF("Sheet1!$A$"+r);

        // Serie value
        CTNumDataSource ctNumDataSource = ctBarSer.addNewVal();
        CTNumRef ctNumRef = ctNumDataSource.addNewNumRef();
//            ctNumRef.setF("Sheet1!$B$" + r + ":$D$" + r);//values
//            ctNumRef.setF("Sheet1!$B$" + r);
        ctNumRef.setF("Sheet1!$B$2:$B$5");

        //at least the border lines in Libreoffice Calc ;-)
        ctBarSer.addNewSpPr().addNewLn().addNewSolidFill().addNewSrgbClr().setVal(new byte[]{0, 0, 0});

//        }


        //telling the BarChart that it has axes and giving them Ids
        ctBarChart.addNewAxId().setVal(123456);
        ctBarChart.addNewAxId().setVal(123457);

        //cat axis - Category Axis Data
        CTCatAx ctCatAx = ctPlotArea.addNewCatAx();
        ctCatAx.addNewAxId().setVal(123456); //id of the cat axis
        CTScaling ctScaling = ctCatAx.addNewScaling();
        ctScaling.addNewOrientation().setVal(STOrientation.MIN_MAX);
        ctCatAx.addNewDelete().setVal(false);
        ctCatAx.addNewAxPos().setVal(STAxPos.B);
        ctCatAx.addNewCrossAx().setVal(123457); //id of the val axis
        ctCatAx.addNewTickLblPos().setVal(STTickLblPos.NEXT_TO);

        //val axis
        CTValAx ctValAx = ctPlotArea.addNewValAx();
        ctValAx.addNewAxId().setVal(123457); //id of the val axis
        ctScaling = ctValAx.addNewScaling();
        ctScaling.addNewOrientation().setVal(STOrientation.MIN_MAX);
        ctScaling.addNewMin().setVal(-10d);
        ctValAx.addNewDelete().setVal(false);
        ctValAx.addNewAxPos().setVal(STAxPos.L);
        ctValAx.addNewCrossAx().setVal(123456); //id of the cat axis
        ctValAx.addNewTickLblPos().setVal(STTickLblPos.NEXT_TO);

        //legend
        CTLegend ctLegend = ctChart.addNewLegend();
        ctLegend.addNewLegendPos().setVal(STLegendPos.B);
        ctLegend.addNewOverlay().setVal(false);

        System.out.println(ctChart);

        FileOutputStream fileOut = new FileOutputStream("BarChart.xlsx");
        wb.write(fileOut);
        fileOut.close();
    }
}
