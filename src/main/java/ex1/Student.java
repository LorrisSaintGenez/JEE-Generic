package Ex1;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lorris on 10/06/2017.
 */

@Data
@Entity
@XmlRootElement
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Getter
    @Setter
    @Column(unique = true)
    @XmlElement
    private String login;

    @Getter
    @Setter
    @Column
    @XmlElement
    private String password;

    @Getter
    @Setter
    @Column
    @XmlElement
    private String firstName;

    @Getter
    @Setter
    @Column
    @XmlElement
    private String lastName;

    @Getter
    @Setter
    @Column
    @XmlElement
    private String email;

    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    @XmlTransient
    private List<Project> project = new ArrayList<>();

    public Student(String login, String password, String firstName, String lastName, String email) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Student() {
    }

    @Override
    public String toString() {
        return "ex1.Student :"
                + "\nLogin : " + login
                + "\nFullName : " + firstName + " " + lastName
                + "\nEmail : " + email;
    }
}
