package ex2.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Lorris on 10/06/2017.
 */

@Entity
@Data
public class Value {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @ManyToOne
    private Media media;

    @ManyToOne
    private Parameter parameter;

    @Column
    private String value;

}
