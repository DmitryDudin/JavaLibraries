package server;

import dto.Message;
import dto.MessageDecoder;
import dto.MessageEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@javax.websocket.server.ServerEndpoint(value = "/echoBasic", encoders = MessageEncoder.class, decoders = MessageDecoder.class)
public class ServerEndpoint {

    private static final Logger LOG = LoggerFactory.getLogger(ServerEndpoint.class);

    static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(Session session) {
        LOG.info("{} joined the chat room.", session.getId());
        peers.add(session);
    }


    @OnMessage
    public void onMessage(Session session, Message message) throws IOException {
        LOG.info("sessionId={}, user={}, {}", session.getId(), session.getUserProperties().get("user"), message);

        String user = (String) session.getUserProperties().get("user");
        if (user == null) {
            user = message.getUserName();
            session.getUserProperties().put("user", user);
            for (Session peer : peers) {
                if (!peer.getId().equals(session.getId())) {
                    peer.getAsyncRemote().sendObject(new Message("SERVER", user + " joined chat.", LocalDateTime.now()));
                }
            }
            return;
        }
        if ("quit".equals(message.getText())) {
            session.close();
            return;
        }

        for (Session peer : peers) {
            if (!peer.getId().equals(session.getId())) {
                peer.getAsyncRemote().sendObject(message);
            }
        }
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        LOG.info(String.format("Session %s close because of %s", session.getId(), closeReason));

        peers.remove(session);
        String user = (String) session.getUserProperties().get("user");
        for (Session peer : peers) {
            if (!peer.getId().equals(session.getId())) {
                peer.getAsyncRemote().sendObject(new Message("SERVER", user + " left chat.", LocalDateTime.now()));
            }
        }
    }

    @OnError
    public void onError(Session session, Throwable t) throws Throwable {
        LOG.error(t.toString());
    }

    /*@OnMessage
    public void echoTextMessage(Session session, String msg) {
        try {
            LOG.debug("message={}, sessionId={}", msg, session.getId());
//            Thread.sleep(1000);
//            session.getBasicRemote().sendText(msg);
        } catch (Exception e) {
            try {
                session.close();
            } catch (IOException e1) {
                // Ignore
            }
        }

        for (Session peer : peers) {
            if (!peer.getId().equals(session.getId())) {
                peer.getAsyncRemote().sendText(msg);
            }
        }
    }


    @OnMessage
    public void echoBinaryMessage(Session session, ByteBuffer msg) {
        LOG.info("start echoBinaryMessage");
        try {
            session.getBasicRemote().sendBinary(msg);
        } catch (IOException e) {
            try {
                session.close();
            } catch (IOException e1) {
                // Ignore
            }
        }
    }*/

}
