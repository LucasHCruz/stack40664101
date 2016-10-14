package com.cnova.mpschedule.core.exception;

public class MpScheduleException extends RuntimeException {

	private static final long serialVersionUID = -6299175182809658885L;

    public MpScheduleException(String message) {
        super(message);
    }

    public MpScheduleException(String message, Throwable cause) {
        super(message, cause);
    }

    public MpScheduleException(Throwable cause) {
        super(cause);
    }

    public MpScheduleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
	
}
