package trycore.trycoredesafioback.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import trycore.trycoredesafioback.application.dto.ActivityDTO;
import trycore.trycoredesafioback.application.dto.ActivityDataDTO;
import trycore.trycoredesafioback.domain.model.Activity;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CalculateIndicatorsDtoMapper.class})
public interface ActivityDtoMapper {
    ActivityDataDTO toDto(Activity activity);

    @Mapping(target = "id", ignore = true)
    Activity toModel(ActivityDTO activityDTO);

    List<ActivityDataDTO> toDto(List<Activity> activities);

}
