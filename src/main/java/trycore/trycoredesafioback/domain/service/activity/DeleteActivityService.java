package trycore.trycoredesafioback.domain.service.activity;

import lombok.RequiredArgsConstructor;
import trycore.trycoredesafioback.domain.exception.ApiException;
import trycore.trycoredesafioback.domain.exception.MessageError;
import trycore.trycoredesafioback.domain.repository.ActivityRepository;

@RequiredArgsConstructor
public class DeleteActivityService {

    private final ActivityRepository activityRepository;

    public void delete(Long id) throws ApiException {
        if(!activityRepository.isExist(id)) {
            throw new ApiException(MessageError.ACTIVITY_NOT_FOUND);
        }
        activityRepository.delete(id);
    }

}
