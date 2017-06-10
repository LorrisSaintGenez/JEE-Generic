package Ex2.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Lorris on 10/06/2017.
 */

@Entity
@Data
public class Parameter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column
    private String name;

    @Column
    private String type;

    @Column
    private String defaultValue;

    @Column
    private String regularExpression;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parameter")
    private List<ProfileHasParameter> profilehasparameters;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parameter")
    private List<Value> values;

    public Parameter(String name, String type, String defaultValue, String regularExpression) {
        this.name = name;
        this.type = type;
        this.defaultValue = defaultValue;
        this.regularExpression = regularExpression;
    }

    public Parameter() {
    }
}
