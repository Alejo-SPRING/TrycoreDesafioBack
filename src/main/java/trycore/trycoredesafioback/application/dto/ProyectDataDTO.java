package trycore.trycoredesafioback.application.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import trycore.trycoredesafioback.domain.model.CalculateIndicators;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProyectDataDTO {
    private Long id;
    private String name;
    private CalculateIndicatorsDTO totalCalculateIndicators;
}
