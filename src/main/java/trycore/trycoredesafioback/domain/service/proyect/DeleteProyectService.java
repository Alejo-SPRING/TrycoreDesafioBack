package trycore.trycoredesafioback.domain.service.proyect;

import lombok.RequiredArgsConstructor;
import trycore.trycoredesafioback.domain.exception.ApiException;
import trycore.trycoredesafioback.domain.exception.MessageError;
import trycore.trycoredesafioback.domain.repository.ProyectRepository;

@RequiredArgsConstructor
public class DeleteProyectService {

    private final ProyectRepository proyectRepository;

    public void remove(Long id) throws ApiException {
        if(!proyectRepository.isExist(id)) {
            throw new ApiException(MessageError.PROYECT_NOT_FOUND);
        }
        proyectRepository.delete(id);
    }

}
