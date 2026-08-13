package trycore.trycoredesafioback.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import trycore.trycoredesafioback.application.dto.ProyectDTO;
import trycore.trycoredesafioback.application.dto.ProyectDataDTO;
import trycore.trycoredesafioback.domain.model.Proyect;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ActivityDtoMapper.class, CalculateIndicatorsDtoMapper.class})
public interface ProyectDtoMapper {
    ProyectDataDTO toDto(Proyect proyect);

    @Mapping(target = "id", ignore = true)
    Proyect toModel(ProyectDTO proyectDTO);

    List<ProyectDataDTO> toDto(List<Proyect> proyects);
}
