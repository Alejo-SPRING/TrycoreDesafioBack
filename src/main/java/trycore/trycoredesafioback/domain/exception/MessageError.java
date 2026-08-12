package trycore.trycoredesafioback.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum MessageError {

    PROYECT_NOT_FOUND("¡El proyecto no fue encontrado!", HttpStatus.NOT_FOUND),
    ACTIVITY_NOT_FOUND("¡La actividad no fue encontrada!", HttpStatus.NOT_FOUND),
    ACTIVITY_ALREADY("¡Ya se encuentra creada una actividad con este nombre!", HttpStatus.BAD_REQUEST),
    PROYECT_ALREADY("¡Ya se encuentra creadO un PROYECTO con ese nombre!", HttpStatus.BAD_REQUEST);

    private String message;
    private HttpStatus status;

    private MessageError(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

}
