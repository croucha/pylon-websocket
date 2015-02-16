package com.pylon.websocket;

import com.pylon.websocket.models.RobotMessage;
import com.pylon.websocket.utils.MessageEncoder;
import com.pylon.websocket.utils.RobotMessageDecoder;
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
    value = "/remote/{robotType}",
    encoders = {MessageEncoder.class},
    decoders = {RobotMessageDecoder.class}
)
public class RemoteRobotEndpoint {
 
    private final Logger log = LogManager.getLogger(getClass().getName()); 

    @OnOpen
    public void open(final Session session, @PathParam("robotType") final String type) {
        log.trace("Session openend and bound to room: {}", type);
        session.getUserProperties().put("robotType", type);
    }

    @OnMessage
    public void onMessage(final Session session, final RobotMessage message) {
        String type = (String) session.getUserProperties().get("robotType");
        log.trace("Endpoint message: {}", message);
        try {
            for (Session s : session.getOpenSessions()) {
                if (s.isOpen() && type.equals(s.getUserProperties().get("robotType"))) {
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