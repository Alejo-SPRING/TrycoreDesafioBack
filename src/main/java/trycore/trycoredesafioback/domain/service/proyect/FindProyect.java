package trycore.trycoredesafioback.domain.service.proyect;

import lombok.RequiredArgsConstructor;
import trycore.trycoredesafioback.domain.model.Proyect;
import trycore.trycoredesafioback.domain.repository.ProyectRepository;

import java.util.List;

@RequiredArgsConstructor
public class FindProyect {

    private final ProyectRepository proyectRepository;

    public List<Proyect> find() {
        return proyectRepository.find();
    }

}
