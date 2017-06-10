package Ex2.Entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Lorris on 10/06/2017.
 */

@Data
@Entity
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column
    private String path;

    @Column
    private String format;

    @ManyToOne
    private Media media;
}
