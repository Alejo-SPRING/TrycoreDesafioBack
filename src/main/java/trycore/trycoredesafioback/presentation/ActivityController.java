package trycore.trycoredesafioback.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trycore.trycoredesafioback.application.dto.ActivityDTO;
import trycore.trycoredesafioback.application.dto.ActivityDataDTO;
import trycore.trycoredesafioback.application.service.ActivityService;
import trycore.trycoredesafioback.domain.exception.ApiException;

import java.util.List;

@RestController
@RequestMapping("/activity")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    @GetMapping("/find")
    public ResponseEntity<List<ActivityDataDTO>> findAll() {
        return new ResponseEntity<>(activityService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/find-by-proyect/{proyectId}")
    public ResponseEntity<List<ActivityDataDTO>> findAll(@PathVariable Long proyectId) throws ApiException {
        return new ResponseEntity<>(activityService.getByProyectId(proyectId), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ActivityDataDTO> create(@Valid @RequestBody ActivityDTO activityDTO) throws ApiException {
        return new ResponseEntity<>(activityService.create(activityDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ActivityDataDTO> create(@Valid @RequestBody ActivityDTO activityDTO, @PathVariable Long id) throws ApiException {
        return new ResponseEntity<>(activityService.update(id, activityDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ApiException {
        activityService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
