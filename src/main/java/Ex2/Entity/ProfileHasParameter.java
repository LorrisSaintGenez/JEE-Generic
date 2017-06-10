package Ex2.Entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Lorris on 10/06/2017.
 */

@Data
@Entity
public class ProfileHasParameter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @ManyToOne
    private Profile profile;

    @ManyToOne
    private Parameter parameter;
}
