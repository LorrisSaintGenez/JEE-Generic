package Ex2.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Lorris on 10/06/2017.
 */

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(unique = true)
    private String login;

    @Column(unique = true)
    private String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Media> medias;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Sharing> sharings;

    @Override
    public String toString() {
        return "User :"
                + "\nLogin : " + login
                + "\nEmail : " + email;
    }

    public User(String login, String email) {
        this.login = login;
        this.email = email;
    }

    public User() {
    }
}
