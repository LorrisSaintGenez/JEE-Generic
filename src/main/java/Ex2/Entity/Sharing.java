package Ex2.Entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Lorris on 10/06/2017.
 */

@Entity
@Data
public class Sharing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @ManyToOne
    private User user1;

    @ManyToOne
    private User user2;

    @ManyToOne
    private Media media;
}
