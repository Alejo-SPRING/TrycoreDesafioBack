package trycore.trycoredesafioback.domain.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class ApiException extends Exception{

    private static final long serialVersionUID = 1L;
    private MessageError source;
    private String value;

    public ApiException(MessageError message) {
        super(message.getMessage());
        this.source = message;
        log.info("ApiException: {}", message.getMessage());
    }

    public ApiException(MessageError message, String value) {
        super(message.getMessage());
        this.source = message;
        this.value = value;
        log.info("ApiException: {}, value: {}", message.getMessage(), value);
    }

}
