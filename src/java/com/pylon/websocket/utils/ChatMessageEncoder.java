package com.pylon.websocket.utils;

import com.google.gson.Gson;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author croucha
 */
public class ChatMessageEncoder 
    implements Encoder.Text<ChatMessage> {

    private static final Logger log = LogManager.getLogger(ChatMessageEncoder.class);
 
    @Override
    public void init(final EndpointConfig config) {}

    @Override
    public void destroy() {}

    @Override
    public String encode(final ChatMessage chatMessage) 
        throws EncodeException
    {
        // Define results
        Map<String,String> results = new LinkedHashMap<>();
        results.put("message", chatMessage.getMessage());
        results.put("sender", chatMessage.getSender());
        results.put("received", chatMessage.getReceived().toString());
        return new Gson().toJson(results);
	}
}