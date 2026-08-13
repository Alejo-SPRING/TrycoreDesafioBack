package trycore.trycoredesafioback.infraestructure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import trycore.trycoredesafioback.domain.model.Proyect;
import trycore.trycoredesafioback.infraestructure.jpa.entity.ProyectEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProyectEntityMapper {
    @Mapping(target = "activities", ignore = true)
    @Mapping(target = "totalCalculateIndicators", ignore = true)
    Proyect toModel(ProyectEntity proyectEntity);

    ProyectEntity toEntity(Proyect proyect);

    List<Proyect> toModel(List<ProyectEntity> proyectEntities);
}
