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
import trycore.trycoredesafioback.domain.service.proyect.CreateProyectService;
import trycore.trycoredesafioback.domain.service.proyect.DeleteProyectService;
import trycore.trycoredesafioback.domain.service.proyect.FindProyectService;
import trycore.trycoredesafioback.domain.service.proyect.UpdateProyectService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProyectService {

    private final ProyectRepository proyectRepository;
    private final ProyectDtoMapper proyectDtoMapper;
    private CreateProyectService createProyect;
    private FindProyectService findProyect;
    private DeleteProyectService deleteProyect;
    private UpdateProyectService updateProyect;

    @PostConstruct
    public void init() {
        createProyect = new CreateProyectService(proyectRepository);
        updateProyect = new UpdateProyectService(proyectRepository);
        findProyect = new FindProyectService(proyectRepository);
        deleteProyect = new DeleteProyectService(proyectRepository);
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
