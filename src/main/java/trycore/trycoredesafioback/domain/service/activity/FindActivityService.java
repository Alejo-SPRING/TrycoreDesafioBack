package trycore.trycoredesafioback.domain.service.activity;

import lombok.RequiredArgsConstructor;
import trycore.trycoredesafioback.domain.exception.ApiException;
import trycore.trycoredesafioback.domain.exception.MessageError;
import trycore.trycoredesafioback.domain.model.Activity;
import trycore.trycoredesafioback.domain.model.CalculateIndicators;
import trycore.trycoredesafioback.domain.repository.ActivityRepository;
import trycore.trycoredesafioback.domain.repository.ProyectRepository;
import trycore.trycoredesafioback.domain.service.CalculateActivityService;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
public class FindActivityService {

    private final ActivityRepository activityRepository;
    private final ProyectRepository proyectRepository;
    private CalculateActivityService calculateActivityService = new CalculateActivityService();

    public List<Activity> find() {
        return activityRepository.find();
    }

    public List<Activity> findByProyect(Long proyectId) throws ApiException {
        if (!proyectRepository.isExist(proyectId)) {
            throw new ApiException(MessageError.PROYECT_NOT_FOUND);
        }
        List<Activity> activities = activityRepository.findByProyect(proyectId);
        activities.forEach(calculateActivityService::calculate);
        return activities;
    }

}
