package ezequiel.projectcrud.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private long id;
    @Column(length = 200, nullable = false)
    private String name;
    @Column(length = 200, nullable = false)
    private String category;
}
