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
import trycore.trycoredesafioback.domain.service.activity.CreateActivityService;
import trycore.trycoredesafioback.domain.service.activity.DeleteActivityService;
import trycore.trycoredesafioback.domain.service.activity.FindActivityService;
import trycore.trycoredesafioback.domain.service.activity.UpdateActivityService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final ActivityDtoMapper activityDtoMapper;
    private final ProyectRepository proyectRepository;
    private CreateActivityService createActivity;
    private DeleteActivityService deleteActivity;
    private FindActivityService findActivity;
    private UpdateActivityService updateActivity;

    @PostConstruct
    public void init() {
        createActivity = new CreateActivityService(activityRepository);
        deleteActivity = new DeleteActivityService(activityRepository);
        findActivity = new FindActivityService(activityRepository, proyectRepository);
        updateActivity = new UpdateActivityService(activityRepository);
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
