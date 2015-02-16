package com.pylon.websocket.utils;

import java.util.Date;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pylon.websocket.interfaces.Message;
import com.pylon.websocket.models.RobotMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author croucha
 */
public class RobotMessageDecoder
    implements Decoder.Text<Message> {

    private static final Logger log = LogManager.getLogger(RobotMessageDecoder.class);

    @Override
    public void init(final EndpointConfig config) {}

    @Override
    public void destroy() {}

    @Override
    public Message decode(final String textMessage) 
        throws DecodeException
    {
        // Define chat message
        Message message = new RobotMessage();
        // Define parser
        JsonParser jsonParser = new JsonParser();
        // Parse results
        JsonObject results = jsonParser.parse(textMessage).getAsJsonObject();
        // Add results to message
        message.setMessage(results.get("message").getAsString());
        message.setSender(results.get("sender").getAsString());
        message.setReceived(new Date());
        return message;
    }

    @Override
    public boolean willDecode(final String s) {
        return true;
    }
}
