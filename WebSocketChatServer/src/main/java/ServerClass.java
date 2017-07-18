import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.net.URLClassLoader;

//import org.apache.catalina.startup.Tomcat;
public class ServerClass {
//    https://github.com/benas/webapp-embedded-tomcat
//    https://github.com/benas/web-socket-lab
//    https://devcenter.heroku.com/articles/create-a-java-web-application-using-embedded-tomcat
//    https://github.com/heroku/devcenter-embedded-tomcat
//    https://tomcat.apache.org/tomcat-7.0-doc/api/org/apache/catalina/startup/Embedded.html
//    http://www.jofre.de/?p=1227

    /*public static void main(String[] args) throws IOException, ServletException, LifecycleException, InterruptedException {
        String webappDirLocation = "WebSocketChatServer/src/main/webapp/";

        ClassLoader cl = ClassLoader.getSystemClassLoader();

        URL[] urls = ((URLClassLoader) cl).getURLs();

        for (URL url : urls) {
            System.out.println(url.getFile());
        }
        System.out.println("---------------------------");
//        Thread.sleep(100000);

        String contextPath = "";
//        String appBase = args[0]; // absolute path to target/${project.artifactId}-${project.version}
        String appBase = "/home/dmitry/dev/from_github/webapp-embedded-tomcat/target/webapp-embedded-tomcat-1.0.0-SNAPSHOT";
//        String appBase = "/home/dmitry/dev/JavaLibraries/WebSocketChatServer/build/libs/WebSocketChatServer-1.0-SNAPSHOT";
//        System.out.println(appBase);
//        int port = args.length > 1 ? parseInt(args[1]) : 8080;
        int port = 8088;

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);
        tomcat.getHost().setAppBase(appBase);
        StandardContext ctx = (StandardContext) tomcat.addWebapp(contextPath, new File(webappDirLocation).getAbsolutePath());
        System.out.println("configuring app with basedir: " + new File("./" + webappDirLocation).getAbsolutePath());

//        tomcat.addWebapp(contextPath, appBase);
        tomcat.start();

        System.out.println("Waiting for requests on http://localhost:" + port + contextPath + "/");
        tomcat.getServer().await();

        *//*InputStream read = System.in;
        BufferedReader reader = new BufferedReader(new InputStreamReader(read));
        String readLine = reader.readLine();
        System.out.println(readLine);
        Scanner scanner = new Scanner(System.in);
        while (true) {
//            scanner.
            System.out.print("Введите любое целое число от 1 до 10: ");
//            Scanner scan = new Scanner(System.in)
            String username0 = scanner.nextLine();
            int number = scanner.nextInt();//read only number, '/n' - symbol
            String username01 = scanner.nextLine();
            System.out.print("Вы ввели число " + username0 + number + username01);

            System.out.print("Enter your username: ");
//            Scanner scanner = new Scanner(System.in);
            String username = "";
//            String username = scanner.nextLine();
            String username1 = scanner.nextLine();
            String username2 = scanner.nextLine();
            int number1 = scanner.nextInt();
            System.out.println("Your username is " + username + username1 + username2 + number1);
    }*//*

    }*/

    public static void main(String[] args)
            throws LifecycleException, InterruptedException, ServletException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8089);

        Context ctx = tomcat.addContext("/", new File(".").getAbsolutePath());

        Tomcat.addServlet(ctx, "hello", new HttpServlet() {
            protected void service(HttpServletRequest req, HttpServletResponse resp)
                    throws ServletException, IOException {
                Writer w = resp.getWriter();
                w.write("Hello, World!");
                w.flush();
            }
        });
        ctx.addServletMapping("/*", "hello");

        tomcat.start();
        tomcat.getServer().await();
    }
}
