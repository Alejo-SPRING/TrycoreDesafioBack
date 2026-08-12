package trycore.trycoredesafioback.application.mapper;

import org.mapstruct.Mapper;
import trycore.trycoredesafioback.application.dto.CalculateIndicatorsDTO;
import trycore.trycoredesafioback.domain.model.CalculateIndicators;

@Mapper(componentModel = "spring")
public interface CalculateIndicatorsDtoMapper {

    CalculateIndicatorsDTO toDto(CalculateIndicators calculateIndicators);

    CalculateIndicators toModel(CalculateIndicatorsDTO calculateIndicatorsDTO);

}
