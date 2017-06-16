package ex2.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Lorris on 10/06/2017.
 */

@Entity
@Data
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column
    private String name;

    @ManyToOne
    private Profile profile;

    @ManyToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "media")
    private List<File> files;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "media")
    private List<Sharing> sharings;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "media")
    private List<Value> values;

    public Media (String name) {
        this.name = name;
    }

    public Media() {
    }
}
