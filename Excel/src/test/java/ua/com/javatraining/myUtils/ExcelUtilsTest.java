package ua.com.javatraining.myUtils;

import org.junit.Test;
import ua.com.javatraining.Employee;

import java.io.*;
import java.util.List;

public class ExcelUtilsTest {

    @Test
    public void createExcelFile() throws IOException {
        List<Employee> employees = Employee.generateSampleEmployeeData(10000);

        ByteArrayOutputStream outputStream = ExcelReportGenerator.generateExcelXlsxFile(employees);
        OutputStream os1 = new FileOutputStream("myExcelUtils_output.xls");
        outputStream.writeTo(os1);
        outputStream.close();
        os1.close();
    }
}
