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
public class CalculateIndicatorsDTO {
    private BigDecimal plannedValue;
    private BigDecimal earnedValue;
    private BigDecimal costVariance;
    private BigDecimal scheduleVariance;
    private BigDecimal costPerformanceIndex;
    private BigDecimal schedulePerformanceIndex;
    private BigDecimal estimateAtCompletion;
    private BigDecimal varianceAtCompletion;
}
