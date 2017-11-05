package dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.JsonUtil;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class MessageEncoder implements Encoder.Text<Message> {
    private static final Logger LOG = LoggerFactory.getLogger(MessageEncoder.class);

    @Override
    public void init(EndpointConfig endpointConfig) {
        LOG.debug("init MessageEncoder.");
    }

    @Override
    public void destroy() {
        LOG.debug("destroy MessageEncoder.");
    }

    @Override
    public String encode(Message message) throws EncodeException {
        LOG.debug("encode message={}", message);
        return JsonUtil.createJsonString(message);
    }
}
