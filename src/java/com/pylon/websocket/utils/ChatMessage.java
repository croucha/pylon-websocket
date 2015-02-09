package com.pylon.websocket.utils;

import com.google.gson.Gson;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author croucha
 */
public class ChatMessage {
	private String message;
	private String sender;
	private Date received;
    
    /**
     * @param message
     * @return
     */
    public ChatMessage setMessage(String message) {
        this.message = message;
        return this;
    }
    
    /**
     * @param sender
     * @return
     */
    public ChatMessage setSender(String sender) {
        this.sender = sender;
        return this;
    }
    
    /**
     * @param received
     * @return
     */
    public ChatMessage setReceived(Date received) {
        this.received = received;
        return this;
    }
    
    /**
     * @return message
     */
    public String getMessage() {
        return this.message;
    }
    
    /**
     * @return sender
     */
    public String getSender() {
        return this.sender;
    }
    
    /**
     * @return date message received
     */
    public Date getReceived() {
        return this.received;
    }
    
    @Override
    public String toString() {
        // Define results
        Map<String,Object> results = new LinkedHashMap<>();
        results.put("message", this.message);
        results.put("sender", this.sender);
        results.put("received", this.received);
        // Return json string representation
        return new Gson().toJson(results);
    }
}