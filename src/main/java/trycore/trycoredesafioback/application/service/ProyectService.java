package trycore.trycoredesafioback.application.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trycore.trycoredesafioback.application.dto.ProyectDTO;
import trycore.trycoredesafioback.application.dto.ProyectDataDTO;
import trycore.trycoredesafioback.application.mapper.ProyectDtoMapper;
import trycore.trycoredesafioback.domain.exception.ApiException;
import trycore.trycoredesafioback.domain.model.Proyect;
import trycore.trycoredesafioback.domain.repository.ProyectRepository;
import trycore.trycoredesafioback.domain.service.proyect.CreateProyect;
import trycore.trycoredesafioback.domain.service.proyect.DeleteProyect;
import trycore.trycoredesafioback.domain.service.proyect.FindProyect;
import trycore.trycoredesafioback.domain.service.proyect.UpdateProyect;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProyectService {

    private final ProyectRepository proyectRepository;
    private final ProyectDtoMapper proyectDtoMapper;
    private CreateProyect createProyect;
    private FindProyect findProyect;
    private DeleteProyect deleteProyect;
    private UpdateProyect updateProyect;

    @PostConstruct
    public void init() {
        createProyect = new CreateProyect(proyectRepository);
        updateProyect = new UpdateProyect(proyectRepository);
        findProyect = new FindProyect(proyectRepository);
        deleteProyect = new DeleteProyect(proyectRepository);
    }

    public ProyectDataDTO create(ProyectDTO proyectDTO) throws ApiException {
        return proyectDtoMapper.toDto(createProyect.save(proyectDtoMapper.toModel(proyectDTO)));
    }

    public ProyectDataDTO update(Long id, ProyectDTO proyectDTO) throws ApiException {
        Proyect proyect = proyectDtoMapper.toModel(proyectDTO);
        proyect.setId(id);
        return proyectDtoMapper.toDto(updateProyect.save(proyect));
    }

    public List<ProyectDataDTO> getAll() {
        return proyectDtoMapper.toDto(findProyect.find());
    }

    public void delete(Long id) throws ApiException {
        deleteProyect.remove(id);
    }

}
