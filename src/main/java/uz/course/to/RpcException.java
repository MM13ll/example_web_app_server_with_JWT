package uz.course.to;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class RpcException extends RuntimeException implements Serializable {

    @JsonIgnore
    public static String suppressed;

    public RpcException(String message) {
        super(message);
    }

    public RpcException() {
    }

    @JsonIgnore
    @Override
    public String getLocalizedMessage() {
        return super.getLocalizedMessage();
    }

    @JsonIgnore
    @Override
    public synchronized Throwable getCause() {
        return super.getCause();
    }

    @JsonIgnore
    @Override
    public StackTraceElement[] getStackTrace() {
        return super.getStackTrace();
    }
}