package com.dddbook.bank.exception;

public class BusinessException extends RuntimeException implements I18nException<BusinessException> {
    private String errorCode;
    protected transient MessageTemplate messageTemplate;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public static BusinessException fromTemplate(String templateId, String... args) {
        return fromTemplate(templateId, (Throwable)null, args);
    }

    public static BusinessException fromTemplate(String templateId, Throwable cause, String... args) {
        BusinessException businessException = new BusinessException(templateId, cause);
        businessException.setMessageTemplate(templateId, args);
        return businessException;
    }

    public BusinessException setMessageTemplate(String templateId, String... args) {
        this.messageTemplate = new MessageTemplate(templateId, args);
        return this;
    }

    public MessageTemplate getMessageTemplate() {
        return this.messageTemplate;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public BusinessException setErrorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }
}
