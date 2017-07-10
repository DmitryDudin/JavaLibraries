import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class TestClass {

    private static String csvString;

    /*@Before
    private static void prepareStr() {
        csvString = "Mike, 5, 01-01-2000, mike@gmail.com, 112233, NewYork";
    }*/

    @Test
    public void testWithSringReadercd() throws ParseException {
        System.out.println();
        List<StudentProfile> studentProfiles = null;
        try (CSVReader reader = new CSVReader(new BufferedReader(new StringReader(csvString), ','))) {
            ColumnPositionMappingStrategy<StudentProfile> beanStrategy = new ColumnPositionMappingStrategy<>();
            beanStrategy.setType(StudentProfile.class);
            beanStrategy.setColumnMapping(new String[]{"name", "grade", "birthdayString", "email", "phone", "address"});
            CsvToBean<StudentProfile> csvToBean = new CsvToBean<>();
            studentProfiles = csvToBean.parse(beanStrategy, reader);
        } catch (IOException e) {

        }

        for (StudentProfile studentProfile : studentProfiles) {
            Date birthDay = DateUtils.parseDate(studentProfile.getBirthdayString(), new String[]{"dd-MM-yyyy", "dd/MM/yyyy"});
//            birthDay = DateTimeUtil.getDateForTimeZone("UTC", birthDay);
            studentProfile.setBirthday(birthDay);
        }
//        return studentProfiles;
    }

    @Test
    public void testss(){
        System.out.println();

    }
}
