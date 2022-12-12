package ezequiel.projectcrud.controller;

import ezequiel.projectcrud.model.Curso;
import ezequiel.projectcrud.repository.CursoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/api/cursos")
@AllArgsConstructor //Tira a necessidade de colocar o @Autowired no Curso repository e outros que precisem de injeção
public class CursosController {

    private final CursoRepository repository;

    @GetMapping
    public List<Curso> listaCursos(){ return repository.findAll();}

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Curso create(@RequestBody Curso curso){
        return repository.save(curso);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> findById(@PathVariable Long id){
        return repository.findById(id).map(curso -> ResponseEntity.ok().body(curso)).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> update(@PathVariable long id, @RequestBody Curso curso){
        return repository.findById(id)
                .map(registroEncontrado -> {
                            registroEncontrado.setName(curso.getName());
                            registroEncontrado.setCategory(curso.getCategory());
                            Curso updated = repository.save(registroEncontrado);
                            return ResponseEntity.ok().body(updated);
                        })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        return repository.findById(id)
                .map(registroEncontrado -> {
                    repository.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
