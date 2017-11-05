package client;

import dto.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;
import java.util.Scanner;

public class Client {

    private static final Logger LOG = LoggerFactory.getLogger(Client.class);
    private static int port = 8089;

    public static void main(String[] args) {

        WebSocketContainer webSocketContainer = ContainerProvider.getWebSocketContainer();
        Session session = null;
        try {
            URI endpointURI = new URI("ws://localhost:" + port + "/api/echoBasic");
//            URI endpointURI = new URI("ws://echo.websocket.org");//for test

            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter your name: ");
            String userName = scanner.nextLine();
            LOG.debug("userName={}", userName);

            session = webSocketContainer.connectToServer(ClientEndpoint.class, endpointURI);
            System.out.println("You are connect!");
            LOG.debug("Connect to server.");

            session.getAsyncRemote().sendObject(new Message(userName, "send message for name", null));//send name

            while (true) {
                String messageText = scanner.nextLine();
                LOG.debug("messageText={}", messageText);
                session.getAsyncRemote().sendObject(new Message(userName, messageText, null));
//              session.getAsyncRemote().sendText(JsonUtil.createJsonString(new Message(userName, messageText, null)));
                if (messageText.equals("quit")) {
                    LOG.debug("Quit.");
//                  session.close(new CloseReason(CloseReason.CloseCodes.NORMAL_CLOSURE, "Close close close!!!"));//close on server side
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("{}", e);
        } finally {
            try {
                session.close();
            } catch (IOException e) {
                e.printStackTrace();
                LOG.error("Can't close session. {}", e);
            }
        }
    }
}
