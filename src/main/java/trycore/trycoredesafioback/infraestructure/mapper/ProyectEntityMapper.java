package trycore.trycoredesafioback.infraestructure.mapper;

import org.mapstruct.Mapper;
import trycore.trycoredesafioback.domain.model.Proyect;
import trycore.trycoredesafioback.infraestructure.jpa.entity.ProyectEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProyectEntityMapper {
    Proyect toModel(ProyectEntity proyectEntity);

    ProyectEntity toEntity(Proyect proyect);

    List<Proyect> toModel(List<ProyectEntity> proyectEntities);
}
