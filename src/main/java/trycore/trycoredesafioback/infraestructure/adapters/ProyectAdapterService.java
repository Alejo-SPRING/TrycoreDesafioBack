package trycore.trycoredesafioback.infraestructure.adapters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trycore.trycoredesafioback.domain.model.Proyect;
import trycore.trycoredesafioback.domain.repository.ProyectRepository;
import trycore.trycoredesafioback.infraestructure.jpa.entity.ActivityEntity;
import trycore.trycoredesafioback.infraestructure.jpa.entity.ProyectEntity;
import trycore.trycoredesafioback.infraestructure.jpa.repository.ActivityDataRepository;
import trycore.trycoredesafioback.infraestructure.jpa.repository.ProyectDataRepository;
import trycore.trycoredesafioback.infraestructure.mapper.ActivityEntityMapper;
import trycore.trycoredesafioback.infraestructure.mapper.ProyectEntityMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Service
public class ProyectAdapterService implements ProyectRepository {

    private final ProyectDataRepository proyectDataRepository;
    private final ActivityDataRepository activityDataRepository;
    private final ProyectEntityMapper proyectEntityMapper;
    private final ActivityEntityMapper activityEntityMapper;

    @Override
    public List<Proyect> find() {
        List<ProyectEntity> proyectEntities = StreamSupport.stream(proyectDataRepository.findAll().spliterator(), false).toList();
        List<ActivityEntity> activityEntities = activityDataRepository.findByProyects(proyectEntities.stream().map(ProyectEntity::getId).toList());
        Map<Long, List<ActivityEntity>> activityGroup = activityEntities.stream().collect(Collectors.groupingBy(activity -> activity.getProyect().getId()));
        List<Proyect> proyects = new ArrayList<>();
        Proyect proyect;
        for(ProyectEntity proyectEntity : proyectEntities) {
            proyect = proyectEntityMapper.toModel(proyectEntity);
            proyect.setActivities(activityEntityMapper.toModel(activityGroup.get(proyect.getId())));
            proyects.add(proyect);
        }
        return proyects;
    }

    @Override
    public Proyect save(Proyect proyect) {
        ProyectEntity proyectEntity = proyectEntityMapper.toEntity(proyect);
        proyectEntity = proyectDataRepository.save(proyectEntity);
        return proyectEntityMapper.toModel(proyectEntity);
    }

    @Override
    public void delete(Long id) {
        List<ActivityEntity> activityEntities = activityDataRepository.findByProyectId(id);
        if(!activityEntities.isEmpty()) {
            activityDataRepository.deleteAll(activityEntities);
        }
        proyectDataRepository.deleteById(id);
    }

    @Override
    public boolean isExist(String name) {
        return proyectDataRepository.isExist(name);
    }

    @Override
    public boolean isExist(Long id) {
        return proyectDataRepository.existsById(id);
    }
}
