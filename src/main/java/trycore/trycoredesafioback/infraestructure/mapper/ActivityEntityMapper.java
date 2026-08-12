package trycore.trycoredesafioback.infraestructure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import trycore.trycoredesafioback.domain.model.Activity;
import trycore.trycoredesafioback.infraestructure.jpa.entity.ActivityEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ActivityEntityMapper {
    @Mapping(target = "proyectId", ignore = true)
    @Mapping(target = "calculateIndicators", ignore = true)
    Activity toModel(ActivityEntity activityEntity);

    @Mapping(target = "proyect", ignore = true)
    ActivityEntity toEntity(Activity activity);

    List<Activity> toModel(List<ActivityEntity> activityEntities);
}
