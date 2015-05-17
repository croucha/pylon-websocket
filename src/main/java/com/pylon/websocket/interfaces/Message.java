package com.pylon.websocket.interfaces;

import java.util.Date;

/**
 *
 * @author croucha
 */
public interface Message {
    
    /**
     * @param message
     * @return Message
     */
    public Message setMessage(String message);
    
    /**
     * @param sender
     * @return Message
     */
    public Message setSender(String sender);
    
    /**
     * @param received
     * @return Message
     */
    public Message setReceived(Date received);
    
    /**
     * @return message
     */
    public String getMessage();
    
    /**
     * @return sender
     */
    public String getSender();
    
    /**
     * @return date message received
     */
    public Date getReceived();
}
