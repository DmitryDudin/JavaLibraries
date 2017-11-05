package dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.io.IOException;
import java.time.LocalDateTime;

public class MessageDecoder implements Decoder.Text<Message> {
    private static final Logger LOG = LoggerFactory.getLogger(MessageDecoder.class);

    @Override
    public Message decode(String textMessage) throws DecodeException {
        LOG.debug("decode message={}", textMessage);

        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());
        Message message = null;
        try {
            message = mapper.readValue(textMessage, Message.class);
            message.setReceivedDate(LocalDateTime.now());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return message;
    }

    @Override
    public boolean willDecode(String s) {
        return true;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
        LOG.debug("init MessageDencoder.");
    }

    @Override
    public void destroy() {
        LOG.debug("destroy MessageDencoder.");
    }
}
