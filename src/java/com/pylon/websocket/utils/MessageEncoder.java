package com.pylon.websocket.utils;

import com.google.gson.Gson;
import com.pylon.websocket.interfaces.Message;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TimeZone;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author croucha
 */
public class MessageEncoder
    implements Encoder.Text<Message> {

    private static final Logger log = LogManager.getLogger(MessageEncoder.class);
 
    @Override
    public void init(final EndpointConfig config) {}

    @Override
    public void destroy() {}

    @Override
    public String encode(final Message message)
        throws EncodeException
    {
        // Define results
        Map<String,String> results = new LinkedHashMap<>();
        results.put("message", message.getMessage());
        results.put("sender", message.getSender());
        // Define time zone
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        // Define Atom (ISO 8601) format
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");
        // Set time zone
        dateFormat.setTimeZone(timeZone);
        // Covert to ISO 8601
        String iso8601 = dateFormat.format(message.getReceived());
        results.put("received", iso8601);
        return new Gson().toJson(results);
    }
}