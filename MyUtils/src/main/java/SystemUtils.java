import java.util.Properties;

public class SystemUtils {

    public Properties getSustemProperties() {
        System.out.println(System.getProperty("java.classpath"));
        Properties properties = System.getProperties();
        return properties;
    }
}
