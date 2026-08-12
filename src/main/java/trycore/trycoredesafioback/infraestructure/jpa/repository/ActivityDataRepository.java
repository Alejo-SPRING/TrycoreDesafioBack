package trycore.trycoredesafioback.infraestructure.jpa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import trycore.trycoredesafioback.infraestructure.jpa.entity.ActivityEntity;

import java.util.List;

@Repository
public interface ActivityDataRepository extends CrudRepository<ActivityEntity, Long> {
    @Query("SELECT COUNT(a) > 0 FROM ActivityEntity a WHERE a.name = :name")
    boolean isExist(@Param("name") String name);

    @Query("SELECT a FROM ActivityEntity a INNER JOIN a.proyect p WHERE p.id = :proyectId")
    List<ActivityEntity> findByProyectId(@Param("proyectId") Long proyectId);
}
