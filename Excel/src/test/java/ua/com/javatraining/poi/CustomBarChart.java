package ua.com.javatraining.poi;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xddf.usermodel.*;
import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.SchemaType;
import org.junit.Test;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBarChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBarDir;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChart;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class CustomBarChart {

    @Test
    public void barChart() throws IOException {
        try (XSSFWorkbook wb = new XSSFWorkbook()) {
            XSSFSheet sheet = wb.createSheet("barchart");

            final int NUM_OF_ROWS = 4;
            final int NUM_OF_COLUMNS = 10;
            {//values
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
            }

            XSSFDrawing drawing = sheet.createDrawingPatriarch();

            //anchor
//		col1 - the column (0 based) of the first cell.
//		row1 - the row (0 based) of the first cell.
//		col2 - the column (0 based) of the second cell.
//		row2 - the row (0 based) of the second cell.
            XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0, 5, 10, 15);

            //two variant chart creation
            XDDFChart xddfChart = drawing.createChart(anchor);
//            XSSFChart xssfChart = drawing.createChart(anchor);

            //legend
//            XSSFChartLegend xssfChartLegend = xssfChart.getOrCreateLegend();
//            xssfChartLegend.setPosition(LegendPosition.RIGHT);
            XDDFChartLegend xddfChartLegend = xddfChart.getOrAddLegend();
            xddfChartLegend.setPosition(org.apache.poi.xddf.usermodel.chart.LegendPosition.BOTTOM);

            //axis
//            MajorTickMark - большая цена деления
//            MinorTickMark - меньшая цена деления
                /*AxisTickMark.IN - цена деления смотрит внутрь
                  AxisTickMark.OUT - цена деления смотрит во вне
                  AxisTickMark.CROSS - цена деления смотрит и во внутрь и во вне*/
            XDDFValueAxis leftAxis = xddfChart.createValueAxis(AxisPosition.LEFT);
            XDDFDateAxis bottomAxis = xddfChart.createDateAxis(AxisPosition.BOTTOM);
//            leftAxis.setMajorUnit(5d);
//            bottomAxis.setMajorUnit(10d);
            System.out.println(bottomAxis.getMajorUnit());
//            leftAxis.setMinorUnit(2d);
//            bottomAxis.setMinorUnit(2d);
            System.out.println(bottomAxis.getMinorUnit());
            leftAxis.setMaximum(50d);
            bottomAxis.setMaximum(20d);
            System.out.println(bottomAxis.getMaximum());
//            leftAxis.setMinimum(-100d);
//            bottomAxis.setMinimum(-5d);
            System.out.println(bottomAxis.getMinimum());
//            leftAxis.setMajorTickMark(AxisTickMark.CROSS);
//            bottomAxis.setMajorTickMark(AxisTickMark.CROSS);
            System.out.println(bottomAxis.getMajorTickMark());
//            leftAxis.setMinorTickMark(AxisTickMark.CROSS);
//            bottomAxis.setMinorTickMark(AxisTickMark.CROSS);
            System.out.println(bottomAxis.getMinorTickMark());
//            leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);
//            leftAxis.setCrosses(AxisCrosses.MAX);
//            bottomAxis.setCrosses(AxisCrosses.MAX);

            CTChart ctChart = xddfChart.getCTChart();
//            ctChart.

            //data
//            lastRow - numOfPoints
//            String dataRange = xddfChart.formatRange(new CellRangeAddress(1, 10, 0, 0));
//            Sheet0!$A$2:$J$2
            String dataRange = xddfChart.formatRange(new CellRangeAddress(1, 1, 0, NUM_OF_COLUMNS - 1));
            XDDFNumericalDataSource<Integer> arrayDataSource = XDDFDataSourcesFactory
                    .fromArray(new Integer[]{3, 70, 3, 10, 3, 10, 3, 10, 3, 10}, dataRange, 50);
//            ChartDataSource<Number> xs = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(dataRangeStartRow, dataRangeEndRow, 0, 0));
//            ChartDataSource<Number> ys = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(dataRangeStartRow, dataRangeEndRow, 1, 1));

            XDDFDataSource<Double> xs = XDDFDataSourcesFactory
                    .fromNumericCellRange(sheet, new CellRangeAddress(0, 0, 0, NUM_OF_COLUMNS - 1));
            XDDFNumericalDataSource<Double> ys1 = XDDFDataSourcesFactory
                    .fromNumericCellRange(sheet, new CellRangeAddress(1, 1, 0, NUM_OF_COLUMNS - 1));
//            XDDFNumericalDataSource<Double> ys2 = XDDFDataSourcesFactory
//                    .fromNumericCellRange(sheet, new CellRangeAddress(2, 2, 0, NUM_OF_COLUMNS - 1));

            //todo ???
//            XDDFChartData data = xddfChart.createData(ChartTypes.BAR, bottomAxis, leftAxis);
            XDDFBarChartData data = (XDDFBarChartData) xddfChart.createData(ChartTypes.BAR, bottomAxis, leftAxis);
            data.addSeries(xs, arrayDataSource);
            data.addSeries(xs, ys1);
//            data.addSeries(xs, ys2);
            xddfChart.plot(data);

            /*data.chart.setBarDir(CTBarDir.Factory.newInstance());
            data.setBarDirection(BarDirection.BAR);
            data.setGapWidth(500);*/

            //data color
            setGreenFillProperties(0, data, getAquaFillProperties());
            setGreenFillProperties(1, data, getGreenFillProperties());
//            setGreenFillProperties(2, data, getYellowFillProperties());

            // Write the output to a file
            try (FileOutputStream fileOut = new FileOutputStream("CustomBarChart.xlsx")) {
                wb.write(fileOut);
            }

        }
    }

    public XDDFSolidFillProperties getBlackFillProperties() {
        XDDFSolidFillProperties fill = new XDDFSolidFillProperties(XDDFColor.from(PresetColor.BLACK));
        return fill;
    }

    public XDDFSolidFillProperties getGreenFillProperties() {
        XDDFSolidFillProperties fill = new XDDFSolidFillProperties(XDDFColor.from(PresetColor.GREEN));
        return fill;
    }

    public XDDFSolidFillProperties getAquaFillProperties() {
        XDDFSolidFillProperties fill = new XDDFSolidFillProperties(XDDFColor.from(PresetColor.AQUA));
        return fill;
    }

    public XDDFFillProperties getYellowFillProperties() {
        XDDFSolidFillProperties fill = new XDDFSolidFillProperties(XDDFColor.from(PresetColor.YELLOW));
        return fill;
    }

    public void setGreenFillProperties(int seriesNum, XDDFChartData data, XDDFFillProperties fillProperties) {
        XDDFChartData.Series secondSeries = data.getSeries().get(seriesNum);
        XDDFShapeProperties secondSeriesProperties = secondSeries.getShapeProperties();
        if (secondSeriesProperties == null) {
            secondSeriesProperties = new XDDFShapeProperties();
        }
        secondSeriesProperties.setFillProperties(fillProperties);
        secondSeries.setShapeProperties(secondSeriesProperties);
    }

}
