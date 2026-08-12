package trycore.trycoredesafioback.domain.service.activity;

import lombok.RequiredArgsConstructor;
import trycore.trycoredesafioback.domain.exception.ApiException;
import trycore.trycoredesafioback.domain.exception.MessageError;
import trycore.trycoredesafioback.domain.model.Activity;
import trycore.trycoredesafioback.domain.model.CalculateIndicators;
import trycore.trycoredesafioback.domain.repository.ActivityRepository;
import trycore.trycoredesafioback.domain.repository.ProyectRepository;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
public class FindActivity {

    private final ActivityRepository activityRepository;
    private final ProyectRepository proyectRepository;

    public List<Activity> find() {
        return activityRepository.find();
    }

    public List<Activity> findByProyect(Long proyectId) throws ApiException {
        if (!proyectRepository.isExist(proyectId)) {
            throw new ApiException(MessageError.PROYECT_NOT_FOUND);
        }
        List<Activity> activities = activityRepository.findByProyect(proyectId);
        activities.forEach(this::calculate);
        return activities;
    }

    private void calculate(Activity activity) {
        CalculateIndicators calculateIndicators = new CalculateIndicators(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
        calculateIndicators.setPlannedValue(BigDecimal.valueOf(activity.getPercentageAdvancePlanned()).multiply(activity.getTotalPlannedBudget()));
        calculateIndicators.setEarnedValue(BigDecimal.valueOf(activity.getPercentageAdvanceReal()).multiply(activity.getTotalPlannedBudget()));
        calculateIndicators.setCostVariance(calculateIndicators.getEarnedValue().subtract(activity.getActualCostIncurred()));
        calculateIndicators.setScheduleVariance(calculateIndicators.getEarnedValue().subtract(calculateIndicators.getPlannedValue()));
        if (activity.getActualCostIncurred().compareTo(BigDecimal.ZERO) != 0) {
            calculateIndicators.setCostPerformanceIndex(calculateIndicators.getEarnedValue().divide(activity.getActualCostIncurred()));
        }
        if (calculateIndicators.getPlannedValue().compareTo(BigDecimal.ZERO) != 0) {
            calculateIndicators.setSchedulePerformanceIndex(calculateIndicators.getEarnedValue().divide(calculateIndicators.getPlannedValue()));
        }
        if (calculateIndicators.getCostPerformanceIndex().compareTo(BigDecimal.ZERO) != 0) {
            calculateIndicators.setEstimateAtCompletion(activity.getTotalPlannedBudget().divide(calculateIndicators.getCostPerformanceIndex()));
        }
        if (calculateIndicators.getEstimateAtCompletion().compareTo(BigDecimal.ZERO) != 0) {
            calculateIndicators.setVarianceAtCompletion(activity.getTotalPlannedBudget().divide(calculateIndicators.getEstimateAtCompletion()));
        }
        activity.setCalculateIndicators(calculateIndicators);
    }

}
