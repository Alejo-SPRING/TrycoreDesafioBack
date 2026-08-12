package trycore.trycoredesafioback.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trycore.trycoredesafioback.application.dto.ProyectDTO;
import trycore.trycoredesafioback.application.dto.ProyectDataDTO;
import trycore.trycoredesafioback.application.service.ProyectService;
import trycore.trycoredesafioback.domain.exception.ApiException;
import trycore.trycoredesafioback.domain.model.Proyect;

import java.util.List;

@RestController
@RequestMapping("/proyect")
@RequiredArgsConstructor
public class ProyectController {

    private final ProyectService proyectService;

    @GetMapping("/find")
    public ResponseEntity<List<ProyectDataDTO>> findAll() {
        return new ResponseEntity<>(proyectService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ProyectDataDTO> create(@Valid @RequestBody ProyectDTO proyectDTO) throws ApiException {
        return new ResponseEntity<>(proyectService.create(proyectDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProyectDataDTO> update(@Valid @RequestBody ProyectDTO proyectDTO, @PathVariable Long id) throws ApiException {
        return new ResponseEntity<>(proyectService.update(id, proyectDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ApiException {
        proyectService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
