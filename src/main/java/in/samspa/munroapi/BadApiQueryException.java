package in.samspa.munroapi;

import java.security.PublicKey;

public class BadApiQueryException extends RuntimeException {

    public BadApiQueryException(String message) {
        super(message);
    }
}
