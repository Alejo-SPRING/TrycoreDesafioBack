package trycore.trycoredesafioback.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Proyect {

    private Long id;
    private String name;
    private CalculateIndicators totalCalculateIndicators;
    private List<Activity> activities;

}
