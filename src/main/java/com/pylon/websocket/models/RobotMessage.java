package com.pylon.websocket.models;

import com.google.gson.Gson;
import com.pylon.websocket.interfaces.Message;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author croucha
 */
public class RobotMessage implements Message {
	private String message;
	private String sender;
	private Date received;
    
    @Override
    public RobotMessage setMessage(String message) {
        this.message = message;
        return this;
    }
    
    @Override
    public RobotMessage setSender(String sender) {
        this.sender = sender;
        return this;
    }
    
    @Override
    public RobotMessage setReceived(Date received) {
        this.received = received;
        return this;
    }
    
    @Override
    public String getMessage() {
        return this.message;
    }
    
    @Override
    public String getSender() {
        return this.sender;
    }
    
    @Override
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