package ua.org.javatraining;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.FilterDef;
import org.apache.tomcat.util.descriptor.web.FilterMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.org.javatraining.servlets.TestServlet;
import ua.org.javatraining.filters.TestFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.Writer;

//import org.apache.catalina.startup.Tomcat;
public class ServerClass {
    private static final Logger LOG = LoggerFactory.getLogger(ServerClass.class);
//    https://github.com/benas/webapp-embedded-tomcat
//    https://github.com/benas/web-socket-lab
//    https://devcenter.heroku.com/articles/create-a-java-web-application-using-embedded-tomcat
//    https://github.com/heroku/devcenter-embedded-tomcat
//    https://tomcat.apache.org/tomcat-7.0-doc/api/org/apache/catalina/startup/Embedded.html
//    http://www.jofre.de/?p=1227

    //    !!!!!!!!!!!!!! :
//    http://blog.sortedset.com/embedded-tomcat-jersey/
//    http://www.artificialworlds.net/blog/2015/02/05/programmatic-equivalents-of-web-xml-sections-for-tomcat/
    private static int port = 8088;

    public static void main(String[] args) throws IOException, ServletException, LifecycleException, InterruptedException {

//        String appBase = args[0]; // absolute path to target/${project.artifactId}-${project.version}
        String appBase = "/home/dmitry/dev/from_github/webapp-embedded-tomcat/target/webapp-embedded-tomcat-1.0.0-SNAPSHOT";
//        String appBase = "/home/dmitry/dev/JavaLibraries/WebSocketChatServer/build/libs/WebSocketChatServer-1.0-SNAPSHOT";
//        System.out.println(appBase);
//        int port = args.length > 1 ? parseInt(args[1]) : 8080;


        Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);
//        tomcat.getHost().setAppBase(appBase);
        String contextPath = "/api";
        String docBase = new File(".").getAbsolutePath();
        StandardContext ctx = (StandardContext) tomcat.addWebapp(contextPath, docBase);
        System.out.println("configuring app with basedir: " + new File(".").getAbsolutePath());
//        Context ctx1 = tomcat.addContext("", new File(".").getAbsolutePath());

        //filter addition:
        FilterDef filterDef = new FilterDef();
        String filterName = TestFilter.class.getSimpleName();
        filterDef.setFilterName(filterName);
        filterDef.setFilterClass(TestFilter.class.getName());
        ctx.addFilterDef(filterDef);

        FilterMap filterMap = new FilterMap();
        filterMap.setFilterName(filterName);
        filterMap.addURLPattern("/*");
        ctx.addFilterMap(filterMap);

        //servlet addition:
        Tomcat
                .addServlet(ctx, "test", new HttpServlet() {
                    protected void service(HttpServletRequest req, HttpServletResponse resp)
                            throws ServletException, IOException {
                        Writer w = resp.getWriter();
                        w.write("Helloooo, World!\n");
                        w.flush();
                        w.close();
                    }
                })
                .addMapping("/h");

        Tomcat.addServlet(ctx, "test1", TestServlet.class.getName())
                .addMapping("/g");

        tomcat.start();

        LOG.info("Waiting for requests on http://localhost:{}{}/", port, contextPath);
        tomcat.getServer().await();

        /*InputStream read = System.in;
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
        }*/

    }

    /*public static void main(String[] args) throws LifecycleException, InterruptedException, ServletException {
        LOG.debug(TestServlet.class.toString());
        LOG.debug("---------------------");
        ClassLoader cl = ClassLoader.getSystemClassLoader();

        URL[] urls = ((URLClassLoader) cl).getURLs();

        for (URL url : urls) {
            System.out.println(url.getFile());
        }
        LOG.debug("---------------------");
        String property = System.getProperty("java.io.tmpdir");
        String absolutePath = new File("./WebSocketChatServer").getAbsolutePath();

        String property1 = System.getProperty("java.io.tmpdir");
        File docBase = new File(property1);

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

//        Context ctx = tomcat.addContext("/", new File(".").getAbsolutePath());
//        Context ctx = tomcat.addContext("", new File(".").getAbsolutePath());
        Context ctx = tomcat.addContext("", new File(".").getAbsolutePath());

        Tomcat.addServlet(ctx, "hello", new HttpServlet() {
            protected void service(HttpServletRequest req, HttpServletResponse resp)
                    throws ServletException, IOException {
                Writer w = resp.getWriter();
                w.write("Hello, World!\n");
                w.flush();
                w.close();
            }
        });
        Tomcat.addServlet(ctx, "endServlet", new HttpServlet() {
            protected void service(HttpServletRequest req, HttpServletResponse resp)
                    throws ServletException, IOException {
                Writer w = resp.getWriter();
                w.write("The end!\n");
                w.flush();
                w.close();
            }
        });

//        Tomcat.addServlet(ctx, "test", TestServlet.class.toString());//ClassNotFoundException
//        Tomcat.addServlet(ctx, "test", TestServlet.class.getName());//work!!!
        Tomcat.addServlet(ctx, "test", new TestServlet());//work!!!

        ctx.addServletMapping("/h", "hello");
        ctx.addServletMapping("/end", "endServlet");
        ctx.addServletMapping("/go", "test");

        tomcat.start();
        tomcat.getServer().await();
    }*/
}
