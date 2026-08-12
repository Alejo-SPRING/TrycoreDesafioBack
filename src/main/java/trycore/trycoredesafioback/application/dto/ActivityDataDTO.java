package trycore.trycoredesafioback.application.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActivityDataDTO {
    private Long id;
    private String name;
    private BigDecimal totalPlannedBudget;
    private Long percentageAdvancePlanned;
    private Long percentageAdvanceReal;
    private BigDecimal actualCostIncurred;
    private CalculateIndicatorsDTO calculateIndicators;
}
