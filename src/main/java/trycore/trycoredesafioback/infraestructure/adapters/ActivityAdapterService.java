package trycore.trycoredesafioback.infraestructure.adapters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trycore.trycoredesafioback.domain.exception.ApiException;
import trycore.trycoredesafioback.domain.exception.MessageError;
import trycore.trycoredesafioback.domain.model.Activity;
import trycore.trycoredesafioback.domain.repository.ActivityRepository;
import trycore.trycoredesafioback.infraestructure.jpa.entity.ActivityEntity;
import trycore.trycoredesafioback.infraestructure.jpa.entity.ProyectEntity;
import trycore.trycoredesafioback.infraestructure.jpa.repository.ActivityDataRepository;
import trycore.trycoredesafioback.infraestructure.jpa.repository.ProyectDataRepository;
import trycore.trycoredesafioback.infraestructure.mapper.ActivityEntityMapper;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ActivityAdapterService implements ActivityRepository {

    private final ActivityDataRepository activityDataRepository;
    private final ProyectDataRepository proyectDataRepository;
    private final ActivityEntityMapper activityEntityMapper;

    @Override
    public List<Activity> find() {
        return activityEntityMapper.toModel(StreamSupport.stream(activityDataRepository.findAll().spliterator(), false).toList());
    }

    @Override
    public Activity save(Activity activity) throws ApiException {
        ActivityEntity activityEntity = activityEntityMapper.toEntity(activity);
        ProyectEntity proyectEntity = proyectDataRepository.findById(activity.getProyectId()).orElseThrow(() -> new ApiException(MessageError.PROYECT_NOT_FOUND));
        activityEntity.setProyect(proyectEntity);
        activityEntity = activityDataRepository.save(activityEntity);
        return activityEntityMapper.toModel(activityEntity);
    }

    @Override
    public void delete(Long id) {
        activityDataRepository.deleteById(id);
    }

    @Override
    public boolean isExist(String name) {
        return activityDataRepository.isExist(name);
    }

    @Override
    public boolean isExist(Long id) {
        return activityDataRepository.existsById(id);
    }

    @Override
    public List<Activity> findByProyect(Long proyectId) {
        return activityEntityMapper.toModel(activityDataRepository.findByProyectId(proyectId));
    }
}
