package com.pylon.websocket.exceptions;

public class WsException extends Exception {
    private Integer code;
    public WsException() { super(); }
    public WsException(String message) { super(message); }
    public WsException(String message, Integer code) {
        super(message);
        this.code = code;
    }
    public WsException(String message, Throwable cause) { super(message); initCause(cause); }
    public WsException(Throwable cause) { initCause(cause); }
    public Integer getCode() { return this.code; }
}