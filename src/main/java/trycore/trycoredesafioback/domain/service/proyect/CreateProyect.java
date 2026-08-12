package trycore.trycoredesafioback.domain.service.proyect;

import lombok.RequiredArgsConstructor;
import trycore.trycoredesafioback.domain.exception.ApiException;
import trycore.trycoredesafioback.domain.exception.MessageError;
import trycore.trycoredesafioback.domain.model.Proyect;
import trycore.trycoredesafioback.domain.repository.ProyectRepository;

@RequiredArgsConstructor
public class CreateProyect {

    private final ProyectRepository proyectRepository;

    public Proyect save(Proyect proyect) throws ApiException {
        if(proyectRepository.isExist(proyect.getName())) {
            throw new ApiException(MessageError.PROYECT_ALREADY);
        }
        return proyectRepository.save(proyect);
    }

}
