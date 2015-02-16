package com.pylon.websocket;

import com.pylon.websocket.models.ChatMessage;
import com.pylon.websocket.utils.ChatMessageDecoder;
import com.pylon.websocket.utils.MessageEncoder;
import java.io.IOException;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author croucha
 */
@ServerEndpoint(
    value = "/chat/{room}",
    encoders = {MessageEncoder.class},
    decoders = {ChatMessageDecoder.class}
)
public class ChatEndpoint {
 
    private final Logger log = LogManager.getLogger(getClass().getName()); 

    @OnOpen
    public void open(final Session session, @PathParam("room") final String room) {
        log.trace("Session openend and bound to room: {}", room);
        session.getUserProperties().put("room", room);
    }

    @OnMessage
    public void onMessage(final Session session, final ChatMessage message) {
        String room = (String) session.getUserProperties().get("room");
        log.trace("Endpoint message: {}", message);
        try {
            for (Session s : session.getOpenSessions()) {
                if (s.isOpen() && room.equals(s.getUserProperties().get("room"))) {
                    s.getBasicRemote().sendObject(message);
                }
            }
        } catch (IOException | EncodeException e) {
            log.trace("onMessage failed: {}", e);
        }
    }

    @OnError
    public void onError(Throwable t) {
        log.trace("onError: {}", t.getMessage());
    }

    @OnClose
    public void onClose(Session session) {
        log.trace("Session has ended: {}", session.getId());
    }
}