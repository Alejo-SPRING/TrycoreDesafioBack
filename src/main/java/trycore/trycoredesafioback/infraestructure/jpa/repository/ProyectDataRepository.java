package trycore.trycoredesafioback.infraestructure.jpa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import trycore.trycoredesafioback.infraestructure.jpa.entity.ProyectEntity;

@Repository
public interface ProyectDataRepository extends CrudRepository<ProyectEntity, Long> {
    @Query("SELECT COUNT(p) > 0 FROM ProyectEntity p WHERE p.name = :name")
    boolean isExist(@Param("name") String name);
}
