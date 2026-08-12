package trycore.trycoredesafioback.domain.repository;

import trycore.trycoredesafioback.domain.model.Proyect;

import java.util.List;

public interface ProyectRepository {
    List<Proyect> find();

    Proyect save(Proyect proyect);

    void delete(Long id);

    boolean isExist(String name);

    boolean isExist(Long id);
}
