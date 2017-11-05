package config;

import org.apache.tomcat.websocket.server.Constants;
import org.apache.tomcat.websocket.server.WsContextListener;

import javax.servlet.ServletContextEvent;
import javax.websocket.server.ServerContainer;
import javax.websocket.server.ServerEndpoint;

//needed when tomcat.addContext() for endPoints init
public class WsConfig extends WsContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        super.contextInitialized(sce);

        ServerContainer serverContainer = (ServerContainer) sce.getServletContext()
                .getAttribute(Constants.SERVER_CONTAINER_SERVLET_CONTEXT_ATTRIBUTE);

        try {
            serverContainer.addEndpoint(ServerEndpoint.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
    }
}
