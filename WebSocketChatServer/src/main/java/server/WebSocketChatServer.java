package server;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import java.io.File;
import java.util.Scanner;

public class WebSocketChatServer {
    //    https://github.com/benas/webapp-embedded-tomcat
    //    https://github.com/benas/web-socket-lab
    private static final Logger LOG = LoggerFactory.getLogger(WebSocketChatServer.class);

    private static int port = 8089;

    public static void main(String[] args)
            throws LifecycleException, InterruptedException, ServletException {

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);
        String contextPath = "/api";
        String docBase = new File(".").getAbsolutePath();
        StandardContext ctx = (StandardContext) tomcat.addWebapp(contextPath, docBase);
//        Context ctx = tomcat.addContext(contextPath, docBase);//instead tomcat.addWebapp()

        LOG.info("configuring app with basedir: {}", new File(".").getAbsolutePath());

//        ctx.addApplicationListener(WsConfig.class.getName());// for context?

        tomcat.start();

        LOG.info("Tomcat started. Waiting for requests on http://localhost:{}{}/", port, contextPath);

        System.out.println("Press \"quit\" to stop the server.");
        while (true) {
            String command = new Scanner(System.in).nextLine();
            if ("quit".equals(command)) break;
        }
        tomcat.getServer().setShutdown("Ok");
    }
}
