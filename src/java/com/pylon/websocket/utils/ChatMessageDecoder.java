package com.pylon.websocket.utils;

/**
 *
 * @author croucha
 */
import java.util.Date;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
 
public class ChatMessageDecoder 
    implements Decoder.Text<ChatMessage> {
    
    private static final Logger log = LogManager.getLogger(ChatMessageDecoder.class);
    
	@Override
	public void init(final EndpointConfig config) {}
 
	@Override
	public void destroy() {}
 
	@Override
	public ChatMessage decode(final String textMessage) 
        throws DecodeException
    {   
        // Define chat message
		ChatMessage chatMessage = new ChatMessage();
        // Define parser
        JsonParser jsonParser = new JsonParser();
        // Parse results
        JsonObject results = jsonParser.parse(textMessage).getAsJsonObject();
        // Add results to message
		chatMessage.setMessage(results.get("message").getAsString());
		chatMessage.setSender(results.get("sender").getAsString());
		chatMessage.setReceived(new Date());
		return chatMessage;
	}
 
	@Override
	public boolean willDecode(final String s) {
		return true;
	}
}
