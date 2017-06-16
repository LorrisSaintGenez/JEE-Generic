package ex2.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Lorris on 10/06/2017.
 */

@Entity
@Data
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profile")
    private List<Media> medias;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profile")
    private List<ProfileHasParameter> profilehasparameters;

    public Profile(String name) {
        this.name = name;
    }

    public Profile() {
    }
}
