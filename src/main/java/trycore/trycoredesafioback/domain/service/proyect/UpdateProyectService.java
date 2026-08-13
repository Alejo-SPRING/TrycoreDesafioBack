package trycore.trycoredesafioback.domain.service.proyect;

import lombok.RequiredArgsConstructor;
import trycore.trycoredesafioback.domain.exception.ApiException;
import trycore.trycoredesafioback.domain.exception.MessageError;
import trycore.trycoredesafioback.domain.model.Proyect;
import trycore.trycoredesafioback.domain.repository.ProyectRepository;

@RequiredArgsConstructor
public class UpdateProyectService {
    private final ProyectRepository proyectRepository;

    public Proyect save(Proyect proyect) throws ApiException {
        if (!proyectRepository.isExist(proyect.getId())) {
            throw new ApiException(MessageError.PROYECT_NOT_FOUND);
        }
        return proyectRepository.save(proyect);
    }
}
