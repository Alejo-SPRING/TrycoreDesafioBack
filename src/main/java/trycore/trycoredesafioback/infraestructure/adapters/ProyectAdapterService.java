package trycore.trycoredesafioback.infraestructure.adapters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trycore.trycoredesafioback.domain.model.Proyect;
import trycore.trycoredesafioback.domain.repository.ProyectRepository;
import trycore.trycoredesafioback.infraestructure.jpa.entity.ProyectEntity;
import trycore.trycoredesafioback.infraestructure.jpa.repository.ProyectDataRepository;
import trycore.trycoredesafioback.infraestructure.mapper.ProyectEntityMapper;

import java.util.List;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Service
public class ProyectAdapterService implements ProyectRepository {

    private final ProyectDataRepository proyectDataRepository;
    private final ProyectEntityMapper proyectEntityMapper;

    @Override
    public List<Proyect> find() {
        return proyectEntityMapper.toModel(StreamSupport.stream(proyectDataRepository.findAll().spliterator(), false).toList());
    }

    @Override
    public Proyect save(Proyect proyect) {
        ProyectEntity proyectEntity = proyectEntityMapper.toEntity(proyect);
        proyectEntity = proyectDataRepository.save(proyectEntity);
        return proyectEntityMapper.toModel(proyectEntity);
    }

    @Override
    public void delete(Long id) {
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
