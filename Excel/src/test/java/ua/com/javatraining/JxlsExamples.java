package ua.com.javatraining;

import org.junit.Test;
import org.jxls.template.SimpleExporter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

public class JxlsExamples {

    //excel file generator from template

    @Test
    public void simpleExporter() throws IOException {
        try (OutputStream os1 = new FileOutputStream("simple_export_output1.xls")) {
            List<Employee> employees = Employee.generateSampleEmployeeData(10);
            List<String> headers = Arrays.asList("Name", "Birthday", "Payyyment");
            SimpleExporter exporter = new SimpleExporter();
            exporter.gridExport(headers, employees, "name, birthDate, payment", os1);

            /*now let's show how to register custom template
            try (InputStream is = SimpleExporterDemo.class.getResourceAsStream(template)) {
                try (OutputStream os2 = new FileOutputStream("target/simple_export_output2.xlsx")) {
                    exporter.registerGridTemplate(is);
                    headers = Arrays.asList("Name", "Payment", "Birth Date");
                    exporter.gridExport(headers, employees, "name,payment, birthDate,", os2);
                }
            }*/
        }
    }

    @Test
    public void simpleExporterWithTemplate() throws IOException {
        List<Employee> employees = Employee.generateSampleEmployeeData(10);
        SimpleExporter exporter = new SimpleExporter();

        //now let's show how to register custom template
        InputStream i1s = this.getClass().getResourceAsStream("grid_template.xls");
        try (InputStream is = this.getClass().getClassLoader().getResourceAsStream("grid_template.xls")) {
            try (OutputStream os2 = new FileOutputStream("simple_export_output2.xlsx")) {
                exporter.registerGridTemplate(is);
                List<String> headers = Arrays.asList("Name", "Payment", "Birth Date", "Booonus");
                exporter.gridExport(headers, employees, "name,payment, birthDate,", os2);
            }
        }
    }

    @Test
    public void transformer() {
    }

}
