package ua.com.javatraining;

import com.sargeraswang.util.ExcelUtil.ExcelCell;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.fluttercode.datafactory.impl.DataFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Employee {
    @ExcelCell(index = 0)
    private String name;
    @ExcelCell(index = 1)
    private Date birthDate;
    @ExcelCell(index = 2)
    private BigDecimal payment;
    @ExcelCell(index = 3)
    private BigDecimal bonus;

    public static List<Employee> generateSampleEmployeeData(Integer limit) {
        List<Employee> result = new ArrayList<>();
        DataFactory df = new DataFactory();
        int seed = 1234567890;
        df.randomize(seed);

        Stream.iterate(0, i -> i + 1)
                .limit(limit)
                .forEach(i -> {
                    result.add(Employee.builder()
                            .name(df.getName())
                            .birthDate(df.getBirthDate())
                            .payment(new BigDecimal(Math.random()))
                            .bonus(new BigDecimal(Math.random()))
                            .build());
                });
        return result;
    }
}
