package ua.com.javatraining.poi;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xddf.usermodel.*;
import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xssf.usermodel.*;
import org.junit.Test;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;

import java.io.FileOutputStream;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class SimpleBarChart {
    //todo delete
//    POI Line chart axis position and other issues  -  https://stackoverflow.com/questions/36447925/poi-line-chart-axis-position-and-other-issues
//    Create Bar Chart in Excel with Apache POI  -  https://stackoverflow.com/questions/38913412/create-bar-chart-in-excel-with-apache-poi
//    Create Bar Chart in XLSX File - POI JFreeChart - Java Example Program  -  http://thinktibits.blogspot.com/2012/12/Java-POI-Bar-Chart-XLSX-Example-Program.html
//    BarChart  -  https://svn.apache.org/repos/asf/poi/trunk/src/examples/src/org/apache/poi/xssf/usermodel/examples/BarChart.java
// !!!chhunchha/Excel-Chart-Using-Java  createChart  ->  https://github.com/chhunchha/Excel-Chart-Using-Java/blob/master/src/com/programmingfree/excelexamples/CreateExcelFile.java

    /*How to add Data Label for a Bar chart in PPT using POI and org.openxmlformats in Java
    https://stackoverflow.com/questions/47218804/how-to-add-data-label-for-a-bar-chart-in-ppt-using-poi-and-org-openxmlformats-in*/

    @Test
    public void name() throws IOException {
        try (XSSFWorkbook wb = new XSSFWorkbook()) {
            XSSFSheet sheet = wb.createSheet("barchart");
            final int NUM_OF_ROWS = 3;
            final int NUM_OF_COLUMNS = 10;

            // Create a row and put some cells in it. Rows are 0 based.
            Row row;
            Cell cell;
            for (int rowIndex = 0; rowIndex < NUM_OF_ROWS; rowIndex++) {
                row = sheet.createRow((short) rowIndex);
                for (int colIndex = 0; colIndex < NUM_OF_COLUMNS; colIndex++) {
                    cell = row.createCell((short) colIndex);
                    cell.setCellValue(colIndex * (rowIndex + 1));
                }
            }

            XSSFDrawing drawing = sheet.createDrawingPatriarch();
            XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0, 5, 10, 15);

            XDDFChart chart = drawing.createChart(anchor);
            XDDFShapeProperties chartShapeProperties = chart.getOrAddShapeProperties();
            chartShapeProperties.setFillProperties(getYellowFillProperties());
            chartShapeProperties.setLineProperties(getLineProperties());

            XDDFChartLegend legend = chart.getOrAddLegend();
            legend.setPosition(LegendPosition.BOTTOM);

            // Use a category axis for the bottom axis.

            //bottom axis
//            XDDFCategoryAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
            XDDFDateAxis bottomAxis = chart.createDateAxis(AxisPosition.BOTTOM);
            bottomAxis.setVisible(true);
            XDDFShapeProperties orAddShapeProperties = bottomAxis.getOrAddShapeProperties();
//            XDDFShapeProperties orAddMajorGridProperties = bottomAxis.getOrAddMajorGridProperties();
//            XDDFShapeProperties orAddMinorGridProperties = bottomAxis.getOrAddMinorGridProperties();
            orAddShapeProperties.setFillProperties(getBlackFillProperties());
//            orAddShapeProperties.setBlackWhiteMode(BlackWhiteMode.BLACK);
//            orAddMajorGridProperties.setBlackWhiteMode(BlackWhiteMode.BLACK);
//            XDDFLineProperties lineProperties = new XDDFLineProperties();
//            lineProperties.setWidth(100);
//            lineProperties.setFillProperties(setFillProperties);
//            lineProperties.setCompoundLine(CompoundLine.DOUBLE);
//            orAddMajorGridProperties.setLineProperties(lineProperties);
//            orAddMinorGridProperties.setBlackWhiteMode(BlackWhiteMode.BLACK);
//            bottomAxis.setCrosses(AxisCrosses.MAX);

            //left Axis
            XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
            leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);
            leftAxis.setVisible(true);

            //data
            XDDFDataSource<Double> xs = XDDFDataSourcesFactory.fromNumericCellRange(sheet, new CellRangeAddress(0, 0, 0, NUM_OF_COLUMNS - 1));
            XDDFNumericalDataSource<Double> ys1 = XDDFDataSourcesFactory.fromNumericCellRange(sheet, new CellRangeAddress(1, 1, 0, NUM_OF_COLUMNS - 1));
            XDDFNumericalDataSource<Double> ys2 = XDDFDataSourcesFactory.fromNumericCellRange(sheet, new CellRangeAddress(2, 2, 0, NUM_OF_COLUMNS - 1));
            //data color
            XDDFChartData data = chart.createData(ChartTypes.BAR, bottomAxis, leftAxis);
            data.addSeries(xs, ys1);
            data.addSeries(xs, ys2);
            chart.plot(data);

            XDDFSolidFillProperties fill = new XDDFSolidFillProperties(XDDFColor.from(PresetColor.GREEN));
            XDDFSolidFillProperties secondFill = new XDDFSolidFillProperties(XDDFColor.from(PresetColor.DARK_RED));
            XDDFChartData.Series firstSeries = data.getSeries().get(0);
            XDDFChartData.Series secondSeries = data.getSeries().get(1);
            XDDFShapeProperties properties = firstSeries.getShapeProperties();
            XDDFShapeProperties secondProperties = secondSeries.getShapeProperties();
            if (properties == null) {
                properties = new XDDFShapeProperties();
                secondProperties = new XDDFShapeProperties();
            }
            properties.setFillProperties(fill);
            secondProperties.setFillProperties(secondFill);
            firstSeries.setShapeProperties(properties);
            secondSeries.setShapeProperties(secondProperties);

            System.out.println(chart);

            // Write the output to a file
            try (FileOutputStream fileOut = new FileOutputStream("ooxml-bar-chart.xlsx")) {
                wb.write(fileOut);
            }
        }
    }

    @Test
    public void name2() throws IOException {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet();

        /*final int NUM_OF_ROWS = 3;
        final int NUM_OF_COLUMNS = 10;

        // Create a row and put some cells in it. Rows are 0 based.
        Row row;
        Cell cell;
        for (int rowIndex = 0; rowIndex < NUM_OF_ROWS; rowIndex++) {
            row = sheet.createRow((short) rowIndex);
            for (int colIndex = 0; colIndex < NUM_OF_COLUMNS; colIndex++) {
                cell = row.createCell((short) colIndex);
                cell.setCellValue(colIndex * (rowIndex + 1));
            }
        }*/

        XSSFDrawing drawing = sheet.createDrawingPatriarch();
        XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 1, 1, 10, 30);
        XSSFChart chart = drawing.createChart(anchor);
        XDDFDateAxis axis = chart.createDateAxis(AxisPosition.BOTTOM);

        axis.setCrosses(AxisCrosses.AUTO_ZERO);
        axis.setLogBase(100.0);
        assertEquals(axis.getCrosses(), AxisCrosses.AUTO_ZERO);

        assertEquals(chart.getAxes().size(), 1);

        try (FileOutputStream fileOut = new FileOutputStream("1234.xlsx")) {
            wb.write(fileOut);
        }
    }

    @Test
    public void line() throws IOException {
        try (XSSFWorkbook wb = new XSSFWorkbook()) {
            XSSFSheet sheet = wb.createSheet("linechart");
            final int NUM_OF_ROWS = 3;
            final int NUM_OF_COLUMNS = 10;

            // Create a row and put some cells in it. Rows are 0 based.
            Row row;
            Cell cell;
            for (int rowIndex = 0; rowIndex < NUM_OF_ROWS; rowIndex++) {
                row = sheet.createRow((short) rowIndex);
                for (int colIndex = 0; colIndex < NUM_OF_COLUMNS; colIndex++) {
                    cell = row.createCell((short) colIndex);
                    cell.setCellValue(colIndex * (rowIndex + 1));
                }
            }

            XSSFDrawing drawing = sheet.createDrawingPatriarch();
            XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0, 5, 10, 15);

            XSSFChart chart = drawing.createChart(anchor);
            XDDFChartLegend legend = chart.getOrAddLegend();
            legend.setPosition(LegendPosition.TOP_RIGHT);

            // Use a category axis for the bottom axis.
            XDDFCategoryAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
            XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
            leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);

            XDDFDataSource<Double> xs = XDDFDataSourcesFactory.fromNumericCellRange(sheet, new CellRangeAddress(0, 0, 0, NUM_OF_COLUMNS - 1));
            XDDFNumericalDataSource<Double> ys1 = XDDFDataSourcesFactory.fromNumericCellRange(sheet, new CellRangeAddress(1, 1, 0, NUM_OF_COLUMNS - 1));
            XDDFNumericalDataSource<Double> ys2 = XDDFDataSourcesFactory.fromNumericCellRange(sheet, new CellRangeAddress(2, 2, 0, NUM_OF_COLUMNS - 1));

            XDDFChartData data = chart.createData(ChartTypes.LINE, bottomAxis, leftAxis);
            data.addSeries(xs, ys1);
            data.addSeries(xs, ys2);
            chart.plot(data);

            // Write the output to a file
            try (FileOutputStream fileOut = new FileOutputStream("ooxml-line-chart.xlsx")) {
                wb.write(fileOut);
            }
        }
    }

    @Test
    public void scatterChart() throws IOException {
        try (XSSFWorkbook wb = new XSSFWorkbook()) {
            XSSFSheet sheet = wb.createSheet("Sheet 1");
            final int NUM_OF_ROWS = 3;
            final int NUM_OF_COLUMNS = 10;

            // Create a row and put some cells in it. Rows are 0 based.
            Row row;
            Cell cell;
            for (int rowIndex = 0; rowIndex < NUM_OF_ROWS; rowIndex++) {
                row = sheet.createRow((short) rowIndex);
                for (int colIndex = 0; colIndex < NUM_OF_COLUMNS; colIndex++) {
                    cell = row.createCell((short) colIndex);
                    cell.setCellValue(colIndex * (rowIndex + 1));
                }
            }

            XSSFDrawing drawing = sheet.createDrawingPatriarch();
            XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0, 5, 10, 15);

            XSSFChart chart = drawing.createChart(anchor);
            XDDFChartLegend legend = chart.getOrAddLegend();
            legend.setPosition(LegendPosition.TOP_RIGHT);

            XDDFValueAxis bottomAxis = chart.createValueAxis(AxisPosition.BOTTOM);
            XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
            leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);

            XDDFDataSource<Double> xs = XDDFDataSourcesFactory.fromNumericCellRange(sheet, new CellRangeAddress(0, 0, 0, NUM_OF_COLUMNS - 1));
            XDDFNumericalDataSource<Double> ys1 = XDDFDataSourcesFactory.fromNumericCellRange(sheet, new CellRangeAddress(1, 1, 0, NUM_OF_COLUMNS - 1));
            XDDFNumericalDataSource<Double> ys2 = XDDFDataSourcesFactory.fromNumericCellRange(sheet, new CellRangeAddress(2, 2, 0, NUM_OF_COLUMNS - 1));


            XDDFChartData data = chart.createData(ChartTypes.SCATTER, bottomAxis, leftAxis);

            data.addSeries(xs, ys1);
            data.addSeries(xs, ys2);
            chart.plot(data);

            // Write the output to a file
            try (FileOutputStream fileOut = new FileOutputStream("ooxml-scatter-chart.xlsx")) {
                wb.write(fileOut);
            }
        }
    }

    public XDDFLineProperties getLineProperties() {
        XDDFLineProperties xddfLineProperties = new XDDFLineProperties();
        xddfLineProperties.setCompoundLine(CompoundLine.DOUBLE);
        xddfLineProperties.setWidth(2);
        xddfLineProperties.setLineCap(LineCap.FLAT);
        xddfLineProperties.setFillProperties(getGreenFillProperties());
        return xddfLineProperties;
    }

    private XDDFFillProperties getYellowFillProperties() {
        XDDFSolidFillProperties solidFillProperties = new XDDFSolidFillProperties(XDDFColor.from(PresetColor.YELLOW));
        return solidFillProperties;
    }
    private XDDFFillProperties getBlackFillProperties() {
        XDDFSolidFillProperties solidFillProperties = new XDDFSolidFillProperties(XDDFColor.from(PresetColor.BLACK));
        return solidFillProperties;
    }
    private XDDFFillProperties getGreenFillProperties() {
        XDDFSolidFillProperties solidFillProperties = new XDDFSolidFillProperties(XDDFColor.from(PresetColor.GREEN));
        return solidFillProperties;
    }

}
