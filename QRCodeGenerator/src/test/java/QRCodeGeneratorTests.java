import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.*;

public class QRCodeGeneratorTests {

    @Test
    public void generateQRCode() throws URISyntaxException, IOException {
//        String data = "google.com.ua";//data for coding!!!
        String data = "http://tutorials.jenkov.com/java-nio/path.html";//data for coding!!!

        ByteArrayOutputStream stream = QRCode.from(data).to(ImageType.PNG).stream();

//first variant with absolute path
//        URI uri = new URI("file:///home/dmitry/Documents/JavaLibraries/QRCodeGenerator/destinationFolder/forDelete.png");
//        Path path = Paths.get(uri);
//        Files.write(path, stream.toByteArray(), StandardOpenOption.CREATE);

//second variant with relative paths
        Path relative2 = Paths.get(Paths.get("").toString(), "destinationFolder/forDelete.png");
        Path relative1 = FileSystems.getDefault().getPath("destinationFolder/forDelete.png");//!!!
        Files.write(relative2, stream.toByteArray(), StandardOpenOption.CREATE);

//third variant with FileOutputStream
//        File file = QRCode.from(data).to(ImageType.PNG).file();
//        FileOutputStream fos = new FileOutputStream("/home/dmitry/dev/tsa-be/src/main/java/com/tamisct/tsa/util/forDelete.png");
//        fos.write(stream.toByteArray());
//        fos.flush();
//        fos.close();

        System.out.println();//finish for debug
    }
}
