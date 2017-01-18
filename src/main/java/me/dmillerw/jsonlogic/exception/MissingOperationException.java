package me.dmillerw.jsonlogic.exception;

/**
 * @author dmillerw
 */
public class MissingOperationException extends RuntimeException {

    public MissingOperationException(String operation) {
        super("Unknown Operation: " + operation);
    }
}
