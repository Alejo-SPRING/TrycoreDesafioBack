package trycore.trycoredesafioback.domain.service.activity;

import lombok.RequiredArgsConstructor;
import trycore.trycoredesafioback.domain.exception.ApiException;
import trycore.trycoredesafioback.domain.exception.MessageError;
import trycore.trycoredesafioback.domain.model.Activity;
import trycore.trycoredesafioback.domain.repository.ActivityRepository;

@RequiredArgsConstructor
public class CreateActivityService {

    private final ActivityRepository activityRepository;

    public Activity save(Activity activity) throws ApiException {
        if(activityRepository.isExist(activity.getName())) {
            throw new ApiException(MessageError.ACTIVITY_ALREADY);
        }
        return activityRepository.save(activity);
    }

}
