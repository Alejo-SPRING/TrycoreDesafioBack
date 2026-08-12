package trycore.trycoredesafioback.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Activity {

    private Long id;
    private String name;
    private BigDecimal totalPlannedBudget;
    private Long percentageAdvancePlanned;
    private Long percentageAdvanceReal;
    private BigDecimal actualCostIncurred;
    private Long proyectId;
    private CalculateIndicators calculateIndicators;

}
