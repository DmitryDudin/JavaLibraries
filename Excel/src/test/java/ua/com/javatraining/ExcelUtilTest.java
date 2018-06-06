package ua.com.javatraining;

import com.sargeraswang.util.ExcelUtil.ExcelUtil;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class ExcelUtilTest {

    // https://github.com/SargerasWang/ExcelUtil

    @Test
    public void simpleTest() throws IOException {
        Map<String, String> headers = new HashMap<>();
        headers.put("name", "Name");
        headers.put("birthDate", "Bithdate");
        headers.put("payment", "Payment");
        headers.put("bonus", "Bonus");
        Collection<Employee> dataset = Employee.generateSampleEmployeeData(100);
        OutputStream out = new FileOutputStream("excelUtilResult.xlsx");
        ExcelUtil.exportExcel(headers, dataset, out);
        out.close();
    }
}
