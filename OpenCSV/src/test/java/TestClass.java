import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

public class TestClass {

    private static String csvString;

    @Before
    public void prepareStr() {
        csvString = "Mike, 5, 01-01-2000, mike@gmail.com, 112233, NewYork\n" +
                "John, 10, 09-09-2009, john@gmail.com, 22222, Chicago\n";
    }


    @Test
    public void testss() throws ParseException {
        CsvReaderClass.mapCsvStringToEnitty(csvString);
    }
}

//  http://www.journaldev.com/12014/opencsv-csvreader-csvwriter-example
//  https://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
