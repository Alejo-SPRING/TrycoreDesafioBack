package trycore.trycoredesafioback.domain.repository;

import trycore.trycoredesafioback.domain.exception.ApiException;
import trycore.trycoredesafioback.domain.model.Activity;

import java.util.List;

public interface ActivityRepository {

    List<Activity> find();

    Activity save(Activity activity) throws ApiException;

    void delete(Long id);

    boolean isExist(String name);

    boolean isExist(Long id);

    List<Activity> findByProyect(Long proyectId);

}
