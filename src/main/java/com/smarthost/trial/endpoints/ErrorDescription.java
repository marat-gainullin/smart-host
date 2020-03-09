package com.smarthost.trial.endpoints;

import org.springframework.http.HttpStatus;

/**
 * Description of any error, that has a meaning for the client.
 * Used in all RESTful endpoints.
 */
public class ErrorDescription {
    private final int status;
    private final int code;
    private final String reason;

    private ErrorDescription(
            final int aStatus,
            final int aCode,
            final String aReason
    ) {
        status = aStatus;
        code = aCode;
        reason = aReason;
    }

    public int getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }

    public String getReason() {
        return reason;
    }

    /**
     * Constructs an {@link ErrorDescription} from {@link HttpStatus} and additional information.
     * @param aStatus Http status. An instance of {@link HttpStatus} enumeration.
     * @param code A subject area driven error code. Intended for extended reference.
     * @param reason A human readable description of an error.
     * @return An {@link ErrorDescription} instance with plain http status, code and error reason.
     */
    public static ErrorDescription of(HttpStatus aStatus, int code, String reason) {
        return new ErrorDescription(aStatus.value(), code, reason);
    }

    /**
     * Constructs an {@link ErrorDescription} from {@link HttpStatus#INTERNAL_SERVER_ERROR}.
     * Intended for protection of server side information, such as stack traces, disk paths and so forth.
     * @return An {@link ErrorDescription} instance with {@link HttpStatus#INTERNAL_SERVER_ERROR} and a message about whom a user should address his error report to.
     */
    public static ErrorDescription ofInternal() {
        return new ErrorDescription(HttpStatus.INTERNAL_SERVER_ERROR.value(), -1, "We encountered an unexpected situation with your request. Get in touch with our support service please.");
    }
}
