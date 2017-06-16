package Ex1;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Lorris on 10/06/2017.
 */

@Data
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Getter
    @Setter
    @Column
    private String name;

    @Getter
    @Setter
    @ManyToOne
    @JsonIgnore
    private Student student;

    public Project(String name, Student s) {
        this.student = s;
        this.name = name;
    }

    public Project() {
    }
}
