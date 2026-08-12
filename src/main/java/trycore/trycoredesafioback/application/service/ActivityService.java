package trycore.trycoredesafioback.application.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trycore.trycoredesafioback.application.dto.ActivityDTO;
import trycore.trycoredesafioback.application.dto.ActivityDataDTO;
import trycore.trycoredesafioback.application.mapper.ActivityDtoMapper;
import trycore.trycoredesafioback.domain.exception.ApiException;
import trycore.trycoredesafioback.domain.model.Activity;
import trycore.trycoredesafioback.domain.repository.ActivityRepository;
import trycore.trycoredesafioback.domain.repository.ProyectRepository;
import trycore.trycoredesafioback.domain.service.activity.CreateActivity;
import trycore.trycoredesafioback.domain.service.activity.DeleteActivity;
import trycore.trycoredesafioback.domain.service.activity.FindActivity;
import trycore.trycoredesafioback.domain.service.activity.UpdateActivity;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final ActivityDtoMapper activityDtoMapper;
    private final ProyectRepository proyectRepository;
    private CreateActivity createActivity;
    private DeleteActivity deleteActivity;
    private FindActivity findActivity;
    private UpdateActivity updateActivity;

    @PostConstruct
    public void init() {
        createActivity = new CreateActivity(activityRepository);
        deleteActivity = new DeleteActivity(activityRepository);
        findActivity = new FindActivity(activityRepository, proyectRepository);
        updateActivity = new UpdateActivity(activityRepository);
    }

    public ActivityDataDTO create(ActivityDTO activityDTO) throws ApiException {
        return activityDtoMapper.toDto(createActivity.save(activityDtoMapper.toModel(activityDTO)));
    }

    public ActivityDataDTO update(Long id, ActivityDTO activityDTO) throws ApiException {
        Activity activity = activityDtoMapper.toModel(activityDTO);
        activity.setId(id);
        return activityDtoMapper.toDto(updateActivity.save(activity));
    }

    public List<ActivityDataDTO> getAll() {
        return activityDtoMapper.toDto(findActivity.find());
    }

    public List<ActivityDataDTO> getByProyectId(Long proyectId) throws ApiException {
        return activityDtoMapper.toDto(findActivity.findByProyect(proyectId));
    }

    public void delete(Long id) throws ApiException {
        deleteActivity.delete(id);
    }

}
