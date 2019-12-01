package ua.com.javatraining;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.*;

public class FilesTest {
    @Test
    public void shouldCreateFileFromInputStream() throws IOException {
        InputStream inputStream = new ByteArrayInputStream(new byte[]{12, 3, 4, 5, 6, 67});
        FileUtils.copyInputStreamToFile(inputStream, new File("newFile"));
    }

}
