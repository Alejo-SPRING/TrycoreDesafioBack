package trycore.trycoredesafioback.domain.service.proyect;

import lombok.RequiredArgsConstructor;
import trycore.trycoredesafioback.domain.model.Proyect;
import trycore.trycoredesafioback.domain.repository.ProyectRepository;
import trycore.trycoredesafioback.domain.service.CalculateActivityService;

import java.util.List;

@RequiredArgsConstructor
public class FindProyectService {

    private final ProyectRepository proyectRepository;
    private CalculateActivityService calculateActivityService = new CalculateActivityService();

    public List<Proyect> find() {
        List<Proyect> proyects = proyectRepository.find();
        proyects.forEach(proyect -> calculateActivityService.calculate(proyect));
        return proyects;
    }

}
