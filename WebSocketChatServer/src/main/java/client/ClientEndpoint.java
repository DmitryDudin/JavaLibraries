package client;

import dto.Message;
import dto.MessageDecoder;
import dto.MessageEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

@javax.websocket.ClientEndpoint(encoders = MessageEncoder.class, decoders = MessageDecoder.class)
public class ClientEndpoint {
    private Session userSession;
    private static final Logger LOG = LoggerFactory.getLogger(ClientEndpoint.class);

    @OnOpen
    public void onOpen(Session userSession) {
        LOG.debug("ClientEndpoint is open.");
        this.userSession = userSession;
    }

    @OnMessage
    public void onMessage(Message message, Session session) throws IOException {
        LOG.debug("message={}", message);
        String receivedDate = message.getReceivedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        System.out.println(String.format("[%s] %s:  %s", receivedDate, message.getUserName(), message.getText()));
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        LOG.debug(String.format("Session %s close because of %s", session.getId(), closeReason));
    }

    @OnError
    public void onError(Session session, Throwable t) {
        LOG.error(t.toString());
    }
}
