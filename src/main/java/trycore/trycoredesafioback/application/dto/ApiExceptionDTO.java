package trycore.trycoredesafioback.application.dto;

import lombok.*;

import java.util.Map;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiExceptionDTO {

    private String error;
    private String source;
    private Map<String, Object> validations;

}
