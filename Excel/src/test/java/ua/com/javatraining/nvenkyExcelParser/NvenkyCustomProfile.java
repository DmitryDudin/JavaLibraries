package ua.com.javatraining.nvenkyExcelParser;

import lombok.*;
import org.javafunk.excelparser.annotations.ExcelField;
import org.javafunk.excelparser.annotations.ExcelObject;
import org.javafunk.excelparser.annotations.ParseType;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ExcelObject(parseType = ParseType.ROW, start = 2) //ignoreAllZerosOrNullRows = true
public class NvenkyCustomProfile {

    @ExcelField(position = 1)
    private String id;
    @ExcelField(position = 2)
    private String name;
    @ExcelField(position = 3)
    private String password;

}
