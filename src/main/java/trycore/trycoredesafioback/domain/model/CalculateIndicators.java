package trycore.trycoredesafioback.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CalculateIndicators {

    private BigDecimal plannedValue;
    private BigDecimal earnedValue;
    private BigDecimal costVariance;
    private BigDecimal scheduleVariance;
    private BigDecimal costPerformanceIndex;
    private BigDecimal schedulePerformanceIndex;
    private BigDecimal estimateAtCompletion;
    private BigDecimal varianceAtCompletion;

}
