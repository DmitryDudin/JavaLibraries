import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import org.apache.commons.lang3.time.DateUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class CsvReaderClass {

    public static List<StudentProfile> mapCsvStringToEnitty(String csvString) throws ParseException {

        List<StudentProfile> studentProfiles = null;
        try (com.opencsv.CSVReader reader = new com.opencsv.CSVReader(new BufferedReader(new StringReader(csvString), ','))) {
            ColumnPositionMappingStrategy<StudentProfile> beanStrategy = new ColumnPositionMappingStrategy<>();
            beanStrategy.setType(StudentProfile.class);
            beanStrategy.setColumnMapping(new String[]{"name", "grade", "birthdayString", "email", "phone", "address"});
            CsvToBean<StudentProfile> csvToBean = new CsvToBean<>();
            studentProfiles = csvToBean.parse(beanStrategy, reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (StudentProfile studentProfile : studentProfiles) {
            Date birthDay = DateUtils.parseDate(studentProfile.getBirthdayString(), new String[]{"dd-MM-yyyy", "dd/MM/yyyy"});
//            Date birthDay = DateUtils.parseDate(studentProfile.getBirthdayString(), "dd-MM-yyyy", "dd/MM/yyyy");
            birthDay = DateTimeUtil.getDateForTimeZone("UTC", birthDay);
            studentProfile.setBirthday(birthDay);
        }
        return studentProfiles;
    }

    /*public static void main(String[] args) throws ParseException {
        String csvString = "Mike, 5, 01-01-2000, mike@gmail.com, 112233, NewYork\n"+
                "John, 10, 09-09-2009, john@gmail.com, 22222, Mayami\n";
        CsvReaderClass.mapCsvStringToEnitty(csvString);
    }*/
}
