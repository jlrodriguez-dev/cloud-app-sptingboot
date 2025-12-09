package co.edu.poli.cloudapp.restcontrollers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.poli.cloudapp.dto.EstudianteDTO;
import co.edu.poli.cloudapp.services.IEstudianteService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/estudiantes")
public class EstudiantesRestController {
    @Autowired
    private IEstudianteService estudianteService;

    @GetMapping
    public ResponseEntity<List<EstudianteDTO>> getAll(){
        return ResponseEntity.ok(estudianteService.findAll());
    }

    @PostMapping
    public ResponseEntity<EstudianteDTO> create(@RequestBody EstudianteDTO dto) {
        EstudianteDTO created = estudianteService.create(dto);        
        return ResponseEntity.created(
            URI.create("/api/estudiantes/" + created.getIdEstudiante())).
                body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstudianteDTO> getById(@PathVariable 
                                                Long id){
        return ResponseEntity.ok(estudianteService.findById(id));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<EstudianteDTO> update(
        @PathVariable Long id, @RequestBody EstudianteDTO dto
    ){
        return ResponseEntity.ok(estudianteService.update(id, dto));
    }

}
