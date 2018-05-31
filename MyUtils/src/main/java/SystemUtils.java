import org.apache.commons.collections.MapUtils;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.Properties;

public class SystemUtils {

    public Properties getSustemProperties() {
        System.out.println(System.getProperty("java.classpath"));
        Properties properties = System.getProperties();
        return properties;
    }

    public void printCurrentProjectClassPath() {
        ClassLoader cl = ClassLoader.getSystemClassLoader();

        URL[] urls = ((URLClassLoader) cl).getURLs();

        for (URL url : urls) {
            System.out.println(url.getFile());
        }
    }

    public void printCurrentProjectClassPath2() {
        String cp = System.getProperty("java.class.path");
        System.out.println("cp - " + cp);
    }

    public void prettyPrintEnvironmentVariables() {
        MapUtils.debugPrint(System.out, "System environment", System.getenv());
    }
}
