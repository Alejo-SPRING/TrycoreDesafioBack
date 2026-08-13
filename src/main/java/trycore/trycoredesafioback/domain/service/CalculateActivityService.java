package trycore.trycoredesafioback.domain.service;

import trycore.trycoredesafioback.domain.model.Activity;
import trycore.trycoredesafioback.domain.model.CalculateIndicators;
import trycore.trycoredesafioback.domain.model.Proyect;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculateActivityService {

    public void calculate(Proyect proyect) {
        if(proyect.getActivities() == null) {
            return;
        }
        CalculateIndicators calculateIndicators = new CalculateIndicators(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
        proyect.getActivities().forEach(activity -> {
            calculate(activity);
            calculateIndicators.setVarianceAtCompletion(calculateIndicators.getVarianceAtCompletion().add(activity.getCalculateIndicators().getVarianceAtCompletion()));
            calculateIndicators.setScheduleVariance(calculateIndicators.getScheduleVariance().add(activity.getCalculateIndicators().getScheduleVariance()));
            calculateIndicators.setCostVariance(calculateIndicators.getCostVariance().add(activity.getCalculateIndicators().getCostVariance()));
            calculateIndicators.setEarnedValue(calculateIndicators.getEarnedValue().add(activity.getCalculateIndicators().getEarnedValue()));
            calculateIndicators.setPlannedValue(calculateIndicators.getPlannedValue().add(activity.getCalculateIndicators().getPlannedValue()));
            calculateIndicators.setEstimateAtCompletion(calculateIndicators.getEstimateAtCompletion().add(activity.getCalculateIndicators().getEstimateAtCompletion()));
            calculateIndicators.setSchedulePerformanceIndex(calculateIndicators.getSchedulePerformanceIndex().add(activity.getCalculateIndicators().getSchedulePerformanceIndex()));
            calculateIndicators.setCostPerformanceIndex(calculateIndicators.getCostPerformanceIndex().add(activity.getCalculateIndicators().getCostPerformanceIndex()));
        });
        proyect.setTotalCalculateIndicators(calculateIndicators);
    }

    public void calculate(Activity activity) {
        CalculateIndicators calculateIndicators = new CalculateIndicators(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
        calculateIndicators.setPlannedValue(BigDecimal.valueOf(activity.getPercentageAdvancePlanned()).multiply(activity.getTotalPlannedBudget()));
        calculateIndicators.setEarnedValue(BigDecimal.valueOf(activity.getPercentageAdvanceReal()).multiply(activity.getTotalPlannedBudget()));
        calculateIndicators.setCostVariance(calculateIndicators.getEarnedValue().subtract(activity.getActualCostIncurred()));
        calculateIndicators.setScheduleVariance(calculateIndicators.getEarnedValue().subtract(calculateIndicators.getPlannedValue()));
        if (activity.getActualCostIncurred().compareTo(BigDecimal.ZERO) != 0) {
            calculateIndicators.setCostPerformanceIndex(calculateIndicators.getEarnedValue().divide(activity.getActualCostIncurred(), 4, RoundingMode.HALF_UP));
        }
        if (calculateIndicators.getPlannedValue().compareTo(BigDecimal.ZERO) != 0) {
            calculateIndicators.setSchedulePerformanceIndex(calculateIndicators.getEarnedValue().divide(calculateIndicators.getPlannedValue(), 4, RoundingMode.HALF_UP));
        }
        if (calculateIndicators.getCostPerformanceIndex().compareTo(BigDecimal.ZERO) != 0) {
            calculateIndicators.setEstimateAtCompletion(activity.getTotalPlannedBudget().divide(calculateIndicators.getCostPerformanceIndex(), 4, RoundingMode.HALF_UP));
        }
        if (calculateIndicators.getEstimateAtCompletion().compareTo(BigDecimal.ZERO) != 0) {
            calculateIndicators.setVarianceAtCompletion(activity.getTotalPlannedBudget().divide(calculateIndicators.getEstimateAtCompletion(), 4, RoundingMode.HALF_UP));
        }
        activity.setCalculateIndicators(calculateIndicators);
    }
}
